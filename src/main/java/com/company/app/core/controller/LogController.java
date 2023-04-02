package com.company.app.core.controller;

import com.company.app.core.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	LogService logService;

	/**
	 * пример запроса: http://localhost:8080/log/logsAsZip
	 */
	@GetMapping(value = "/logsAsZip", produces = "application/zip")
	public ResponseEntity<byte[]> load() {
		return ResponseEntity.ok(logService.getLogsAsZip());
	}
}
