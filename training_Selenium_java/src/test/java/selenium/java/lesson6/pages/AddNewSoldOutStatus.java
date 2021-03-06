package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewSoldOutStatus extends Page {

    public AddNewSoldOutStatus(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="name[en]")
    public WebElement name;

    @FindBy(name="save")
    public WebElement saveButton;

    public AddNewSoldOutStatus enterName(String productSoldOutStatus){
        name.sendKeys(productSoldOutStatus);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
    }
}
