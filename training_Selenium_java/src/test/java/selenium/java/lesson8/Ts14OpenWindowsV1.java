package selenium.java.lesson8;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Ts14OpenWindowsV1 extends BaseSeleniumTest {
    private static final String LOGIN_TO_ADMIN_URL = "http://localhost/litecart/admin/login.php";
    private static final String COUNTRIES_IN_ADMIN_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";

    private static final By ADD_COUNTRY_LOCATOR = By.cssSelector("div#content-wrapper a.button");
    private static final By BUTTONS_TO_EXTERNAL_LINKS_LOCATOR = By.cssSelector(("i[class='fa fa-external-link']"));

    /**
     * This test asserts that each new link is opened in a new window by:
     * - checking that two windows are opened after click
     * - checking that handles of windows are different
     */
    @Test
    public void openWindows(){
        driver.get(LOGIN_TO_ADMIN_URL);
        loginToAdmin("admin", "admin", false);
        driver.get(COUNTRIES_IN_ADMIN_URL);
        driver.findElement(ADD_COUNTRY_LOCATOR).click();
        String[] openedWindows;
        List<WebElement> buttonsTOExternalWindow = driver.findElements(BUTTONS_TO_EXTERNAL_LINKS_LOCATOR);
        for(WebElement button : buttonsTOExternalWindow){
            button.click();
            wait.until(numberOfWindowsToBe(2) );
            openedWindows = driver.getWindowHandles().toArray(new String[0]);
            driver.switchTo().window(openedWindows[1]);
            assertEquals(2, driver.getWindowHandles().size());
            assertNotEquals(openedWindows[0],openedWindows[1]);
            driver.close();
            driver.switchTo().window(openedWindows[0]);
            assertEquals(1, driver.getWindowHandles().size());
        }
        }
}
