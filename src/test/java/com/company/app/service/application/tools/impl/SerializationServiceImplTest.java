package com.company.app.service.application.tools.impl;

import com.company.app.entity.Lot;
import com.company.app.service.application.tools.api.SerializationService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class SerializationServiceImplTest<T> {

	private static final String FILE_NAME = "src/test/resources/wildberries/lot_test.json";

	private SerializationService<Lot> serializationService;

	@Before
	public void init() {
		serializationService = new SerializationServiceImpl<>();
	}

	private void cleanFile() throws IOException {
		FileUtils.write(new File(FILE_NAME), "", Charset.defaultCharset());
	}

	private List<Lot> createLots() {
		return ImmutableList.<Lot>builder()
				.add(Lot.builder().name("43409221").price("1500").discount("0.19").build())
				.add(Lot.builder().name("15694225").price("5500").discount("0.17").build())
				.build();
	}

	@SneakyThrows
	@Test
	public void saveAndLoadTest() {
		cleanFile();
		List<Lot> list = createLots();

		serializationService.save(list, FILE_NAME);
		List<Lot> load = serializationService.load(FILE_NAME, Lot[].class);

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}

	/**
	 * Этот вариант работает, но он сохраняет в виде
	 * {"id":null,"name":"43409221","price":"1500","discount":"0.19"} {"id":null,"name":"15694225","price":"5500","discount":"0.17"}
	 * мне не нравится, я хочу в виде
	 * [{"id":null,"name":"43409221","price":"1500","discount":"0.19"},{"id":null,"name":"15694225","price":"5500","discount":"0.17"}]
	 */
	@SneakyThrows
//	@Test
	public void saveAndLoadTest2() {
		cleanFile();
		List<Lot> list = createLots();

		save(list);
		List<Lot> load = load();

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}

	private void save(List<Lot> list) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try (SequenceWriter sequenceWriter = mapper.writer().writeValues(new File(FILE_NAME))) {
			sequenceWriter.writeAll(list.toArray());
		}
	}

	private List<Lot> load() throws IOException {
		List<Lot> result;
		ObjectMapper mapper = new ObjectMapper();
		try (JsonParser parser = mapper.reader().createParser(new File(FILE_NAME))) {
			result =  Lists.newArrayList(parser.readValuesAs(Lot.class));
		}
		return result;
	}

	/**
	 * Этот вариант работает, но мне не нравится передавать Class<T[]> type
	 */
	private boolean save(List<T> list, String fileName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(fileName), list.toArray());
		return true;
	}

	private List<T> load(String fileName, Class<T[]> type) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		T[] array = mapper.readValue(new File(fileName), type);
		return Arrays.asList(array);
	}
}