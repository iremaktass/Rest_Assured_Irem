package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P19_CsvFileSource extends SpartanTestBase {


    @ParameterizedTest
    @CsvFileSource (resources = "/spartanDDT.csv", numLinesToSkip = 1)
    public void test1(String name, String gender, long phone){

        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        Map<String, Object> spartnMap = new LinkedHashMap<>();
        spartnMap.put("name", name);
        spartnMap.put("gender", gender);
        spartnMap.put("phone", phone);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartnMap)   //serilization  ---> JAVA TO JSON
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();

        Assertions.assertEquals(name, jsonPath.getString("data.name"));



    }
}
