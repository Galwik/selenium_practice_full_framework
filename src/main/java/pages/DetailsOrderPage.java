package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.models.Product;
import pages.models.adress.Address;
import pages.models.user.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class DetailsOrderPage extends BasePage {

    public DetailsOrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-products tbody tr")
    List<WebElement> rows;

    @FindBy(css = "#delivery-address address")
    WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    WebElement invoiceAddress;


    public WebElement findReference(String reference) {
        List<WebElement> row = rows.stream().filter(x -> x.findElement(By.cssSelector("th")).getText().equals(reference)).collect(Collectors.toList());
        return row.get(0);
    }


    public DetailsOrderPage checkDetails(List<Product> products) {

        int i = 0;
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("strong a")).getText();
            String quantity = row.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String price = row.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String totalPrice = row.findElement(By.cssSelector("td:nth-of-type(4)")).getText();

            logger.info("name  " + name);
            logger.info("quantity  " + quantity);
            logger.info("price  " + price);
            logger.info("totalPrice  " + totalPrice);

            Product product = products.get(i);

            logger.info("name after  " + product.getName());
            logger.info("quantity after  " + product.getQuantity());
            logger.info("price after  " + product.getPrice());


            assertThat("Wrong name", name.contains(product.getName()));
            assertThat("Wrong quantity", quantity.equals(product.getQuantity().toString()));
            assertThat("Wrong price", price.contains(product.getPrice().toString()));
            i++;

        }
        return this;
    }

    public DetailsOrderPage checkAddress(Address address, User user) {
        convertAndCheckAddress(address, user, deliveryAddress);
        convertAndCheckAddress(address, user, invoiceAddress);
        return this;
    }

    private void convertAndCheckAddress(Address address, User user, WebElement element) {

        String split[] = element.getText().split("\\r?\\n");

        String name = split[0];
        String street = split[1];
        String zipcode = split[2].split(" ")[0];
        String city = split[2].split(" ", 2)[1];
        String country = split[3];


        assertThat("Wrong name", name.equals(user.getFirstName() + " " + user.getLastName()));
        assertThat("Wrong street", street.equals(address.getStreet()));
        assertThat("Wrong zipcode", zipcode.equals(address.getZipCode()));
        assertThat("Wrong city", city.equals(address.getCity()));
        assertThat("Wrong country", country.equals("Poland"));

    }
}