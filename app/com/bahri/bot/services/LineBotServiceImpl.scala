package com.bahri.bot.services
import com.bahri.bot.responses.Event
import com.typesafe.config.ConfigFactory

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/15/17.
  */
class LineBotServiceImpl extends LineBotService{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")

    override def replyChat(chat: Event): Future[Boolean] = {

        Future.apply(true)
    }
}
