package selenium.java;

import org.openqa.selenium.By;

/**
 * This interface contains locators to login and password fields of Admin login form
 */
public interface AdminLoginForm {
    By ADMIN_LOGIN_LOCATOR = By.cssSelector("[name='username']");
    By ADMIN_PASSWORD_LOCATOR = By.cssSelector("[name='password']");
}
