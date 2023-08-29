package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BookitTestBase {

   protected String token;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://api.qa.bookit.cydeo.com";
    }

    @BeforeEach
    public void getToken(){

        String email = "lfinnisz@yolasite.com";
        String password = "lissiefinnis";

        token = RestAssured
                .given()
                .queryParam("email", email)
                .queryParam("password", password)
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");
    }
}
