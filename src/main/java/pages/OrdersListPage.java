package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class OrdersListPage extends BasePage {


    public OrdersListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    List<WebElement> rows;


    public WebElement findReference(String reference) {
        List<WebElement> row = rows.stream().filter(x -> x.findElement(By.cssSelector("th")).getText().equals(reference)).collect(Collectors.toList());
        return row.get(0);
    }


    public String todayDate() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = dateObj.format(formatter);
        return date;
    }


    public OrdersListPage checkDetails(String orderReference, String totalPriceToCheck) {
        WebElement reference = findReference(orderReference);
        String date = reference.findElement(By.cssSelector("td:nth-of-type(1)")).getText();
        String totalPrice = reference.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
        String payment = reference.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
        String status = reference.findElement(By.cssSelector("td:nth-of-type(4)")).getText();

        assertThat("Wrong date", date.equals(todayDate()));
        assertThat("Wrong total price", totalPrice.equals(totalPriceToCheck));
        assertThat("Wrong payment", payment.equals("Bank transfer"));
        assertThat("Wrong status", status.equals("Awaiting bank wire payment"));

        return this;
    }

    public DetailsOrderPage clickOnDetails(String orderReference) {
        WebElement reference = findReference(orderReference);
        click(reference.findElement(By.cssSelector("td:nth-of-type(6) a:first-child")));
        return new DetailsOrderPage(driver);
    }
}