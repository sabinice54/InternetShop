package internetShop.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.config.Config;
import internetShop.api.endpoint.AuthEndpoints;
import internetShop.api.model.AuthApiResponse;
import internetShop.api.model.AuthRequestModel;
import internetShop.api.model.AuthResponseModel;
import internetShop.api.model.SignUpRequestModel;
import kg.nurtelecom.api.model.*;

import static internetShop.api.endpoint.AuthEndpoints.LOGIN;

public class AuthService {

    private static final String BASE_EMAIL = Config.getProperty("test.base.user.email");
    private static final String BASE_PASSWORD = Config.getProperty("test.base.user.password");

    private static String token;

    public static String getToken() {
        if (token == null || !isTokenValid(token)) {
            token = generateToken();
        }
        return token;
    }

    private static boolean isTokenValid(String token) {
        Response response = RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .get(Config.getProperty("api.base_url") + "/api/projects")
                .then()
                .extract()
                .response();

        return response.getStatusCode() == 200 && response.getBody() != null;
    }

    public static String generateToken() {
        AuthRequestModel request = new AuthRequestModel(BASE_EMAIL, BASE_PASSWORD);

        Response response = RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .when()
                .put(LOGIN)
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .response();

        AuthApiResponse apiResponse = response.as(AuthApiResponse.class);
        AuthResponseModel authResponse = apiResponse.getContent();

        if (authResponse == null || authResponse.getToken() == null) {
            throw new RuntimeException("Authentication failed: token not found in response");
        }

        return authResponse.getToken();
    }

    public static Response signUpUser(SignUpRequestModel request) {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body(request)
                .when()
                .post(AuthEndpoints.SIGN_UP)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static SignUpRequestModel buildSignUpRequest() {
        String email = "sabi" + System.currentTimeMillis() + "@test.com";
        return new SignUpRequestModel(
                "Sabina",
                email,
                "Test123",
                "+996700000000",
                100.0,
                "Bishkek"
        );
    }

    public static String loginAsUser(String email, String password) {
        AuthRequestModel request = new AuthRequestModel(email, password);

        Response response = RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .when()
                .put(LOGIN)
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .response();

        AuthApiResponse apiResponse = response.as(AuthApiResponse.class);
        AuthResponseModel authResponse = apiResponse.getContent();

        if (authResponse == null || authResponse.getToken() == null) {
            throw new RuntimeException("User login failed: token not found");
        }

        return authResponse.getToken();
    }
}