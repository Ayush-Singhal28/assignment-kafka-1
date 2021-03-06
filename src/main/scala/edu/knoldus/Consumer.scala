package edu.knoldus

import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object Consumer extends App {

  import java.util.Properties

  val TOPIC="test"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "edu.knoldus.CustomDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, Student](props)

  consumer.subscribe(util.Collections.singletonList(TOPIC))

  while(true){
    val records=consumer.poll(50)
    for (record<-records.asScala){
      println(record.key() + " "+ record.value())
    }
  }
}