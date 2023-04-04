package com.mailytica.ai.api.monday
import spray.json._
import DefaultJsonProtocol._

case class MondayBoards(
                       seq: Seq[Board]
                       ) {

}

object MondayBoards{

  def parseJson(jsonString: String): Option[MondayBoards] = {

  val jsonValue: JsValue = jsonString.parseJson
  implicit val nameIdFormat = jsonFormat2(Board)

  val keyValueSeq = jsonValue match {
    case JsArray(elements) => elements.map(_.convertTo[Board])
    case _ => Seq.empty[Board]
  }
  println(keyValueSeq)
  }
}