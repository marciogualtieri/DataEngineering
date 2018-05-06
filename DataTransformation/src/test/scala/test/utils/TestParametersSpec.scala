package test.utils

import dataengineering.datatransformation.{EntityData, Record}

trait TestParametersSpec {

  val TestMessage : String =
    """
      |{
      |  "data": {
      |    "deviceId": "11c1310e-c0c2-461b-a4eb-f6bf8da2d23c",
      |    "temperature": 12,
      |    "location": {
      |      "latitude": "52.14691120000001",
      |      "longitude": "11.658838699999933"
      |    },
      |    "time": "1509793231"
      |  }
      |}
    """.stripMargin.replaceAll("\\s", "")

  val ExpectedRecord = Record(entityData=EntityData(deviceId="11c1310e-c0c2-461b-a4eb-f6bf8da2d23c",
                              temperature=12,
                              latitude=52.14691120000001, longitude=11.658838699999933,
                              time="2017-11-04T11:00:31000"),
                              rawData=TestMessage)
}
