package com.cydeo.Homeworks;

import com.cydeo.pojo.Constructor;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Homework04_2 extends FormulaTestBase {

    /*
    TASK 2 : Solve same task with 4 different way

    - Use JSONPATH
    int total = jsonpath.getInt(“pathOfField”)

    - Use HAMCREST MATCHERS
    then().body(..........)
    Print all names of constructor by using extract method after HamCrest
    - Convert Constructor information to Java Structure
    List<Map<String,Object>> constructorMap=jsonpath.getList(“pathOfConsts”);

    - Convert Constructor information POJO Class
    List<ConstructorPOJO>
    constr=getObject(“pathOfConstr”,ConstructorPOJO.class)

    NOTE
    —> There is a class in JAVA That’s why give your class name
    ConstrutorPOJO to separate from existing one. Wrong imports may cause
    issue
    - Given accept type is json
    - When user send request /constructorStandings/1/constructors.json
    - Then verify status code is 200
    - And content type is application/json; charset=utf-8
    - And total is 17
    - And limit is 30
    - And each constructor has constructorId
    - And constructor has name
    - And size of constructor is 17
    - And constructor IDs has “benetton”, “mercedes”,”team_lotus”
     */

    @Test
    public void test1() {

        System.out.println("---------JSONPATH---------");

        Response response = given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json");


        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        int total = jsonPath.getInt("MRData.total");
        assertEquals(17, total);

        int limit = jsonPath.getInt("MRData.limit");
        assertEquals(30, limit);

        List<Object> constructorID = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        assertTrue(!constructorID.isEmpty());

        for (Object eachCI : constructorID) {
            System.out.println(eachCI);
        }

        List<Object> constructorNames = jsonPath.getList("MRData.ConstructorTable.Constructors.name");
        assertTrue(!constructorNames.isEmpty());

        //And size of constructor is 17
        List<Object> sizeOfConstructor = jsonPath.getList("MRData.ConstructorTable.Constructors");
        assertEquals(17, sizeOfConstructor.size());

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”

        assertTrue(constructorID.contains("mercedes") && constructorID.contains("benetton") && constructorID.contains("team_lotus"));


    }

    @Test
    public void test2() {

        System.out.println("--------HAMCREST MATCHERS--------");


        given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is(equalTo("17")))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.name", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors", hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"))
        ;

    }

    @Test
    public void test3() {

        System.out.println("------Convert Driver information to Java Structure-------");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();


        //And total is 17
        int total = jsonPath.getInt("MRData.total");
        assertEquals(17, total);

        //    - And limit is 30
        int limit = jsonPath.getInt("MRData.limit");
        assertEquals(30, limit);

        //    - And each constructor has constructorId
        List<Object> constructorID = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        assertTrue(!constructorID.isEmpty());

        //    - And constructor has name
        List<Object> constructorNames = jsonPath.getList("MRData.ConstructorTable.Constructors.name");
        assertTrue(!constructorNames.isEmpty());

        //    - And size of constructor is 17
        int size = constructorID.size();
        assertEquals(17, size);

        //    - And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        assertTrue(constructorID.contains("mercedes") && constructorID.contains("benetton") && constructorID.contains("team_lotus"));
    }


    @Test
    public void test4() {

        System.out.println("------ Convert Driver information POJO Class-------");


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json")
                .then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        Constructor constructor1 = jsonPath.getObject("MRData", Constructor.class);

        int total = constructor1.getTotal();
        assertEquals(17, total);

        int limit = constructor1.getLimit();
        assertEquals(30, limit);

        Constructor constructor = jsonPath.getObject("MRData.ConstructorTable.Constructors[0]", Constructor.class);

        assertTrue(!constructor.getConstructorId().isEmpty());

        assertTrue(!constructor.getName().isEmpty());

        assertTrue((constructor.getConstructorId().contains("mercedes") )&& (constructor.getConstructorId().contains("benetton")) && (constructor.getConstructorId().contains("team_lotus")));




    }
}