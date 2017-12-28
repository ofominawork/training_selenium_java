package selenium.java.lesson6;

import org.junit.*;
import selenium.java.BaseSeleniumTest;

public class Ts11CustomerRegistration extends BaseSeleniumTest {

    /**
     * This method opens http://localhost/litecart/en/create_account
     */
    @Before
    public void start(){
        super.start();
        driver.navigate().to("http://localhost/litecart/en/create_account");
    }

    /**
     * This test fills in registration form with required parameters, registers customer, performs logout from the store,
     * then performs customer login and logout to check the registration is correct
     */
    @Test
    public void checkRegistrationWithRequiredParameters() {
        Account userAccount = new Account("Olga", "Fomina", "1947 Portland ave", "12345", "St.Paul", "United States", "Minnesota", "olga.fomina", "@gmail.com", "+1 234 567-89-00", "password", "password");
        userAccount.fillRegistrationRequiredFields();
        userAccount.registerUserWithRequiredFields();
        userAccount.logout();
        userAccount.login();
        userAccount.logout();
    }
}


