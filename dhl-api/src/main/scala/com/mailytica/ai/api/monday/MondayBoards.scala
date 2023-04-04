package com.mailytica.ai.api.monday
import spray.json._
import DefaultJsonProtocol._

case class MondayBoards(
                       seq: Seq[Board]
                       ) {

}

object MondayBoards{

  def parseJson(jsonString: String): Option[MondayBoards] = {

  // Return None if the JSON string is empty
  if (jsonString.trim.isEmpty) return None

    val json = jsonString.parseJson
    val boards = json.asJsObject.fields("data").asJsObject.fields("boards").convertTo[Seq[JsObject]]
    val boardSeq = boards.map(board => Board(
      BoardName(board.fields("name").convertTo[String]),
      BoardId(board.fields("id").convertTo[String])
    ))

  Some(MondayBoards(boardSeq))
  }
}