package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P05_JsonPath {

     /*
    Given accept type is JSON
    And path param id is 3
    When user sends GET request x
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
    void getSingleSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when()
                .get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());


        //create jason path object

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        assertEquals(3, id);
        assertEquals("Fidole", name);
        assertEquals("Male", gender);
        assertEquals(6105035231l, phone);


    }

    @Test
    void getAllSpartans() {

        Response response = get("api/spartans");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //get first spartan id
        System.out.println("jsonPath.getInt(\"id[0]\") = " + jsonPath.getInt("id[0]"));

        //get ne second spartan name
        System.out.println("jsonPath.getString(\"name[0]\") = " + jsonPath.getString("name[1]"));

        //get me last spartan name
        System.out.println("jsonPath.getString(\"name[-1]\") = " + jsonPath.getString("name[-1]"));

        //get me all spartan names
        List<String> allNames = jsonPath.getList("name");
        System.out.println(allNames);

        //get me all spartan ids
        List<Object> allId = jsonPath.getList("id");
        System.out.println(allId);


    }
}
