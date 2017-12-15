package selenium.java.lesson4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ts8CheckProductForOneSticker extends BaseSeleniumTest {

    /**
     * This method checks that every product on page http://localhost/litecart" has exactly one sticker
     * Number of stickers (new, sale or etc) = (0 or >=2) fails the test
     */
    @Test
    public void checkProductForOneSticker(){
        driver.navigate().to("http://localhost/litecart");
        List<WebElement> products= driver.findElements(By.className("image-wrapper"));
        for (WebElement product:products){
            assertEquals(1,product.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size());
        }
    }

}
