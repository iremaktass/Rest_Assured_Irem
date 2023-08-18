package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {

    /*
     * Given content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    String url = "http://54.164.41.54:8000";

    @Test
    void spartanGetRequest() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)    //hey api please send me json response
                .when()
                .get(url + "/api/spartans");

        //print the response body
        //response.prettyPrint();

        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        Assertions.assertEquals("application/json", response.contentType());
    }


    @Test
    void test2() {


        /*
         * Given content type is application/json
         * When user sends GET request /api/spartans/3 endpoint
         * Then status code should be 200
         * And Content type should be application/json
         * And response body needs to contains Fidole
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.header("Content-Type"));

        Assertions.assertTrue(response.getBody().toString().contains("Fidole"));

    }

    @Test
    public void helloSpartan() {

        /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
         */

        Response response = RestAssured.when().get(url + "/api/hello");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta", response.getBody().toString());
        Assertions.assertTrue(response.body().toString().equals("Hello from Sparta"));


    }
}
