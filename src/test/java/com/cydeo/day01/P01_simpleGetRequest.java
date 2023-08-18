package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url = "http://54.164.41.54:8000/api/spartans";


    @Test
    public void simpleTestRequest(){

        Response response = RestAssured.get(url);

        System.out.println(response.statusCode());
        System.out.println(response.getStatusCode());   // same with statusCode

        //verify that status code is 200
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        //how ro print json respond body on console
        response.prettyPrint();
    }

}
