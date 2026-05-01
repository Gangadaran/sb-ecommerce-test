package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {

    // Base URL (can override via -DbaseUrl=...)
    String baseUrl = System.getProperty("baseUrl", "http://3.7.60.195:8080");

    @BeforeEach
    void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void loginSuccessTest() {

        String requestBody = """
            {
              "username": "admin",
              "password": "adminPass"
            }
        """;

        Response response =
                RestAssured
                        .given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .log().all()
                        .when()
                        .post("/api/auth/signin")
                        .then()
                        .log().all()
                        .extract().response();

        // ✅ Status validation
        assertEquals(200, response.getStatusCode());

        // ✅ Correct field (FIXED)
        String token = response.jsonPath().getString("jwtToken");
        assertNotNull(token);

        // ✅ Additional validations (good practice)
        assertEquals("admin", response.jsonPath().getString("username"));
        assertTrue(response.jsonPath().getList("roles").contains("ROLE_ADMIN"));

        System.out.println("JWT Token: " + token);
    }

    @Test
    void loginFailureTest() {

        String requestBody = """
            {
              "username": "wrong",
              "password": "wrong"
            }
        """;

        Response response =
                RestAssured
                        .given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/api/auth/signin")
                        .then()
                        .extract().response();

        // ✅ Negative validation
        assertEquals(401, response.getStatusCode());
    }
}