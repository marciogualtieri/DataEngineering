package dataengineering.datatransformation

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils

object Transformation extends TransformationParameters {

  val persistor: Persistor = HBasePersistor(HBaseZookeeper, Table)
  val sparkConf: SparkConf = new SparkConf().setAppName(SparkJobName)
  val streamingContext = new StreamingContext(sparkConf, Seconds(10))

  def main(args: Array[String]): Unit = {
    new Transformation(persistor, streamingContext).transform()
  }

  def apply (persistor: Persistor, streamingContext: StreamingContext) = new Transformation(persistor, streamingContext)
}

class Transformation(persistor: Persistor, streamingContext: StreamingContext) extends TransformationParameters
  with JsonParser {

  def transform(): Unit = {
    val kafkaStream = KafkaUtils.createStream(streamingContext, KafkaZookeeper, SparkJobName,
                                              Map(KafkaTopic -> KafkaPartitions))
    val messages = kafkaStream.map { case (_, data) => data }
    messages.foreachRDD { rdd =>
      rdd.collect().foreach { message =>
        val record = jsonToRecord(message)
        persistor.persist(record)
      }
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
