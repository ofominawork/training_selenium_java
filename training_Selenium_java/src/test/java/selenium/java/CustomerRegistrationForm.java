package selenium.java;

import org.openqa.selenium.By;

public interface CustomerRegistrationForm {
    By FIRST_NAME_LOCATOR = By.cssSelector("div#create-account input[name='firstname']");
    By LAST_NAME_LOCATOR = By.cssSelector("div#create-account input[name='lastname']");
    By ADDRESS1_LOCATOR = By.cssSelector("div#create-account input[name='address1']");
    By COUNTRY_LOCATOR = By.cssSelector("div#create-account span.select2-selection__rendered");
    By COUNTRY_SEARCH_LOCATOR = By.xpath(".//span[contains(@class,'select2-search')]//input[contains(@class,'select2-search')]");
    By CITY_LOCATOR = By.cssSelector("div#create-account input[name='city']");
    By POST_CODE_LOCATOR = By.cssSelector("div#create-account input[name='postcode']");
    By EMAIL_LOCATOR = By.cssSelector("div#create-account input[name='email']");
    By PHONE_LOCATOR = By.cssSelector("div#create-account input[name='phone']");
    By PASSWORD_LOCATOR = By.cssSelector("div#create-account input[name='password']");
    By PASSWORD_CONFIRM_LOCATOR = By.cssSelector("div#create-account input[name='confirmed_password']");
    By CREATE_ACCOUNT_BUTTON_LOCATOR = By.cssSelector("div#create-account button[name='create_account']");
    By EMAIL_NOT_UNIQUE_ERROR_LOCATOR = By.xpath(".//div[contains(@class,'notice errors')]");
    By ACCOUNT_MENU_LOCATOR = By.cssSelector("div#box-account li a");

    String EMAIL_NOT_UNIQUE_ERROR_TEXT = " The email address already exists in our customer database. Please login or select a different email address.";
}
