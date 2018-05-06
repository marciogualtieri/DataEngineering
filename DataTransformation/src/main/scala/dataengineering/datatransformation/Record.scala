package dataengineering.datatransformation

case class EntityData(deviceId: String, temperature: Int, latitude: Double, longitude: Double, time: String)
case class Record(entityData: EntityData, rawData: String)
