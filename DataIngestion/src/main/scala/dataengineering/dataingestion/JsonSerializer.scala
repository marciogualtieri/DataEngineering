package dataengineering.dataingestion

import org.json4s.native.Serialization.write

trait JsonSerializer {
  import org.json4s._
  implicit val formats = DefaultFormats

  def messageToJson(message: Message): String = write(message)
}
