package selenium.java.lesson2;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class includes common methods for tests.
 */
public class BaseSeleniumTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * This method starts Google Chrome browser.
     */
    @Before
    public void start(){
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver, 10);
    }

    /**
     * This method stops Google Chrome browser.
     */
    @After
    public void stop(){
        driver.quit();
        driver=null;
    }

    /**
     * This method opens the provided link.
     * @param url link to open.
     */
    public void openLink(String url){
        driver.navigate().to(url);
    }

    /**
     * This method finds text field by name and sets the provided value.
     * @param fieldName name of the text field.
     * @param fieldValue input value of the text field.
     */
    public void fillTextField(String fieldName, String fieldValue){
        WebElement textField = driver.findElement(By.name(fieldName));
        textField.sendKeys(fieldValue);
    }

}
