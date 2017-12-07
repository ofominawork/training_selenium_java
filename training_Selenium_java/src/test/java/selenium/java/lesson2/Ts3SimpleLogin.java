package selenium.java.lesson2;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Ts3SimpleLogin extends BaseSeleniumTest {

    /**
     * This method performs login with correct credentials and unchecked value of "Remember me".
     */
    @Test
    public void correctLogin(){
        openLink("http://localhost/litecart/admin/login.php");
        fillTextField("username", "admin");
        fillTextField("password", "admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    /**
     * This method performs login with correct credentials and checked value of "Remember me".
     */
    @Test
    public void correctLoginWithRemember(){
        openLink("http://localhost/litecart/admin/login.php");
        fillTextField("username", "admin");
        fillTextField("password", "admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }


}
