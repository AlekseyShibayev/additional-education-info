package Services;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class FileDataExtractorService {

    private static final String FILE_NAME = "application.properties";

    public Map<String, String> extract() {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(FILE_NAME)) {
            properties.load(stream);
        } catch (IOException e) {
            //todo
        }
        return Maps.fromProperties(properties);
    }
}
