package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ProductGroupsPage extends Page {

    public ProductGroupsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductGroupsPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=product_groups");
        return this;
    }

    @FindBy(css="tr.row td a")
    private List<WebElement> tableLinks;

    @FindBy(css="tr.row")
    private List<WebElement> tableRows;


    @FindBy(css="a.button")
    private WebElement createNewGroupButton;

    public boolean isProductGroupPresent(String productGroupName){
        for(int i = 0; i< tableLinks.size(); i+=2){
            if (productGroupName.equals(tableLinks.get(i).getText())){
                return true;
            }
        }
        return false;
    }

    public void clickCreateNewGroupButton(){
        createNewGroupButton.click();
        wait.until(titleIs("Create New Product Group | My Store"));
    }

    public void viewGroupValues(String productGroupName){
        for(int i = 0; i< tableLinks.size(); i+=2){
            if (productGroupName.equals(tableLinks.get(i).getText())){
                tableLinks.get(i).click();
                wait.until(titleIs("Edit Product Group | My Store"));
            }
        }
    }

    public String getProductId(String productGroupName){
        for(WebElement tableRow: tableRows){
            String[] fieldValues = tableRow.getAttribute("outerText").trim().split("\t");
            if (productGroupName.equals(fieldValues[1])){
                return fieldValues[0];
            }
        }
        throw new NoSuchProductGroupException(productGroupName);
    }

    public static class NoSuchProductGroupException extends RuntimeException {
        NoSuchProductGroupException(String name) {
            super(name);
        }
    }
}

