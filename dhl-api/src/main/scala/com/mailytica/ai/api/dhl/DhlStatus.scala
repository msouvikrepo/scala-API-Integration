package com.mailytica.ai.api.dhl

import spray.json._


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

  def parseJson(jsonString: String): Option[DhlStatus] = {


    // spray
    val jsonValue: JsValue = jsonString.parseJson
    val JsArray(shipmentsJson) = jsonValue.asJsObject.fields("shipments")

    val shipmentOption: Option[JsValue] = shipmentsJson.headOption // Some(shipment), None

    val shipment = shipmentsJson.head
    // TODO learn read a bit about Scala option and .map function of options

    shipmentOption.map {shipment =>

    val JsObject(statusObject) = shipment.asJsObject.fields("status")

    val JsString(statusCode) = statusObject("statusCode")
    val JsString(status) = statusObject("status")
    val JsString(description) = statusObject("description")

    println(statusCode)
    println(status)
    println(description)

    // create dhl status from the attributes from the json string
    DhlStatus(
      StatusCode(statusCode),
      Description(description),
      Status(status)
    )
  }
}
}