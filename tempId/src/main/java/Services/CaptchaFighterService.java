package Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CaptchaFighterService {

    public static void fight(int of, int to) throws InterruptedException {
        long sleepTime = of + getRandomInt(to - of);
//        NotificationService.logNotification("wait: " + String.valueOf(sleepTime));
        Thread.sleep(sleepTime);
    }

    private static int getRandomInt(int required) {
        Random rand = new Random();
        int n = rand.nextInt(required);
        n += 1;
        return n;
    }

    public static List<String> getQueue(List<String> list) {
        List<String> result = getCopyList(list);
        Collections.shuffle(result);
        Collections.shuffle(result);
        Collections.shuffle(result);
        return result;
    }

    //todo see copy()
    private static List<String> getCopyList(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String string : list) {
            result.add(string);
        }
        return result;
    }
}
