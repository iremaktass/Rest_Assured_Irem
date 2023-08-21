package com.cydeo.Live.week2;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P02_QueryParam extends FruitTestBase {


    /**
     *1- Given accept type is Json
     *2- Query Parameters value is
     *     - start —> 1
     *     - limit —> 100
     *     - search —> "Fruit"
     *3- When user sends GET request to /products
     *4- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - start and limit values are matching with query params
     *     - Product Names contains Fruit
     *     - Get all product names
     *     - Get product ids
     *
     */

    @Test
    public  void getProducts() {


        JsonPath jsonPath = given().log().uri().accept(ContentType.JSON)
                .queryParam("start", 1)
                .queryParam("limit", 100)
                .queryParam("search", "Fruit")
                .when().get("/products")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("meta.limit", is(equalTo(100)))
                .body("meta.start", is(1))
                .body("products.name", everyItem(containsString("Fruit")))
                .extract().jsonPath();


        //Get all product names
        List<String> allProductNames = jsonPath.getList("products.name");
        System.out.println(allProductNames);

        //Get product ids
        List<Integer> allProductIDs = jsonPath.getList("products.id");
        System.out.println(allProductIDs);

    }
}
