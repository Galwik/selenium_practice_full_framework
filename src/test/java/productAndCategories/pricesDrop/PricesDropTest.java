package productAndCategories.pricesDrop;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.productsPages.PricesDropPage;
import pages.productsPages.PricesDropProductPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class PricesDropTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(PricesDropTest.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldCheckIfPriceDropIsCorrect() {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        PricesDropPage pricesDropPage = new PricesDropPage(driver);
        PricesDropProductPage pricesDropProductPage = new PricesDropProductPage(driver);


        // check if “on sale” pages loaded
        homePage.goToPricesDropPage();
        assertThat("Wrong page loaded!",
                pricesDropPage.getName(pricesDropPage.getH2Title())
                        .equals("ON SALE"));

        // check if “-20%” is displayed on each product image
        for (WebElement product : pricesDropPage.getProducts()) {
            logger.info("Checking for discount flag");
            assertThat("Not every product have discount flag \"-20%\"",
                    pricesDropPage.getDiscountFlagFromProduct(product).getText()
                            .equals("-20%"));
        }

        // check if each product has regular and discounted price displayed
        for (WebElement product : pricesDropPage.getProducts()) {
            logger.info("Checking for regular and discounted price displayed");
            assertThat("Not every product have discounted price displayed",
                    pricesDropPage.getDiscountedPriceFromProduct(product).isDisplayed());
            assertThat("Not every product have regular price displayed",
                    pricesDropPage.getRegularPriceFromProduct(product).isDisplayed());
        }

        // for each product calculate if actual price is 20% lower than regular
        for (WebElement product : pricesDropPage.getProducts()) {
            logger.info("Calculate if price discount is correct");
            assertThat("Miscalculated value!", pricesDropPage.calculateIfPriceDiscountIsCorrect(product));
        }

        pricesDropPage.click(pricesDropPage.getRandomElement(pricesDropPage.getProducts()));
        assertThat("\"SAVE 20%\" label is not displayed", pricesDropProductPage.getSaveDiscountLabel().isDisplayed());
        assertThat("Regular price is not displayed!", pricesDropProductPage.getRegularPrice().isDisplayed());
        assertThat("Discounted price is not displayed!", pricesDropProductPage.getDiscountedPrice().isDisplayed());
        assertThat("Miscalculated value!", pricesDropProductPage.calculateIfPriceDiscountIsCorrect());

    }
}