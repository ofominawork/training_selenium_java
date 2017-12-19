package selenium.java.lesson5;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import selenium.java.BaseSeleniumTest;
import org.openqa.selenium.By;

import java.util.*;
import java.util.stream.Collectors;

public class Ts9CheckSorting extends BaseSeleniumTest {

    private static final String ALL_ZONES = "-- all zones --";

    /**
     * This method calls start() method of parent-class and performs login on http://localhost/litecart/admin/login.php
      */
    @Before
    public void start(){
        super.start();
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        login("admin", "admin", false);
    }

    /**
     * This method checks sorting of countries on http://localhost/litecart/admin/?app=countries&doc=countries
     */
    @Test
    public void checkCountriesSorting(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> tblRowCountries = findRowsInTableByLocator(By.className("dataTable"),By.cssSelector("[class='row']") );
        List<String> countryNames= findValuesInColumn("outerText", tblRowCountries, 2,4);
        checkSortingInColumn(countryNames);
    }

    /**
     * This method checks sorting of zones for countries on http://localhost/litecart/admin/?app=countries&doc=countries
     */
    @Test
    public void checkZonesForCountrySorting(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> trCountries = findRowsInTableByLocator(By.className("dataTable"),By.cssSelector("[class='row']") );
        List<String> linksToClick = findLinksForNonZeroValuesInColumn("outerText", trCountries,3);
        for (String link: linksToClick) {
            driver.navigate().to(link);
            List <WebElement> tblRowZones = findRowsInTableByLocator(By.id("table-zones"), By.cssSelector("tr"));
            tblRowZones.remove(tblRowZones.size() - 1);
            tblRowZones.remove(0);
            List<String> zoneNames = findValuesInColumn("outerText", tblRowZones,2,3);
            checkSortingInColumn(zoneNames);
        }

    }

    /**
     * This method checks sorting of zones for countries om http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
     */
    @Test
    public void checkGeoZonesSorting(){
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> tblRowZones=findRowsInTableByLocator(By.className("dataTable"), By.className("row"));
        List<String> linksToClick = findLinksForNonZeroValuesInColumn("outerText", tblRowZones, 2);
        for(String link: linksToClick){
            driver.navigate().to(link);
            List<WebElement> selectedOptions=driver.findElements(By.xpath(".//select[contains(@name,'zone_code')]//option[@selected]"));
            List<String> zoneValues=new ArrayList<>();
            for(WebElement selectedOption: selectedOptions){
                String zone = selectedOption.getAttribute("textContent").toLowerCase();
                if(!zone.equals(ALL_ZONES)) {
                    zoneValues.add(zone);
                }
            }
            checkSortingInColumn(zoneValues);
               }

    }


            /*Another variant of code which works long
            public void checkGeoZonesSorting(){
            driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            List<WebElement> tblRowZones=findRowsInTableByLocator(By.className("dataTable"), By.className("row"));
            List<String> linksToClick = findLinksForNonZeroValuesInColumn("outerText", tblRowZones, 2);
            for(String link: linksToClick){
                driver.navigate().to(link);
                List<WebElement> tblColumnZones=driver.findElements(By.xpath(".//select[contains(@name,'zone_code')]"));
                List<String> zonesValues=new ArrayList<>();
                for(WebElement tblColumnZone:tblColumnZones){
                    List <WebElement> selectorOptions=tblColumnZone.findElements(By.tagName("option"));
                    for(WebElement selectorOption: selectorOptions){
                        String selectedValue=selectorOption.getAttribute("selected");
                        if("true".equals(selectedValue)){
                            String textContent = selectorOption.getAttribute("textContent").toLowerCase();
                            if (!textContent.equals(ALL_ZONES)) {
                            zonesValues.add(textContent);
                        }
                    }

                }
                checkSortingInColumn(zonesValues);
            }

        }

    }
*/
    /**
     * This method finds non-zero elements in column of table
     * @param attribute to find column and its values
     * @param tblRows list of rows of table
     * @param tblColumnNumber number of table column
     * @return list of links of a column with non-zero values
     */
    private List<String> findLinksForNonZeroValuesInColumn(String attribute, List<WebElement> tblRows, int tblColumnNumber ){
        List<String> tblColumnValues=new ArrayList<>();
        for (WebElement tblRow : tblRows) {
            String tblRowValues[] = tblRow.getAttribute(attribute).trim().split("\t");
            if (Integer.valueOf(tblRowValues[tblColumnNumber]) > 0) {
                tblColumnValues.add(tblRow.findElement(By.tagName("a")).getAttribute("href"));
            }
        }
        return tblColumnValues;
    }
}


