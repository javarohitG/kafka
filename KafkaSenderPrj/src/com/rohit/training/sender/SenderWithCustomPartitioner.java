package com.rohit.training.sender;

import java.util.Properties;

import com.rohit.training.partition.MessagePartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SenderWithCustomPartitioner {
public static void main(String[] args) {
	Properties props=new Properties();
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MessagePartitioner.class.getName());
	
	KafkaProducer<String,String> producer=new KafkaProducer<>(props);
	
	
	String topic="first-topic";
	
	for(int i=1;i<=10;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "first-key", 
				"This is a test message "+i);
		producer.send(record);
	}
	for(int i=11;i<=20;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "second-key", 
				"This is a test message "+i);
		producer.send(record);
	}
	
	for(int i=21;i<=30;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "third-key", 
				"This is a test message "+i);
		producer.send(record);
	}
	
	for(int i=31;i<=40;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "fourth-key", 
				"This is a test message "+i);
		producer.send(record);
	}
	for(int i=41;i<=50;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "fifth-key", 
				"This is a test message "+i);
		producer.send(record);
	}
	
	
	System.out.println("messages sent");
	producer.close();
}
}
