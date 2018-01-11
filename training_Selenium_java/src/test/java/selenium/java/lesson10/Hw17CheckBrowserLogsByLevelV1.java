package selenium.java.lesson10;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Hw17CheckBrowserLogsByLevelV1 extends TestBase {
    private final By ADMIN_LOGIN_LOCATOR = By.cssSelector("[name='username']");
    private final By ADMIN_PASSWORD_LOCATOR = By.cssSelector("[name='password']");
    private final By TABLE_ROW_LOCATOR = By.cssSelector("tr.row a");
    private final String ADMIN_LOGIN_PAGE_URL = "http://localhost/litecart/admin/login.php";
    private final String CATEGORY_PAGE_URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";

    @Test
    public void checkFinestLevel(){
        checkBrowserLogs(Level.FINEST, Level.FINER);
    }

    @Test
    public void checkFinerLevel(){
        checkBrowserLogs(Level.FINER, Level.FINE);
    }

    @Test
    public void checkFineLevel(){
        checkBrowserLogs(Level.FINE, Level.CONFIG);
    }

    @Test
    public void checkConfigLevel(){
        checkBrowserLogs(Level.CONFIG, Level.INFO);
    }

    @Test
    public void checkInfoLevel(){
        checkBrowserLogs(Level.INFO, Level.WARNING);
    }

    @Test
    public void checkWarningLevel(){
        checkBrowserLogs(Level.WARNING, Level.SEVERE);
    }

    @Test
    public void checkSevereLevel(){
        checkBrowserLogs(Level.SEVERE, Level.SEVERE);
    }

    private void checkBrowserLogs(Level levelLow, Level levelHigh) {
        driver.get(ADMIN_LOGIN_PAGE_URL);
        loginToAdmin("admin", "admin", false);
        driver.get(CATEGORY_PAGE_URL);
        List<WebElement> rows = driver.findElements(TABLE_ROW_LOCATOR);
        int rowsSize = rows.size();
        for (int i = 1; i < rowsSize; i += 2) {
            if (rows.get(i).getAttribute("href").contains("category_id=1&product_id")) {
                rows.get(i).click();
                    assertEquals(0,numberOfEntries(levelLow, levelHigh));
                    driver.navigate().back();
                    wait.until(titleIs("Catalog | My Store"));
                    rows = driver.findElements(By.cssSelector("tr.row a"));
            }
        }
    }

    private int numberOfEntries(Level levelLow, Level levelHigh){
        List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();
        List<LogEntry> logsOfLevel = new ArrayList<>();
        for (LogEntry logEntry : logEntries) {
            if ((logEntry.getLevel().intValue() >= levelLow.intValue() &&
                    logEntry.getLevel().intValue() < levelHigh.intValue())
                    ||(logEntry.getLevel() == Level.SEVERE && levelLow == Level.SEVERE && levelHigh==Level.SEVERE)){
                logsOfLevel.add(logEntry);
                System.out.println(logEntry);
            }
        }
       return logsOfLevel.size();
    }

    private void fillTextField (By fieldLocator, String fieldValue){
        WebElement textField = driver.findElement(fieldLocator);
        textField.sendKeys(fieldValue);
    }

    private void loginToAdmin (String login, String password,boolean remember){
        fillTextField(ADMIN_LOGIN_LOCATOR, login);
        fillTextField(ADMIN_PASSWORD_LOCATOR, password);
        if (remember) {
            driver.findElement(By.name("remember_me")).click();
        }
            driver.findElement(By.name("login")).click();
            wait.until(titleIs("My Store"));
        }

    }
