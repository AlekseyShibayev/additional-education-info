package com.company.app.services.impl;

import com.company.app.services.api.CaptchaFighterService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class CaptchaFighterServiceImpl implements CaptchaFighterService {

    @Override
    public void fight(int of, int to) throws InterruptedException {
        long sleepTime = of + getRandomInt(to - of);
        Thread.sleep(sleepTime);
    }

    @Override
    public List<String> getQueue(List<String> list) {
        List<String> result = getCopyList(list);
        Collections.shuffle(result);
        return result;
    }

    private int getRandomInt(int required) {
        Random rand = new Random();
        int n = rand.nextInt(required);
        n += 1;
        return n;
    }

    private List<String> getCopyList(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String string : list) {
            result.add(string);
        }
        return result;
    }
}
