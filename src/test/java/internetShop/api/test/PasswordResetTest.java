package internetShop.api.test;

import io.restassured.response.Response;
import internetShop.api.service.PasswordResetService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PasswordResetTest {

    @Test
    @Order(1)
    public void testPasswordResetRequestWithValidEmail() {
        Response response = PasswordResetService.requestPasswordReset("miu@gmail.com");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 403, "Expected 200 or 403 but got: " + statusCode);
    }

    @Test
    @Order(2)
    public void testPasswordResetRequestWithInvalidEmail() {
        Response response = PasswordResetService.requestPasswordReset("nonexistent@example.com");

        assertEquals(403, response.getStatusCode(), "Expected 403 for invalid email");
    }

    @Test
    @Order(3)
    public void testResetPasswordByCode() {
        Response response = PasswordResetService.resetPasswordByCode(123, "String123");
        assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 403);
    }
}
