package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.productsPages.PricesDropPage;
import pages.productsPages.ProductPage;
import pages.productsPages.categories.CategoriesPage;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".product-description .product-title")
    private List<WebElement> productsTitles;

    @FindBy(id = "category-3")
    private WebElement clothesCategoryButton;

    @FindBy(id = "category-6")
    private WebElement accessoriesCategoryButton;

    @FindBy(id = "category-9")
    private WebElement artCategoryButton;

    @FindBy(className = "h1")
    private WebElement categoryName;

    @FindBy(id = "link-product-page-prices-drop-1")
    private WebElement pricesDropButton;

    @FindBy(xpath = "//a[text()='Stationery']")
    private WebElement stationerySubcategoryButton;

    @FindBy(xpath = "//a[text()='Home Accessories']")
    private WebElement homeAccessoriesSubcategoryButton;

    @FindBy(xpath = "//a[text()='Men']")
    private WebElement menSubcategoryButton;

    @FindBy(xpath = "//a[text()='Women']")
    private WebElement womenSubcategoryButton;

    @FindBy(css = "#top-menu > li")
    private List<WebElement> categories;

    @FindBy(css = ".breadcrumb li:nth-last-child(2)")
    private WebElement prevoiusCategoryButton;

    @FindBy(id = "search_filters")
    private WebElement filtersSideMenu;

    @FindBy(className = "filter-block")
    private WebElement filtersblock;

    @FindBy(css = ".products .product")
    private List<WebElement> products;

    @FindBy(css = ".category-sub-menu li")
    private List<WebElement> subcategories;

    @FindBy(className = "total-products")
    private WebElement productsAmountText;

    @FindBy(className = "logo")
    private WebElement logo;

    @FindBy(className = "blockcart")
    private WebElement cartButton;

    @FindBy(css = ".user-info>a")
    private WebElement singInButton;

    public WebElement getCartButton() {
        return cartButton;
    }

    public BasketPage goToBasket() {
        click(cartButton);
        return new BasketPage(driver);
    }

    public String getRandomProductName() {
        return getRandomElement(productsTitles).getText();
    }

    public boolean checkIfFilterSideMenuIsDisplayed() {
        waitToBeVisible(filtersSideMenu);
        logger.info("Filter side menu is displayed: " + filtersSideMenu.isDisplayed());
        return filtersSideMenu.isDisplayed();
    }

    public Integer countProductsInCategory() {
        return products.size();
    }

    public Integer checkAmountOfProductsTextAndConvertToInt() {
        String[] split = productsAmountText.getText().split(" ");
        logger.info("Full text: " + productsAmountText.getText());
        logger.info("Only int split: " + split[2]);
        return Integer.parseInt(split[2]);
    }

    public CategoriesPage goToClothesCategoryPage() {
        click(clothesCategoryButton);
        return new CategoriesPage(driver);
    }

    public CategoriesPage goToAccessoriesCategoryPage() {
        click(accessoriesCategoryButton);
        return new CategoriesPage(driver);
    }

    public CategoriesPage goToArtCategoryPage() {
        click(artCategoryButton);
        return new CategoriesPage(driver);
    }

    public List<WebElement> getCategories() {
        return categories;
    }

    public List<WebElement> getSubcategories() {
        return subcategories;
    }

    public ProductPage goToRandomProduct() {
        click(getRandomElement(getCategories()));
        click(getRandomElement(getProducts()));
        return new ProductPage(driver);
    }

    public void chooseRightCategoryPage(String category) {
        switch (category) {
            case "CLOTHES":
                logger.info("Choosing CLOTHES category...");
                goToClothesCategoryPage();
                break;
            case "ACCESSORIES":
                logger.info("Choosing ACCESSORIES category...");
                goToAccessoriesCategoryPage();
                break;
            case "ART":
                logger.info("Choosing ART category...");
                goToArtCategoryPage();
                break;
        }
    }

    public void chooseRightSubcategoryPage(String subcategory) {
        switch (subcategory) {
            case "MEN":
                logger.info("Choosing Men subcategory...");
                goToMenSubcategoryPage();
                break;
            case "WOMEN":
                logger.info("Choosing Women subcategory...");
                goToWomenSubcategoryPage();
                break;
            case "STATIONERY":
                logger.info("Choosing Stationery subcategory...");
                goToStationerySubcategoryPage();
                break;
            case "HOME ACCESSORIES":
                logger.info("Choosing Home Accessories subcategory...");
                goToHomeAccessoriesSubcategoryPage();
                break;
        }
    }

    public void goToPreviousCategorySite() {
        click(prevoiusCategoryButton);
    }

    private CategoriesPage goToHomeAccessoriesSubcategoryPage() {
        click(homeAccessoriesSubcategoryButton);
        return new CategoriesPage(driver);
    }

    private CategoriesPage goToStationerySubcategoryPage() {
        click(stationerySubcategoryButton);
        return new CategoriesPage(driver);
    }

    public PricesDropPage goToPricesDropPage() {
        click(pricesDropButton);
        return new PricesDropPage(driver);
    }

    public SignInPage signIn() {
        click(singInButton);
        return new SignInPage(driver);
    }

    public WebElement getProductsAmountText() {
        return productsAmountText;
    }

    private CategoriesPage goToWomenSubcategoryPage() {
        click(womenSubcategoryButton);
        return new CategoriesPage(driver);
    }

    private CategoriesPage goToMenSubcategoryPage() {
        click(menSubcategoryButton);
        return new CategoriesPage(driver);
    }

    public List<WebElement> getProducts() {
        return products;
    }

    public WebElement getFiltersSideMenu() {
        return filtersSideMenu;
    }

    public WebElement getFiltersblock() {
        return filtersblock;
    }

    public WebElement getCategoryName() {
        return categoryName;
    }

    public WebElement getPricesDropButton() {
        return pricesDropButton;
    }

    public WebElement getLogo() {
        return logo;
    }

    @Override
    public HomePage getHome() {
        click(getLogo());
        return super.getHome();
    }
}