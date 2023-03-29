package com.company.app.experiment;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Этот вариант работает, но он сохраняет в виде
 * {"id":null,"name":"43409221","price":"1500","discount":"0.19"} {"id":null,"name":"15694225","price":"5500","discount":"0.17"}
 * мне не нравится, я хочу в виде
 * [{"id":null,"name":"43409221","price":"1500","discount":"0.19"},{"id":null,"name":"15694225","price":"5500","discount":"0.17"}]
 *
 * И не хочу Class<T> type в load()
 */
@Component
public class SerializationExperimentService2<T> {

	@SneakyThrows
	public void save(List<T> list, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		try (SequenceWriter sequenceWriter = mapper.writer().writeValues(new File(fileName))) {
			sequenceWriter.writeAll(list.toArray());
		}
	}

	@SneakyThrows
	public List<T> load(String fileName, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		try (JsonParser parser = mapper.reader().createParser(new File(fileName))) {
			return Lists.newArrayList(parser.readValuesAs(type));
		}
	}
}
