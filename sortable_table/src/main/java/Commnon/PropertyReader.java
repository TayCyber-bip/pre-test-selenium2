package Commnon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties loadProperties(String fileName) {
        Properties props = new Properties();
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
