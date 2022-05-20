package configuration;

import configuration.model.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestProperties {

    static Logger logger = LoggerFactory.getLogger(TestProperties.class);
    YamlReader yamlReader = new YamlReader();
    private Browser browser;

    public TestProperties() {
        setBrowserProperties();
//        setEnvironmentProperties();
    }


    private void setBrowserProperties() {
        browser = YamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getBrowserProperties();
        for (Map.Entry entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }
}