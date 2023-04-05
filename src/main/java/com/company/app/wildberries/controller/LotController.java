package com.company.app.wildberries.controller;

import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.service.LotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wildberries/lot")
public class LotController {

	@Autowired
	LotService lotService;

	/**
	 * пример запроса: http://localhost:8080/wildberries/lot/1
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Lot> getById(@PathVariable Long id) {
		return ResponseEntity.ok(lotService.getLot(id));
	}

	/**
	 * пример запроса: http://localhost:8080/wildberries/lot/5
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(lotService.deleteLot(id));
	}

	/**
	 * пример запроса: http://localhost:8080/wildberries/lot/all
	 */
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<Lot>> getAll() {
		return ResponseEntity.ok(lotService.getAll());
	}

	/**
	 * пример запроса: http://localhost:8080/wildberries/lot
	 */
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Long> create(@RequestBody LotDto lotDto) {
		return ResponseEntity.ok(lotService.create(lotDto));
	}
}
