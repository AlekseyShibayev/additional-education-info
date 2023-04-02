package com.company.app.core.tools.impl;

import com.company.app.core.tools.JsonSearcher;
import com.company.app.core.tools.api.DataExtractorService;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataExtractorServiceImpl implements DataExtractorService {

	@Override
	public JSONObject getJsonObject(JSONObject jsonObject, String searchString) {
		JsonSearcher jsonSearcher = new JsonSearcher();
		jsonSearcher.doRecursiveSearch(jsonObject, searchString);
		return jsonSearcher.getResult();
	}

	@Override
	public JSONArray getJsonArray(JSONObject jsonObject, String searchString) {
		JsonSearcher jsonSearcher = new JsonSearcher();
		jsonSearcher.doRecursiveSearch(jsonObject, searchString);
		return jsonSearcher.getResultArray();
	}

	@Override
	public String getFile(String fileName) {
		String result;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
			result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("Can't read from file.", e);
		}
		return result;
	}

	@SneakyThrows
	@Override
	public List<File> getFiles(String packageName) {
		try (Stream<Path> walk = Files.walk(Paths.get(packageName))) {
			return walk
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.collect(Collectors.toList());
		}
	}

	@Override
	public Map<String, String> getProperties(String fileName) {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream stream = classLoader.getResourceAsStream(fileName)) {
			properties.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Can't read properties from file.", e);
		}
		return Maps.fromProperties(properties);
	}

	@Override
	public String getHtmlResponse(String urlName) {
		StringBuilder response = new StringBuilder();
		try {
			URL url = new URL(urlName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				fillResponse(connection, response);
			} else {
				throw new RuntimeException("http request is not 200.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Can't read html from URL.", e);
		}
		return response.toString();
	}

	private void fillResponse(HttpURLConnection con, StringBuilder response) throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		}
	}
}