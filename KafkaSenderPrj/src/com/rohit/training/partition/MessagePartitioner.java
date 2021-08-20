package com.rohit.training.partition;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class MessagePartitioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> props) {
		// TODO Auto-generated method stub
		
		System.out.println("*************configure***************");
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("*************close***************");
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		int partition=3;
		if(key.equals("first-key")) {
			partition=0;
		}
		else if(key.equals("second-key")) {
			partition=1;
		}
		else if(key.equals("third-key")) {
			partition=2;
		}
		System.out.println("sending message with key "+key+" to partition "+partition);
		return partition;
	}

}
