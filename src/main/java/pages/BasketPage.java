package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.models.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-line-info:first-child")
    List<WebElement> itemsTitle;

    @FindBy(className = "current-price")
    List<WebElement> itemsCurrentPrice;

    @FindBy(css = ".cart-item:first-child")
    WebElement firstCartItem;

    @FindBy(css = "#cart-subtotal-products .value")
    WebElement productsPrice;

    @FindBy(css = "#cart-subtotal-shipping>.value")
    WebElement shippingPrice;

    @FindBy(css = ".cart-total .value")
    WebElement totalProductsPrice;

    @FindBy(css = ".cart-summary .btn")
    WebElement proceedToCheckoutButton;

    @FindBy(className = "js-cart-line-product-quantity")
    List<WebElement> quantityNumbers;


    public BasketPage deleteFirstItem() throws InterruptedException {
        click(getFirstCartItem().findElement(By.cssSelector(".remove-from-cart")));
        Thread.sleep(1000);
        return this;
    }

    public BasketPage setFirstProductQuantityTo(int quantity, List<Product> products) throws InterruptedException {
        WebElement element = getFirstCartItem().findElement(By.cssSelector(".js-cart-line-product-quantity"));
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        sendKeys(element, String.valueOf(quantity));
        element.sendKeys(Keys.ENTER);
        products.get(0).setQuantity(BigDecimal.valueOf(5));

        Thread.sleep(1000);

        return this;
    }

    public BasketPage clickUPArrowInProductQuantity(List<Product> products) throws InterruptedException {
        Integer quantityBefore = getFirsProductQuantity();
        logger.info(">>>actual Quantity before click up: " + getFirsProductQuantity());

        click(getFirstCartItem().findElement(By.cssSelector(".touchspin-up")));
        products.get(0).setQuantity(BigDecimal.valueOf(quantityBefore + 1));
        Thread.sleep(1000);
        checkIfQuantityIncreased(quantityBefore);
        return this;
    }

    public BasketPage clickDOWNArrowInProductQuantity(List<Product> products) throws InterruptedException {
        Integer quantityBefore = getFirsProductQuantity();
        logger.info(">>>actual Quantity before click down: " + getFirsProductQuantity());


        click(getFirstCartItem().findElement(By.cssSelector(".touchspin-down")));
        products.get(0).setQuantity(BigDecimal.valueOf(quantityBefore - 1));
        Thread.sleep(1000);
        checkIfQuantityDecreased(quantityBefore);
        return this;
    }

    public BasketPage checkIfQuantityDecreased(Integer quantityBefore) {
        logger.info(">>>Quantity before: " + quantityBefore);
        logger.info(">>>actual Quantity before click down: " + getFirsProductQuantity());
//        waitForChange(getProductsPrice());

        assertThat("Quantity did not decrease", getFirsProductQuantity().equals(quantityBefore - 1));

        return this;
    }

    public BasketPage checkIfQuantityIncreased(Integer quantityBefore) {
        logger.info(">>>Quantity before: " + quantityBefore);
        logger.info(">>>actual Quantity before click up: " + getFirsProductQuantity());
//        waitForChange(getProductsPrice());

        assertThat("Quantity did not increase", getFirsProductQuantity().equals(quantityBefore + 1));

        return this;
    }

    public BasketPage checkIfTotalOrderPriceIsCorrect(List<Product> products) {

        BigDecimal productsValueSum = new BigDecimal("0.00");
        for (Product product : products) {
            logger.info(">>>Product price: " + product.getPrice());
            productsValueSum = product.getPrice().multiply(product.getQuantity()).add(productsValueSum);
            logger.info(">>>Product sum: " + productsValueSum);
        }

        BigDecimal productsPrice = getPriceAndConvertToBigDecimal(getProductsPrice());
        BigDecimal shippingPriceValue;
        try {
            shippingPriceValue = getPriceAndConvertToBigDecimal(getShippingPrice());
        } catch (NoSuchElementException exception) {
            shippingPriceValue = BigDecimal.ZERO;
        }
        BigDecimal totalPrice = getPriceAndConvertToBigDecimal(getTotalProductsPrice());

        assertThat("Items value is not correct", productsValueSum.equals(productsPrice));
        assertThat("Total order value is not correct", totalPrice.equals(productsPrice.add(shippingPriceValue)));

        return this;
    }

    public BasketPage checkIfQuantityIs(Integer quantity) {
        assertThat("Quantity is wrong", quantity.equals(getFirsProductQuantity()));
        return this;
    }

    public BasketPage checkIfEveryProductIsAdded(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {

            assertThat("Wrong product name", getItemsTitle().get(i).getText().equals(products.get(i).getName()));
            assertThat("Wrong product price", getItemsCurrentPrice().get(i).getText().replace("$", "").equals(products.get(i).getPrice().toString()));
            assertThat("Wrong product quantity", getQuantityNumbers().get(i).getAttribute("value").equals(products.get(i).getQuantity().toString()));
        }
        return this;
    }

    public Integer getFirsProductQuantity() {
        return Integer.parseInt(getFirstCartItem()
                .findElement(By.cssSelector(".js-cart-line-product-quantity"))
                .getAttribute("value"));
    }

    public BigDecimal getPriceAndConvertToBigDecimal(WebElement element) {
        String replace = element.getText().replace("$", "");
        return new BigDecimal(replace);
    }

    public void clickProceedToCheckout() {
        click(proceedToCheckoutButton);
    }

    public List<WebElement> getQuantityNumbers() {
        return quantityNumbers;
    }

    public WebElement getFirstCartItem() {
        return firstCartItem;
    }

    public WebElement getProductsPrice() {
        return productsPrice;
    }

    public WebElement getShippingPrice() {
        return shippingPrice;
    }

    public WebElement getTotalProductsPrice() {
        return totalProductsPrice;
    }

    public List<WebElement> getItemsTitle() {
        return itemsTitle;
    }

    public List<WebElement> getItemsCurrentPrice() {
        return itemsCurrentPrice;
    }
}