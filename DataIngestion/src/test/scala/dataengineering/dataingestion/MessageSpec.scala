package dataengineering.dataingestion

import org.scalatest.{FlatSpec, Matchers}
import test.utils.TestParametersSpec

class MessageSpec extends FlatSpec with Matchers with TestParametersSpec {

  "Random message generator" should "create random message" in {
    val randomMessage = Message.createRandomMessage()
    randomMessage.data.deviceId shouldBe NewMessage.data.deviceId
    randomMessage.data.temperature shouldBe NewMessage.data.temperature
    randomMessage.data.location shouldBe NewMessage.data.location
  }

  "Message random modifier" should "modify message at random" in {
    val randomMessage = Message.randomModifyMessage(NewMessage)
    randomMessage.data.deviceId shouldBe ModifiedMessage.data.deviceId
    randomMessage.data.temperature shouldBe ModifiedMessage.data.temperature
    randomMessage.data.location shouldBe ModifiedMessage.data.location

  }
}
