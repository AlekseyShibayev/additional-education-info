package com.company.app.core.tools.impl;

import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.core.tool.impl.JsonSerializationToolImpl;
import com.company.app.wildberries.entity.Lot;
import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class SerializationServiceImplTest {

	private static final String FILE_NAME = "src/test/resources/wildberries/lot_test.json";

	private JsonSerializationTool<Lot> serializationService;

	@Before
	public void init() {
		serializationService = new JsonSerializationToolImpl<>();
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

		serializationService.save(list, new File(FILE_NAME));
		List<Lot> load = serializationService.load(new File(FILE_NAME), Lot.class);

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}
}