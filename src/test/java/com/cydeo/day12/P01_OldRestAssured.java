package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test
    public void getAllSpartan(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(1))
                .body("id[1]",is(2))
                .log().body();

    }

    @Test
    public void getAllSpartanOldWay(){

        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(1))
                .body("id[1]",is(2))
                .when()
                .get("/spartans");

    }

 /*
    OLD WAY --> EXPECT()
        -It works like soft assertion. if one step failed, other steps will execute
    NEW WAY --> then() (this is one that we are using)
        -It works like hard assertion. if one step failed, other steps will not execute, ot will stop.
  */
}
