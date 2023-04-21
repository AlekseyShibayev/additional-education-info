package com.company.app.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class Experiment {

	@Autowired
	public Experiment() {
		log.debug("***   1 стадия (меня не должно быть в тестах!!!)   ***");
	}

	@PostConstruct
	void init() {
		log.debug("***   2 стадия (меня не должно быть в тестах!!!)   ***");
	}
}
