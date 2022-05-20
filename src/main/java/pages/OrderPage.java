package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class OrderPage extends BasePage {
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "order-line")
    List<WebElement> productList;

    @FindBy(css = "#order-details li:nth-of-type(3)")
    WebElement shippingMethod;

    @FindBy(css = "#order-details li:nth-of-type(2)")
    WebElement paymentMathod;

    @FindBy(css = "#order-details li:nth-of-type(1)")
    WebElement orderReference;

    @FindBy(css = ".account-list a[title=\"Orders\"]")
    WebElement ordersLink;

    @FindBy(css = ".total-value td:last-child")
    WebElement totalPrice;


    public OrderPage checkIfEveryProductIsAdded(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {

            logger.info("product name" + products.get(i).getName());
            logger.info("product name" + getProductName(productList.get(i)));
            logger.info("product price" + products.get(i).getPrice());
            logger.info("product price" + getProductPrice(productList.get(i)));
            logger.info("product quantity" + products.get(i).getQuantity());
            logger.info("product quantity" + getProductQuantity(productList.get(i)));


            assertThat("Wrong product name", getProductName(productList.get(i)).contains(products.get(i).getName()));
            assertThat("Wrong product price", getProductPrice(productList.get(i)).equals(products.get(i).getPrice()));
            assertThat("Wrong product quantity", getProductQuantity(productList.get(i)).equals(products.get(i).getQuantity()));
        }
        return this;
    }

    public String getProductName(WebElement element) {
        return element.findElement(By.cssSelector(".details>span")).getText();
    }

    public BigDecimal getProductQuantity(WebElement element) {
        return new BigDecimal(element.findElement(By.cssSelector(".order-line .row div:nth-of-type(2):not(.value)")).getText());
    }

    public BigDecimal getProductPrice(WebElement element) {
        return getPriceAndConvertToBigDecimal(element.findElement(By.cssSelector(".order-line .row div:nth-of-type(1):not(.label)")));
    }

    public BigDecimal getPriceAndConvertToBigDecimal(WebElement element) {
        String replace = element.getText().replace("$", "");
        return new BigDecimal(replace);
    }

    public OrderPage checkIfShippingAndPaymentIsCorrect(StringBuilder selectedShippingMethod) {
        assertThat("Wrong shipping method", shippingMethod.getText().contains(selectedShippingMethod));
        assertThat("Wrong payment method", paymentMathod.getText().contains("Bank transfer"));
        return this;
    }

    public String getTotalPrice() {
        waitForPageLoad();
        return totalPrice.getText();
    }

    public String getOrderReference() {
        String[] split = orderReference.getText().split(": ");
        return split[1];
    }

    public OrdersListPage goToOrderList() {
        click(ordersLink);
        return new OrdersListPage(driver);
    }

}