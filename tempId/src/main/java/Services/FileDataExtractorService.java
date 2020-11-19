package Services;

import com.google.common.collect.Maps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class FileDataExtractorService {

    //todo use absolute path
    private static final String FILE_NAME = "C:\\Users\\АЕ\\Documents\\GitHub\\FutureSpringBootPractice\\tempId\\src\\main\\resources\\application.properties";

    public static Map<String, String> extract() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            properties.load(fis);
        } catch (IOException e) {
//            NotificationService.errorNotification(e);
        }
        return Maps.fromProperties(properties);
    }
}
