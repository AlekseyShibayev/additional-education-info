package com.company.app.services.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CaptchaFighterService {

    void fight(int of, int to) throws InterruptedException;

    List<String> getQueue(List<String> list);
}
