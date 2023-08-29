package com.cydeo.day11;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class P09_SpartanPOSTDDT extends SpartanTestBase {


    /**
     *
     *  POST SPARTAN DDT
     *
     *  Given content type is json
     *  And accept type is json
     *  When I POST Spartan
     *  And status code needs to 201
     *  Verify spartan informations macthing with dynamic taht we provide
     *
     *  Generate DUMMY DATA
     *  https://www.mockaroo.com/
     *
     *  name
     *  gender
     *  phone
     *
     */


    @ParameterizedTest
    @CsvFileSource(resources = "/SpartanInfo.csv", numLinesToSkip = 1)
    public void test1(String name, String gender, long phone){

        Map<String, Object> spartanInfo = new LinkedHashMap();
        spartanInfo.put("name", name);
        spartanInfo.put("gender", gender);
        spartanInfo.put("phone", phone);

        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "admin")
                .body(spartanInfo)
                .when().post("/api/spartans")
                .then().statusCode(201);
    }

}
