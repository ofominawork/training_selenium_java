package selenium.java.lesson2;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Hw3CorrectLoginToAdmin extends TestBase {
    By ADMIN_LOGIN_LOCATOR = By.cssSelector("[name='username']");
    By ADMIN_PASSWORD_LOCATOR = By.cssSelector("[name='password']");

    @Before
    public void start(){
        super.start();
        driver.navigate().to("http://localhost/litecart/admin/login.php");
    }

    @Test
    public void correctLogin(){
        loginToAdmin("admin", "admin", false);
    }

    @Test
    public void correctLoginWithRemember(){
        loginToAdmin("admin", "admin", true);
    }

    private void fillTextField(By fieldLocator, String fieldValue){
        WebElement textField = driver.findElement(fieldLocator);
        textField.sendKeys(fieldValue);
    }

    private void loginToAdmin(String login, String password, boolean remember){
        fillTextField(ADMIN_LOGIN_LOCATOR, login);
        fillTextField(ADMIN_PASSWORD_LOCATOR,password);
        if (remember) {
            driver.findElement(By.name("remember_me")).click();
        }
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }
}
