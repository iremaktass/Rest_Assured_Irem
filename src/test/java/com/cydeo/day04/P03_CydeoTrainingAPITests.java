package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {


    /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
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

    @DisplayName("GET /student/2")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when()
                .get("/student/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("date"));

        assertEquals("envoy", response.header("server"));


        JsonPath jsonPath = response.jsonPath();

        assertEquals("Mark",jsonPath.getString("students[0].firstName"));

        assertEquals(13,jsonPath.getInt("students[0].batch"));

        assertEquals("math",jsonPath.getString("students[0].major"));

        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

        assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));

        assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));

        assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));


    }

    /*

    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("batch", 22)
                .when()
                .get("/student/batch/{batch}");

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("date"));

        assertEquals("envoy", response.header("server"));

        JsonPath jsonPath = response.jsonPath();

        List<Object> list = jsonPath.getList("students.batch");

        //assertTrue(response.body("students.batch", everyItem(equalsTo(2))));


    }

}
