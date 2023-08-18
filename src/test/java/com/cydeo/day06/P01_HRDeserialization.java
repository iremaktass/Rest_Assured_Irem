package com.cydeo.day06;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P01_HRDeserialization extends HrTestBase {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */

    @DisplayName("GET /locations to deserialization into Java Collections")
    @Test
    public void test1(){

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/locations")
                .then().statusCode(200).contentType("application/json")
                .extract().jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        //response.as("items[0]",Map.class) --> there is no method like this, that is why as() method is not enough
        List<String> links = jsonPath.getList("items[0].links");
        System.out.println(links);
        //OR
        Map<String, Object> firstMap = jsonPath.getMap("items[0]");
        System.out.println("firstMap = " + firstMap);
        System.out.println("firstMap.get(\"location_id\") = " + firstMap.get("location_id"));

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        List<Map<String,Object>> allLocations = jsonPath.getList("items");
        //OR
        Map<String, Object> firstMapLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstMapLinks = " + firstMapLinks);

        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        for (Map<String, Object> allLocation : allLocations) {
            System.out.println(allLocation);
        }
        //OR
        List<Map<String,Object>> allLocationsMap = jsonPath.getList("items");

        for (Map<String, Object> eachLocations : allLocationsMap) {
            System.out.println("eachLocations = " + eachLocations);
        }

        System.out.println("====== FIRST LOCATION ======");
        Map<Object, Object> firstLocations = jsonPath.getMap("items[0]");
        System.out.println(firstLocations);
        //OR
        System.out.println(allLocations.get(0));

        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println(allLocations.get(0).get("location_id"));
        //OR
        int firstLocationId = jsonPath.getInt("items[0].location_id");
        System.out.println(firstLocationId);

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        String string = jsonPath.getString("items[0].country_id");
        System.out.println(string);
        //or
        System.out.println(allLocations.get(0).get("country_id"));
        System.out.println("allLocationsMap.get(0).get(\"country_id\") = " + allLocationsMap.get(0).get("country_id"));
        String countryId = (String) allLocationsMap.get(0).get("country_id");


        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println(allLocations.get(0).get("links"));
        //or
        List<Object> list = jsonPath.getList("items[0].links");
        System.out.println(list);

        //we want to get href from first location what we need to do ?
        List<Map<String,Object>> linkks = (List<Map<String, Object>>) allLocationsMap.get(0).get("links");
        System.out.println("links = " + linkks);
        System.out.println("links.get(0).get(\"href\") = " + linkks.get(0).get("href"));














    }
}
