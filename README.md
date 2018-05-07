# DataEngineering

This project is comprised of the following sub-projects:

* [Data Ingestion](./DataIngestion): IoT Simulator that generates JSON data and publishes it to a Kafka topic.
* [Data Transformation](./DataTransformation): Spark Streaming job that consumes data from a Kafka topic and persists it to HBase.
* [Data Analysis](./DataAnalysis): Database scripts that build an Impala table on top of a HBase table and perform a few queries.
