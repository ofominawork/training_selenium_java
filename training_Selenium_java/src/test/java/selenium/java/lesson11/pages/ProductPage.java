package selenium.java.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class ProductPage extends Page {

    private static final By SIZE_SELECTOR_LOCATOR = By.cssSelector("[name='options[Size]']");

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='options[Size]']" )
    private WebElement sizeSelector;

    @FindBy(css = "[name='add_cart_product']")
    private WebElement addCartButton;

    @FindBy(css = "div#cart span.quantity")
    private WebElement itemsInCart;

    public void addToCart(){
        String quantityInCartExpected = String.valueOf(quantityOfItemsInCart()+1);
        if (isElementPresentOnPage(SIZE_SELECTOR_LOCATOR)){
          selectFirstAvailableProductSize();
        }
        addCartButton.click();
        wait.until(attributeContains(itemsInCart,"textContent",quantityInCartExpected));
    }

    private boolean isElementPresentOnPage(By locator){
        return driver.findElements(locator).size()>0;
    }

    private void selectFirstAvailableProductSize(){
        sizeSelector.click();
        sizeSelector.sendKeys(Keys.ARROW_DOWN);
        sizeSelector.click();
    }

    public int quantityOfItemsInCart(){
        return Integer.valueOf(itemsInCart.getAttribute("textContent"));
    }

}
