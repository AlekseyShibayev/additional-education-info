package com.company.app.core.tools.api;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Component
public interface SerializationService<T> {

	void save(List<T> list, File file);

	List<T> load(File file, Class<T> type);

	List<T> load(String string, Class<T> type);

	List<T> load(InputStream inputStream, Class<T> type);

	List<T> load(Resource resource, Class<T> type);
}
