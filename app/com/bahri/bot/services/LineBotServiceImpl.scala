package com.bahri.bot.services
import javax.inject.Inject

import com.bahri.bot.infra.DBConnection
import com.bahri.bot.responses.{Event, PushText, ReplyPayload}
import com.typesafe.config.ConfigFactory
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import slick.driver.MySQLDriver.api._
import tables.Tables.TableMemories
import com.bahri.bot.responses.LineBotResponsesFormatters._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/15/17.
  */
class LineBotServiceImpl @Inject() (ws: WSClient) extends LineBotService{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")
    val replyUrl = conf.getString("line.replyURL")

    override def replyChat(chats: Seq[Event]): Future[Boolean] = {
        val chat = chats(0)
        DBConnection.db.run(LineBotServiceImpl.memoryTable.map(mt => (mt.userId, mt.replyToken, mt.typeEvent, mt.typeSource, mt.typeMessage, mt.text,
        mt.stickerId, mt.packageId, mt.messageId, mt.title, mt.address, mt.latitude, mt.longitude)) +=
            (chat.source.userId, chat.replyToken, chat.`type`, chat.source.`type`, chat.message.`type`, chat.message.text,
            chat.message.stickerId, chat.message.packageId, chat.message.id, chat.message.title, chat.message.address, chat.message.latitude, chat.message.longitude))
        val msg = PushText("text", s"Hello ${chat.message.text.getOrElse(chat.message.id)}")
        val replyPayload = ReplyPayload(chat.replyToken,Seq[PushText](msg))
        ws.url(replyUrl).withHeaders("Content-Type" -> "application/json","Authorization" -> s"Bearer $lChannelAccessToken").post(Json.toJson(replyPayload)).map { response =>
            Logger.info(s"response status: ${response.status}, body: ${response.body}")
        }
        Future.apply(true)
    }
}

object LineBotServiceImpl{
    val memoryTable= TableQuery[TableMemories]
}

