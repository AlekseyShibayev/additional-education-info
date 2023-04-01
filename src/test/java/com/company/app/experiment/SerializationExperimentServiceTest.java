package com.company.app.experiment;

import com.company.app.entity.Lot;
import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class SerializationExperimentServiceTest<T> {

	private static final String FILE_NAME = "src/test/resources/wildberries/lot_test.json";

	private void cleanFile() throws IOException {
		FileUtils.write(new File(FILE_NAME), "", Charset.defaultCharset());
	}

	private List<LotForExperiment> createLots() {
		return ImmutableList.<LotForExperiment>builder()
				.add(LotForExperiment.builder()
						.name("43409221")
						.price("1500")
						.discount("0.19")
						.someList(ImmutableList.<String>builder().add("1").build())
						.build())
				.add(LotForExperiment.builder()
						.name("15694225")
						.price("5500")
						.discount("0.17")
						.someList(ImmutableList.<String>builder().add("2").build())
						.build())
				.build();
	}

	@SneakyThrows
	@Test
	public void saveAndLoadTest1() {
		cleanFile();
		List<LotForExperiment> list = createLots();

		SerializationExperimentService1 serializationService = new SerializationExperimentService1();
		serializationService.save(list, FILE_NAME);
		List<LotForExperiment> load = serializationService.load(FILE_NAME, LotForExperiment[].class);

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}


	@SneakyThrows
	@Test
	public void saveAndLoadTest2() {
		cleanFile();
		List<LotForExperiment> list = createLots();

		SerializationExperimentService2 serializationService = new SerializationExperimentService2();
		serializationService.save(list, FILE_NAME);
		List<LotForExperiment> load = serializationService.load(FILE_NAME, LotForExperiment.class);

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}

	@SneakyThrows
	@Test
	public void saveAndLoadTest3() {
		cleanFile();
		List<LotForExperiment> list = createLots();

		SerializationExperimentService3 serializationService = new SerializationExperimentService3();

		serializationService.save(list, FILE_NAME);
		List<LotForExperiment> load = serializationService.load(FILE_NAME, LotForExperiment.class);

		Assert.assertEquals(load.size(), 2);
		Assert.assertEquals(list.get(0).getPrice(), load.get(0).getPrice());
	}
}