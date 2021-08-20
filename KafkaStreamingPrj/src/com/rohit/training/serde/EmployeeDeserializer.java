package com.rohit.training.serde;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohit.training.domain.Employee;

public class EmployeeDeserializer implements Deserializer<Employee>{

	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public Employee deserialize(String topic, byte[] array) {
		// TODO Auto-generated method stub
		Employee employee=null;
		try {
			employee=mapper.readValue(array, Employee.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
}
