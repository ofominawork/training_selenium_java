package selenium.java;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * This class includes common methods for tests.
 */
public class BaseSeleniumTest {
    protected WebDriver driver;
    private WebDriverWait wait;

    /**
     * This method starts Google Chrome browser.
     */
    @Before
    public void start(){
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver, 10);
    }

    /**
     * This method stops Google Chrome browser.
     */
    @After
    public void stop(){
        driver.quit();
        driver=null;
    }

    /**
     * This method finds text field by name and sets the provided value.
     * @param fieldName name of the text field.
     * @param fieldValue input value of the text field.
     */
    private void fillTextField(String fieldName, String fieldValue){
        WebElement textField = driver.findElement(By.name(fieldName));
        textField.sendKeys(fieldValue);
    }

    /**
     * This method performs login with the given login, password and "Remember me" option
     * @param login user login value
     * @param password user password value
     * @param remember value of "Remember me" option
     */
    protected void login(String login, String password, boolean remember){
        fillTextField("username", login);
        fillTextField("password",password);
        if (remember) {
            driver.findElement(By.name("remember_me")).click();
        }
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    /**
     * This method finds rows in table on a page
     * @param tblLocator table locator on a page
     * @param tblRowLocator row locator in table
     * @return list of rows of table
     */
    protected List<WebElement> findRowsInTableByLocator(By tblLocator, By tblRowLocator){
        WebElement tbl = driver.findElement(tblLocator);
        return tbl.findElements(tblRowLocator);
    }

    /**
     * This method checks sorting of elements in a column of table
     * @param columnValues list of values in a column of table
     */
    protected void checkSortingInColumn(List<String> columnValues){
        List<String> unsortedColumnValues=new ArrayList<>(columnValues);
        Collections.sort(columnValues);
        assertEquals(columnValues, unsortedColumnValues);
    }

    /**
     * This method finds values in column of table
     * @param attribute parameter to search a column of table
     * @param tblRows list of rows in table
     * @param tblColumnNumber number of column in table
     * @param tblNumberOfColumns total number of cilumns in table
     * @return list of values of column
     */
    protected List<String> findValuesInColumn(String attribute, List<WebElement> tblRows, int tblColumnNumber, int tblNumberOfColumns){
        List<String> tblColumnValues=new ArrayList<>();
        for (WebElement tblRow : tblRows) {
            String tblRowValues[] = tblRow.getAttribute(attribute).trim().split("\t");
            if(tblRowValues.length<tblNumberOfColumns){
                tblColumnValues.add("");
            }
            else {
                tblColumnValues.add(tblRowValues[tblColumnNumber].toLowerCase());
            }
        }
        return tblColumnValues;
    }

}
