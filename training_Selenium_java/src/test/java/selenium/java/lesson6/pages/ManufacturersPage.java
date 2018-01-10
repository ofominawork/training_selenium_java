package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ManufacturersPage extends Page {

    public ManufacturersPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public ManufacturersPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=manufacturers");
        return this;
    }

    @FindBy(css="tr.row")
    private List<WebElement> tableRows;

    @FindBy(css="a.button")
    private WebElement addNewManufacturerButton;


    public boolean isManufacturerPresent(String manufacturer){
        for(WebElement row: tableRows){
            String[] rowValues = row.getAttribute("innerText").trim().split("\t");
            if(manufacturer.equals(rowValues[0])){
                return true;
            }
        }
        return false;
    }

    public void clickAddNewManufacturerButton(){
        addNewManufacturerButton.click();
    }
}
