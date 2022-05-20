package pages.productsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.BasketPage;
import pages.models.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

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

    public ProductPage clickContinueShopping() throws InterruptedException {
        Thread.sleep(1000);
        click(continueShoppingButton);
        return new ProductPage(driver);
    }

    public ProductPage clickContinueShopping(List<Product> products) throws InterruptedException {
        Thread.sleep(1000);
        addProductInfoToList(products);
        click(continueShoppingButton);
        return new ProductPage(driver);
    }

    @Override
    public ProductPage addProductInfoToList(List<Product> products) throws InterruptedException {
        Thread.sleep(1000);
        AtomicBoolean alreadyInCart = new AtomicBoolean(false);
        products.forEach(x -> {
            if (x.getName().equals(getProductName().getText())) {
                x.setQuantity(new BigDecimal(productQuantity.getText()));
                alreadyInCart.set(true);
            }
        });
        if (!alreadyInCart.get()) {
            products.add(new Product(productName.getText(), getPriceAndConvertToBigDecimal(productPrice), new BigDecimal(productQuantity.getText())));
        }
        return this;
    }

    public BigDecimal getNumberOfAllProductsInCart() {
        String[] split = productsCountText.getText().split(" ");
        logger.info("Number of products after split: " + split[2]);
        return new BigDecimal(split[2]);
    }

    public BasketPage clickProceedToCheckout(List<Product> products) throws InterruptedException {
        Thread.sleep(1000);
        addProductInfoToList(products);
        click(proceedToCheckout);
        return new BasketPage(driver);
    }

    public ProductPopUpPage checkDetails(List<Product> products) throws InterruptedException {
        addProductInfoToList(products);
        Product product = products.get(products.size() - 1);
        BigDecimal totalQuantity = new BigDecimal(products.stream().mapToInt(x -> x.getQuantity().intValue()).sum());

        logger.info("totalValue: " + totalValue(products));
        logger.info("shippingValuestr: " + shippingValue.getText());
        logger.info("shippingValue: " + shippingValue());
        logger.info("productsTotalValue: " + getPriceAndConvertToBigDecimal(productsTotalValue));

        assertThat("Wrong product name exp: " + productName.getText() + "   giv: " + product.getName(),
                productName.getText().equals(product.getName()));
        assertThat("Wrong product price exp: " + productPrice.getText() + "   giv: " + product.getPrice(),
                getPriceAndConvertToBigDecimal(productPrice).equals(product.getPrice()));
        assertThat("Wrong product quantity exp: " + product.getQuantity() + "   giv: " + productQuantity.getText(),
                product.getQuantity().equals(new BigDecimal(productQuantity.getText())));
        assertThat("Wrong summary quantity exp: " + getNumberOfAllProductsInCart() + "   giv: " + totalQuantity,
                getNumberOfAllProductsInCart().equals(totalQuantity));
        assertThat("Wrong total value exp: " + productsTotalValue.getText() + "   giv: " + totalValue(products),
                getPriceAndConvertToBigDecimal(productsTotalValue).equals(totalValue(products)));

        return this;
    }

    private BigDecimal shippingValue() {
        if (shippingValue.getText().equals("Free")) {
            return BigDecimal.ZERO;
        } else {
            return getPriceAndConvertToBigDecimal(shippingValue);
        }
    }

    private BigDecimal totalValue(List<Product> products) {
        BigDecimal decimal = new BigDecimal("0.00");

        for (Product product : products) {
             decimal = decimal.add(product.getPrice().multiply(product.getQuantity()));
        }
        decimal = decimal.add(shippingValue());
        return decimal;
    }
}