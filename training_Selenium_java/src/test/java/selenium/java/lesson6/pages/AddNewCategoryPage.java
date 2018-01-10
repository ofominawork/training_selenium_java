package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddNewCategoryPage extends Page {

    public AddNewCategoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name[en]")
    private WebElement nameField;

    @FindBy(name = "save")
    public WebElement saveButton;

    public AddNewCategoryPage enterName(String categoryName){
        nameField.sendKeys(categoryName);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
        wait.until(titleIs("Catalog | My Store"));
    }
}
