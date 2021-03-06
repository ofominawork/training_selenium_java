package selenium.java.lesson4;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class Ts7CheckHeadingIsOnPage extends BaseSeleniumTest {

    /**
     * This method opens http://localhost/litecart/admin/login.php
     */
    @Before
    public void start(){
        super.start();
        driver.navigate().to("http://localhost/litecart/admin/login.php");
    }

    /**
     * This test clicks each item of left menu on http://localhost/litecart/admin/
     * and checks if heading (h1 tag) is present on the page
     */
    @Test
    public void checkHeadingIsOnPage(){
        loginToAdmin("admin", "admin", false);
        List<WebElement> parents=driver.findElements(By.id("app-"));;
        for (int i=0; i<parents.size(); i++){
            parents.get(i).click();
            assertEquals(1, driver.findElements(By.tagName("h1")).size());
            parents=driver.findElements(By.id("app-"));
            List<WebElement> children=parents.get(i).findElements(By.tagName("li"));
            if (children.size()>0) {
                for (int k = 0; k < children.size(); k++) {
                    children.get(k).click();
                    assertEquals(1, driver.findElements(By.tagName("h1")).size());
                    parents = driver.findElements(By.id("app-"));
                    children = parents.get(i).findElements(By.tagName("li"));
                }
            }
            }

        }

}
