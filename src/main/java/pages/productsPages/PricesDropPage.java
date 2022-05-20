package pages.productsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;

public class PricesDropPage extends HomePage {
    public PricesDropPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "h2")
    private WebElement onSaleTitle;

    @FindBy(className = "discount")
    private WebElement discountFlag;

    @FindBy(css = ".product-description .price")
    private WebElement discountedPrice;

    @FindBy(css = ".product-description .regular-price")
    private WebElement regularPrice;


    public WebElement getH2Title() {
        return onSaleTitle;
    }

    public WebElement getDiscountFlagFromProduct(WebElement product) {
        return product.findElement(By.cssSelector(".discount"));
    }

    public WebElement getDiscountedPriceFromProduct(WebElement product) {
        return product.findElement(By.cssSelector(".product-description .price"));
    }

    public WebElement getRegularPriceFromProduct(WebElement product) {
        return product.findElement(By.cssSelector(".product-description .regular-price"));
    }

    public boolean calculateIfPriceDiscountIsCorrect(WebElement product) {
        Double regularPriceAfterConvert = Double.parseDouble(getRegularPriceFromProduct(product).getText().replace("$", ""));
        logger.info("Regular price after convert: " + regularPriceAfterConvert);
        Double discountedPriceAfterConvert = Double.parseDouble(getDiscountedPriceFromProduct(product).getText().replace("$", ""));
        logger.info("Discounted price after convert: " + discountedPriceAfterConvert);
        logger.info("Discounted price after math: " + regularPriceAfterConvert * 0.8);
        return (regularPriceAfterConvert * 0.8) == discountedPriceAfterConvert;
    }
}