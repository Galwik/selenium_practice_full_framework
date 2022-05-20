package pages.productsPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;
import pages.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductPage extends HomePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "h1")
    private WebElement productName;

    @FindBy(css = ".current-price :first-child")
    private WebElement productPrice;

    @FindBy(className = "bootstrap-touchspin-up")
    private WebElement quantityUpButton;

    @FindBy(className = "add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = ".header .cart-products-count")
    private WebElement numberOfItemsInCart;

    @FindBy(css = ".card.card-block")
    private WebElement customization;

    @FindBy(css = ".product-message")
    private WebElement productMessage;

    @FindBy(name = "submitCustomizedData")
    private WebElement saveCustomization;


    public WebElement getProductName() {
        return productName;
    }

    public WebElement getProductPrice() {
        return productPrice;
    }

    public WebElement getQuantityUpButton() {
        return quantityUpButton;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public Integer getNumberOfItemsInCart() {
        return Integer.parseInt(numberOfItemsInCart.getText().replace("(", "").replace(")", ""));
    }

    public ProductPage addProductInfoToList(List<Product> products) {
        AtomicBoolean alreadyInCart = new AtomicBoolean(false);
        products.forEach(x -> {
            if (x.getName().equals(getProductName().getText())) {
                x.setQuantity(x.getQuantity().add(BigDecimal.ONE));
                alreadyInCart.set(true);
            }
        });
        if (!alreadyInCart.get()) {
            products.add(new Product(getProductName().getText(), getPriceAndConvertToBigDecimal(getProductPrice()), BigDecimal.ONE));
        }
        return this;
    }

    public ProductPopUpPage addProductToCart() {
        try {
            if (customization.isDisplayed()) {
                sendKeys(productMessage, "Text");
                click(saveCustomization);
            }
        } catch (NoSuchElementException exception) {
            exception.getMessage();
        }
        click(getAddToCartButton());
        return new ProductPopUpPage(driver);
    }

    public ProductPopUpPage addProductToCart(boolean random, int maxAmount) {
        if (random) {
            int randomInt = new Random().nextInt(maxAmount);
            for (int i = 0; i < randomInt; i++) {
                click(getQuantityUpButton());
            }
        }
        try {
            if (customization.isDisplayed()) {
                sendKeys(productMessage, "Text");
                click(saveCustomization);
            }
        } catch (NoSuchElementException exception) {
            exception.getMessage();
        }
        click(getAddToCartButton());

        return new ProductPopUpPage(driver);
    }


    public Double getPriceAndConvertToDouble(WebElement element) {
        String replace = element.getText().replace("$", "");
        return Double.parseDouble(replace);
    }

    public BigDecimal getPriceAndConvertToBigDecimal(WebElement element) {
        String replace = element.getText().replace("$", "");
        return new BigDecimal(replace);
    }
}