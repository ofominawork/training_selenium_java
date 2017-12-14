package selenium.java.lesson2;

import org.junit.Test;
import org.openqa.selenium.By;
import selenium.java.BaseSeleniumTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Ts3SimpleLogin extends BaseSeleniumTest {

    /**
     * This method performs login with correct credentials and unchecked value of "Remember me".
     */
    @Test
    public void correctLogin(){
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        login("admin", "admin", false);
    }

    /**
     * This method performs login with correct credentials and checked value of "Remember me".
     */
    @Test
    public void correctLoginWithRemember(){
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        login("admin", "admin", true);
    }


}
