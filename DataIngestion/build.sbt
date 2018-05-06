name := "DataIngestion"

version := "1.0"

scalaVersion := "2.12.6"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.3"
libraryDependencies += "org.apache.kafka" %% "kafka" % "1.1.0" exclude("org.slf4j", "slf4j-api")
libraryDependencies += "org.apache.kafka" % "kafka-log4j-appender" % "1.1.0"
libraryDependencies += "net.manub" %% "scalatest-embedded-kafka" % "1.1.0" % "test"
libraryDependencies += "commons-daemon" % "commons-daemon" % "1.1.0"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"

logLevel in assembly := Level.Error
