package com.mailytica.ai.api.spinoco
import spray.json._

case class SpinocoMessage(
                         subject: Subject,
                         hashtags: Hashtags,
                         fromSourceEmail: FromSourceEmail,
                         emailMessageBody: EmailMessageBody,
                         destinationToAccount: DestinationToAccount
                         )

object SpinocoMessage{

  def parseJson(jsonString : String): SpinocoMessage = {
    ???
  }
}


