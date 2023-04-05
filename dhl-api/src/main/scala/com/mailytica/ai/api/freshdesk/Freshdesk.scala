package com.mailytica.ai.api.freshdesk
import spray.json._

case class Freshdesk(
                    bodyText: BodyText,
                    id: Id,
                    supportEmail: SupportEmail,
                    toEmails: ToEmails
                    ){

}

object Freshdesk{

  def parseJson(jsonString : String): Freshdesk = {

    val jsonValue = jsonString.parseJson
    val JsString(bodyText) = jsonValue.asJsObject.fields("body_text")
    val JsString(id) = jsonValue.asJsObject.fields("id")
    val JsString(freshdeskSupportEmailString) = jsonValue.asJsObject.fields("support_email")
    val supportEmail = Email(freshdeskSupportEmailString)
    val JsArray(toEmailsArray) = jsonValue.asJsObject.fields("to_emails")
    val toEmailsSeq: Seq[Email] = toEmailsArray
      .map { case JsString(toEmail) => Email(toEmail) }
    Freshdesk(
      BodyText(bodyText),
      Id(id.toLong),
      SupportEmail(supportEmail),
      ToEmails(toEmailsSeq)
    )

  }

}