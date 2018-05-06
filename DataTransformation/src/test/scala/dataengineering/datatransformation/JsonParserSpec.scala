package dataengineering.datatransformation

import org.scalatest.{FlatSpec, Matchers}
import test.utils.TestParametersSpec

class JsonSerializerSpec extends FlatSpec with Matchers with JsonParser with TestParametersSpec {

  "JSON Parser" should "parse JSON String into record" in {
    jsonToRecord(TestMessage) shouldBe ExpectedRecord
  }
}
