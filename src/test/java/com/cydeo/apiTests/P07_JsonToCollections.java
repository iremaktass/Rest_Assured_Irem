package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

public class P07_JsonToCollections extends SpartanTestBase {

     /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */


    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 3)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //FIRST WAY --> RESPONSE.AS() --->MAP
        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);

        assertEquals(3, spartanMap.get("id"));
        assertEquals("Fidole", spartanMap.get("name"));
        
        // SECOND WAY JSONPATH.GETMAP --->MAP

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> spMap = jsonPath.getMap("");

        assertEquals(3, spMap.get("id"));
        assertEquals("Fidole", spMap.get("name"));
    }


    @Test
    public void jasonToList() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/")
                .then()
                .statusCode(200)
                .extract().response();

        // FIRST WAY ---> RESPONSE.AS(List.class)

         List<Map<String,Object>>  spartanListAsMap = response.as(List.class);

        for (Map<String, Object> eachSpartan : spartanListAsMap) {

            System.out.println(eachSpartan);
        }


        // SECOND WAY --->JSONPATH.GETLIST("")

        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> spListAsMap = jsonPath.getList("");

        for (Map<String, Object> eachSpartan : spListAsMap) {

            System.out.println(eachSpartan);
        }

    }


    @Test
    public void partialJsonMap(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/")
                .then()
                .statusCode(200)
                .extract().response();

        //RESPONSE AS  ---> it will not allow to put GPATH SYNTAX to retrieve partial JSON OBJECT
        //JASONPATH ---> Will allow us to put GPATH SYNTAX to retrieve partial JSON OBJECT

        JsonPath jsonPath = response.jsonPath();

        Map<String, Object> firstSpartanAsMap = jsonPath.getMap("[0]");
        System.out.println(firstSpartanAsMap);


    }






    }
