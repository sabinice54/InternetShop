package internetShop.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.endpoint.UserEndpoints;
import internetShop.api.model.UserRequestModel;

public class UserService {

    public static Response updateUser(long id, UserRequestModel request) {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthService.getToken())
                .queryParam("id", id)
                .body(request)
                .when()
                .put(UserEndpoints.UPDATE_USER)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getAllUsers() {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthService.getToken())
                .when()
                .get(UserEndpoints.GET_ALL_USERS)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getUserById(long id) {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthService.getToken())
                .queryParam("id", id)
                .when()
                .get(UserEndpoints.GET_USER_BY_ID)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response deleteUserById(long id) {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .queryParam("id", id)
                .when()
                .delete(UserEndpoints.DELETE_USER_BY_ID)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static UserRequestModel buildUpdateUserRequest(String email) {
        return new UserRequestModel(
                "Updated Name",
                email,
                "UpdatedPass123",
                "+996701111111",
                150.0,
                "Updated Address"
        );
    }
}
