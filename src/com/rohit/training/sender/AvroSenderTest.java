package com.rohit.training.sender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class AvroSenderTest {
public static void main(String[] args) {
	byte[] array=new byte[1024];
	try {
		FileInputStream fin=new FileInputStream("customer.avsc");
		fin.read(array);
		fin.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Properties props=new Properties();
	props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
	props.setProperty("schema.registry.url", "http://localhost:8081");
	
	KafkaProducer<String, GenericRecord> producer=new KafkaProducer<>(props);
	
	
	Parser parser=new Parser();
	Schema schema=parser.parse(new String(array));
	GenericRecord genericRecord=new GenericData.Record(schema);
	
	genericRecord.put("id", 1001);
	genericRecord.put("name", "Arvind");
	genericRecord.put("email", "arvind@gmail.com");
	
	ProducerRecord<String, GenericRecord> record=new ProducerRecord<String, GenericRecord>("test-avro-topic", "rec-1", genericRecord);
	
	producer.send(record);
	System.out.println("message sent");
	producer.close();
}
}
