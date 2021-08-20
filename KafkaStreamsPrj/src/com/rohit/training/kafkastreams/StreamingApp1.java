package com.jpmc.training.kafkastreams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

public class StreamingApp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props=new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "simple-stream-app");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StringSerde.class.getName());
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		KafkaStreams streams=new KafkaStreams(createTopology(), props);
		
		streams.start();
	}

	
	static Topology createTopology()
	{
		String inputTopic="etl-source-topic";
		String outputTopic="etl-sink-topic";
		StreamsBuilder builder=new StreamsBuilder();
		KStream<String, String> inputStream=builder.stream(inputTopic);
		KStream<String, String> transformedStream=inputStream.mapValues(line->{
			System.out.println("processing "+line);
			return line.toUpperCase();
		});
		transformedStream.to(outputTopic);
		return builder.build();
	}
	
	
}
