package com.company.app.core.tools.impl;

import com.company.app.core.tools.api.SerializationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Service
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
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, type));
	}

	@SneakyThrows
	@Override
	public List<T> load(String string, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(string, mapper.getTypeFactory().constructCollectionType(List.class, type));
	}

	@SneakyThrows
	@Override
	public List<T> load(InputStream inputStream, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, type));
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
