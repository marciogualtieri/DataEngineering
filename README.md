# DataEngineering

This project is comprised of the following sub-projects:

* [Data Ingestion](./DataIngestion): IoT Simulator that generates JSON data and publishes it to a Kafka topic.
* [Data Transformation](./DataTransformation): Spark Streaming job that consumes data from a Kafka topic and persists it to HBase.
* [Data Analysis](./DataAnalysis): Database scripts that build an Impala table on top of a HBase table and perform a few queries.

I completed this challenge as part of the interviewing process for a company named [Ultra Tendency](http://www.ultratendency.com/). 

***Beware of this people:*** *They advertise roles as being remote just to attract applicants. They don't have the intention of hiring anyone remotely. Right after you complete the coding test, they will back down and try to coerce you to relocate to Germany and work on-site.*
