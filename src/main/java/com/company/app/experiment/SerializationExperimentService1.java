package com.company.app.experiment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Рабочий вариант, как в SerializationServiceImpl
 * не устраивает Class<T[]> type в load()
 */
@Component
public class SerializationExperimentService1<T> {

	@SneakyThrows
	public boolean save(List<T> list, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(fileName), list.toArray());
		return true;
	}

	@SneakyThrows
	public List<T> load(String fileName, Class<T[]> type) {
		ObjectMapper mapper = new ObjectMapper();
		T[] array = mapper.readValue(new File(fileName), type);
		return Arrays.asList(array);
	}
}
