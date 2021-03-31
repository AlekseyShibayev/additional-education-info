package com.company.app;

import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

@Autowired
private NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostMapping(value = "/")
	public void interceptAll(@RequestBody Map<String, Object> lookupRequestObject) {
		notificationService.eventNotification(lookupRequestObject.toString());
	}
}
