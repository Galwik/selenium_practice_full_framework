package pages.productsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;

import java.util.Arrays;

public class CategoriesPage extends HomePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-slider a:last-of-type")
    private WebElement priceSliderMax;

    @FindBy(css = ".ui-slider a:first-of-type")
    private WebElement priceSliderMin;

    @FindBy(css = ".faceted-slider p")
    private WebElement priceText;

    public CategoriesPage setMaxPriceTo(int wantedMaxPrice) {
        Actions action = actions.clickAndHold(getPriceSliderMax());
        if (wantedMaxPrice > convertMaxPriceTextToInt()) {
            do {
                action.moveByOffset(5, 0).perform();
            } while (!convertMaxPriceTextToInt().equals(wantedMaxPrice));
            actions.release().perform();
        } else {
            do {
                action.moveByOffset(-5, 0).perform();
            } while (!convertMaxPriceTextToInt().equals(wantedMaxPrice));
            actions.release().perform();
        }
        return this;
    }

    public CategoriesPage setMinPriceTo(int wantedMinPrice) {
        Actions action = actions.clickAndHold(getPriceSliderMin());
        if (wantedMinPrice > convertMinPriceTextToInt()) {
            do {
                action.moveByOffset(5, 0).perform();
            } while (!convertMinPriceTextToInt().equals(wantedMinPrice));
            actions.release().perform();
        } else {
            do {
                action.moveByOffset(-5, 0).perform();
            } while (!convertMinPriceTextToInt().equals(wantedMinPrice));
            actions.release().perform();
        }
        return this;
    }

    private Integer convertMinPriceTextToInt() {
        String[] split = getPriceText().getText().split(" ");
        logger.info("split 1: " + Arrays.toString(split));
        String[] split2 = split[0].split("\\.");
        logger.info("split 2: " + Arrays.toString(split2));
        String replace = split2[0].replace("$", "");
        logger.info("replace to int: " + replace);
        return Integer.parseInt(replace);
    }

    private Integer convertMaxPriceTextToInt() {
        String[] split = getPriceText().getText().split(" ");
        logger.info("split 1: " + Arrays.toString(split));
        String[] split2 = split[2].split("\\.");
        logger.info("split 2: " + Arrays.toString(split2));
        String replace = split2[0].replace("$", "");
        logger.info("replace to int: " + replace);
        return Integer.parseInt(replace);
    }

    public WebElement getPriceSliderMax() {
        return priceSliderMax;
    }

    public WebElement getPriceSliderMin() {
        return priceSliderMin;
    }

    public WebElement getPriceText() {
        return priceText;
    }

    public ProductPage clickRandomProduct() {
        click(getRandomElement(getProducts()));
        return new ProductPage(driver);
    }
}