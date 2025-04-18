package internetShop.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public static final By emailField = By.name("email");
    public static final By passwordField = By.name("password");
    public static final By submitButton = By.xpath("//button[@type='submit']");
    public static final By errorMessage = By.cssSelector(".error-message");
    public static final By username = By.xpath("//div[@class='user-name']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://195.38.164.139:5174/login");
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}

