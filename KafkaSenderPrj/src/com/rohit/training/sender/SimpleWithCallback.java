package com.jpmc.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleWithCallback {
public static void main(String[] args) {
	Properties props=new Properties();
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	
	KafkaProducer<String,String> producer=new KafkaProducer<>(props);
	String topic="first-topic";
	for(int i=1;i<=10;i++) {
		//Each message is represented as a ProducerRecord
		String key="first-key";
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, 
				key,"This is a test message "+i);
		producer.send(record,new MyCallaback(key));
	}
	for(int i=11;i<=20;i++) {
		String key="second-key";
		ProducerRecord<String, String> record=new ProducerRecord<String, String>(topic, 
				key,"This is a test message "+i);
		producer.send(record,new MyCallaback(key));
	}
	
	
	System.out.println("messages sent");
	producer.close();
}
}


class MyCallaback implements Callback{

	private String key;
	
	
	public MyCallaback(String key) {
		super();
		this.key = key;
	}


	@Override
	public void onCompletion(RecordMetadata rmd, Exception ex) {
		// TODO Auto-generated method stub
		if(ex==null) {
			System.out.println("message with key "+key+" delivered to partition "+rmd.partition()+
					" at offset "+rmd.offset());
		}
		
	}
	
}