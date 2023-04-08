package com.company.app.springboottest.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class JpaTest {

	@Autowired
	TestEntityManager testEntityManager;
}
