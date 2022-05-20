package pages.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public static Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    public HomePage getHome() {
        return new HomePage(driver);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        WebElement webElement = elements.get(new Random().nextInt(elements.size()));
        logger.info("Random element: " + webElement.getText());
        return webElement;
    }

    public void waitToBeClicable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void waitToBeStaleness(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForPageLoad() {
        wait.until(driver1 -> isPageLoaded());
    }

    public boolean isPageLoaded() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete")
                && ((JavascriptExecutor) driver).executeScript("return jQuery.active").toString().equals("0");
    }


    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitToBeClicable(element);
        logger.info("Clicking on: " + element.getText());
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitToBeVisible(element);
        logger.info("Typing: " + text);
        element.clear();
        element.sendKeys(text);
    }

    public String getName(WebElement element) {
        logger.info("Element name: " + element.getText());
        return element.getText();
    }


}