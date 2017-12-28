package selenium.java;

import org.openqa.selenium.By;

/**
 * This interface contains locators to email, password fields and login-button
 */
public interface CustomerLoginForm {
    By CUSTOMER_EMAIL_LOCATOR = By.cssSelector("input[name='email']");
    By CUSTOMER_PASSWORD_LOCATOR = By.cssSelector("input[name='password");
    By CUSTOMER_LOGIN_BUTTON_LOCATOR = By.cssSelector("button[name='login']");
}
