package com.mailytica.ai.api.dhl

object DhlTest extends App {

  println("start app")

  val jsonString: String =
    """
      |{
      |  "shipments": [
      |    {
      |      "serviceUrl": "https://www.dhl.de/de/privatkunden.html?piececode=470800204932&cid=c_dhl_de_352_20205002_151_M040",
      |      "id": "470800204932",
      |      "service": "parcel-de",
      |      "origin": {
      |        "address": {
      |          "countryCode": "DE"
      |        }
      |      },
      |      "destination": {
      |        "address": {
      |          "countryCode": "DE"
      |        }
      |      },
      |      "status": {
      |        "timestamp": "2023-03-13T09:50:00",
      |        "location": {
      |          "address": {
      |            "addressLocality": "Deutschland"
      |          }
      |        },
      |        "statusCode": "delivered",
      |        "status": "Die Sendung wurde zugestellt.",
      |        "description": "Die Sendung wurde zugestellt."
      |      },
      |      "details": {
      |        "product": {
      |          "productName": "DHL PAKET, Bahntransport"
      |        },
      |        "proofOfDeliverySignedAvailable": false,
      |        "totalNumberOfPieces": 1,
      |        "pieceIds": [
      |          "47080020493"
      |        ],
      |        "weight": {
      |          "value": 10,
      |          "unitText": "kg"
      |        },
      |        "dimensions": {
      |          "width": {
      |            "value": 0.6,
      |            "unitText": "m"
      |          },
      |          "height": {
      |            "value": 0.6,
      |            "unitText": "m"
      |          },
      |          "length": {
      |            "value": 1.2,
      |            "unitText": "m"
      |          }
      |        }
      |      },
      |      "events": [
      |        {
      |          "timestamp": "2023-03-13T09:50:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "delivered",
      |          "status": "Die Sendung wurde zugestellt.",
      |          "description": "Die Sendung wurde zugestellt."
      |        },
      |        {
      |          "timestamp": "2023-03-13T08:36:00",
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde in das Zustellfahrzeug geladen. Die Zustellung erfolgt voraussichtlich heute.",
      |          "description": "Die Sendung wurde in das Zustellfahrzeug geladen. Die Zustellung erfolgt voraussichtlich heute."
      |        },
      |        {
      |          "timestamp": "2023-03-11T22:20:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde zurückgestellt. Die Zustellung erfolgt vrs. am nächsten Werktag.",
      |          "description": "Die Sendung wurde zurückgestellt. Die Zustellung erfolgt vrs. am nächsten Werktag."
      |        },
      |        {
      |          "timestamp": "2023-03-10T23:56:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung ist in der Region des Empfängers angekommen und wird im nächsten Schritt zur Zustellbasis transportiert.",
      |          "description": "Die Sendung ist in der Region des Empfängers angekommen und wird im nächsten Schritt zur Zustellbasis transportiert."
      |        },
      |        {
      |          "timestamp": "2023-03-09T19:16:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde von DHL bearbeitet und wird für den Weitertransport in die Region des Empfängers vorbereitet.",
      |          "description": "Die Sendung wurde von DHL bearbeitet und wird für den Weitertransport in die Region des Empfängers vorbereitet."
      |        },
      |        {
      |          "timestamp": "2023-03-09T18:37:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde für den Weitertransport vorbereitet.",
      |          "description": "Die Sendung wurde für den Weitertransport vorbereitet."
      |        },
      |        {
      |          "timestamp": "2023-03-08T14:43:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde für den Weitertransport vorbereitet.",
      |          "description": "Die Sendung wurde für den Weitertransport vorbereitet."
      |        },
      |        {
      |          "timestamp": "2023-03-02T14:13:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde für den Weitertransport vorbereitet.",
      |          "description": "Die Sendung wurde für den Weitertransport vorbereitet."
      |        },
      |        {
      |          "timestamp": "2023-03-01T16:18:00",
      |          "statusCode": "pre-transit",
      |          "status": "Die Sendung wurde elektronisch angekündigt. Sobald die Sendung von uns bearbeitet wurde, erhalten Sie weitere Informationen.",
      |          "description": "Die Sendung wurde elektronisch angekündigt. Sobald die Sendung von uns bearbeitet wurde, erhalten Sie weitere Informationen."
      |        },
      |        {
      |          "timestamp": "2023-03-01T16:14:00",
      |          "location": {
      |            "address": {
      |              "addressLocality": "Deutschland"
      |            }
      |          },
      |          "statusCode": "transit",
      |          "status": "Die Sendung wurde vom Absender in der Filiale eingeliefert.",
      |          "description": "Die Sendung wurde vom Absender in der Filiale eingeliefert."
      |        }
      |      ]
      |    }
      |  ],
      |  "possibleAdditionalShipmentsUrl": [
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=freight",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=dgf",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-nl",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-pl",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=express",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=post-de",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=sameday",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=parcel-uk",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce-apac",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=ecommerce-europe",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=svb",
      |    "/track/shipments?trackingNumber=470800204932&language=DE&service=post-international"
      |  ]
      |}
      |""".stripMargin


  val DhlStatus(code, description, status) = DhlStatus.parseJson(this.jsonString)


 // this.dhlStatus.doingSomething(2)


  // this.dhlStatus.doingSomething(2)

}