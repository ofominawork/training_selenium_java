package selenium.java.lesson11.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage open(){
        driver.get("http://localhost/litecart/en/");
        return this;
    }

    @FindBy(css="div#box-most-popular li")
    public WebElement firstProductInMostPopular;

    @FindBy(css="div#cart span.quantity")
    private WebElement itemsInCart;

    public int quantityOfItemsInCart(){
        return Integer.valueOf(itemsInCart.getAttribute("textContent"));
    }








}
