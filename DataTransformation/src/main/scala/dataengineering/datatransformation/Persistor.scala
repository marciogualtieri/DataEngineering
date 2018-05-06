package dataengineering.datatransformation

import java.util.UUID.randomUUID

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put, Table}
import org.apache.hadoop.hbase.util.Bytes

abstract class Persistor {
  def persist(record: Record): Unit
}

case class HBasePersistor(zookeeper: String, table: String) extends Persistor {
  val EntityDataColumnFamily: String = "Entity_Data"

  val hbaseConfig: Configuration = HBaseConfiguration.create()
  hbaseConfig.set("hbase.zookeeper.quorum", zookeeper)
  val hbaseConnection: Connection = ConnectionFactory.createConnection(hbaseConfig)
  val hbaseTable: Table = hbaseConnection.getTable(TableName.valueOf(Bytes.toBytes(table)))

  override def persist(record: Record): Unit = {
    val put = new Put(Bytes.toBytes(randomUUID.toString))
    put.addColumn(Bytes.toBytes("Raw_Data"), Bytes.toBytes("Message"), Bytes.toBytes(record.rawData))
    put.addColumn(Bytes.toBytes(EntityDataColumnFamily), Bytes.toBytes("deviceId"), Bytes.toBytes(record.entityData.deviceId))
    put.addColumn(Bytes.toBytes(EntityDataColumnFamily), Bytes.toBytes("temperature"), Bytes.toBytes(record.entityData.temperature))
    put.addColumn(Bytes.toBytes(EntityDataColumnFamily), Bytes.toBytes("latitude"), Bytes.toBytes(record.entityData.latitude))
    put.addColumn(Bytes.toBytes(EntityDataColumnFamily), Bytes.toBytes("longitude"), Bytes.toBytes(record.entityData.longitude))
    put.addColumn(Bytes.toBytes(EntityDataColumnFamily), Bytes.toBytes("time"), Bytes.toBytes(record.entityData.time))
    hbaseTable.put(put)
  }
}
