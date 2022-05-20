package basketAndCheckout;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasketPage;
import pages.HomePage;
import pages.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(Basket.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldAddFiveRandomItemsToBasketAndCheckDetails() throws InterruptedException {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        BasketPage basketPage = new BasketPage(driver);

        List<Product> products = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            homePage.goToRandomProduct().addProductInfoToList(products).addProductToCart().clickContinueShopping();
        }
        homePage.goToBasket();
        Thread.sleep(1000);
        basketPage
                .checkIfEveryProductIsAdded(products)
                .checkIfTotalOrderPriceIsCorrect(products)
                .setFirstProductQuantityTo(5, products)
                .checkIfQuantityIs(5)
                .checkIfTotalOrderPriceIsCorrect(products)
                .clickUPArrowInProductQuantity(products)
                .checkIfTotalOrderPriceIsCorrect(products)
                .clickDOWNArrowInProductQuantity(products)
                .checkIfTotalOrderPriceIsCorrect(products);

        while (!products.isEmpty()) {
            products.remove(0);
            basketPage.deleteFirstItem()
                    .checkIfTotalOrderPriceIsCorrect(products);
        }
    }
}