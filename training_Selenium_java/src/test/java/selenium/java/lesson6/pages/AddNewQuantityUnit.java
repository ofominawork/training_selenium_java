package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewQuantityUnit extends Page {

    public AddNewQuantityUnit(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(name="name[en]")
    public WebElement name;

    @FindBy(name="save")
    public WebElement saveButton;

    public AddNewQuantityUnit enterName(String unitName){
        name.sendKeys(unitName);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
    }
}
