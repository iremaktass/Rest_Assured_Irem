package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class ErgastTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://ergast.com/api/f1/";
    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }
}
