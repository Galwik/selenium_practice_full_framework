package productAndCategories.categories;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class SubcategoriesTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(SubcategoriesTest.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldIterateThroughEachSubcategory() {
        driver.get(URL);
        HomePage homePage = new HomePage(driver);

        for (int i = 0; i < homePage.getCategories().size() - 1; i++) {
            String wantedCategoryName = homePage.getCategories().get(i).getText();
            homePage.chooseRightCategoryPage(wantedCategoryName);
            for (int j = 0; j < homePage.getSubcategories().size(); j++) {
                String wantedSubcategoryName = homePage.getSubcategories().get(j).getText().toUpperCase();
                logger.info("Wanted subcategory name: " + wantedSubcategoryName);
                homePage.chooseRightSubcategoryPage(wantedSubcategoryName);
                String resultSubcategoryName = homePage.getName(homePage.getCategoryName());
                logger.info("Result subcategory name: " + resultSubcategoryName);

                assertThat("Wrong category name!", wantedSubcategoryName.equals(resultSubcategoryName));
                assertThat("Filter side menu is not displayed!", homePage.checkIfFilterSideMenuIsDisplayed());
                assertThat("Wrong amount of products", Objects.equals(homePage.countProductsInCategory(), homePage.checkAmountOfProductsTextAndConvertToInt()));
                homePage.goToPreviousCategorySite();
            }
        }
    }
}