package com.mailytica.ai.api.dhl
import spray.json._
import spray.json.DefaultJsonProtocol._
import spray.json

case class FreshdeskMail(
                        freshdeskBodyText: FreshdeskBodyText,
                        freshdeskId: FreshdeskId,
                        freshdeskSupportEmail: FreshdeskSupportEmail,
                        freshdeskToEmails: FreshdeskToEmails
                        ) {

}

object FreshdeskMail {

  def parseJson(jsonString: String): Option[FreshdeskMail] = {

    if (jsonString.trim.isEmpty) return None

    //extract the values body_text, id, support_email, to_emails
    val jsonValue: JsValue = jsonString.parseJson

    val JsString(freshdeskBodyText) = jsonValue.asJsObject.fields("body_text")
    val JsString(freshdeskId) = jsonValue.asJsObject.fields("id")
    val JsString(freshdeskSupportEmailString) = jsonValue.asJsObject.fields("support_email")
    val freshdeskSupportEmail = (FreshdeskSupportEmail)((FreshdeskEmail)freshdeskSupportEMailString))
    val JsArray(freshdeskToEmailsArray) = jsonValue.asJsObject.fields("to_emails")

    // create a Seq of FreshdeskEmail instances from the JsArray
    val freshdeskEmailSeq: Seq[FreshdeskEmail] = JsArray(freshdeskToEmailsArray).convertTo[Seq[String]].map(str => FreshdeskEmail(str))

    // create an instance of FreshdeskToEmails from the Seq of FreshdeskEmail instances and pray that the typecasting works
    val freshdeskToEmails = FreshdeskToEmails(freshdeskEmailSeq)

    Some(FreshdeskMail(
      FreshdeskBodyText(freshdeskBodyText),
      FreshdeskId(freshdeskId),
      FreshdeskSupportEmail(fres)
    ))



  }
}