package selenium.java.lesson6.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.java.lesson6.model.Currency;
import selenium.java.lesson6.model.Product;
import selenium.java.lesson6.pages.*;

public class Application {
    private WebDriver driver;

    private LoginPage loginPage;
    private CatalogPage catalogPage;
    private AddNewCategoryPage addNewCategoryPage;
    private AddNewProductPage addNewProductPage;
    private ProductGroupsPage productGroupsPage;
    private CreateNewProductGroupPage createNewProductGroupPage;
    private ProductGroupValuesPage productGroupValuesPage;
    private QuantityUnitsPage quantityUnitsPage;
    private AddNewQuantityUnit addNewQuantityUnitPage;
    private DeliveryStatusesPage deliveryStatusesPage;
    private CreateNewDeliveryStatusPage createNewDeliveryStatusPage;
    private SoldOutStatusesPage soldOutStatusesPage;
    private AddNewSoldOutStatus addNewSoldOutStatus;
    private ManufacturersPage manufacturersPage;
    private AddNewManufacturerPage addNewManufacturerPage;
    private SuppliersPage suppliersPage;
    private AddNewSupplierPage addNewSupplierPage;
    private CurrenciesPage currenciesPage;
    private AddNewCurrencyPage addNewCurrencyPage;

    public Application() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        addNewCategoryPage = new AddNewCategoryPage(driver);
        addNewProductPage = new AddNewProductPage(driver);
        productGroupsPage = new ProductGroupsPage(driver);
        createNewProductGroupPage = new CreateNewProductGroupPage(driver);
        productGroupValuesPage = new ProductGroupValuesPage(driver);
        quantityUnitsPage = new QuantityUnitsPage(driver);
        addNewQuantityUnitPage = new AddNewQuantityUnit(driver);
        deliveryStatusesPage = new DeliveryStatusesPage(driver);
        createNewDeliveryStatusPage = new CreateNewDeliveryStatusPage(driver);
        deliveryStatusesPage = new DeliveryStatusesPage(driver);
        createNewDeliveryStatusPage = new CreateNewDeliveryStatusPage(driver);
        soldOutStatusesPage = new SoldOutStatusesPage(driver);
        addNewSoldOutStatus = new AddNewSoldOutStatus(driver);
        manufacturersPage = new ManufacturersPage(driver);
        addNewManufacturerPage = new AddNewManufacturerPage(driver);
        suppliersPage = new SuppliersPage(driver);
        addNewSupplierPage = new AddNewSupplierPage(driver);
        currenciesPage = new CurrenciesPage(driver);
        addNewCurrencyPage=new AddNewCurrencyPage(driver);


    }

    public void quit() {
         driver.quit();
    }

    public void loginToAdmin(){
        loginPage.open().enterUsername("admin").enterPassword("admin").submitLogin();
    }

    public void addNewCategory(String categoryName){
        catalogPage.open();
        if (!catalogPage.isCategoryPresent(categoryName)){
            catalogPage.clickAddCategoryButton();
            addNewCategoryPage.enterName(categoryName).clickSaveButton();
        }
    }

    public void addNewProduct(Product product){
        String groupValue = getProductGroupValue(product);
        catalogPage.open().clickAddNewProductButton();
        fillProductGeneralTab(product, groupValue);
        addNewProductPage.clickInformtationTab();
        fillProductInformationTab(product);
        addNewProductPage.clickPricesTab();
        fillProductPricesTab(product);
        addNewProductPage.clickSaveButton();
    }

    public void addNewProductGroupValue(String productGroupName, String productGroupValue){
        productGroupsPage.open();
        if(!productGroupsPage.isProductGroupPresent(productGroupName)){
            productGroupsPage.clickCreateNewGroupButton();
            createNewProductGroupPage.enterName(productGroupName).clickAddGroupValueButton()
                    .enterGroupValue(productGroupValue).clickSaveButton();
        }
        else{
            productGroupsPage.viewGroupValues(productGroupName);
            if(!productGroupValuesPage.isProductValuePresent(productGroupValue)){
                productGroupValuesPage.clickAddNewValueButton().enterGroupValue(productGroupValue)
                        .clickSaveButton();
            }
        }
    }

    public void addNewUnit(String productQuantityUnit){
        quantityUnitsPage.open();
        if(!quantityUnitsPage.isQuantityUnitPresent(productQuantityUnit)){
            quantityUnitsPage.clickAddNewUnitButton();
            addNewQuantityUnitPage.enterName(productQuantityUnit).clickSaveButton();
        }
    }

    public void addNewDeliveryStatus(String productDeliveryStatus){
        deliveryStatusesPage.open();
        if(!deliveryStatusesPage.isDeliveryStatusPresent(productDeliveryStatus)){
            deliveryStatusesPage.clickAddNewUnitButton();
            createNewDeliveryStatusPage.enterName(productDeliveryStatus).clickSaveButton();
        }
    }

    public void addNewSoldOutStatus(String productSoldOutStatus){
        soldOutStatusesPage.open();
        if(!soldOutStatusesPage.isSoldOutStatusPresent(productSoldOutStatus)){
            soldOutStatusesPage.clickCreateNewStatusButton();
            addNewSoldOutStatus.enterName(productSoldOutStatus).clickSaveButton();
        }
    }

    public void addNewManufacturer(String productManufacturer){
        manufacturersPage.open();
        if(!manufacturersPage.isManufacturerPresent(productManufacturer)){
            manufacturersPage.clickAddNewManufacturerButton();
            addNewManufacturerPage.enterName(productManufacturer).clickSaveButton();
        }
    }

    public void addNewSupplier(String productSupplier){
        suppliersPage.open();
        if(!suppliersPage.isSupplierPresent(productSupplier)){
            suppliersPage.clickAddNewSupplierButton();
            addNewSupplierPage.enterName(productSupplier).clickSaveButton();
        }
    }

    public void addNewCurrency(Currency currency){
        currenciesPage.open();
        if(!currenciesPage.isCurrencyPresent(currency.getName())){
            currenciesPage.clickAddNewCurrencyButton();
            addNewCurrencyPage.enterName(currency.getName()).enterCode(currency.getLetterCode()).
                    enterCodeNumber(currency.getNumberCode()).selectStatus(currency
                    .getStatus()).enterValue(currency.getValue()).clickSaveButton();
        }
    }
    private void fillProductGeneralTab(Product product, String groupValue){
        addNewProductPage.selectStatus(product.getStatus())
                .enterName(product.getName()).enterCode(product.getCode()).selectCategory(product.getCategory())
                .selectDefaultCategory(product.getDefaultCategory()).clickGroupValue(groupValue)
                .enterQuantity(product.getQuantity()).selectDeliveryStaus(product.getDeliveryStatus())
                .selectDSoldOutStaus(product.getSoldOutStatus()).enterdateValidFrom(product.getDateFrom())
                .enterdateValidTo(product.getDateTo()).selectQuantityUnit(product.getQuantityUnit())
                .selectImage(product.getImagePath());
    }

    private void fillProductInformationTab(Product product){
        addNewProductPage.selectManufacturer(product.getManufacturer()).selectSupplier(product.getSupplier())
                .enterKeyWords(product.getKeywords()).enterShortDescription(product.getShortDescription())
                .enterDescription(product.getDescription()).enterHeadTitle(product.getHeadTitle())
                .enterMetaDescription(product.getMetaDescription());
    }

    private void fillProductPricesTab(Product product){
        addNewProductPage.enterPrice(product.getPrice()).selectCurrency(product.getCurrency());
    }

    /**
     * This method finds the value of group on addNewProductPage since it is complex
     * and has the following format: groupId-valueId
     */
    private String getProductGroupValue(Product product){
        String productGroupPart = productGroupsPage.open().getProductId(product.getGroup());
        productGroupsPage.viewGroupValues(product.getGroup());
        String productValuePart= productGroupValuesPage.getProductValueId(product.getGroupValue());
        return productGroupPart+"-"+productValuePart;
    }

    public int numberOfProductsByName(String query){
        return catalogPage.open().performSearch(query).numberOfExactSearchResults(query);
    }

}

