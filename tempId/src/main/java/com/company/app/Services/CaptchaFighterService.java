package com.company.app.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CaptchaFighterService {

    public void fight(int of, int to) throws InterruptedException {
        long sleepTime = of + getRandomInt(to - of);
        Thread.sleep(sleepTime);
    }

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
