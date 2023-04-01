package com.company.app.core.tools.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SerializationService<T> {

	void save(List<T> list, String fileName);

	List<T> load(String fileName, Class<T> type);
}
