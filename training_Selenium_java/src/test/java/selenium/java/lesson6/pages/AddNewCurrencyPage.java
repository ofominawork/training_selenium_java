package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddNewCurrencyPage extends Page {

    public AddNewCurrencyPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    public WebElement name;

    @FindBy(name = "save")
    public WebElement saveButton;

    @FindBy(name="code")
    public WebElement code;

    @FindBy(name="number")
    public WebElement number;

    @FindBy(css="[name='status'][value='1']")
    public WebElement statusEnabled;

    @FindBy(css="[name='status'][value='0']")
    public WebElement statusDisabled;

    @FindBy(name="value")
    public WebElement value;

    public AddNewCurrencyPage enterName(String currencyName){
        name.sendKeys(currencyName);
        return this;
    }

    public AddNewCurrencyPage enterCode(String productCode){
        code.sendKeys(productCode);
        return this;
    }

    public AddNewCurrencyPage enterCodeNumber(String productCodeNumber){
        number.sendKeys(productCodeNumber);
        return this;
    }

    public AddNewCurrencyPage enterValue(String currencyValue){
        value.click();
        value.clear();
        value.sendKeys(currencyValue);
        return this;
    }

    public void clickSaveButton(){
        saveButton.click();
        wait.until(titleIs("Currencies | My Store"));
    }

    public AddNewCurrencyPage selectStatus(boolean status){
        if (status){
            statusEnabled.click();
        }
        else statusDisabled.click();
        return this;
    }
}
