package selenium.java.lesson8;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import selenium.java.BaseSeleniumTest;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Ts14OpenWindowsV2 extends BaseSeleniumTest {
    private static final String LOGIN_TO_ADMIN_URL = "http://localhost/litecart/admin/login.php";
    private static final String COUNTRIES_IN_ADMIN_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";

    private static final By ADD_COUNTRY_LOCATOR = By.cssSelector("div#content-wrapper a.button");
    private static final By BUTTONS_TO_EXTERNAL_LINKS_LOCATOR = By.cssSelector(("i[class='fa fa-external-link']"));

    /**
     * This test asserts that each new link is opened in a new window by:
     * - checking that two windows are opened after click
     */
    @Test
    public void openWindows(){
        driver.get(LOGIN_TO_ADMIN_URL);
        loginToAdmin("admin", "admin", false);
        driver.get(COUNTRIES_IN_ADMIN_URL);
        driver.findElement(ADD_COUNTRY_LOCATOR).click();
        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        List<WebElement> buttonsTOExternalWindow = driver.findElements(BUTTONS_TO_EXTERNAL_LINKS_LOCATOR);
        for(WebElement button : buttonsTOExternalWindow) {
            button.click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            assertEquals(2,driver.getWindowHandles().size());
            driver.close();
            driver.switchTo().window(originalWindow);
            assertEquals(1, driver.getWindowHandles().size());
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size()>0 ? handles.iterator().next():null;
        };
    }
}

