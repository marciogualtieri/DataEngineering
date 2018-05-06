package test.utils

import dataengineering.dataingestion.{Data, Location, Message}

trait TestParametersSpec {

  val TestMessage = Message(data=Data(deviceId="11c1310e-c0c2-461b-a4eb-f6bf8da2d23c",
    temperature=12,
    Location(latitude="52.14691120000001", longitude="11.658838699999933"),
    time="1509793231"))

  val NewMessage = Message(data=Data(deviceId="3df20194-ddd4-3082-95b2-20b6e6ed1c4e",
    temperature=14,
    Location(latitude="52.31667386813187", longitude="11.616658325468425"),
    time="1525262539"))

  val ModifiedMessage = Message(data=Data(deviceId="3df20194-ddd4-3082-95b2-20b6e6ed1c4e",
    temperature=15,
    Location(latitude="52.31667386813187", longitude="11.616684349063151"),
    time="1525262539"))

  val ExpectedMessage : String =
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
}
