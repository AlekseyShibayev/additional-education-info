package com.company.app.MainLogic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationStarter {

	private static final String SPRING_XML_PATH = "config.xml";

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_PATH);
			context.getBean(SearchJob.class).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
