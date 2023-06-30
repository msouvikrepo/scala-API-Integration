package com.mailytica.ai.api.monday

object MondayTest extends App{

  println("start App")
  val jsonString: String =
    """
      |{
      |  "data": {
      |    "boards": [
      |      {
      |        "name": "Advanced Tasks for the Week",
      |        "id": "101313546"
      |      },
      |      {
      |        "name": "New Product Launch",
      |        "id": "101339646"
      |      },
      |      {
      |      "name": "Getting User Feedback with Jotform",
      |      "id": "108249154"
      |    },
      |    {
      |      "name": "Grant and Sponsorship Proposals",
      |      "id": "108267572"
      |    },
      |    {
      |      "name": "Updates",
      |      "id": "108295854"
      |    }
      |  ]
      |},
      |"account_id": 1825528
      |}
      |""".stripMargin

  private val mondayBoardsOption = MondayBoards.parseJson(this.jsonString).getOrElse("Not a valid json")
  println(mondayBoardsOption)
}
