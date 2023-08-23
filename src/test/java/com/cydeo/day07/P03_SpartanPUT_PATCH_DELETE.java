package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P03_SpartanPUT_PATCH_DELETE extends SpartanTestBase {

    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1() {
        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "JChanda js");
        requestBodyMap.put("gender", "Female");
        requestBodyMap.put("phone", "9289012086");

        //PUT will update existing record so we choose one the existing ID, make sure it exist in your IP address
        int id = 93;

        given()
                .contentType(ContentType.JSON)
                .and().pathParam("id", 93)
                .body(requestBodyMap)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

    }


    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2() {
        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "JChanda PATCH");


        //PUT will update existing record so we choose one the existing ID, make sure it exist in your IP address
        int id = 93;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

    }


    @DisplayName("DELETE Spartan")
    @Test
    public void test3(){

        //we can delete one id only one time, so it will give 204 only for the first execution
        int id = 92;

        given()
                .pathParam("id",id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //after deleted when we send get request to id that we deleted, it needs to give 404
        given()
                .pathParam("id", 92)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);

    }


}
