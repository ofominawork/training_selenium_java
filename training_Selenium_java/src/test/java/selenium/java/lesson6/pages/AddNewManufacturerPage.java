package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddNewManufacturerPage extends Page {
    public AddNewManufacturerPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    public WebElement name;

    @FindBy(name = "save")
    public WebElement saveButton;

    public AddNewManufacturerPage enterName(String manufacturerName){
        name.sendKeys(manufacturerName);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
        wait.until(titleIs("Manufacturers | My Store"));
    }
}
