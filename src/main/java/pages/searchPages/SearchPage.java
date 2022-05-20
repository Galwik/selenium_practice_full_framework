package pages.searchPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;


import java.util.List;

public class SearchPage extends HomePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-description .product-title")
    private List<WebElement> protuctTitles;

    @FindBy(css = ".product-description .product-title")
    private WebElement protuctTitleInSearchResult;

    @FindBy(className = "ui-autocomplete-input")
    private WebElement searchField;

    @FindBy(css = "#search_widget [type=submit]")
    private WebElement submitSearchButton;

    @FindBy(css = "#ui-id-1 .ui-menu-item .product")
    private WebElement protuctTitleInDropdownMenu;



    public String enterRandomProductNameIntoSearchFieldAndClick(){
        String randomProductName = getRandomProductName();
        sendKeys(searchField, randomProductName);
        click(submitSearchButton);
        return randomProductName;
    }

    public String enterRandomProductNameIntoSearchField(){
        String randomProductName = getRandomProductName();
        sendKeys(searchField, randomProductName);
        return randomProductName;
    }

    public String getProductNameFromSearchResults(){
        String text = protuctTitleInSearchResult.getText();
        logger.info("Product name in search result: " + text);
        return text;
    }

    public String getProductNameFromDropdownMenu(){
        waitToBeVisible(protuctTitleInDropdownMenu);
        String text = protuctTitleInDropdownMenu.getText();
        logger.info("Product name in search dropdown: " + text);
        return text;
    }
}