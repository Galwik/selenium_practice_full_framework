package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {
    public static Config config;


    public YamlReader() {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            this.config = om.readValue(new File("src/main/resources/config.yaml"), Config.class);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static Config getConfig() {
        return config;
    }
}