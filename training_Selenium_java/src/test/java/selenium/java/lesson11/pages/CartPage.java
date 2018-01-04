package selenium.java.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage extends Page {

    private static final By PRODUCTS_IN_TABLE_LOCATOR = By.cssSelector("div#order_confirmation-wrapper td.item");

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open(){
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    @FindBy(css="div#order_confirmation-wrapper td.item")
    private List<WebElement> productsInTableList;

    @FindBy(css="button[name='remove_cart_item']")
    private List<WebElement> removeButtonsList;

    @FindBy(css="li.shortcut")
    private List<WebElement> shortcutsList;


    public void removeProductsFromCart(){
        int quantityOfProductsInCart = productsInTableList.size();
        for (int i = (quantityOfProductsInCart-1); i >= 0; i--) {
            if(i==0){
                removeButtonsList.get(i).click();
                wait.until(stalenessOf(productsInTableList.get(0)));
            }
            else {
                shortcutsList.get(i).click();
                wait.until(visibilityOf(removeButtonsList.get(i)));
                removeButtonsList.get(i).click();
                wait.until(numberOfElementsToBe(PRODUCTS_IN_TABLE_LOCATOR, i));

            }
        }
    }
}
