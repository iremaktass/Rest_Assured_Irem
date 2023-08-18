package com.cydeo.Homeworks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import com.cydeo.utilities.HrTestBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Homework02 extends HrTestBase {

    /*
    TASK 1 :
    - Given accept type is Json
    - Path param value- US
    - When users sends request to /countries
    - Then status code is 200
    - And Content - Type is Json
    - And country_id is US
    - And Country_name is United States of America
    And Region_id is 2
     */

    @Test
    public void task1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("country_id", "US")
                .when()
                .get("/countries/{country_id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        String countryId = response.path("country_id");
        assertEquals("US", countryId);

        String countryName = response.path("country_name");
        assertEquals("United States of America", countryName);

        int regionId = response.path("region_id");
        assertEquals(2, regionId);
    }


    /*
    TASK 2 :
    - Given accept type is Json
    - Query param value - q={"department_id":80}
    - When users sends request to /employees
    - Then status code is 200
    - And Content - Type is Json
    - And all job_ids start with 'SA'
    - And all department_ids are 80
    - Count is 25
     */
    @Test
    public void task2(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        assertEquals(200, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());



        List<String> allJobId = response.path("items.job_id");

        for (String allJobWithSA : allJobId) {
            assertTrue(allJobWithSA.startsWith("SA"));
            //assertEquals("SA", allJobWithSA.substring(0,2));
                System.out.println(allJobWithSA);
        }



        List<Integer> allDepartmentId = response.path("items.department_id");

        for (Integer eachDepartmentId : allDepartmentId) {
            assertTrue(eachDepartmentId.equals(80));
            //assertEquals("80", eachDepartmentId.toString().substring(0,2));
            System.out.println(eachDepartmentId);
        }



        int count = response.path("count");
        System.out.println(count);
    }


    /*
    TASK 3 :
    - Given accept type is Json
    - Query param value q={“region_id":3}
    - When users sends request to /countries
    - Then status code is 200
    - And all regions_id is 3
    - And count is 6
    - And hasMore is false
    - And Country_name are;
    Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void task3(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":3}")
                .when()
                .get("/countries");

        response.prettyPrint();

        assertEquals(200, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        List<Integer> regionId = response.path("items.region_id");
        for (Integer eachId : regionId) {
            assertEquals(3, eachId);
            assertTrue(eachId.equals(3));
           // System.out.println(eachId);
        }


        int count = response.path("count");
        System.out.println(count);

        Boolean hasMore = response.path("hasMore");
        assertTrue(hasMore.equals(false));

        List<String> countryName = response.path("items.country_name");
        List<String> expectedCountryNames = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));

        for (String eachCountry : countryName) {
            assertTrue(expectedCountryNames.equals(countryName));
            System.out.println(eachCountry);
        }
    }
}
