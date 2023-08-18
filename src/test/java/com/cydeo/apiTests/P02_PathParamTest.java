package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParamTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.164.41.54:8000";
    }

    @Test
    public void pathParamTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 3).
                when().get("api/spartans/{id}");



        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);


        Assertions.assertEquals("application/json", response.contentType());


        response.prettyPrint();
    }
}
