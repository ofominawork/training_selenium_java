package selenium.java.lesson4;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Ts7ClickMenuElements extends BaseSeleniumTest {

    /**
     * This method performs login and clicks each item of left menu on http://localhost/litecart/admin/
     */
    @Test
    public void clickMenuElements(){
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        login("admin", "admin", false);

        List<WebElement> parents=driver.findElements(By.id("app-"));;
        for (int i=0; i<parents.size(); i++){
            parents.get(i).click();
            parents=driver.findElements(By.id("app-"));
            List<WebElement> children=parents.get(i).findElements(By.tagName("li"));
            if (children.size()>0) {
                for (int k = 0; k < children.size(); k++) {
                    children.get(k).click();
                    parents = driver.findElements(By.id("app-"));
                    children = parents.get(i).findElements(By.tagName("li"));
                }
            }

            }


        }
}
