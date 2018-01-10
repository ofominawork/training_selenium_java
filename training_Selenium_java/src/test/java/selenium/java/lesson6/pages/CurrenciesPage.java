package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrenciesPage extends Page{

    public CurrenciesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public CurrenciesPage open(){
        driver.get("http://localhost/litecart/admin/?app=currencies&doc=currencies");
        return this;
    }

    @FindBy(css="tr.row")
    private List<WebElement> tableRows;

    @FindBy(css="a.button")
    private WebElement addNewCurrencyButton;


    public boolean isCurrencyPresent(String currency){
        for(WebElement row: tableRows){
            String[] rowValues = row.getAttribute("outerText").trim().split("\t");
            if(currency.equals(rowValues[2])){
                return true;
            }
        }
        return false;
    }

    public void clickAddNewCurrencyButton(){
        addNewCurrencyButton.click();
    }
}
