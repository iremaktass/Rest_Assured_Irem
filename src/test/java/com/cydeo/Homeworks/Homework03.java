package com.cydeo.Homeworks;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Homework03 extends ZippopotamTestBase {

    /*
    Given Accept application/json
    And path zipcode is 22031
    When I send a GET request to /us endpoint
    Then status code must be 200
    And content type must be application/json
    And Server header is cloudflare
    And Report-To header exists
    And body should contains following information
    post code is 22031
    country is United States
    country abbreviation is US
    place name is Fairfax state is Virginia
     */

    @Test
    public void test1(){
        JsonPath jsonPath = given().log().ifValidationFails()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",22031)
                .when().get("/us/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Server", "cloudflare")
                .and()
                .header("Report-To", notNullValue())
                .body("'post code'",is(equalTo("22031")))
                .body("country", is("United States"))
                .body("'country abbreviation'", equalTo("US"))
                .body("places[0].'place name'", is("Fairfax"))
                .body("places[0].state", is(equalTo("Virginia")))
                .extract().jsonPath();

    }

    @Test
    public void test2(){

        /*
        TASK 2
        Given Accept application/json
        And path zipcode is 50000
        When I send a GET request to /us endpoint
        Then status code must be 404
        And content type must be application/json
         */

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 50000)
                .when().get("/us/{id}")
                .then().statusCode(404)
                .contentType("application/json");
    }

    @Test
    public void test3(){

        /*
        TASK 3
        Given Accept application/json
        And path state is va
        And path city is fairfax
        When I send a GET request to /us endpoint
        Then status code must be 200
        And content type must be application/json
        And payload should contains following information
        country abbreviation is US
        country United States
        place name Fairfax
        each places must contains fairfax as a value each post code must start with 22
         */

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("state", "va")
                .and()
                .pathParam("city", "fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .contentType("application/json")
                .body("'country abbreviation'", is("US"))
                .body("country", is(equalTo("United States")))
                .body("'place name'", equalTo("Fairfax"))
                .body("places.'place name'", everyItem(containsString("Fairfax")))
                .body("places.'post code'", containsInRelativeOrder(startsWith("22")))
                .extract().jsonPath();



    }
}
