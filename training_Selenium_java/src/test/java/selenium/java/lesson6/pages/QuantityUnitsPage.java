package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QuantityUnitsPage extends Page {

    public QuantityUnitsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public QuantityUnitsPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=quantity_units");
        return this;
    }

    @FindBy (css="tr.row")
    private List<WebElement> tableRows;

    @FindBy(css="a.button")
    private WebElement addNewUnitButton;


    public boolean isQuantityUnitPresent(String quantityUnit){
        for(WebElement row: tableRows){
            String[] rowValues = row.getAttribute("innerText").trim().split("\t");
            if(quantityUnit.equals(rowValues[1])){
                return true;
            }
        }
        return false;
    }

    public void clickAddNewUnitButton(){
        addNewUnitButton.click();
    }
}
