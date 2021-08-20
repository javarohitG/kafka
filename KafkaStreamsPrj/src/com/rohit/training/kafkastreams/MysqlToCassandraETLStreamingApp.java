package com.jpmc.training.kafkastreams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import com.jpmc.training.domain.Account;
import com.jpmc.training.serde.AccountSerde;

public class MysqlToCassandraETLStreamingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props=new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "rdbms-mysql-etl-stream-app");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AccountSerde.class.getName());
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		KafkaStreams streams=new KafkaStreams(createTopology(), props);
		
		streams.start();
	}

	
	static Topology createTopology()
	{
		String inputTopic="etl-mysql-topic-account";
		String outputTopic="etl-cassandra-topic-account";
		StreamsBuilder builder=new StreamsBuilder();
		KStream<String, Account> inputStream=builder.stream(inputTopic);
		KStream<String, Account> transformedStream=inputStream.mapValues(acc->{
			System.out.println("processing account of "+acc.getAccount_no());
			String accountType=acc.getAccount_type();
			Account account=new Account(acc.getAccount_no(), acc.getCustomer_id(), accountType);
			double interestRate=0;
			if(accountType.equals("SB")) {
				interestRate=0.03;
			}
			else if(accountType.equals("RD")) {
				interestRate=0.07;
			}
			account.setInterest_rate(interestRate);
			return account;
		});
		transformedStream.to(outputTopic);
		return builder.build();
	}
	
	
}
