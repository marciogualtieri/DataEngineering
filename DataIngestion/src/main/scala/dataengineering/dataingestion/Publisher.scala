package dataengineering.dataingestion

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import dataengineering.utils.currentTimestamp

abstract class Publisher {
  def publish(message: Message): Unit
}

case class KafkaPublisher(brokers: String, publisherId: String, topic: String) extends Publisher with JsonSerializer {

  val props = new Properties()
  props.put("bootstrap.servers", brokers)
  props.put("client.id", publisherId)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  override def publish(message: Message): Unit = {
    val producer = new KafkaProducer[String, String](props)
    try {
      producer.send(new ProducerRecord[String, String](topic, currentTimestamp.toString, messageToJson(message)))
    } finally {
      producer.close()
    }
  }
}