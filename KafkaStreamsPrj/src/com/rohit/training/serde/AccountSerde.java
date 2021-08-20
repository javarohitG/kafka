package com.jpmc.training.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.jpmc.training.domain.Account;

public class AccountSerde implements Serde<Account>{

	@Override
	public Deserializer<Account> deserializer() {
		// TODO Auto-generated method stub
		return new AccountDeserializer();
	}

	@Override
	public Serializer<Account> serializer() {
		// TODO Auto-generated method stub
		return new AccountSerializer();
	}

}
