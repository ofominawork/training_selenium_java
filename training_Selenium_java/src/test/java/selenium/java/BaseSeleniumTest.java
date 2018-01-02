package selenium.java;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * This class includes common methods for tests.
 */
public class BaseSeleniumTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

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
     * @param fieldLocator search parameter to find the field on the page.
     * @param fieldValue input value of the text field.
     */

    private void fillTextField(By fieldLocator, String fieldValue){
        WebElement textField = driver.findElement(fieldLocator);
        textField.sendKeys(fieldValue);
    }
    /**
     * This method performs login to admin with the given login, password and "Remember me" option
     * @param login user login value
     * @param password user password value
     * @param remember value of "Remember me" option
     */
    protected void loginToAdmin(String login, String password, boolean remember){
        fillTextField(AdminLoginForm.ADMIN_LOGIN_LOCATOR, login);
        fillTextField(AdminLoginForm.ADMIN_PASSWORD_LOCATOR,password);
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

    /**
     * This class is designed to describe customer's account in the store at http://localhost/litecart/en/
     */
    public class Account {
        String taxId;
        String company;
        String firstName;
        String lastName;
        String address1;
        String address2;
        String postcode;
        String city;
        String country;
        String zoneStateProvince;
        String email;
        String emailName;
        String emailDomain;
        String phone;
        String newsLetter;
        String password;
        String confirmPassword;

        public Account(String firstName, String lastName, String address1, String postcode, String city, String country, String zoneStateProvince, String emailName, String emailDomain, String phone, String password, String confirmPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address1 = address1;
            this.postcode = postcode;
            this.city = city;
            this.country = country;
            this.zoneStateProvince = zoneStateProvince;
            this.emailName = emailName;
            this.emailDomain = emailDomain;
            this.email = generateUnigueEmail(emailName, emailDomain);
            this.phone = phone;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

        /**
         * This method generates unique email for customer registration in the store
         * @param emailName the part of email which goes before @
         * @param emailDomain the part of email which goes after @
         * @return unique generated customer email
         */
        private String generateUnigueEmail(String emailName, String emailDomain) {
            final Random random = new Random();
            int unique = random.nextInt(1000000) + 1;
            email = emailName + String.valueOf(unique) + emailDomain;
            return email;
        }

        /**
         * This method fills the required fields in customer's registration form
         */
        public void fillRegistrationRequiredFields() {
            fillTextField(CustomerRegistrationForm.FIRST_NAME_LOCATOR, firstName);
            fillTextField(CustomerRegistrationForm.LAST_NAME_LOCATOR, lastName);
            fillTextField(CustomerRegistrationForm.ADDRESS1_LOCATOR, address1);
            driver.findElement(CustomerRegistrationForm.COUNTRY_LOCATOR).click();
            driver.findElement(CustomerRegistrationForm.COUNTRY_SEARCH_LOCATOR).sendKeys(country + Keys.ENTER);
            fillTextField(CustomerRegistrationForm.CITY_LOCATOR, city);
            fillTextField(CustomerRegistrationForm.POST_CODE_LOCATOR, postcode);
            WebElement zoneCode = driver.findElement(By.cssSelector("div#create-account select[name='zone_code']"));
            zoneCode.click();
            zoneCode.sendKeys(zoneStateProvince + Keys.ENTER);
            fillTextField(CustomerRegistrationForm.EMAIL_LOCATOR, email);
            fillTextField(CustomerRegistrationForm.PHONE_LOCATOR, phone);
            fillTextField(CustomerRegistrationForm.PASSWORD_LOCATOR, password);
            fillTextField(CustomerRegistrationForm.PASSWORD_CONFIRM_LOCATOR, confirmPassword);
        }

        /**
         * This method registers customer in the store.
         * If generated email is not unique the method calls generateUnigueEmail() to generate unique
         * This method should be called after fillRegistrationRequiredFields() method (registration form is filled in
         * with required parameters.
         */
        public void registerUserWithRequiredFields() {
            driver.findElement(CustomerRegistrationForm.CREATE_ACCOUNT_BUTTON_LOCATOR).click();
            while ((driver.findElements(CustomerRegistrationForm.EMAIL_NOT_UNIQUE_ERROR_LOCATOR)).size() == 1) {
                if ((driver.findElement(CustomerRegistrationForm.EMAIL_NOT_UNIQUE_ERROR_LOCATOR)).getAttribute("textContent").equals(CustomerRegistrationForm.EMAIL_NOT_UNIQUE_ERROR_TEXT)) {
                    System.out.println("hello");
                    driver.findElement(CustomerRegistrationForm.EMAIL_LOCATOR).clear();
                    fillTextField(CustomerRegistrationForm.EMAIL_LOCATOR, generateUnigueEmail(emailName, emailDomain));
                    fillTextField(CustomerRegistrationForm.PASSWORD_LOCATOR, password);
                    fillTextField(CustomerRegistrationForm.PASSWORD_CONFIRM_LOCATOR, confirmPassword);
                    driver.findElement(CustomerRegistrationForm.CREATE_ACCOUNT_BUTTON_LOCATOR).click();
                }
            }
            wait.until(titleIs("Online Store | My Store"));
        }

        /**
         * This method performs customer's login to store
         */
        public void login(){
            fillTextField(CustomerLoginForm.CUSTOMER_EMAIL_LOCATOR, email);
            fillTextField(CustomerLoginForm.CUSTOMER_PASSWORD_LOCATOR, "password");
            driver.findElement(CustomerLoginForm.CUSTOMER_LOGIN_BUTTON_LOCATOR).click();
            wait.until(titleIs("Online Store | My Store"));
        }

        /**
         * This method performs customet's logout from the store homepage (http://localhost/litecart/en/)
         */
        public void logout() {
            List<WebElement> accountMenu = driver.findElements(CustomerRegistrationForm.ACCOUNT_MENU_LOCATOR);
            for (WebElement item : accountMenu) {
                if (item.getText().equals("Logout")) {
                    item.click();
                    wait.until(titleIs("Online Store | My Store"));
                }
            }
        }
    }

}