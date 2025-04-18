package internetShop.api.test;

import io.restassured.response.Response;
import internetShop.api.model.SignUpRequestModel;
import internetShop.api.model.UserRequestModel;
import internetShop.api.model.UserResponseModel;
import internetShop.api.service.AuthService;
import internetShop.api.service.UserService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private static long createdUserId;
    private static SignUpRequestModel signUpRequest;

    @BeforeAll
    public static void setUp() {
        signUpRequest = AuthService.buildSignUpRequest();
        Response response = AuthService.signUpUser(signUpRequest);
        assertEquals(200, response.getStatusCode());

        UserResponseModel user = response.jsonPath().getObject("content", UserResponseModel.class);
        createdUserId = user.id;
        assertNotNull(createdUserId, "User ID must be extracted");
    }

    @Test
    @Order(1)
    public void testUpdateUser() {
        String newEmail = "updated_" + signUpRequest.email();

        UserRequestModel request = UserService.buildUpdateUserRequest(newEmail);

        Response updateResponse = UserService.updateUser(createdUserId, request);
        assertTrue(
                updateResponse.statusCode() == 200 || updateResponse.statusCode() == 204,
                "Expected 200 or 204 but got: " + updateResponse.statusCode()
        );

        Response getResponse = UserService.getUserById(createdUserId);
        UserResponseModel updatedUser = getResponse.jsonPath().getObject("content", UserResponseModel.class);

        assertEquals("Updated Name", updatedUser.fullName);
        assertEquals(newEmail, updatedUser.email);
        assertEquals("+996701111111", updatedUser.phoneNumber);
        assertEquals("Updated Address", updatedUser.address);
        assertEquals(150.0, updatedUser.balance);
    }

    @Test
    @Order(2)
    public void testGetAllUsers() {
        Response response = UserService.getAllUsers();

        assertTrue(response.statusCode() == 200 || response.statusCode() == 403);

        if (response.statusCode() == 200) {
            List<UserResponseModel> users = response.jsonPath().getList("content", UserResponseModel.class);
            assertNotNull(users);
            assertFalse(users.isEmpty(), "User list should not be empty");
        }
    }

    @Test
    @Order(3)
    public void testGetUserById() {
        Response response = UserService.getUserById(createdUserId);

        assertTrue(response.statusCode() == 200 || response.statusCode() == 403);

        if (response.statusCode() == 200) {
            UserResponseModel user = response.jsonPath().getObject("content", UserResponseModel.class);
            assertNotNull(user);
            assertEquals(createdUserId, user.id);
        }
    }

    @Test
    @Order(4)
    public void testDeleteUserById() {
        Response response = UserService.deleteUserById(createdUserId);

        assertTrue(
                response.statusCode() == 200 ||
                        response.statusCode() == 204 ,
                "Unexpected response code: " + response.statusCode()
        );
    }
}
