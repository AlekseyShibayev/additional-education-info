package com.company.app.core.tools.impl;

import com.company.app.core.tools.api.SerializationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Component
public class SerializationServiceImpl<T> implements SerializationService<T> {

	@SneakyThrows
	@Override
	public void save(List<T> list, File file) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, list.toArray());
	}

	@SneakyThrows
	@Override
	public List<T> load(File file, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
		return mapper.readValue(file, listType);
	}

	@SneakyThrows
	@Override
	public List<T> load(String string, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
		return mapper.readValue(string, listType);
	}

	@SneakyThrows
	@Override
	public List<T> load(InputStream inputStream, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
		return mapper.readValue(inputStream, listType);
	}

	@SneakyThrows
	@Override
	public List<T> load(Resource resource, Class<T> type) {
		List<T> list;
		try (InputStream inputStream = resource.getInputStream()) {
			list = this.load(inputStream, type);
		}
		return list;
	}
}
