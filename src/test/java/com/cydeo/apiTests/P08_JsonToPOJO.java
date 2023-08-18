package com.cydeo.apiTests;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

public class P08_JsonToPOJO extends SpartanTestBase {

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

    @Test
    public void getSingleSpartan() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Spartan sp = jsonPath.getObject("", Spartan.class);
        System.out.println(sp);

        System.out.println(sp.getGender());
        System.out.println(sp.getId());
        System.out.println(sp.getName());
        System.out.println(sp.getPhone());
    }


    @Test
    public void getAllSpartans() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .extract().jsonPath();

        List<Spartan> allSpartanAsPOJO = jsonPath.getList("", Spartan.class);

        for (Spartan eachSpartan : allSpartanAsPOJO) {
            System.out.println(eachSpartan);
        }

    }

    @Test
    public void partiallyPOJO() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .extract().jsonPath();

        Spartan sp = jsonPath.getObject("[0]", Spartan.class);

        System.out.println(sp);
    }
}