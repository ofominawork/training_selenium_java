package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CreateNewProductGroupPage extends Page {

    public CreateNewProductGroupPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="name[en]")
    public WebElement name;

    @FindBy(name="save")
    public WebElement saveButton;

    @FindBy(css="a#add-group-value")
    private WebElement addGroupValueButton;

    @FindBy(name="values[new_1][name][en]")
    private WebElement groupValue;

    public CreateNewProductGroupPage enterName(String productGroupName){
        name.sendKeys(productGroupName);
        return this;
    }

    public CreateNewProductGroupPage clickAddGroupValueButton(){
        addGroupValueButton.click();
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
        wait.until(titleIs("Product Groups | My Store"));
    }

    public CreateNewProductGroupPage enterGroupValue(String productGroupValue){
        groupValue.sendKeys(productGroupValue);
        return this;
    }

}
