package com.company.app.wildberries.controller;

import com.company.app.wildberries.service.api.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wildberries/noRest/lot")
public class NoRestLotController {

	@Autowired
	LotService lotService;

	/**
	 * пример запроса: http://localhost:8080/wildberries/lot/manualCreate?name=tempName&price=999&discount=0.11
	 */
	@GetMapping(value = "/manualCreate", produces = "application/json")
	public ResponseEntity<Long> create(@RequestParam String name,
									   @RequestParam String price,
									   @RequestParam String discount) {
		return ResponseEntity.ok(lotService.create(name, price, discount));
	}
}
