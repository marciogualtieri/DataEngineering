package dataengineering.dataingestion

import net.manub.embeddedkafka.EmbeddedKafka
import org.apache.kafka.common.serialization.StringDeserializer
import org.scalatest.{FlatSpec, Matchers}
import test.utils.TestParametersSpec

class PublisherSpec extends FlatSpec with Matchers with EmbeddedKafka with TestParametersSpec {

  implicit val deserializer = new StringDeserializer

  "Kafka Publisher" should "send a message to a topic" in {

    withRunningKafka {
      val publisher = KafkaPublisher("localhost:6001", "IoT_Simulator", "IoT_Devices")
      publisher.publish(TestMessage)
      consumeFirstMessageFrom("IoT_Devices") shouldBe ExpectedMessage
    }

  }
}
