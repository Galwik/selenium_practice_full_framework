package basketAndCheckout;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import pages.models.adress.Address;
import pages.models.adress.AddressFactory;
import pages.models.user.User;
import pages.models.user.UserFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(Basket.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldCheckAllPurchaseSteps() throws InterruptedException {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        User user = new UserFactory().getRandomUser();
        Address address = new AddressFactory().getRandomAddress();
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, user);
        CheckoutPage checkoutPage = new CheckoutPage(driver, address);
        OrderPage orderPage = new OrderPage(driver);

        StringBuilder selectedShippingMethod = new StringBuilder("");

        List<Product> products = new ArrayList<>();


        homePage.signIn().createNewAccount();
        createAccountPage.registerUser();

        for (int i = 0; i < 4; i++) {
            homePage.goToRandomProduct().addProductToCart(true, 3).clickContinueShopping(products);
        }
        homePage.goToRandomProduct().addProductToCart(true, 3).clickProceedToCheckout(products).clickProceedToCheckout();

        checkoutPage
                .fillAddress()
                .selectRandomShippingMethod(selectedShippingMethod)
                .selectBankWire()
                .clickTermsOfService()
                .acceptTermsOfService()
                .placeOrder()
                .checkIfEveryProductIsAdded(products)
                .checkIfShippingAndPaymentIsCorrect(selectedShippingMethod);

        String totalValue = orderPage.getTotalPrice();
        String orderReference = orderPage.getOrderReference();

        orderPage.goToOrderList().checkDetails(orderReference, totalValue).clickOnDetails(orderReference).checkDetails(products).checkAddress(address, user);


    }
}