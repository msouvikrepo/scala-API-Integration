/*
package com.mailytica.ai.api.monday

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser}

@RunWith(classOf[JUnitRunner])
class MondayBoardsSpec extends  FlatSpec with Matchers with OptionValues {
  it should "convert a json of a monday response into MondayBoards" in {

    val (jsonInput, expectedMondayBoards) = MondayBoardsSpec.defaultJsonFixture()

    val actualMondayBoards: MondayBoards = MondayBoards.parseJson(jsonInput.toString()).get

    expectedMondayBoards shouldEqual Some(actualMondayBoards)
  }

  it should "convert a json of an empty Monday response and return a none" in {

    val (jsonInput, _) = MondayBoardsSpec.defaultJsonFixture()

    val actualMondayBoards: Option[MondayBoards] = MondayBoards.parseJson(jsonInput.toString())

    actualMondayBoards shouldEqual None
  }
}

object MondayBoardsSpec {

  def defaultJsonFixture(
                        mondayBoards: Option[MondayBoards]
                        ): (JsValue, Option[MondayBoards]) = {


    val jsonString = JsonParser(
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
        |""".stripMargin)

    (jsonString, mondayBoards)
  }
}
*/
