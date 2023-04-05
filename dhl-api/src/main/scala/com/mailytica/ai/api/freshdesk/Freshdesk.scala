package com.mailytica.ai.api.freshdesk

case class Freshdesk(
                    bodyText: BodyText,
                    id: Id,
                    supportEmail: SupportEmail,
                    toEmails: ToEmails
                    ){

}

object Freshdesk{

  def parseJson(jsonString : String): Freshdesk = {
    ???
  }

}