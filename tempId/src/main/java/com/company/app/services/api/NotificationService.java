package com.company.app.services.api;

import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

    void eventNotification(Object message);

    void logNotification(Object message);

    void showLog() ;

    void errorNotification(Exception e);
}
