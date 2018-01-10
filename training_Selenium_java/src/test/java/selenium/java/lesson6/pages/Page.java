package selenium.java.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    public WebDriver driver;
    WebDriverWait wait;

    Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

}