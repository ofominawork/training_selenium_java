package selenium.java.lesson11.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.java.lesson11.pages.CartPage;
import selenium.java.lesson11.pages.HomePage;
import selenium.java.lesson11.pages.ProductPage;

public class Application {
    private WebDriver driver;

    public HomePage homePage;
    public ProductPage productPage;
    private CartPage cartPage;

    public Application(){
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void addFirstMostPopularProductToCart(){
        homePage.open().firstProductInMostPopular.click();
        productPage.addToCart();
    }


    public void removeAllProductsFromCart(){
        cartPage.open().removeProductsFromCart();
    }
}