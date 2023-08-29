package com.cydeo.day11;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class P03_ValurSourceTest extends ZippopotamTestBase {


    @ParameterizedTest
    @ValueSource(ints = {3, 56, 78, 9, 12})
    public void test1(int number) {

        System.out.println(number);
        Assertions.assertTrue(number < 100);
    }


    @ParameterizedTest(name = "{index} - verify name is {0}")
    @ValueSource(strings = {"Mike", "Rose", "Steven", "TJ", "Harold", "Severus", "Sherlock"})
    public void test2(String name) {

        System.out.println("name = " + name);
        Assertions.assertTrue(name.length() > 4);
    }


    //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030 ,22031, 22032, 22033 , 22034, 22035, 22036})
    public void test3(int zipcode){

        given().contentType(ContentType.JSON)
                .pathParam("zipCode", zipcode)
                .when().get("/us/{zipCode}")
                .then().statusCode(200);

    }
}
