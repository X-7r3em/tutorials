package com.example.unittesting.endpoints.junit5;

import com.example.unittesting.dtos.Odd;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * webEnvironment is needed for the RestAssured port to be set properly
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTest {
    /**
     * Gets the port used for this test
     */
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.reset();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void getOdd_givenRequest_willReturnOdd() {
        when().get("/odd")
                .then()
                .body("name", equalTo("Some name"))
                .body("amount", equalTo(12))
                .body("index", equalTo(4214));
    }

    @Test
    public void postOdd_givenRequest_willReturnOdd() throws JsonProcessingException {
        Odd actual = with()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new Odd("Odd", 1, 1))
                .when()
                .post("/odd")
                .then()
                // Testing individual fields
                .body("name", equalTo("Mock Odd"))
                .body("amount", equalTo(2))
                .body("index", equalTo(11))
                .extract()
                .as(Odd.class);
        
        // Testing whole object
        assertThat(actual, equalTo(new Odd("Mock Odd", 2,11)));
    }

    /**
     * Checking an exception caught and managed by a {@link org.springframework.web.bind.annotation.ControllerAdvice}.
     * We can test it using RestAssured.
     */
    @Test
    public void createException_givenRequest_willThrowIllegalArgumentException() {
        when().get("/exception")
                .then()
                .body("errorCode", equalTo(418))
                .body("message", equalTo("I guess I am a teapot"));
    }

    /**
     * This is the default Spring Exception and we can handle it using RestAssured
     */
    @Test
    public void createUnhandledException_givenRequest_willThrowNullPointerException() {
        when().get("/exception/unhandled")
                .then()
                .body("status", equalTo(500))
                .body("message", equalTo("This is the message of the Mock Exception"))
                .body("error", equalTo("Internal Server Error"))
                .body("path", equalTo("/exception/unhandled"));
    }
}
