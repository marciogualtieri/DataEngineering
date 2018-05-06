package dataengineering.dataingestion

import org.scalatest.{FlatSpec, Matchers}
import test.utils.TestParametersSpec

class JsonSerializerSpec extends FlatSpec with Matchers with JsonSerializer with TestParametersSpec {

  "JSON Serializer" should "return JSON String for data" in {
    messageToJson(TestMessage) shouldBe ExpectedMessage
  }
}