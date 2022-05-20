package productAndCategories.categories;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class CategoriesTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(CategoriesTest.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldIterateThroughEachCategory() {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);

        for (int i = 0; i < homePage.getCategories().size(); i++) {
            String wantedCategoryName = homePage.getCategories().get(i).getText();
            logger.info("Wanted category name: " + wantedCategoryName);
            homePage.chooseRightCategoryPage(wantedCategoryName);
            String resultCategoryName = homePage.getName(homePage.getCategoryName());
            logger.info("Result category name: " + resultCategoryName);

            assertThat("Wrong category name!", wantedCategoryName.equals(resultCategoryName));
            assertThat("Filter side menu is not displayed!", homePage.checkIfFilterSideMenuIsDisplayed());
            assertThat("Wrong amount of products", Objects.equals(homePage.countProductsInCategory(), homePage.checkAmountOfProductsTextAndConvertToInt()));
        }
    }
}