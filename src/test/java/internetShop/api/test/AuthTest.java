package internetShop.api.test;

import io.restassured.response.Response;
import internetShop.api.model.AuthApiResponse;
import internetShop.api.model.AuthRequestModel;
import internetShop.api.model.SignUpRequestModel;
import internetShop.api.service.AuthService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTest {

    @Test
    @Order(1)
    public void testLogin() {
        AuthRequestModel loginModel = new AuthRequestModel(
                "admin@gmail.com",
                "Admin123"
        );

        Response response = AuthService.signUpUser(new SignUpRequestModel(
                "Test User",
                "testuser" + System.currentTimeMillis() + "@mail.com",
                "TestPass123",
                "+996700123456",
                50.0,
                "Test Address"
        ));

        assertEquals(200, response.getStatusCode());
        AuthApiResponse apiResponse = response.as(AuthApiResponse.class);
        assertNotNull(apiResponse.getContent());
        assertNotNull(apiResponse.getContent().getToken());
    }


    @Test
    @Order(2)
    public void testSignUp() {
        SignUpRequestModel request = AuthService.buildSignUpRequest();

        Response response = AuthService.signUpUser(request);

        assertEquals(200, response.getStatusCode(), "Sign up should return 200 OK");

        String token = response.jsonPath().getString("content.token");
        assertNotNull(token, "Token should not be null in sign-up response");
    }

}
