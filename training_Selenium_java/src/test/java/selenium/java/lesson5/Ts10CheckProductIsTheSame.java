package selenium.java.lesson5;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Ts10CheckProductIsTheSame extends BaseSeleniumTest {
    private static final String COLOR_SPLITTER = "[A-Za-z ,()]+";
    private static final String SIZE_SPLITTER = "[A-Za-z]+";
    private Product productMainPage;
    private Product productInfoPage;

    /**
     * This method calls start() method of parent-class and creates two corresponding objects of type Product
     * for main page and product page
     */
    @Before
    public void start() {
        super.start();
        String urlMainPage = "http://localhost/litecart/en/";
        String nameMainPageLocator = ".//div[contains(@id,'box-campaigns')]//div[contains(@class,'name')]";
        String priceCampaignMainPageLocator = ".//div[contains(@id,'box-campaigns')]//strong[contains(@class,'campaign-price')]";
        String priceRegularMainPageLocator=".//div[contains(@id,'box-campaigns')]//s[contains(@class,'regular-price')]";
        productMainPage = new Product(urlMainPage, nameMainPageLocator,priceCampaignMainPageLocator,priceRegularMainPageLocator);

        String urlInfoPage = driver.findElement(By.xpath(".//div[contains(@id,'box-campaigns')]//a")).getAttribute("href");
        String nameInfoPageLocator = ".//div[contains(@id,'box-product')]//h1";
        String priceCampaignInfoPageLocator=".//div[contains(@class,'content')]//strong[contains(@class,'campaign-price')]";
        String priceRegularInfoPageLocator=".//div[contains(@class,'content')]//s[contains(@class,'regular-price')]";
        productInfoPage = new Product(urlInfoPage, nameInfoPageLocator, priceCampaignInfoPageLocator, priceRegularInfoPageLocator);
    }

    /**
     * This test asserts that the names of products on main page and product page are the same
     */
    @Test
    public void compareNames(){
        assertEquals("Compare names of products", productMainPage.productName, productInfoPage.productName);
    }

    /**
     * This test asserts that campaign prices of product on main and product pages are the same
     */
    @Test
    public void compareCampaignPriceValues(){
        assertEquals(productMainPage.priceCampaign.value, productInfoPage.priceCampaign.value);
    }

    /**
     * This test asserts that regular prices of product on the main and product pages are the same
     */
    @Test
    public void compareRegularPriceValues(){
        assertEquals(productMainPage.priceRegular.value, productInfoPage.priceRegular.value);
    }

    /**
     * This test asserts that regular prices of product on the main and product pages are both strike (s-tag)
     */
    @Test
    public void checkRegularPriceIsStrike(){
        assertEquals("s",productMainPage.priceRegular.style);
        assertEquals(productMainPage.priceRegular.style,productInfoPage.priceRegular.style);
    }

    /**
     * This test asserts that campaign prices of product on the main and product pages are both bold (strong-tag)
     */
    @Test
    public void checkCampaignPriceIsStrong(){
        assertEquals(productMainPage.priceCampaign.style,productInfoPage.priceCampaign.style);
        assertEquals("strong",productMainPage.priceCampaign.style );
    }

    /**
     * This test calls checkPriceIsRed method to assert that campaign prices of product on main and product pages
     * have red color of font
     */
    @Test
    public void checkCampaignPriceIsRed(){
        checkPriceIsRed(productMainPage.priceCampaign.color);
        checkPriceIsRed(productInfoPage.priceCampaign.color);
    }

    /**
     * This test calls checkPriceIsGrey to assert that regular prices of product on main and product pages
     * have grey color of font
     */
    @Test
    public void checkRegularPriceIsGrey(){
        checkPriceIsGrey(productMainPage.priceRegular.color);
        checkPriceIsGrey(productInfoPage.priceRegular.color);
    }

    /**
     * This test asserts that the size of campaign price is greater than the size of regular price on the main and
     * product pages
     */
    @Test
    public void compareSizeOfCampaignRegular(){
        assertTrue(productMainPage.priceCampaign.size>productMainPage.priceRegular.size);
        assertTrue(productInfoPage.priceCampaign.size>productInfoPage.priceRegular.size);
    }

    /**
     * This method checks if the RGB-color of price of product is grey: R-value = G-value = B-value
     * @param productColor color of the product font  in RGB
     */
    private void checkPriceIsGrey(String productColor){
        String[] colors = productColor.split(COLOR_SPLITTER);
        assertTrue(colors[1].equals(colors[2]) && colors[2].equals(colors[3]));
    }

    /**
     * This method checks if the RGB-color of price of product is red: R-value >0, G-value=B-value=0
     * @param productColor color of the product font in RGB
     */
    private void checkPriceIsRed(String productColor){
        String[] colors = productColor.split(COLOR_SPLITTER);
        assertTrue(Integer.valueOf(colors[1])>0);
        assertTrue("0".equals(colors[2]) && "0".equals(colors[3]));
    }

    /**
     * This class is designed to describe product of the page
     */
    class Product{
        final String productName;
        final Price priceCampaign;
        final Price priceRegular;
        final private String url;

        Product(String url, String nameLocator, String priceCampaignLocator, String priceRegularLocator ) {
            this.url = url;
            driver.navigate().to(url);
            productName=driver.findElement(By.xpath(nameLocator)).getText();
            priceCampaign=new Price(priceCampaignLocator);
            priceRegular=new Price(priceRegularLocator);
        }

        /**
         * This class is designed to describe price of product on the page
         */
        class Price{
            final String value;
            final String color;
            final String style;
            final Float size;

            Price(String priceLocator){
                String[] sizePrice;
                WebElement priceElement = driver.findElement(By.xpath(priceLocator));
                value = priceElement.getText();
                color = priceElement.getCssValue("color");
                style = priceElement.getTagName();
                size = Float.valueOf(((priceElement.getCssValue("font-size")).split(SIZE_SPLITTER))[0]);
            }
        }
    }


}
