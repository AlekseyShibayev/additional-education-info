package com.company.app.core.tools.impl;

import com.company.app.core.tools.api.SerializationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class SerializationServiceImpl<T> implements SerializationService<T> {

	@SneakyThrows
	@Override
	public void save(List<T> list, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(fileName), list.toArray());
	}

	@SneakyThrows
	@Override
	public List<T> load(String fileName, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
		return mapper.readValue(new File(fileName), listType);
	}
}
