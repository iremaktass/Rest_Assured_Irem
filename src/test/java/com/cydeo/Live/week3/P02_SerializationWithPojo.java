package com.cydeo.Live.week3;

import com.cydeo.pojo.Fruit;
import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class P02_SerializationWithPojo extends FruitTestBase {

    @Test
    public void createFruit() {


        Fruit requestBody = new Fruit("Fruit E", 3.45);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post("/products")
                .then().statusCode(201)
                .extract().response();
    }
}