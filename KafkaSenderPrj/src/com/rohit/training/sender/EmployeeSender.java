package com.rohit.training.sender;

import java.util.Properties;

import com.rohit.training.serializer.EmployeeSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.rohit.training.domain.Employee;

public class EmployeeSender {
public static void main(String[] args) {
	Properties props=new Properties();
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
	
	KafkaProducer<String,Employee> producer=new KafkaProducer<>(props);
	
	
	String topic="emp-topic";
	
	Employee e1=new Employee(5001, "Rajiv", "Developer");
	ProducerRecord<String, Employee> record1=new ProducerRecord<String, Employee>(topic, "emp-1",e1);
	
	Employee e2=new Employee(5002, "Arvind", "Accountant");
	ProducerRecord<String, Employee> record2=new ProducerRecord<String, Employee>(topic, "emp-2",e2);
	
	producer.send(record1);
	producer.send(record2);
	System.out.println("messages sent");
	producer.close();
}
}
