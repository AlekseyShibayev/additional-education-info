package com.company.app.core.service.impl;

import com.company.app.core.service.api.LogService;
import com.company.app.core.tools.api.DataExtractorService;
import lombok.SneakyThrows;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

	public static final String LOG_ZIP_FILE_NAME = "logs.zip";
	public static final String PACKAGE_NAME = "logs";

	@Autowired
	DataExtractorService dataExtractorService;

	@SneakyThrows
	public byte[] getLogsAsZip() {
		List<File> files = dataExtractorService.getFiles(PACKAGE_NAME);
		byte[] bytes = getBytes(files);
		FileUtils.forceDelete(new File(LOG_ZIP_FILE_NAME));
		return bytes;
	}

	private byte[] getBytes(List<File> files) throws IOException {
		try (ZipFile zipFile = new ZipFile(LOG_ZIP_FILE_NAME)) {
			zipFile.addFiles(files);
			return FileUtils.readFileToByteArray(zipFile.getFile());
		}
	}
}
