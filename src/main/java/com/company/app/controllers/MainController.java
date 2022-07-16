package com.company.app.controllers;

import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping(value = "/")
    public void interceptAll(@RequestBody Map<String, Object> lookupRequestObject) {
        notificationService.eventNotification(lookupRequestObject.toString());
    }
}
