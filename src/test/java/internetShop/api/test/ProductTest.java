package internetShop.api.test;

import io.restassured.response.Response;
import internetShop.api.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    private static final Path FILE_NAME_PATH = Path.of("target/uploaded_filename.txt");

    @Test
    @Order(1)
    public void testCreateProduct() throws IOException {

        Response response = ProductService.createProduct();
        assertTrue(response.statusCode() >= 200 && response.statusCode() < 300, "Unexpected response code");

        String imagePath = response.jsonPath().getString("content.imagePath");
        assertNotNull(imagePath, "imagePath must not be null");

        String fileName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
        assertTrue(fileName.endsWith(".png"), "Extracted fileName must be a PNG");

        Files.writeString(FILE_NAME_PATH, fileName);
    }

    @Test
    @Order(2)
    public void testGetProductImage() throws IOException {
        assertTrue(Files.exists(FILE_NAME_PATH), "Uploaded filename file must exist");

        String fileName = Files.readString(FILE_NAME_PATH).trim();
        assertNotNull(fileName, "File name should not be null or empty");
        assertFalse(fileName.isEmpty(), "File name should not be empty");

        Response response = ProductService.getProductImage(fileName);

        assertEquals(200, response.statusCode(), "Image should be retrieved successfully");
        assertNotNull(response.getBody().asByteArray(), "Response body should not be null");
    }

    @Test
    @Order(3)
    public void testGetProductById() throws IOException {
        Response createResponse = ProductService.createProduct();
        assertEquals(200, createResponse.statusCode());

        Long productId = createResponse.jsonPath().getLong("content.id");
        assertNotNull(productId, "Product ID should not be null");

        Response getByIdResponse = ProductService.getProductById(productId);

        assertEquals(200, getByIdResponse.statusCode(), "Product by ID should be retrieved");
        assertNotNull(getByIdResponse.jsonPath().get("content"), "Product content should not be null");
    }

    @Test
    @Order(4)
    public void testGetAllProducts() {
        Response response = ProductService.getAllProducts();

        assertEquals(200, response.statusCode(), "All products should be retrieved successfully");
        assertNotNull(response.jsonPath().getList("content"), "Product list should not be null");
    }

    @Test
    @Order(5)
    public void testDeleteProduct() {
        Response createResponse = ProductService.createProduct();
        assertEquals(200, createResponse.statusCode());

        Long productId = createResponse.jsonPath().getLong("content.id");
        assertNotNull(productId, "Product ID should not be null");

        Response deleteResponse = ProductService.deleteProduct(productId);
        assertEquals(200, deleteResponse.statusCode(), "Product should be deleted successfully");
    }
}
