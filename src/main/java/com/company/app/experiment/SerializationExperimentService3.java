package com.company.app.experiment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Хочу модернизировать SerializationServiceImpl, чтобы не передавать Class<T[]> type в load()
 * Хочу сделать по типу spring rest template, чтобы как он делать объекты из JSON
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
	public List<T> load(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(fileName), new TypeReference<>() {});
	}
}

