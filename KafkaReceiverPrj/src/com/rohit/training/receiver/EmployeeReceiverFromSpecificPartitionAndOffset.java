package com.rohit.training.receiver;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


import com.rohit.training.deserializer.EmployeeDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.rohit.training.domain.Employee;

public class EmployeeReceiverFromSpecificPartitionAndOffset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EmployeeDeserializer.class.getName());
		//props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-2");
		
		int partitionNo=Integer.parseInt(args[0]);
		
		KafkaConsumer<String, Employee> consumer=new KafkaConsumer<>(props);
		
		TopicPartition partition=new TopicPartition("emp-topic", partitionNo);
		List<TopicPartition> partitions=Collections.singletonList(partition);
		
		consumer.assign(partitions);
		int offset=Integer.parseInt(args[1]);
		consumer.seek(partition, offset);
		System.out.println("consuming messages within partition "+partitionNo+" starting from offset "+offset);
		while(true) {
			ConsumerRecords<String, Employee> records=consumer.poll(Duration.ofSeconds(20));
			records.forEach(record->{
				System.out.println("key: "+record.key()+"\tpartition: "+record.partition()+"\t offset: "+record.offset());
				System.out.println("Value");
				Employee employee=record.value();
				System.out.println(employee.getId()+"\t"+employee.getName()+"\t"+employee.getDesignation());
			});
		}
		
		

	}

}
