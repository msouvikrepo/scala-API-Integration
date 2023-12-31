package com.mailytica.ai.api.spinoco

import spray.json.DefaultJsonProtocol.{StringJsonFormat, jsonFormat1, seqFormat}
import spray.json.{JsString, _}

case class SpinocoMessage(
                           subject: Subject,
                           hashtags: Hashtags,
                           fromSourceEmail: FromSourceEmail,
                           emailMessageBody: EmailMessageBody,
                           destinationToAccount: DestinationToAccount
                         )

object SpinocoMessage {

  def parseJson(jsonString: String): SpinocoMessage = {

    val spinocoMessageJsValue = jsonString.parseJson
    val JsString(subjectString) = spinocoMessageJsValue.asJsObject.fields("subject")
    val JsArray(hashTagsArray) = spinocoMessageJsValue.asJsObject.fields("hashTags")
    val JsString(fromSourceEmailString) = spinocoMessageJsValue.asJsObject.fields("from").asJsObject.fields("source").asJsObject.fields("email")
    val JsString(emailMessageBody) = spinocoMessageJsValue.asJsObject.fields("emailMessageBody").asJsObject.fields("body")
    val JsArray(destinationToAccountArray) = spinocoMessageJsValue.asJsObject.fields("destination").asJsObject.fields("To")
    val JsString(destinationToAccountEmail) = destinationToAccountArray.head.asJsObject.fields("source").asJsObject.fields("account")

    val hashtagsSeq: Seq[String] = hashTagsArray.map{case JsString(hashtag) => hashtag}

    /*implicit val hashtagFormat = jsonFormat1(Hashtags)
    val hashtagsSeq = hashTagsArray.convertTo(Hashtags)*/

    SpinocoMessage(subject = Subject(subjectString),
      hashtags = Hashtags(hashtagsSeq),
      fromSourceEmail = FromSourceEmail(Email(fromSourceEmailString)),
      emailMessageBody = EmailMessageBody(emailMessageBody),
      destinationToAccount = DestinationToAccount(Email(destinationToAccountEmail))
    )

  }
}


