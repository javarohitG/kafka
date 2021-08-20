package com.rohit.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleSenderWithCallback {
public static void main(String[] args) {
	Properties props=new Properties();
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	
	KafkaProducer<String,String> producer=new KafkaProducer<>(props);
	
	
	String topic="first-topic";
	
	for(int i=1;i<=10;i++) {
		//each message is represented as a producer record
		
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, "sample-key", 
				"This is a test message "+i);
		producer.send(record,new Callback() {
			
			@Override
			public void onCompletion(RecordMetadata rmd, Exception e) {
				// TODO Auto-generated method stub
				if(e==null) {
					System.out.println("message delivered in partition "+rmd.partition()+"\t at offset "+rmd.offset());
				}
				
			}
		});
	}
	
	System.out.println("messages sent");
	producer.close();
}
}
