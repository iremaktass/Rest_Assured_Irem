package com.cydeo.Live.week3;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class P03_AuthTestWithBasic extends SpartanAuthTestBase {

    @Test
    void basic_auth_in_header() {

        RestAssured.given()
                .header("Authorization", "Basic dXNlcjp1c2Vy")
                .when()
                .get("/api/spartans")
                .then().statusCode(200);
    }

    @Test
    void negative_basic_auth() {

        RestAssured.given()
                .auth().basic("user1", "user")
                .when()
                .get("/api/spartans")
                .then().statusCode(401);
    }
}
