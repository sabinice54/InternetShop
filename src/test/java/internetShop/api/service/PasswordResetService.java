package internetShop.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.endpoint.PasswordResetEndpoints;
import internetShop.api.model.PasswordResetChangeRequestModel;

public class PasswordResetService {

    public static Response requestPasswordReset(String email) {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .queryParam("email", email)
                .when()
                .post(PasswordResetEndpoints.RESET_PASSWORD_BY_EMAIL)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response resetPasswordByCode(int code, String newPassword) {
        PasswordResetChangeRequestModel request = new PasswordResetChangeRequestModel(newPassword);

        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .queryParam("passwordResetCode", code)
                .body(request)
                .when()
                .patch(PasswordResetEndpoints.RESET_PASSWORD_BY_EMAIL)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
