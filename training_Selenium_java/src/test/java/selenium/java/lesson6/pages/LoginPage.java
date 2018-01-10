package selenium.java.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="username")
    private WebElement userNameField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(name="login")
    private WebElement loginButton;

    public LoginPage open() {
        driver.get("http://localhost/litecart/admin/login.php");
        return this;
    }

    public LoginPage enterUsername(String username) {
        userNameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public void submitLogin() {
        loginButton.click();
        wait.until(presenceOfElementLocated(By.id("box-apps-menu")));
    }
}
