package selenium.java;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

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
     * This method finds text field by name and sets the provided value.
     * @param fieldName name of the text field.
     * @param fieldValue input value of the text field.
     */
    public void fillTextField(String fieldName, String fieldValue){
        WebElement textField = driver.findElement(By.name(fieldName));
        textField.sendKeys(fieldValue);
    }

    /**
     * This method performs login with the given login, password and "Remember me" option
     * @param login user login value
     * @param password user password value
     * @param remember value of "Remember me" option
     */
    public void login(String login, String password, boolean remember){
        fillTextField("username", login);
        fillTextField("password",password);
        if (remember) {
            driver.findElement(By.name("remember_me")).click();
        }
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

}
