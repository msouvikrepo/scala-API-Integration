package com.mailytica.ai.api.dhl

object DhlTest extends App {

  println("start app")

  val jsonString: String =
    """
      |
      |""".stripMargin

  val dhlStatus: DhlStatus = DhlStatus.parseJson(this.jsonString)

  this.dhlStatus.doingSomething(2)

}
