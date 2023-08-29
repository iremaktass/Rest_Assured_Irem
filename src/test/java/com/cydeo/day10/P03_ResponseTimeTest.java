package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P03_ResponseTimeTest extends SpartanAuthTestBase {


    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                //.time(greaterThan(100L))   // or lessThan method we can use  ---> this method is storing actual response time
                //.time(lessThan(500l));
                .time(both(lessThan(100l)).and(greaterThan(100L)))
                .extract().response();


        System.out.println(response.getTime());

        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));

    }
}
