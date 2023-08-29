package com.cydeo.Live.week3;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class P01_SerializationWithMap extends FruitTestBase {


    public  static int createdId;
    @Test
    public void createFruit() {


        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "Fruit C");
        requestBody.put("price", 5.46);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                /*
                .body("{\n" +
                        "  \"name\": \"Fruit A\",\n" +
                        "  \"price\": 4.57\n" +
                        "}")

                 */
                .post("/products")
                .then().statusCode(201)
                .extract().response();



                //lets assume that api is not returning id how to we get created fruit test

        String selfLink = response.path("self_link");
        String idOfString = selfLink.substring(selfLink.lastIndexOf("/") + 1);

        int id = Integer.parseInt(idOfString);
        createdId=id;
        System.out.println(createdId);
    }


}
