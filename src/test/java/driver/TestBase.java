package driver;

import configuration.BrowserPicker;
import configuration.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    protected static BrowserPicker browserPicker;
    private static Logger logger = LoggerFactory.getLogger("TestBase.class");
    public WebDriver driver;


    @BeforeEach
    void beforeEach() {
        TestProperties properties = new TestProperties();
        browserPicker = new BrowserPicker();
        driver = browserPicker.getDriver();
        logger.info(">>>DRIVER INITIATED PROPERLY");
    }

    @AfterEach
    void afterEach() {
        driver.manage().deleteAllCookies();
        driver.quit();
        logger.info(">>>DRIVER CLOSED PROPERLY");
    }
}