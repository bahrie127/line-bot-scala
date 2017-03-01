package com.bahri.bot.services
import javax.inject.Inject

import com.bahri.bot.infra.{DBConnection, LineUtils}
import com.bahri.bot.responses._
import com.typesafe.config.ConfigFactory
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import slick.driver.MySQLDriver.api._
import tables.Tables.{TableAnswers, TableMemories, TableProducts}
import com.bahri.bot.responses.LineBotResponsesFormatters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/15/17.
  */
class LineBotServiceImpl @Inject()(ws: WSClient) extends LineBotService{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")
    val replyUrl = conf.getString("line.replyURL")

    override def replyChat(chats: Seq[Event]): Future[Boolean] = {
        val chat = chats(0)
        storingChat(chat)

        val action = for{
            countRow <-LineBotServiceImpl.answerTable.filter(_.userId===chat.source.userId.getOrElse("")).length.result
        }yield countRow

        DBConnection.db.run(action).map{
            case number =>
                number match {
                    case 7 => DBConnection.db.run(LineBotServiceImpl.answerTable.filter(_.userId===chat.source.userId.getOrElse("")).delete)
                    case _ => saveFormulir(chat, number)
                }
            number match {
                case 0 => lineReplyTempleteCarousel(chat.replyToken)
                case _ => lineReply(chat.replyToken, LineUtils.botQuestions(number+1))
            }

        }
        Future.apply(true)
    }

    def storingChat(chat: Event)= {
        DBConnection.db.run(LineBotServiceImpl.memoryTable.map(mt => (mt.userId, mt.replyToken, mt.typeEvent, mt.typeSource, mt.typeMessage, mt.text,
            mt.stickerId, mt.packageId, mt.messageId, mt.title, mt.address, mt.latitude, mt.longitude, mt.roomId, mt.groupId)) +=
            (chat.source.userId, chat.replyToken, chat.`type`, chat.source.`type`, chat.message.`type`, chat.message.text,
                chat.message.stickerId, chat.message.packageId, chat.message.id, chat.message.title, chat.message.address, chat.message.latitude, chat.message.longitude,
                chat.source.roomId, chat.source.groupId))
    }

    def lineReply(destination: String, msg: String) = {
        val message = PushText("text", s"$msg")
        val replyPayload = ReplyPayload(destination, Seq[PushText](message))
        ws.url(replyUrl).withHeaders("Content-Type" -> "application/json","Authorization" -> s"Bearer $lChannelAccessToken").post(Json.toJson(replyPayload)).map { response =>
            Logger.info(s"response token: $destination, status: ${response.status}, body: ${response.body}")
        }
    }

    def lineReplyTempleteCarousel(destination: String) = {
            var columnSeq = Seq[Column]()
        DBConnection.db.run(LineBotServiceImpl.productTable.result).map{
            data =>  data.map{
                product => columnSeq = columnSeq :+ Column(product.imgUrl1.getOrElse(""), product.codeProduct.getOrElse(""), product.nameProduct.getOrElse(""), Seq[Action](Action("postback","Beli",product.nameProduct.get)))
            }
                val templete = TempleteProduct("template", "Kaos Bapak Soleh", Carousel("carousel", columnSeq))
                val replyPayload = ReplyPayloadTemplate(destination, Seq[TempleteProduct](templete))
                Logger.info(s"reply payload template : ${replyPayload.toString}")
                ws.url(replyUrl).withHeaders("Content-Type" -> "application/json","Authorization" -> s"Bearer $lChannelAccessToken").post(Json.toJson(replyPayload)).map { response =>
                    Logger.info(s"response token: $destination, status: ${response.status}, body: ${response.body}")
                }
        }

//        val message = PushText("text", s"")
//        val replyPayload = ReplyPayload(destination, Seq[PushText](message))

    }

    def saveFormulir(chat: Event, no: Int) = {
        val action = LineBotServiceImpl.answerTable.map(at => (at.userId, at.answerNo, at.answerText)) returning LineBotServiceImpl.answerTable.map(_.id) +=
            (chat.source.userId.getOrElse(""), no, chat.message.text.getOrElse(""))
        DBConnection.db.run(action)
    }
}

object LineBotServiceImpl{
    val memoryTable= TableQuery[TableMemories]
    val answerTable = TableQuery[TableAnswers]
    val productTable = TableQuery[TableProducts]
}

