package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_PathMethod {

    /*
    Given accept type is JSON
    And path param id is 3
    When user sends GET request /api/spartans/{id}
    Then response status code should be 200
    And response content type is application/json
    And response payload values are
    id is 3
    name is "Fidole"
    gender is "Male"
    phone is 6105035231
     */

    @BeforeAll
    public static void init(){

        baseURI = "http://54.164.41.54:8000";
    }

    @Test
    public void pathMethod() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(3, id);
        assertEquals("Fidole", name);
        assertEquals("Male", gender);
        assertEquals(6105035231l, phone);


    }

    @Test
    public void getAllSpartans(){

        Response response = get("/api/spartans");


        //get me first spartan id
        System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));

        //get me second spartan name
        System.out.println("response.path(\"name[1]\") = " + response.path("name[1]"));

        //get me last spartan name
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

        //get me all spartan names
        List<String> allNames = response.path("name");
        System.out.println(allNames);

        //get me all spartan id
        List<Integer> allID = response.path("id");
        System.out.println(allID);

    }


}
