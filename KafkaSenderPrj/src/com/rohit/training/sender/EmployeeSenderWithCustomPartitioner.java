package com.rohit.training.sender;

import java.util.Properties;

import com.rohit.training.partition.EmployeePartitioner;
import com.rohit.training.serializer.EmployeeSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.rohit.training.domain.Employee;

public class EmployeeSenderWithCustomPartitioner {
public static void main(String[] args) {
	Properties props=new Properties();
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
	props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EmployeePartitioner.class.getName());
	
	KafkaProducer<String,Employee> producer=new KafkaProducer<>(props);
	
	
	String topic="emp-topic";
	
	for(int i=1001;i<=1010;i++) {
		ProducerRecord<String, Employee> record=new ProducerRecord<String, Employee>(topic, 
				new Employee(i, "Name "+i, "Developer"));
		producer.send(record);
	}
	
	for(int i=2001;i<=2010;i++) {
		ProducerRecord<String, Employee> record=new ProducerRecord<String, Employee>(topic, 
				new Employee(i, "Name "+i, "Accountant"));
		producer.send(record);
	}
	
	
	for(int i=3001;i<=3010;i++) {
		ProducerRecord<String, Employee> record=new ProducerRecord<String, Employee>(topic, 
				new Employee(i, "Name "+i, "Architect"));
		producer.send(record);
	}
	
	
	for(int i=4001;i<=4010;i++) {
		ProducerRecord<String, Employee> record=new ProducerRecord<String, Employee>(topic, 
				new Employee(i, "Name "+i, "System Admin"));
		producer.send(record);
	}
	
	for(int i=5001;i<=5010;i++) {
		ProducerRecord<String, Employee> record=new ProducerRecord<String, Employee>(topic, 
				new Employee(i, "Name "+i, "Project Manager"));
		producer.send(record);
	}
	System.out.println("messages sent");
	producer.close();
}
}
