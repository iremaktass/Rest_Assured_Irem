package com.cydeo.apiTests;

import com.cydeo.utilities.BookitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BookitAPIJWT extends BookitTestBase {

    /*
    team_leader_email=lfinnisz@yolasite.com
    team_leader_password=lissiefinnis
     */

    @Test
    public void test1(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", "lfinnisz@yolasite.com")
                .queryParam("password", "lissiefinnis")
                .when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath();

        //Retrieve accessToken from playload
        String accessToken = "Bearer " + jsonPath.getString("accessToken");
        System.out.println("accessToken = " + accessToken);


        //send request/api/users/me endpoint to get info about token owner

        Response response = given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/users/me")
                .then().statusCode(200)
                .extract().response();

        response.prettyPrint();


    }
}
