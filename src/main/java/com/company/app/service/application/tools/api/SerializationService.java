package com.company.app.service.application.tools.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SerializationService<T> {

	boolean save(List<T> list, String fileName);

	List<T> load(String fileName, Class<T[]> type);
}
