package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.models.adress.Address;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutPage extends BasePage {

    private Address address;

    public CheckoutPage(WebDriver driver, Address address) {
        super(driver);
        this.address = address;
    }


    @FindBy(name = "address1")
    WebElement street;

    @FindBy(name = "city")
    WebElement city;

    @FindBy(name = "postcode")
    WebElement zipcode;

    @FindBy(name = "id_country")
    WebElement country;

    @FindBy(name = "id_state")
    WebElement state;

    @FindBy(css = ".form-footer .btn")
    WebElement saveButton;

    @FindBy(name = "confirmDeliveryOption")
    WebElement continueButton;

    @FindBy(id = "payment-option-2")
    WebElement bankWireButton;

    @FindBy(css = ".custom-checkbox input")
    WebElement termsOfServiceCheckbox;

    @FindBy(id = "cta-terms-and-conditions-0")
    WebElement termsOfServiceLink;

    @FindBy(css = ".in .js-modal-content")
    WebElement termsText;

    @FindBy(css = ".in .close")
    WebElement termsCloseButton;

    @FindBy(css = "#payment-confirmation .btn")
    WebElement placeOrderButton;

    @FindBy(css = ".js-country option:not(:first-child)")
    List<WebElement> countries;

    @FindBy(css = ".form-fields .custom-radio")
    List<WebElement> shippingMethods;


    public CheckoutPage fillAddress() {
        sendKeys(street, address.getStreet());
        sendKeys(zipcode, address.getZipCode());
        sendKeys(city, address.getCity());
        click(country);
        click(countries.get(0));
        waitForPageLoad();

        click(saveButton);

        return this;
    }

    public CheckoutPage selectRandomShippingMethod(StringBuilder shippingMethod) {
        waitForPageLoad();
        int random = new Random().nextInt(2);
        click(shippingMethods.get(random));
        if (random == 0) {
            shippingMethod.append("TesterSii");
        } else {
            shippingMethod.append("My carrier");
        }
        click(continueButton);

        return this;
    }

    public CheckoutPage selectBankWire() {
        waitForPageLoad();
        bankWireButton.click();

        return this;
    }

    public CheckoutPage clickTermsOfService() {
        click(termsOfServiceLink);
        waitToBeVisible(termsText);
        assertThat("Terms and conditions of use is empty", !termsText.getText().isEmpty());
        click(termsCloseButton);
        return this;
    }

    public CheckoutPage acceptTermsOfService() {
        waitForPageLoad();
        termsOfServiceCheckbox.click();
        return this;
    }

    public OrderPage placeOrder() {
        click(placeOrderButton);
        return new OrderPage(driver);
    }


}