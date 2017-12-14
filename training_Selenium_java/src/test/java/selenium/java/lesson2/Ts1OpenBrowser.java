package selenium.java.lesson2;

import org.junit.Test;
import selenium.java.BaseSeleniumTest;

public class Ts1OpenBrowser extends BaseSeleniumTest {

    @Test
    public void openUrlInBrowser(){
        driver.navigate().to("http://localhost/litecart/admin/login.php");
    }

}
