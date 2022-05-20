package productAndCategories.filters;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.productsPages.categories.CategoriesPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class FiltersTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(FiltersTest.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldCheckAllPriceFilter() throws InterruptedException {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        CategoriesPage categoriesPage = new CategoriesPage(driver);

        homePage.goToArtCategoryPage().setMaxPriceTo(10);
        categoriesPage.waitToBeStaleness(categoriesPage.getProducts().get(0));
        assertThat("Wrong number of products!", homePage.checkAmountOfProductsTextAndConvertToInt().equals(3));

        categoriesPage.setMaxPriceTo(28);
        categoriesPage.waitToBeStaleness(categoriesPage.getProducts().get(0));
        categoriesPage.setMinPriceTo(10);
        categoriesPage.waitToBeStaleness(categoriesPage.getProducts().get(0));
        assertThat("Wrong number of products!", homePage.countProductsInCategory().equals(0));

        categoriesPage.setMaxPriceTo(29);
        Thread.sleep(500);
        categoriesPage.setMinPriceTo(28);
        categoriesPage.waitToBeStaleness(categoriesPage.getProducts().get(0));
        assertThat("Wrong number of products!", homePage.checkAmountOfProductsTextAndConvertToInt().equals(3));
    }
}