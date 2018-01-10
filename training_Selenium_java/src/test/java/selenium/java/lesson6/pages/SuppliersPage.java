package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SuppliersPage extends Page{
    public SuppliersPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public SuppliersPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=suppliers");
        return this;
    }

    @FindBy(css="tr.row")
    private List<WebElement> tableRows;

    @FindBy(css="a.button")
    private WebElement addNewSupplierButton;


    public boolean isSupplierPresent(String productSupplier){
        for(WebElement row: tableRows){
            String[] rowValues = row.getAttribute("innerText").trim().split("\t");
            if(productSupplier.equals(rowValues[0])){
                return true;
            }
        }
        return false;
    }

    public void clickAddNewSupplierButton(){
        addNewSupplierButton.click();
    }
}
