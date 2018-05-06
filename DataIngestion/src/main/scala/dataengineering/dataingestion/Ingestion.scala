package dataengineering.dataingestion

import org.apache.commons.daemon.Daemon
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.apache.commons.daemon.DaemonContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import com.typesafe.config._

class Ingestion extends Daemon {

  val conf: Config = ConfigFactory.load()
  val Devices: Int = conf.getInt("number.devices")
  val Brokers: String = conf.getString("brokers")
  val PublisherId: String = conf.getString("publisher.id")
  val Topic: String = conf.getString("topic")

  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  val executorService: ExecutorService = Executors.newSingleThreadExecutor()

  override def init(context: DaemonContext): Unit = {}

  override def start(): Unit = {
    logger.info("Starting up Ingestion...")
    executorService.execute(() => {
      val publisher = KafkaPublisher(Brokers, PublisherId, Topic)
      var messages: List[Message] = List.fill(Devices)(Message.createRandomMessage())

      do {
           publishMessages(publisher, messages)
           messages = messages.map(Message.randomModifyMessage)
           TimeUnit.SECONDS.sleep(1)
      } while(true)
    })
  }

  override def stop(): Unit = {
    logger.info("Shutting down Ingestion...")
    executorService.shutdown()
  }

  override def destroy(): Unit = {}

  private def publishMessages(publisher: KafkaPublisher, messages: List[Message]) = {
    for {message <- messages} {
      publisher.publish(message)
    }
  }
}
