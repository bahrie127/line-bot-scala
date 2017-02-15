package com.bahri.bot.controllers

import com.bahri.bot.responses.{EmptyDataResponse, LinePayload, SuccessBot}
import com.typesafe.config.ConfigFactory
import play.api.mvc._
import play.api.libs.json.{JsResult, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.bahri.bot.responses.LineBotResponsesFormatters._
import com.linecorp.bot.client.{LineMessagingServiceBuilder, LineSignatureValidator}
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.message.TextMessage
import play.api.Logger

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/12/17.
  */
class LineBotController extends Controller{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")

    def callback = Action.async(parse.json) { request =>
        Logger.info(s"Log Request all => ${request.body.toString()}")
        handleInvalidJsonFuture {
            request.body.validate[LinePayload] map {
                pl =>
                    getAuthLine map {auth =>
                        Logger.info(s"request => ${request.body.toString()}")
                                val aXLineSignature = request.headers.get("X-Line-Signature").getOrElse("")
                        Logger.info(s"signature => ${aXLineSignature}")
                                val valid = new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(request.body.toString().getBytes, aXLineSignature)
                        Logger.info(s"valid => ${valid}")
                        val textMessage = new TextMessage(s"scala ${pl.events(0).message.text}")
                        val replyMessage = new ReplyMessage(pl.events(0).replyToken, textMessage)
                        LineMessagingServiceBuilder
                            .create(lChannelAccessToken)
                            .build()
                            .replyMessage(replyMessage)
                            .execute();
                          Ok(Json.toJson(SuccessBot(auth)))
                    }

            }
        }
    }

    def getAuthLine: Future[Int]=Future.successful(200)

    def handleInvalidJsonFuture(json: JsResult[Future[Result]]): Future[Result] = {
        json.recoverTotal {
            case _ => Future.successful(Results.BadRequest(Json.toJson(EmptyDataResponse(status = 400, message = "Invalid request body!"))))
        }
    }

}
