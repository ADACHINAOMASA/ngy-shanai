package nis.framework.web.json;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

import nis.framework.type.Hiduke;
import nis.framework.type.Nengetsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Singleton
public class ObjectMapperFactory {

	private ObjectMapper mapper;

	@PostConstruct
	public void init() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("MyModule");

		module.addSerializer(new HidukeSerializer());
		module.addDeserializer(Hiduke.class, new HidukeDeserializer());

		module.addSerializer(new NengetsuSerializer());
		module.addDeserializer(Nengetsu.class, new NengetsuDeserializer());

		mapper.registerModule(module);
    	this.mapper = mapper;
	}

	@Produces
	public ObjectMapper getObjectMapper() {
		return mapper;
	}

}
