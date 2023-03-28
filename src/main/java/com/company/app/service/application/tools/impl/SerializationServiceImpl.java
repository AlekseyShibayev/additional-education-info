package com.company.app.service.application.tools.impl;

import com.company.app.service.application.tools.api.SerializationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class SerializationServiceImpl<T> implements SerializationService<T> {

	@SneakyThrows
	@Override
	public boolean save(List<T> list, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(fileName), list.toArray());
		return true;
	}

	@SneakyThrows
	@Override
	public List<T> load(String fileName, Class<T[]> type) {
		ObjectMapper mapper = new ObjectMapper();
		T[] array = mapper.readValue(new File(fileName), type);
		return Arrays.asList(array);
//	todo: использование анонимного класса тест проходит, но конфликтует со спригом, подумать:   return Arrays.asList(mapper.readValue(new File(fileName), new TypeReference<>() {}));
	}
}
