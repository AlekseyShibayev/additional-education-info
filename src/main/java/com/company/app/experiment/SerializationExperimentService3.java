package com.company.app.experiment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Хочу модернизировать SerializationServiceImpl, чтобы не передавать Class<T[]> type в load()
 * Хочу сделать по типу spring rest template, чтобы как он делать объекты из JSON
 *
 * Так работает, но все же, я попробую избавиться от Class<T> type в сигнатуре load()
 */
@Component
public class SerializationExperimentService3<T> {

	@SneakyThrows
	public boolean save(List<T> list, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(fileName), list.toArray());
		return true;
	}

	@SneakyThrows
	public List<T> load(String fileName, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
		return mapper.readValue(new File(fileName), listType);
	}
}

