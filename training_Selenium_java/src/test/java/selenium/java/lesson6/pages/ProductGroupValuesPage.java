package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductGroupValuesPage extends Page{

    public ProductGroupValuesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="table.dataTable span.input-wrapper input")
    private List<WebElement> groupValues;

    @FindBy(css="a#add-group-value")
    private WebElement addNewValuebutton;

    @FindBy(name="values[new_1][name][en]")
    private WebElement newGroupValueName;

    @FindBy(name="save")
    public WebElement saveButton;

    @FindBy(css="table.dataTable input[type='hidden']")
    public WebElement groupValueId;

    public boolean isProductValuePresent(String productGroupValue){
        for(WebElement groupValue:groupValues){
            if (productGroupValue.equals(groupValue.getAttribute("value"))){
                return true;
            }
        }
        return false;
    }

    public ProductGroupValuesPage clickAddNewValueButton(){
        addNewValuebutton.click();
        return this;
    }

    public ProductGroupValuesPage enterGroupValue(String productGroupValue){
        newGroupValueName.sendKeys(productGroupValue);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public String getProductValueId(String productGroupValue){

        for (WebElement groupValue: groupValues){
            if (productGroupValue.equals(groupValue.getAttribute("value"))){
                return groupValue.getAttribute("name").replaceAll("[^0-9]+", "");
            }
        }
        throw new ProductGroupValuesPage.NoSuchProductGroupValueException(productGroupValue);
    }

    private static class NoSuchProductGroupValueException extends RuntimeException {
        private NoSuchProductGroupValueException(String value) {
            super(value);
        }
    }

}
