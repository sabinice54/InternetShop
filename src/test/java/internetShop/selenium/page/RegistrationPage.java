package internetShop.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://195.38.164.139:5174/registration");
    }

    public void register(String fullName, String email, String phone, String password, String confirmPassword) {
        driver.findElement(By.name("fullName")).sendKeys(fullName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phoneNumber")).sendKeys(phone);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public String getFullNameErrorMessage() {
        return driver.findElement(By.cssSelector("#fullName-helper-text")).getText();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(By.cssSelector("#email-helper-text")).getText();
    }

    public String getPhoneNumberErrorMessage() {
        return driver.findElement(By.cssSelector("#phoneNumber-helper-text")).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(By.cssSelector("#password-helper-text")).getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return driver.findElement(By.cssSelector("#confirmPassword-helper-text")).getText();
    }
}