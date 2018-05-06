package dataengineering

package object utils {
  val MillisecondsInASecond: Int = 1000
  val currentTimestamp: Long = System.currentTimeMillis / MillisecondsInASecond
}