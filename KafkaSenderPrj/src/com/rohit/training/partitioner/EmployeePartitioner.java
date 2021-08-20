package com.jpmc.training.partitioner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import com.jpmc.training.domain.Employee;

public class EmployeePartitioner implements Partitioner{
	
	private Properties props=new Properties();

	@Override
	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fin=new FileInputStream("designation.properties");
			props.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		props=null;
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		int partition=3;
		Employee employee=(Employee)value;
		String designation=employee.getDesignation();
		if(props.containsKey(designation)) {
			partition=Integer.parseInt(props.getProperty(designation));
		}
		return partition;
	}

}
