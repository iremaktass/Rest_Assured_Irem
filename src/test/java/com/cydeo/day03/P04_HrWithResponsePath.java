package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1() {


            Response response = given().accept(ContentType.JSON)
                    .queryParam("q", "{\"region_id\":2}")
                    .when().get("/countries");


        //print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));

        //print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));

        //print 3rd element href
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        //get all countries names
        List<String> allCountries = response.path("items.country_name");
        System.out.println("allCountries = " + allCountries);

        //verify all region_ids equals to 2
        List<String> allRegionId = response.path("items.country_id");

        for (String eachRegionId : allRegionId) {
            Assertions.assertEquals(2, "id");
            System.out.println(eachRegionId);
        }

    }
}
