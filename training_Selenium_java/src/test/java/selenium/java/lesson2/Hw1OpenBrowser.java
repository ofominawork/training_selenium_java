package selenium.java.lesson2;

import org.junit.Test;

public class Hw1OpenBrowser extends TestBase {
    @Test
    public void openUrlInBrowser(){
        driver.navigate().to("http://localhost/litecart/admin/login.php");
    }
}
