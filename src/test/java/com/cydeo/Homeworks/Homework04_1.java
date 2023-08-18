package com.cydeo.Homeworks;

import com.cydeo.pojo.Driver;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Homework04_1 extends FormulaTestBase {

    /*
    BASE URL —> http://ergast.com/api/f1/

TASK 1 : Solve same task with 4 different way

- Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
- And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish

- Use JSONPATH
int total = jsonpath.getInt(“pathOfField”)

- Use HAMCREST MATCHERS
then().body(..........)
Print givenName of Driver by using extract method after HamCrest

- Convert Driver information to Java Structure
Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)

- Convert Driver information POJO Class
Driver driver=getObject(“pathOfDriver”,Driver.class)

     */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("drivers/{driverId}.json");
        response.prettyPrint();

        System.out.println("---------JSONPATH---------");

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json; charset=utf-8", response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        //- And total is 1
        int total = jsonPath.getInt("MRData.total");
        System.out.println(total);
        assertEquals(1, total);


        //- And givenName is Fernando
        String givenName = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        System.out.println(givenName);
        assertEquals("Fernando", givenName);

        //- And familyName is Alonso
        String familyName = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        assertEquals("Alonso", familyName);

        //- And nationality is Spanish
        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");
        assertEquals("Spanish", nationality);


    }

    @Test
    public void test2() {

        System.out.println("--------HAMCREST MATCHERS--------");

        given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is(equalTo("1")))
                .body("MRData.DriverTable.Drivers[0].givenName", is(equalTo("Fernando")))
                .body("MRData.DriverTable.Drivers[0].familyName", is(equalTo("Alonso")))
                .body("MRData.DriverTable.Drivers[0].nationality", is(equalTo("Spanish")));


    }




    @Test
    public void test3() {

        System.out.println("------Convert Driver information to Java Structure-------");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        //Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)

        Map<String, Object> map1 = jsonPath.getMap("MRData");

        //- And total is 1
        String total = (String) map1.get("total");
        assertEquals("1", total);

        Map<String, Object> map = jsonPath.getMap("MRData.DriverTable.Drivers[0]");


        //- And givenName is Fernando
        String givenName = (String) map.get("givenName");
        assertEquals("Fernando", givenName);


        //- And familyName is Alonso
        String familyName = (String) map.get("familyName");
        assertEquals("Alonso", familyName);

        //- And nationality is Spanish
        String nationality = (String) map.get("nationality");
        assertEquals("Spanish", nationality);


    }


    @Test
    public void test4() {

        System.out.println("------ Convert Driver information POJO Class-------");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();


        Driver driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Driver.class);

        String givenName = driver.getGivenName();
        assertEquals("Fernando", givenName);

        String familyName = driver.getFamilyName();
        assertEquals("Alonso", familyName);

        String nationality = driver.getNationality();
        assertEquals("Spanish", nationality);


        Driver driver2 = jsonPath.getObject("MRData", Driver.class);
        int total = driver2.getTotal();
        assertEquals(1,total);

    }}

