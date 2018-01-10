package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SoldOutStatusesPage extends Page{
    public SoldOutStatusesPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public SoldOutStatusesPage open(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=sold_out_statuses");
        return this;
    }

    @FindBy(css="tr.row")
    private List<WebElement> tableRows;

    @FindBy(css="a.button")
    private WebElement createNewStatusButton;


    public boolean isSoldOutStatusPresent(String soldOutStatus){
        for(WebElement row: tableRows){
            String[] rowValues = row.getAttribute("innerText").trim().split("\t");
            if(soldOutStatus.equals(rowValues[1])){
                return true;
            }
        }
        return false;
    }

    public void clickCreateNewStatusButton(){
        createNewStatusButton.click();
    }
}
