package com.mailytica.ai.api.dhl

import spray.json._

case class DhlStatus(
                      statusCode: StatusCode,
                      description: Description,
                      status: Status,
                      trackingCode: TrackingCode,
                      statusTimestamp: StatusTimestamp,
                      serviceUrl: ServiceUrl
                    ) {


}

// static methode and variables
object DhlStatus {

  def dhlHttpGetRequest(): Int = {
    0
  }

  def parseJson(jsonString: String): Option[DhlStatus] = {


    // spray
    val jsonValue: JsValue = jsonString.parseJson
    val JsArray(shipmentsJson) = jsonValue.asJsObject.fields("shipments")

    val shipmentOption: Option[JsValue] = shipmentsJson.headOption // Some(shipment), None


    shipmentOption.map{shipment =>

    val JsObject(statusObject) = shipment.asJsObject.fields("status")
    val JsString(trackingCode) = shipment.asJsObject.fields("id")
    val JsString(serviceUrl) = shipment.asJsObject.fields("serviceUrl")

    val JsString(statusTimestamp) = statusObject("timestamp")
    val JsString(statusCode) = statusObject("statusCode")
    val JsString(status) = statusObject("status")
    val JsString(description) = statusObject("description")

    // create dhl status from the attributes from the json string
    DhlStatus(
      StatusCode(statusCode),
      Description(description),
      Status(status),
      TrackingCode(trackingCode),
      StatusTimestamp(java.time.LocalDateTime.parse(statusTimestamp)),
      ServiceUrl(serviceUrl)
    )
  }
}
}
