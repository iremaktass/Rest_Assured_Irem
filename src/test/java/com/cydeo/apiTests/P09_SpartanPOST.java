package com.cydeo.apiTests;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P09_SpartanPOST extends SpartanTestBase {

    @Test
    public void SpartanAsString(){

        String request = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"John\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();

        String expectedMessage = "A Spartan is Born!";

        /*
        {
    "success": "A Spartan is Born!",
    "data": {
        "id": 102,
        "name": "John",
        "gender": "Male",
        "phone": 1234567890
    }
}
         */

        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals("John", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(1234567890l, jsonPath.getLong("data.phone"));


        //get me id that we post as spartan
        System.out.println(jsonPath.getInt("data.id"));

    }

    @Test
    public void SpartanAsMap(){

        Map<String, Object> spartnMap = new LinkedHashMap<>();
        spartnMap.put("name", "John MAP");
        spartnMap.put("gender", "Male");
        spartnMap.put("phone", 1234567890l);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartnMap)   //serilization  ---> JAVA TO JSON
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();

        String expectedMessage = "A Spartan is Born!";

        /*
        {
    "success": "A Spartan is Born!",
    "data": {
        "id": 102,
        "name": "John",
        "gender": "Male",
        "phone": 1234567890

         */

        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals(spartnMap.get("name"), jsonPath.getString("data.name"));
        assertEquals(spartnMap.get("gender"), jsonPath.getString("data.gender"));
        assertEquals(spartnMap.get("phone"), jsonPath.getLong("data.phone"));

        //get me id that we post as spartan
        System.out.println(jsonPath.getInt("data.id"));
    }


    @Test
    public void SpartanAsPOJO(){

        Spartan spBody = new Spartan();
        spBody.setName("John POJO");
        spBody.setGender("Male");
        spBody.setPhone(3213213321l);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spBody)   //serilization  ---> JAVA/POJO TO JSON
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();


        String expectedMessage = "A Spartan is Born!";


        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals(spBody.getName(), jsonPath.getString("data.name"));
        assertEquals(spBody.getGender(), jsonPath.getString("data.gender"));
        assertEquals(spBody.getPhone(), jsonPath.getLong("data.phone"));

        //get me id that we post as spartan
        System.out.println(jsonPath.getInt("data.id"));
    }
}

