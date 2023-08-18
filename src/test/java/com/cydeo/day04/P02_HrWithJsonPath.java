package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET all /countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        // response.prettyPrint();

        assertEquals(200, response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("items[2].country_name"));  // 3. country name

        //give me the last country_name
        System.out.println(jsonPath.getString("items[-1].country_name"));

        //get me 3. and 4. country name together
        System.out.println(jsonPath.getString("items[2, 3].country_name"));
        System.out.println(jsonPath.getList("items[2,3].country_name"));

        //get me all country name where region_id is 2, it will do filter
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(list);

        //get me the all country id
        List<Integer> list1 = jsonPath.getList("items.country_id");
        System.out.println(list1);


    }

     /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............

     */

    @DisplayName("GET all /employees?limit=200 with JsonPath")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //get emails from response
        List<String> list = jsonPath.getList("items.email");
        System.out.println(list);
        System.out.println(list.size());

        //get all emails who is working as IT_PROG
        List<String> list1 = jsonPath.getList("items.findAll {it.job_id == 'IT_PROG'}.email");
        System.out.println(list1);

        //get me all employees first names whose salary is more than 10000
        List<String> list2 = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println(list2);

        //get me all information from response who has max salary
        System.out.println(jsonPath.getString("items.max {it.salary}"));

        //get me first name from response who has max salary
        System.out.println(jsonPath.getString("items.max {it.salary}.first_name"));

        //get me firstname from response who has min salary
        System.out.println(jsonPath.getString("items.min {it.salary}.first_name"));


    }

    /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */


    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/locations");

        assertEquals(200, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        JsonPath jsonPath = response.jsonPath();

        //get the second city with JsonPath
        System.out.println(jsonPath.getString("items[1].city"));

        // get the last city with JsonPath
        System.out.println(jsonPath.getString("items[-1].city"));

        //get all country ids
        List<String> list1 = jsonPath.getList("items.country_id");
        System.out.println(list1);

        //get all city where their country id is UK
        List<String> list = jsonPath.getList("items.findAll {it.country_id == 'UK'}.city");

        System.out.println(list);
    }

}
