package com.jpmc.training.serde;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.training.domain.Account;

public class AccountSerializer implements Serializer<Account>{
	
	private ObjectMapper mapper=new ObjectMapper();

	@Override
	public byte[] serialize(String topic, Account account) {
		// TODO Auto-generated method stub
		byte[] array=null;
		try {
			array=mapper.writeValueAsBytes(account);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

}
