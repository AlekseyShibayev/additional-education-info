package com.company.app.core.service;

import com.company.app.core.tools.api.DataExtractorService;
import lombok.SneakyThrows;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
public class LogService {

	public static final String LOG_ZIP_FILE_NAME = "logs.zip";
	public static final String LOG_PACKAGE_NAME = "logs";

	@Autowired
	DataExtractorService dataExtractorService;

	@SneakyThrows
	public byte[] getLogsAsZip() {
		File file = new File(LOG_ZIP_FILE_NAME);
		file.delete();

		List<File> files = dataExtractorService.getFiles(LOG_PACKAGE_NAME);

		try (ZipFile zipFile = new ZipFile(LOG_ZIP_FILE_NAME)) {
			zipFile.addFiles(files);
			return FileUtils.readFileToByteArray(zipFile.getFile());
		}
	}
}
