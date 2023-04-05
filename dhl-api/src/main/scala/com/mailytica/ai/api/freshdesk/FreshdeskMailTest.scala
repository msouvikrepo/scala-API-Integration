package com.mailytica.ai.api.freshdesk

object FreshdeskMailTest extends App {

  println("Start app")
  val jsonString: String ="""{
                            |  "body_text": "Hi Lea Artner, Antwort1 auf Testticket",
                            |  "id": 101002738565,
                            |  "incoming": false,
                            |  "private": false,
                            |  "user_id": 101002862811,
                            |  "support_email": "support@newaccount1636718700987.freshdesk.com",
                            |  "source": 0,
                            |  "category": 1,
                            |  "to_emails": [
                            |    "lea.artner@gmx.de"
                            |  ],
                            |  "from_email": "Mailytica <support@newaccount1636718700987.freshdesk.com>",
                            |  "cc_emails": [
                            |
                            |  ],
                            |  "bcc_emails": [
                            |
                            |  ],
                            |  "email_failure_count": null,
                            |  "outgoing_failures": null,
                            |  "thread_id": null,
                            |  "thread_message_id": null,
                            |  "created_at": "2021-11-19T12:20:19Z",
                            |  "updated_at": "2021-11-19T12:20:19Z",
                            |  "last_edited_at": null,
                            |  "last_edited_user_id": null,
                            |  "attachments": [
                            |
                            |  ],
                            |  "automation_id": null,
                            |  "automation_type_id": null,
                            |  "auto_response": false,
                            |  "ticket_id": 16,
                            |  "source_additional_info": null
                            |}""".stripMargin

  println(FreshdeskMail.parseJson(jsonString))
  print(FreshdeskMail.parseJson(""))

}
