package pages.productsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PricesDropProductPage extends PricesDropPage {
    public PricesDropProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "discount-percentage")
    private WebElement saveDiscountLabel;

    @FindBy(className = "regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".current-price :first-child")
    private WebElement discountedPrice;

    public WebElement getSaveDiscountLabel() {
        return saveDiscountLabel;
    }

    public WebElement getRegularPrice() {
        return regularPrice;
    }

    public WebElement getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean calculateIfPriceDiscountIsCorrect() {
        Double regularPriceAfterConvert = Double.parseDouble(getRegularPrice().getText().replace("$", ""));
        logger.info("Regular price after convert: " + regularPriceAfterConvert);
        Double discountedPriceAfterConvert = Double.parseDouble(getDiscountedPrice().getText().replace("$", ""));
        logger.info("Discounted price after convert: " + discountedPriceAfterConvert);
        logger.info("Discounted price after math: " + regularPriceAfterConvert * 0.8);
        return (regularPriceAfterConvert * 0.8) == discountedPriceAfterConvert;
    }
}