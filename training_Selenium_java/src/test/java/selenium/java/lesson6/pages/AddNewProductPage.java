package selenium.java.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddNewProductPage extends Page {
    public AddNewProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.tabs li a")
    public List<WebElement> tabs;

    /**
     * Elements of tab "General"
     */
    @FindBy(css = "[name='status'][value='1']")
    public WebElement statusEnabled;

    @FindBy(css = "[name='status'][value='0']")
    public WebElement statusDisabled;

    @FindBy(name = "name[en]")
    public WebElement name;

    @FindBy(name = "code")
    public WebElement code;

    @FindBy(name = "default_category_id")
    public WebElement defaultCategory;

    @FindBy(name = "product_groups[]")
    public List<WebElement> groups;

    @FindBy(name = "quantity")
    public WebElement quantity;

    @FindBy(name = "quantity_unit_id")
    public WebElement quantityUnit;

    @FindBy(name = "delivery_status_id")
    public WebElement deliveryStatus;

    @FindBy(name = "sold_out_status_id")
    public WebElement soldOutStatus;

    @FindBy(name = "new_images[]")
    public WebElement image;

    @FindBy(name = "date_valid_from")
    public WebElement dateValidFrom;

    @FindBy(name = "date_valid_to")
    public WebElement dateValidTo;

    /**
     * Elements of tab "Information"
     */
    @FindBy(name = "manufacturer_id")
    public WebElement manufacturerId;

    @FindBy(name = "supplier_id")
    public WebElement supplierId;

    @FindBy(name = "keywords")
    public WebElement keywords;

    @FindBy(name = "short_description[en]")
    public WebElement shortDescription;

    @FindBy(css = "div.trumbowyg-editor")
    public WebElement description;

    @FindBy(name = "head_title[en]")
    public WebElement headTitle;

    @FindBy(name = "meta_description[en]")
    public WebElement metaDescription;

    /**
     * Elements of tab "Prices"
     */
    @FindBy(name = "purchase_price")
    public WebElement purchasePrice;

    @FindBy(name = "purchase_price_currency_code")
    public WebElement purchasePriceCurrencyCode;

    /**
     * Buttons
     */

    @FindBy(name="save")
    public WebElement saveButton;


    public AddNewProductPage selectStatus(boolean productStatus) {
        if (productStatus) {
            statusEnabled.click();
        } else {
            statusDisabled.click();
        }
        return this;
    }

    public AddNewProductPage enterName(String productName) {
        name.sendKeys(productName);
        return this;
    }

    public AddNewProductPage enterCode(String productCode) {
        code.sendKeys(productCode);
        return this;
    }

    public AddNewProductPage selectCategory(String productCategory) {
        String categoryLocator = "[data-name='" + productCategory + "']";
        driver.findElement(By.cssSelector(categoryLocator)).click();
        return this;
    }

    public AddNewProductPage selectDefaultCategory(String productDefaultCategory) {
        Select selectCategory = new Select(defaultCategory);
        selectCategory.selectByVisibleText(productDefaultCategory);
        return this;
    }

    public AddNewProductPage clickGroupValue(String groupValueId){
        for(WebElement group: groups){
            if (groupValueId.equals(group.getAttribute("value"))){
                group.click();
            }
        }
        return this;
    }

    public AddNewProductPage enterQuantity(String productQuantiry){
        quantity.click();
        quantity.clear();
        quantity.sendKeys(productQuantiry);
        return this;
    }

    public AddNewProductPage selectQuantityUnit(String productQuantityUnit){
        Select selectUnit = new Select(quantityUnit);
        selectUnit.selectByVisibleText(productQuantityUnit);
        return this;
    }

    public AddNewProductPage selectDeliveryStaus(String productDeliveryStatus){
        Select selectDeliveryStatus = new Select(deliveryStatus);
        selectDeliveryStatus.selectByVisibleText(productDeliveryStatus);
        return this;
    }

    public AddNewProductPage selectDSoldOutStaus(String productSoldOutStatus){
        Select selectSoldOutStatus = new Select(soldOutStatus);
        selectSoldOutStatus.selectByVisibleText(productSoldOutStatus);
        return this;
    }

    public AddNewProductPage selectImage(String filePath){
        image.sendKeys(new File(filePath).getAbsolutePath());
        return this;
    }

    public AddNewProductPage enterdateValidFrom(String productFromDate){
        dateValidFrom.sendKeys(productFromDate);
        return this;
    }

    public AddNewProductPage enterdateValidTo(String productToDate){
        dateValidTo.sendKeys(productToDate);
        return this;
    }

    public void clickInformtationTab(){
        tabs.get(1).click();
        wait.until(presenceOfElementLocated(By.name("manufacturer_id")));
        // поставить ожидание
    }

    public AddNewProductPage selectManufacturer(String productManufacturer){
        Select selectManufacturer = new Select(manufacturerId);
        selectManufacturer.selectByVisibleText(productManufacturer);
        return this;
    }

    public AddNewProductPage selectSupplier(String productSupplier){
        Select selectSupplier = new Select(supplierId);
        selectSupplier.selectByVisibleText(productSupplier);
        return this;
    }

    public AddNewProductPage enterKeyWords(String productKeywords) {
        keywords.sendKeys(productKeywords);
        return this;
    }

    public AddNewProductPage enterShortDescription(String productShortDescription) {
        shortDescription.sendKeys(productShortDescription);
        return this;
    }

    public AddNewProductPage enterDescription(String productDescription) {
        description.sendKeys(productDescription);
        return this;
    }

    public AddNewProductPage enterHeadTitle(String productHeadTitle) {
        headTitle.sendKeys(productHeadTitle);
        return this;
    }

    public AddNewProductPage enterMetaDescription(String productMetaDescription) {
        metaDescription.sendKeys(productMetaDescription);
        return this;
    }

    public void clickPricesTab(){
        tabs.get(3).click();
        wait.until(presenceOfElementLocated(By.name("purchase_price")));
    }

    public AddNewProductPage enterPrice(String productPrice) {
        purchasePrice.click();
        purchasePrice.clear();
        purchasePrice.sendKeys(productPrice);
        return this;
    }

    public AddNewProductPage selectCurrency(String productCurrency){
        Select selectCurrency = new Select(purchasePriceCurrencyCode);
        selectCurrency.selectByVisibleText(productCurrency);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
        wait.until(titleIs("Catalog | My Store"));
    }

}