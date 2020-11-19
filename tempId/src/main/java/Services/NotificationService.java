package Services;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private static final boolean logEnabled = true;
    private List<String> log;

    public NotificationService() {
        this.log = new ArrayList<>();
    }

    public void eventNotification(Object message) {
        System.out.println(message.toString());
    }

    public void logNotification(Object message) {
        if (logEnabled) {
            log.add(message.toString());
        }
    }

    public void writeLog() {
        for (String s : log) {
            System.out.println(s);
        }
    }
}
