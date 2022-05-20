package basketAndCheckout;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.productsPages.ProductPage;
import pages.productsPages.ProductPopUpPage;
import pages.productsPages.categories.CategoriesPage;

import java.text.DecimalFormat;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(ShoppingCartTest.class);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldAddProductsToBasket(){
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ProductPopUpPage productPopUpPage = new ProductPopUpPage(driver);


        int sumQuantity = 0;
        int firstQuantity = 0;
        int secondQuantity = 0;
        int thirdQuantity = 0;
        Double shippingPrice = 0.0;

        String firstProduct = "";
        String secondProduct = "";
        String thirdProduct = "";

        logger.info(String.valueOf(Math.round(29.90)));

        Double summaryPrice = 0.0;

        for (int i = 0; i < 3; i++) {


            homePage.click(homePage.getRandomElement(homePage.getCategories()));
            categoriesPage.click(categoriesPage.getRandomElement(categoriesPage.getProducts()));

            String productName = productPage.getProductName().getText();
            if (i == 0) {
                firstProduct = productName;
            }
            if (i == 1) {
                secondProduct = productName;
            }
            if (i == 2) {
                thirdProduct = productName;
            }
            Double productPrice = productPage.getPriceAndConvertToDouble(productPage.getProductPrice());


            int random = new Random().nextInt(5);
            sumQuantity += random + 1;
            for (int j = 0; j < random; j++) {
                productPage.click(productPage.getQuantityUpButton());
//                Thread.sleep(1000);
            }

            logger.info("Quantity: " + sumQuantity);
            logger.info("firstProduct: " + firstProduct);
            logger.info("secondProduct: " + secondProduct);
            logger.info("thirdProduct: " + thirdProduct);
            logger.info("firstProductPrize: " + thirdProduct);

            productPage.click(productPage.getAddToCartButton());
            productPage.waitToBeVisible(productPopUpPage.getPopUpLabel());
            shippingPrice = productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getShippingValue());
            logger.info("shippingPrice: " + shippingPrice);
            assertThat("", productPopUpPage.getProductName().getText().equals(productName));
            assertThat("", productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getProductPrice()).equals(productPrice));

            if (i == 0) {
                assertThat("", productPopUpPage.getProductQuantity().equals(sumQuantity));
                firstQuantity = sumQuantity;
                logger.info("firstQuantity: " + firstQuantity);
            } else if (i == 1) {
                secondQuantity = random + 1;
                logger.info("secondQuantity: " + secondQuantity);
                if (secondProduct.equals(firstProduct)) {
                    assertThat("", productPopUpPage.getProductQuantity().equals(sumQuantity));
                } else assertThat("", productPopUpPage.getProductQuantity().equals(secondQuantity));
            } else {
                thirdQuantity = random + 1;
                logger.info("thirdQuantity: " + thirdQuantity);
                if (thirdProduct.equals(firstProduct)) {
                    assertThat("", productPopUpPage.getProductQuantity().equals(thirdQuantity + firstQuantity));
                } else if (thirdProduct.equals(secondProduct)) {
                    assertThat("", productPopUpPage.getProductQuantity().equals(thirdQuantity + secondQuantity));
                } else assertThat("", productPopUpPage.getProductQuantity().equals(thirdQuantity));

            }

            logger.info(">>>>>>>> " + ((productPrice * firstQuantity) + shippingPrice));
            logger.info(">>>>>>>> " + ((productPrice * secondQuantity) + shippingPrice));
            logger.info(">>>>>>>> " + ((productPrice * thirdQuantity) + shippingPrice));

            assertThat("", productPopUpPage.getNumberOfAllProductsInCart().equals(sumQuantity));
            if (i == 0) {
                assertThat("", productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getProductsTotalValue()).toString().equals(df.format(productPrice * firstQuantity + shippingPrice)));

            }
            if (i == 1) {
                assertThat("", productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getProductsTotalValue()).toString().equals(df.format(productPrice * secondQuantity + summaryPrice + shippingPrice)));

            }
            if (i == 2) {
                assertThat("", productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getProductsTotalValue()).toString().equals(df.format(productPrice * thirdQuantity + summaryPrice + shippingPrice)));

            }
            summaryPrice = productPopUpPage.getPriceAndConvertToDouble(productPopUpPage.getProductsTotalValue()) - shippingPrice;
            logger.info("summaryPrize: " + summaryPrice);

            productPopUpPage.click(productPopUpPage.getContinueShoppingButton());
            assertThat("", productPage.getNumberOfItemsInCart().equals(sumQuantity));

            homePage.getHome();


        }
    }
}