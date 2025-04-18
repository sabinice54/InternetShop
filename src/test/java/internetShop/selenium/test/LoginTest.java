package internetShop.selenium.test;

import internetShop.api.config.Config;
import internetShop.selenium.page.LoginPage;
import internetShop.selenium.util.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    static final String BASE_EMAIL = Config.getProperty("test.base.admin.email");
    static final String BASE_PASSWORD = Config.getProperty("test.base.admin.password");

    private static final String INVALID_EMAIL = Config.getProperty("test.invalid.data.user.email");
    private static final String INVALID_PASSWORD = Config.getProperty("test.invalid.data.user.password");

    private LoginPage loginPage;

    @BeforeEach
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLoginAsAdmin() {
        loginPage.login(BASE_EMAIL, BASE_PASSWORD);

        assertTrue(driver.findElement(LoginPage.username).isEnabled(), "User is not authorized");
    }

    @Test
    public void testLoginWithInvalidEmail() {
        loginPage.login(INVALID_EMAIL, BASE_PASSWORD);

        assertEquals("User with email: " + INVALID_EMAIL + " not found", loginPage.getErrorMessage(),
                "Error message for incorrect email is not displayed");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        loginPage.login(BASE_EMAIL, INVALID_PASSWORD);

        assertEquals("Incorrect password", loginPage.getErrorMessage(),
                "Error message for incorrect password is not displayed");
    }
}
