public class NotificationService {

    public static void errorNotification(Exception e) {
        e.printStackTrace();
    }

    public static void eventNotification(String message) {
        System.out.println(message);
    }
}
