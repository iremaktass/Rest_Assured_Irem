package com.cydeo.Homeworks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Homework01 {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.164.41.54:1000/ords/hr";
    }

    @Test
    public void task1(){

        /*
        - Given accept type is Json
        - When users sends request to /countries/US
        - Then status code is 200
        - And Content - Type is application/json
        - And response contains United States of America
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.getBody().asString().contains("United States of America"));
        assertTrue(response.path("country_name").toString().contains("United States of America"));
        assertTrue(response.asPrettyString().contains("United States of America"));
    }

    @Test
    public void task2(){

        /*
        Given accept type is Json
        - When users sends request to /employees/1
        - Then status code is 404
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        assertEquals(404, response.statusCode());
    }

    @Test
    public void task3(){

        /*
        Given accept type is Json
        - When users sends request to /regions/1
        - Then status code is 200
        - And Content - Type is application/json
        - And response contains Europe
        - And header should contains Date
        - And Transfer-Encoding should be chunked
         */
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions/1");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Europe"));
        assertTrue(response.path("region_name").toString().contains("Europe"));

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals("chunked",response.header("Transfer-Encoding"));

        //third link rel is "describedby"

        assertEquals("describedby", response.path("links[2].rel"));

        //get link details

        List<Map<String, Object>> links = response.path("links");

        for (Map<String, Object> eachLink : links) {
            System.out.println(eachLink);
        }

        //get me last object of llinks rel information

        String lastRel = response.path("links[-1].rel");
        System.out.println(lastRel);

        Map<String, Object> lastLinkMap = links.get(links.size() - 1);
        System.out.println(lastLinkMap.get("rel"));

        //get all rels
        List<String> path = response.path("links.rel");
        System.out.println(path);

        //find all href that endswith regions/1 by using links arraylist and stream

        



    }
}
