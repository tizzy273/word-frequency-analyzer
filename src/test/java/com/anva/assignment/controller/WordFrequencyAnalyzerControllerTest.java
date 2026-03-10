package com.anva.assignment.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WordFrequencyAnalyzerControllerTest {

    @LocalServerPort
    int port;

    @Value("${application.basepath}")
    String basePath;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void highestFrequencyReturns3Test() {
        Map<String, Object> request = Map.of("text", "Hello hello world world world");

        Response response =
                RestAssured.given()
                        .contentType(JSON)
                        .accept(JSON)
                        .body(request)
                        .when()
                        .post(basePath + "/word-frequency-analyzer/highest-frequency");

        response.then()
                .statusCode(200)
                .contentType(JSON)
                .body(equalTo("3"));
    }

    @Test
    void frequencyForWordWorldReturns3Test() {
        Map<String, Object> request = Map.of("text", "Hello hello world world world", "word", "world");

        Response response =
                RestAssured.given()
                        .contentType(JSON)
                        .accept(JSON)
                        .body(request)
                        .when()
                        .post(basePath + "/word-frequency-analyzer/frequency-for-word");

        response.then()
                .statusCode(200)
                .contentType(JSON)
                .body(equalTo("3"));
    }

    @Test
    void mostFrequentWordsTop3ReturnsWorldHelloTestTest() {
        Map<String, Object> request = Map.of("text", "Hello hello world world world test test", "n", 3);

        Response response =
                RestAssured.given()
                        .contentType(JSON)
                        .accept(JSON)
                        .body(request)
                        .when()
                        .post(basePath + "/word-frequency-analyzer/most-frequent-words");

        response.then()
                .statusCode(200)
                .contentType(JSON)
                .body("", hasSize(3))
                .body("word", contains("world", "hello", "test"))
                .body("frequency", contains(3, 2, 2));
    }

    // --- Validation edge cases ---

    @Test
    void highestFrequencyReturns400WhenTextIsMissingTest() {
        RestAssured.given()
                .contentType(JSON)
                .accept(JSON)
                .body("{}")
                .when()
                .post(basePath + "/word-frequency-analyzer/highest-frequency")
                .then()
                .statusCode(400);
    }

    @Test
    void frequencyForWordReturns400WhenWordIsMissingTest() {
        Map<String, Object> request = Map.of("text", "hello world");

        RestAssured.given()
                .contentType(JSON)
                .accept(JSON)
                .body(request)
                .when()
                .post(basePath + "/word-frequency-analyzer/frequency-for-word")
                .then()
                .statusCode(400);
    }

    @Test
    void mostFrequentWordsReturns400WhenNIsNegativeTest() {
        Map<String, Object> request = Map.of("text", "hello world", "n", -1);

        RestAssured.given()
                .contentType(JSON)
                .accept(JSON)
                .body(request)
                .when()
                .post(basePath + "/word-frequency-analyzer/most-frequent-words")
                .then()
                .statusCode(400);
    }
}