package dataengineering.datatransformation

import com.typesafe.config.{Config, ConfigFactory}

trait TransformationParameters {
  private val conf: Config = ConfigFactory.load()
  val Table: String = conf.getString("hbase.table")
  val HBaseZookeeper: String = conf.getString("hbase.zookeeper")
  val SparkJobName: String = conf.getString("spark.job.name")
  val KafkaZookeeper: String = conf.getString("kafka.zookeeper")
  val KafkaTopic: String = conf.getString("kafka.topic")
  val KafkaPartitions: Int = conf.getInt("kafka.partitions")
}
