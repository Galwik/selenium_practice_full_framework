package searchTest;

import driver.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.searchPages.SearchPage;

import static org.hamcrest.MatcherAssert.assertThat;
public class SearchTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(SearchTest.class);
    private String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldFindProductByProductName(){
        driver.get(URL);
        SearchPage searchPage = new SearchPage(driver);
        String randomProductName = searchPage.enterRandomProductNameIntoSearchFieldAndClick();

        assertThat("Wrong search result!", randomProductName.equals(searchPage.getProductNameFromSearchResults()));

    }

}