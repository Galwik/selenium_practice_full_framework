package pages.productsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasketPage;
import pages.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductPopUpPage extends ProductPage {
    public ProductPopUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "modal-content")
    private WebElement popUp;

    @FindBy(id = "myModalLabel")
    private WebElement popUpLabel;

    @FindBy(css = ".modal-content .product-name")
    private WebElement productName;

    @FindBy(css = ".modal-content .product-price")
    private WebElement productPrice;

    @FindBy(css = ".modal-content .product-quantity strong")
    private WebElement productQuantity;

    @FindBy(css = ".modal-content .cart-products-count")
    private WebElement productsCountText;

    @FindBy(css = ".modal-content .product-total .value")
    private WebElement productsTotalValue;

    @FindBy(css = ".modal-content .btn-secondary")
    private WebElement continueShoppingButton;

    @FindBy(css = ".modal-content .btn-primary")
    private WebElement proceedToCheckout;

    @FindBy(css = ".shipping.value")
    private WebElement shippingValue;

    public WebElement getPopUp() {
        return popUp;
    }

    @Override
    public WebElement getProductName() {
        return productName;
    }

    @Override
    public WebElement getProductPrice() {
        return productPrice;
    }

    public Integer getProductQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public WebElement getProductsCountText() {
        return productsCountText;
    }

    public WebElement getProductsTotalValue() {
        return productsTotalValue;
    }

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    public ProductPage clickContinueShopping() {
        click(getContinueShoppingButton());
        return new ProductPage(driver);
    }

    public ProductPage clickContinueShopping(List<Product> products) throws InterruptedException {
        Thread.sleep(1000);
        addProductInfoToList(products);
        click(getContinueShoppingButton());
        return new ProductPage(driver);
    }

    @Override
    public ProductPage addProductInfoToList(List<Product> products) {
        waitForPageLoad();
        AtomicBoolean alreadyInCart = new AtomicBoolean(false);
        products.forEach(x -> {
            if (x.getName().equals(getProductName().getText())) {
                x.setQuantity(BigDecimal.valueOf(getProductQuantity()));
                alreadyInCart.set(true);
            }
        });
        if (!alreadyInCart.get()) {
            products.add(new Product(getProductName().getText(), getPriceAndConvertToBigDecimal(getProductPrice()), BigDecimal.valueOf(getProductQuantity())));
        }
        return this;
    }

    public Integer getNumberOfAllProductsInCart() {
        String[] split = getProductsCountText().getText().split(" ");
        logger.info("Number of products after split: " + split[2]);
        return Integer.parseInt(split[2]);
    }

    public Double getPriceAndConvertToDouble(WebElement element) {
        if (element.getText().equals("Free")) {
            return 0.0;
        }
        String replace = element.getText().replace("$", "");
        return Double.parseDouble(replace);
    }

    public WebElement getPopUpLabel() {
        return popUpLabel;
    }

    public WebElement getShippingValue() {
        return shippingValue;
    }

    public BasketPage clickProceedToCheckout() {
        click(proceedToCheckout);
        return new BasketPage(driver);
    }

    public BasketPage clickProceedToCheckout(List<Product> products) throws InterruptedException {
        Thread.sleep(1000);
        addProductInfoToList(products);
        click(proceedToCheckout);
        return new BasketPage(driver);
    }
}