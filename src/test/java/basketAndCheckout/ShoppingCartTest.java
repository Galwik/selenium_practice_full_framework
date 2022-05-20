package basketAndCheckout;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.models.Product;
import pages.productsPages.ProductPage;
import pages.productsPages.ProductPopUpPage;
import pages.productsPages.CategoriesPage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartTest.class);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldAddProductsToBasket() throws InterruptedException {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            homePage.goToRandomCategory()
                    .clickRandomProduct()
                    .addProductToCart(true,5)
                    .checkDetails(products)
                    .clickContinueShopping()
                    .checkCartIconQuantity(products);
        }
            homePage.getHome();
    }
}