package com.mailytica.ai.api.dhl

import spray.json.JsString

case class DhlStatus(
                    statusCode: StatusCode,
                    description: Description,
                    status: Status
                    ) {

  def doingSomething(value: Int): String = {

    "produced a string"
  }
}

// static methode and variables
object DhlStatus {

  def parseJson(jsonString: String): DhlStatus = {

    // spray


    // create dhl status from the attributes from the json string

    DhlStatus(
      StatusCode(404),
      Description("Entity not found"),
      Status("Not Found")
    )
  }
}