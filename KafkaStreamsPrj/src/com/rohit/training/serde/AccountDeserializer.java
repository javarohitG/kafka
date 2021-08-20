package com.jpmc.training.serde;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.training.domain.Account;

public class AccountDeserializer implements Deserializer<Account>{
	private ObjectMapper mapper=new ObjectMapper();

	@Override
	public Account deserialize(String topic, byte[] array) {
		// TODO Auto-generated method stub
		Account account=null;
		try {
			account=mapper.readValue(array, Account.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

}
