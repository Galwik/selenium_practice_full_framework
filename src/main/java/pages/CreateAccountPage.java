package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.models.user.User;

import java.util.List;

public class CreateAccountPage extends BasePage {

    private User user;

    public CreateAccountPage(WebDriver driver, User user) {
        super(driver);
        this.user = user;
    }

    @FindBy(css = ".custom-radio")
    List<WebElement> prefix;

    @FindBy(name = "firstname")
    WebElement firstName;

    @FindBy(name = "lastname")
    WebElement lastName;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "birthday")
    WebElement birthday;

    @FindBy(css = ".form-footer .btn")
    WebElement saveButton;

    @FindBy(css = ".custom-checkbox input")
    List<WebElement> checkboxes;


    public HomePage registerUser() {
        click(prefix.get(user.getSocialTitle()));
        sendKeys(firstName, user.getFirstName());
        sendKeys(lastName, user.getLastName());
        sendKeys(email, user.getEmail());
        sendKeys(password, user.getPassword());
        sendKeys(birthday, (user.getMonth()) + "/" + user.getDay() + "/" + user.getYear());
        accept(checkboxes.get(0), user.isReceiveOffers());
        accept(checkboxes.get(1), user.isDataPrivacy());
        accept(checkboxes.get(2), user.isNewsletter());
        accept(checkboxes.get(3), user.isAcceptPolicy());
        click(saveButton);

        return new HomePage(driver);
    }

    @Override
    public void click(WebElement element) {
        logger.info("Clicking on: " + element.getText());
        element.click();
    }

    private void accept(WebElement element, boolean isAccept) {
        if (isAccept) {
            click(element);
        }


    }
}