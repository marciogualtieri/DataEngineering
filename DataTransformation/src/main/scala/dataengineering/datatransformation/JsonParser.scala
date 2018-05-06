package dataengineering.datatransformation

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.json4s.native.JsonMethods.parse
import org.joda.time.{DateTime, DateTimeZone}

trait JsonParser {
  val OneSecondInMilliseconds: Long = 1000L
  val StringDateFormat: String = "yyyy-MM-dd'T'HH:mm:ssSSS"

  import org.json4s._
  implicit val formats = DefaultFormats

  def jsonToRecord(jsonString: String): Record = {
    val json = parse(jsonString)

    val records: List[Record] = for { JObject(message) <- json
          JField("data", JObject(data)) <- message
          JField("deviceId", JString(deviceId)) <- data
          JField("temperature", JInt(temperature)) <- data
          JField("location", JObject(location)) <- data
          JField("latitude", JString(latitude)) <- location
          JField("longitude", JString(longitude)) <- location
          JField("time", JString(time)) <- data
    } yield Record(EntityData(deviceId,
                              temperature.toInt,
                              latitude.toDouble,
                              longitude.toDouble,
                              timestampToStringDate(time.toLong)),
                   jsonString)
    records.head
  }

  private def timestampToStringDate(timestamp: Long): String = {
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormat.forPattern(StringDateFormat).withZone(DateTimeZone.UTC)
    new DateTime(timestamp * OneSecondInMilliseconds).toString(dateTimeFormatter)
  }
}
