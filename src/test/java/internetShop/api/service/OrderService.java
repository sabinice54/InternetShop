package internetShop.api.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.endpoint.OrderEndpoints;

public class OrderService {

    public static Response createOrder(Long productId, String token) {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .queryParam("productId", productId)
                .when()
                .post(OrderEndpoints.CREATE_ORDER)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getAllOrders(String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .when()
                .get(OrderEndpoints.GET_ALL_ORDERS);
    }

    public static Response cancelOrderById(Long orderId, String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .queryParam("orderId", orderId)
                .when()
                .put(OrderEndpoints.CANCEL_ORDER_BY_ID)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getOrderById(Long orderId, String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .accept("*/*")
                .queryParam("orderId", orderId)
                .when()
                .get(OrderEndpoints.GET_ORDER_BY_ID)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
