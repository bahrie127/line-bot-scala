package com.bahri.bot.infra

import java.sql.SQLTimeoutException

import com.bahri.bot.responses.EmptyDataResponse
import play.api.Logger
import play.api.libs.json.{JsResult, Json}
import play.api.mvc.{Result, Results}

import scala.concurrent.{ExecutionContext, Future}
import com.bahri.bot.responses.LineBotResponsesFormatters._

/**
  * Created by saifulbahri on 2/18/17.
  */
object LineUtils {

    def handleInvalidJsonFuture(json: JsResult[Future[Result]]): Future[Result] = {
        json.recoverTotal {
            case _ => Future.successful(Results.BadRequest(Json.toJson(EmptyDataResponse(status = 400, message = "Invalid request body!"))))
        }
    }

    def handleResponseException(response: Future[Result], additionalHeaders: Seq[(String, String)])(implicit ec: ExecutionContext): Future[Result] = {
        response.map(_.withHeaders(additionalHeaders: _*)).recover({
            case e: SQLTimeoutException => Results.InternalServerError(Json.toJson(EmptyDataResponse(status = 500, message = "Database timeout"))).withHeaders(additionalHeaders: _*)
            case e: Exception =>
                Logger.error("Action", e)
                Results.InternalServerError(Json.toJson(EmptyDataResponse(status = 500, message = "Something went wrong"))).withHeaders(additionalHeaders: _*)
        })
    }

}
