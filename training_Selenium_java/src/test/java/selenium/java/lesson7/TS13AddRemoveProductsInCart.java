package selenium.java.lesson7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.java.BaseSeleniumTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TS13AddRemoveProductsInCart extends BaseSeleniumTest {
    private static final By MOST_POPULAR_PRODUCTS_LOCATOR = By.cssSelector("div#box-most-popular li");

    private static final By SIZE_OF_PRODUCT_LOCATOR = By.cssSelector("[name='options[Size]']");
    private static final By ADD_TO_CART_LOCATOR = By.cssSelector("[name='add_cart_product']");
    private static final By QUANTITY_IN_CART_LOCATOR = By.cssSelector("div#cart span.quantity");
    private static final By CHECKOUT_LOCATOR = By.cssSelector("div#cart a.link");

    private static final By PRODUCT_SHORTCUTS_LOCATOR = By.cssSelector("li.shortcut");
    private static final By REMOVE_BUTTONS_LOCATOR = By.cssSelector("button[name='remove_cart_item']");
    private static final By ORDER_CONFIRMATION_TBL_LOCATOR = By.cssSelector("div#order_confirmation-wrapper td.item");

    /**
     * This test adds three products to cart and then removes them all
     */
    @Test
    public void addRemoveProductsInCart() {
        int numberOfItemsInCart=3;
        for (int i=0; i<numberOfItemsInCart; i++) {
            addProductToCart();
        }
        assertEquals("" + numberOfItemsInCart, driver.findElement(QUANTITY_IN_CART_LOCATOR).getAttribute("textContent"));
        driver.findElement(CHECKOUT_LOCATOR).click();
        removeAllProductsFromCart();
        driver.get("http://localhost/litecart/en/");
        assertEquals("0", driver.findElement(QUANTITY_IN_CART_LOCATOR).getAttribute("textContent"));
    }

    /**
     * This method removes all products from the cart
     */
    private void removeAllProductsFromCart(){
        List<WebElement> shortcuts;
        List<WebElement> removeButtons;
        int numberOfUniqueProducts = driver.findElements(REMOVE_BUTTONS_LOCATOR).size();
        for (int i = (numberOfUniqueProducts-1); i >= 0; i--) {
            if(i==0){
                driver.findElement(REMOVE_BUTTONS_LOCATOR).click();
                wait.until(stalenessOf(driver.findElement(ORDER_CONFIRMATION_TBL_LOCATOR)));
            }
            else {
                shortcuts = driver.findElements(PRODUCT_SHORTCUTS_LOCATOR);
                shortcuts.get(i).click();
                removeButtons = driver.findElements(REMOVE_BUTTONS_LOCATOR);
                wait.until(visibilityOf(removeButtons.get(i)));
                removeButtons.get(i).click();
                wait.until(numberOfElementsToBe(ORDER_CONFIRMATION_TBL_LOCATOR, i));

            }
        }
    }

    /**
     * This method adds the first product on Home page to the cart
     */
    private void addProductToCart(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(MOST_POPULAR_PRODUCTS_LOCATOR).click();
        int numberInCart = Integer.valueOf((driver.findElement(QUANTITY_IN_CART_LOCATOR)).getAttribute("textContent"));
        if (isElementPresent(SIZE_OF_PRODUCT_LOCATOR)){
            Select sizeSelector = new Select(driver.findElement(SIZE_OF_PRODUCT_LOCATOR));
            sizeSelector.selectByVisibleText("Small");
        }
        driver.findElement(ADD_TO_CART_LOCATOR).click();
        wait.until(attributeContains(driver.findElement((QUANTITY_IN_CART_LOCATOR)),"textContent",String.valueOf(numberInCart+1)));
    }

    private boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }
}