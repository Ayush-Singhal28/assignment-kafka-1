package edu.knoldus

case class Student(id: Int, name: String)

object Producer extends App {
  val student = Student(1,"ayush")
  import java.util.Properties

  import org.apache.kafka.clients.producer._


  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "edu.knoldus.CustomSerializer")

  val producer = new KafkaProducer[String, Student](props)

  val TOPIC="test"


    val record = new ProducerRecord(TOPIC, student.id.toString, Student(1,"Ayush"))
    producer.send(record)

  producer.close()
}
