package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {

    String spartanBaseURL = "http://54.164.41.54:8000";

    @Test
    public void getAllSpartan(){

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        Assertions.assertEquals(200, statusCode);



        String contentType = response.contentType();
        System.out.println(contentType);

        Assertions.assertEquals("application/json", contentType);



        //PRINT BODY
        System.out.println(response.asString());

        //OR
        System.out.println(response.prettyPrint());


    }
}
