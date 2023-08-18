package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartanWithParameters extends SpartanTestBase {


      /*   Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */

    @DisplayName("GET Spartan /api/spartans{id} path param")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 24)
                .when()
                .get("/api/spartans/{id}");


        assertEquals(200, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        assertTrue(response.body().asString().contains("Julio"));
    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */

    @DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(404, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void task3() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.getStatusCode());
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        assertTrue(response.body().asString().contains("Female"));

        assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void task4() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.getStatusCode());
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        assertTrue(response.body().asString().contains("Female"));

        assertTrue(response.body().asString().contains("Janette"));

    }
}
