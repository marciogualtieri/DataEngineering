package dataengineering.dataingestion

import java.util.UUID.nameUUIDFromBytes
import com.typesafe.config.{Config, ConfigFactory}
import dataengineering.utils.MillisecondsInASecond

case class Location(latitude: String, longitude: String)
case class Data(deviceId: String, temperature: Int, location: Location, time: String)

trait MessageParameters {

  private val conf: Config = ConfigFactory.load()

  val MinTemperatureCelsius: Int = conf.getInt("min.temperature.celsius")
  val MaxTemperatureCelsius: Int = conf.getInt("max.temperature.celsius")

  val MinTemperatureChange: Int = conf.getInt("min.temperature.change.celsius")
  val MaxTemperatureChange: Int = conf.getInt("max.temperature.change.celsius")

  val MaxSteps: Int = conf.getInt("min.location.change.steps")
  val MinSteps: Int = conf.getInt("max.location.change.steps")

  val CentreLongitudeInDegrees: Double = conf.getDouble("centre.longitude.degrees")
  val CentreLatitudeInDegrees: Double = conf.getDouble(" centre.latitude.degrees")

  val FeetInAStep: Double = 2.5
  val OneDegreeOfLongitudeInFeet: Int = 288200
  val OneDegreeOfLatitudeInFeet: Int = 364000

  val OneStepInLongitudeDegrees: Double = FeetInAStep / OneDegreeOfLongitudeInFeet
  val OneStepInLatitudeDegrees: Double = FeetInAStep / OneDegreeOfLatitudeInFeet

  val RandomSeed: Long = 123L

  val UuidStringSize: Int = 100
}

case class Message(data: Data)

object Message extends MessageParameters {

  def createRandomMessage(): Message = {
    Message(data = Data(deviceId = randomUuid,
      temperature = randomInt(MinTemperatureCelsius, MaxTemperatureCelsius),
      Location(latitude = walkOneLatitudeStep(CentreLatitudeInDegrees).toString,
        longitude = walkOneLongitudeStep(CentreLongitudeInDegrees).toString),
      time = currentTimestamp.toString)
    )
  }

  def randomModifyMessage(message: Message): Message = {
    Message(data = Data(deviceId = message.data.deviceId,
      temperature = message.data.temperature + randomInt(MinTemperatureChange, MaxTemperatureChange),
      Location(latitude = walkOneLatitudeStep(message.data.location.latitude.toDouble).toString,
        longitude = walkOneLongitudeStep(message.data.location.longitude.toDouble).toString),
      time = currentTimestamp.toString)
    )
  }

  val currentTimestamp: Long = System.currentTimeMillis / MillisecondsInASecond

  private val randomGenerator = new scala.util.Random(RandomSeed)

  private def randomInt(from: Int, to: Int): Int =
    from + randomGenerator.nextInt((to - from) + 1)

  private def randomUuid: String =
    nameUUIDFromBytes(randomGenerator.nextString(UuidStringSize).getBytes).toString

  private def walkOneLatitudeStep(latitude: Double): Double =
    latitude + (randomInt(MinSteps, MaxSteps) * OneStepInLatitudeDegrees)

  private def walkOneLongitudeStep(longitude: Double): Double =
    longitude + (randomInt(MinSteps, MaxSteps) * OneStepInLongitudeDegrees)

}