package selenium.java.lesson2;

import org.junit.Before;
import org.junit.Test;
import selenium.java.BaseSeleniumTest;


public class Ts3CorrectLoginToAdmin extends BaseSeleniumTest {

    /**
     * This method opens http://localhost/litecart/admin/login.php
     */
    @Before
    public void start(){
        super.start();
        driver.navigate().to("http://localhost/litecart/admin/login.php");
    }

    /**
     * This test performs login to amdin with correct credentials and unchecked value of "Remember me".
     */
    @Test
    public void correctLogin(){
        loginToAdmin("admin", "admin", false);
    }

    /**
     * This test performs login to admin with correct credentials and checked value of "Remember me".
     */
    @Test
    public void correctLoginWithRemember(){
        loginToAdmin("admin", "admin", true);
    }


}
