package internetShop.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import internetShop.api.endpoint.ProductEndpoints;
import internetShop.api.model.ProductRequestModel;

import java.io.File;

public class ProductService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Response createProduct() {
        ProductRequestModel request = new ProductRequestModel(
                "Heart Icon",
                "About Product SVG",
                99.99,
                5
        );

        File file = new File("src/test/resources/file/heart-handshake.png");
        if (!file.exists()) {
            throw new RuntimeException("File not found at: " + file.getPath());
        }

        String jsonProduct;
        try {
            jsonProduct = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize product request", e);
        }

        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .contentType("multipart/form-data")
                .multiPart("file", file)
                .queryParam("productRequest", jsonProduct)
                .when()
                .post(ProductEndpoints.CREATE_PRODUCT)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getProductImage(String fileName) {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .pathParam("fileName", fileName)
                .when()
                .get(ProductEndpoints.GET_IMAGE)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getProductById(Long id) {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .queryParam("id", id)
                .when()
                .get(ProductEndpoints.GET_BY_ID)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response getAllProducts() {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .when()
                .get(ProductEndpoints.GET_ALL)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response deleteProduct(Long id) {
        return RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + AuthService.getToken())
                .queryParam("id", id)
                .when()
                .delete(ProductEndpoints.DELETE_PRODUCT)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
