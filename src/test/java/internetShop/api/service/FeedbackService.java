package internetShop.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.endpoint.FeedbackEndpoints;
import internetShop.api.model.FeedbackRequestModel;

public class FeedbackService {

    public static Long orderId;
    public static Long feedbackId;
    public static String token;
    public static FeedbackRequestModel request;

    public static Response createFeedback() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .accept("*/*")
                .queryParam("orderId", orderId)
                .body(request)
                .when()
                .post(FeedbackEndpoints.CREATE_FEEDBACK)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response updateFeedback() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .accept("*/*")
                .queryParam("feedbackId", feedbackId)
                .body(request)
                .when()
                .put(FeedbackEndpoints.UPDATE_FEEDBACK)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getAllFeedback() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .when()
                .get(FeedbackEndpoints.GET_ALL_FEEDBACK)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getFeedbackById() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .queryParam("id", feedbackId)
                .when()
                .get(FeedbackEndpoints.GET_BY_ID_FEEDBACK)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response deleteFeedbackById() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .queryParam("feedbackId", feedbackId)
                .when()
                .delete(FeedbackEndpoints.DELETE_BY_ID_FEEDBACK)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
