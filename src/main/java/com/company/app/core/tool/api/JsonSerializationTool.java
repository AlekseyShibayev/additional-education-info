package com.company.app.core.tool.api;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface JsonSerializationTool<T> {

	void save(List<T> list, File file);

	String asString(List<T> list);

	List<T> load(File file, Class<T> type);

	List<T> load(String string, Class<T> type);

	List<T> load(InputStream inputStream, Class<T> type);

	List<T> load(Resource resource, Class<T> type);
}
