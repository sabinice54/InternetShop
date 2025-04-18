package internetShop.api.test;

import io.restassured.response.Response;
import internetShop.api.model.FeedbackRequestModel;
import internetShop.api.model.SignUpRequestModel;
import internetShop.api.service.AuthService;
import internetShop.api.service.FeedbackService;
import internetShop.api.service.OrderService;
import internetShop.api.service.ProductService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeedbackTest {

    private static Long productId;
    private static Long orderId;

    @BeforeAll
    public static void init() {
        Response productResponse = ProductService.createProduct();
        int productStatus = productResponse.statusCode();
        assertTrue(productStatus == 200 || productStatus == 201, "Expected 200 or 201 for product, got: " + productStatus);
        productId = productResponse.jsonPath().getLong("content.id");

        SignUpRequestModel signUpRequest = AuthService.buildSignUpRequest();
        Response signUpResponse = AuthService.signUpUser(signUpRequest);
        assertEquals(200, signUpResponse.statusCode());

        String userToken = AuthService.loginAsUser(signUpRequest.email(), signUpRequest.password());
        assertNotNull(userToken);
        FeedbackService.token = userToken;

        Response orderResponse = OrderService.createOrder(productId, userToken);
        int orderStatus = orderResponse.statusCode();
        assertTrue(orderStatus == 200 || orderStatus == 201, "Expected 200 or 201 for order, got: " + orderStatus);
        orderId = orderResponse.jsonPath().getLong("content.id");

        FeedbackService.orderId = orderId;
    }


    @Test
    @Order(1)
    public void testCreateFeedback() {
        FeedbackRequestModel feedback = new FeedbackRequestModel(5, "Great product!");
        FeedbackService.request = feedback;

        Response response = FeedbackService.createFeedback();
        int status = response.statusCode();
        assertTrue(status == 201 || status == 403, "Expected 201 or 403, got: " + status);

        if (status == 201) {
            FeedbackService.feedbackId = response.jsonPath().getLong("content.id");
            assertNotNull(FeedbackService.feedbackId);
        }
    }

    @Test
    @Order(2)
    public void testUpdateFeedback() {
        assumeTrue(FeedbackService.feedbackId != null, "feedbackId must be set from previous test");

        FeedbackService.request = new FeedbackRequestModel(4, "Updated review text.");
        Response response = FeedbackService.updateFeedback();

        int status = response.statusCode();
        if (status == 403) {
            System.out.println("Forbidden: cannot update feedback.");
        }

        assertTrue(status == 200 || status == 403, "Expected 200 or 403, got: " + status);
    }

    @Test
    @Order(3)
    public void testGetAllFeedback() {
        Response response = FeedbackService.getAllFeedback();
        int status = response.statusCode();
        assertEquals(200, status, "Expected 200 from GET /feedback/get-all");
    }

    @Test
    @Order(4)
    public void testGetFeedbackById() {
        assumeTrue(FeedbackService.feedbackId != null, "feedbackId must be set from createFeedback");

        Response response = FeedbackService.getFeedbackById();
        int status = response.statusCode();

        if (status == 403) {
            System.out.println("Forbidden: can't access feedback by ID");
        }

        assertTrue(status == 200 || status == 403, "Expected 200 or 403, got: " + status);
    }

    @Test
    @Order(5)
    public void testDeleteFeedbackById() {
        assumeTrue(FeedbackService.feedbackId != null, "feedbackId must be set from createFeedback");

        Response response = FeedbackService.deleteFeedbackById();
        int status = response.statusCode();

        if (status == 403) {
            System.out.println("Forbidden: can't delete feedback");
        }

        assertTrue(status == 200 || status == 403, "Expected 200 or 403, got: " + status);
    }
}
