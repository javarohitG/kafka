package com.rohit.training.streams;

import java.util.Properties;
import java.util.stream.Stream;

import com.rohit.training.serde.EmployeeSerde;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import com.rohit.training.domain.Employee;

public class StreamingApp {
	
	public static void main(String[] args) {
		Properties props=new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "etl-stream-app");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EmployeeSerde.class.getName());
		KafkaStreams streams=new KafkaStreams(createTopology(), props);
		streams.start();
		System.out.println("stream started");
	}
	
	
	static Topology createTopology()
	{
		
		String inputTopic="mysql-topic-employee";
		String outputTopic="cassandra-topic-employee";
		StreamsBuilder builder=new StreamsBuilder();
		KStream<String, Employee> inputStream=builder.stream(inputTopic);
		KStream<String,Employee> transformedStream=inputStream.mapValues(e->{
			System.out.println("processing the eployee with id "+e.getEmp_id());
			String designation=e.getDesignation();
			Employee employee=new Employee(e.getEmp_id(), e.getName(), designation);
			double salary=60000;
			if(designation.equals("Developer")) {
				salary=30000;
			}
			else if(designation.equals("Accountant")) {
				salary=25000;
			}
			else if(designation.equals("Architect")) {
				salary=80000;
			}
			employee.setSalary(salary);
			return employee;
		});
		transformedStream.to(outputTopic);
		return builder.build();
	}

}
