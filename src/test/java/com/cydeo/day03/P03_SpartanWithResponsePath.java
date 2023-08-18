package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

     /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("GET spartan with Response Path")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        Assertions.assertEquals(10, id);
        Assertions.assertEquals("Lorenza", name);
        Assertions.assertEquals("Female", gender);
        Assertions.assertEquals(3312820936l, phone);


    }

    @DisplayName("GET all Spartans")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        // get me first spartan id
        int firstID = response.path("id[0]");
        System.out.println(firstID);

        //get first spartan name
        String firstName = response.path("name[0]");
        System.out.println(firstName);

        //get me last spartan name
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

        //get me second last spartan name
        System.out.println("response.path(\"name[-2]\") = " + response.path("name[-2]"));

        //get me all spartan names
        List<String> allNames = response.path("name");
        System.out.println(allNames);

        //or

        for (String allName : allNames) {
            System.out.println(allName);
        }
    }
}
