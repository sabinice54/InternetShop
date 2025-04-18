package internetShop.selenium.test;

import internetShop.api.config.Config;
import internetShop.selenium.page.RegistrationPage;
import internetShop.selenium.util.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends BaseTest {

    private static final String TEST_FULL_NAME = Config.getProperty("test.data.user.full_name");
    private static final String TEST_EMAIL = Config.getProperty("test.data.user.email");
    private static final String TEST_PHONE_NUMBER = Config.getProperty("test.data.user.phone_number.ui");
    private static final String TEST_PASSWORD = Config.getProperty("test.data.user.password");
    private static final String TEST_CONFIRM_PASSWORD = Config.getProperty("test.data.user.password");

    private RegistrationPage registrationPage;

    @BeforeEach
    public void initPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void testSuccessfulRegistration() {
        registrationPage.register(TEST_FULL_NAME, TEST_EMAIL, TEST_PHONE_NUMBER, TEST_PASSWORD, TEST_CONFIRM_PASSWORD);

        assertTrue(driver.findElement(By.xpath("//*[@id=\"app\"]/layout/div/main/section/div")).isEnabled(),
                "User is not registered");
    }

    @Test
    public void testRegistrationWithEmptyFullName() {
        registrationPage.register("", TEST_EMAIL, TEST_PHONE_NUMBER, TEST_PASSWORD, TEST_CONFIRM_PASSWORD);

        assertEquals("Объязательно для заполнения",
                registrationPage.getFullNameErrorMessage(),
                "Error message for empty full name is not displayed");
    }

    @Test
    public void testRegistrationWithEmptyEmail() {
        registrationPage.register(TEST_FULL_NAME, "", TEST_PHONE_NUMBER, TEST_PASSWORD, TEST_CONFIRM_PASSWORD);

        assertEquals("Объязательно для заполнения",
                registrationPage.getEmailErrorMessage(),
                "Error message for empty email is not displayed");
    }

    @Test
    public void testRegistrationWithEmptyPhoneNumber() {
        registrationPage.register(TEST_FULL_NAME, TEST_EMAIL, "", TEST_PASSWORD, TEST_CONFIRM_PASSWORD);

        assertEquals("Объязательно для заполнения",
                registrationPage.getPhoneNumberErrorMessage(),
                "Error message for empty phone number is not displayed");
    }

    @Test
    public void testRegistrationWithEmptyPassword() {
        registrationPage.register(TEST_FULL_NAME, TEST_EMAIL, TEST_PHONE_NUMBER, "", TEST_CONFIRM_PASSWORD);

        assertEquals("Объязательно для заполнения",
                registrationPage.getPasswordErrorMessage(),
                "Error message for empty password is not displayed");
    }

    @Test
    public void testRegistrationWithEmptyConfirmPassword() {
        registrationPage.register(TEST_FULL_NAME, TEST_EMAIL, TEST_PHONE_NUMBER, TEST_PASSWORD, "");

        assertEquals("Объязательно для заполнения",
                registrationPage.getConfirmPasswordErrorMessage(),
                "Error message for empty confirm password is not displayed");
    }

    @Test
    public void testRegistrationWithAllEmptyFields(){
        registrationPage.register("", "", "", "", "");

        assertEquals("Объязательно для заполнения", registrationPage.getFullNameErrorMessage());
        assertEquals("Объязательно для заполнения", registrationPage.getEmailErrorMessage());
        assertEquals("Объязательно для заполнения", registrationPage.getPhoneNumberErrorMessage());
        assertEquals("Объязательно для заполнения", registrationPage.getPasswordErrorMessage());
        assertEquals("Объязательно для заполнения", registrationPage.getConfirmPasswordErrorMessage());
    }

}
