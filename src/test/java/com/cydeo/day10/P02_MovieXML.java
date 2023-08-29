package com.cydeo.day10;

import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class P02_MovieXML {

    @Test
    void test1() {

        Response response = given().queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("t", "Harry Potter")
                .when().get("https://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        System.out.println(xmlPath.getString("root.movie.@year"));

        System.out.println(xmlPath.getString("root.movie.@title"));

        System.out.println(xmlPath.getString("root.movie.@"));


    }


    /**
     * http://www.omdbapi.com?apikey=81815fe6&r=xml&s=Harry Potter
     * --Try to get all years and verify they are greater then 2000
     * --Print each title and make sure each of them contains Harry Potter
     * --verify total result is 127
     */

    @Test
    void test2() {

        Response response = given().queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .when().get("https://www.omdbapi.com").prettyPeek();


        XmlPath xmlPath = response.xmlPath();

        List<String> years = xmlPath.getList("root.result.@year");


        for (String eachYear : years) {
            System.out.println(eachYear);
            int yearInt = Integer.parseInt(eachYear);
            Assertions.assertTrue(yearInt> 2000);
        }


        List<String> titles = xmlPath.getList("root.result.@title");

        for (String eachTitle : titles) {
            Assertions.assertTrue(eachTitle.contains("Harry Potter"));
        }

        String result = xmlPath.getString("root.@totalResults");

        Assertions.assertEquals("134", result );

    }
}
