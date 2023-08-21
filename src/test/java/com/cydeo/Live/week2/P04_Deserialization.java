package com.cydeo.Live.week2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P04_Deserialization {


    /**

     Send request to FruitAPI url and save the response
     Accept application/json
     GET /customers
     Store the response in Response Object that comes from get Request
     Print out followings
     Print response
     Content-Type is application/json
     Status Code is 200
     Retrieve data as JAVA Collections and print out following informations
     *
     System.out.println("====== GET META ======");
     System.out.println("====== GET LIMIT ======");
     System.out.println("====== GET CUSTOMERS ======");
     System.out.println("====== GET FIRST CUSTOMER ======");
     System.out.println("====== PRINT CUSTOMERS IDs ======");
     System.out.println("====== PRINT CUSTOMERS Names ======");

     *
     */

    @Test
    public void getCustomers(){

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/customers").prettyPeek()
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().jsonPath();

        Map<String, Object> allData = jsonPath.getMap("");

        System.out.println("====== GET META ======");
        Map<String, Integer> meta = (Map<String, Integer>) allData.get("meta");
        System.out.println(meta);

        System.out.println("====== GET LIMIT ======");
        System.out.println(allData.get("limit"));

        System.out.println("====== GET CUSTOMERS ======");
        List<Map<String, Object>> allCustomers = (List<Map<String, Object>>) allData.get("customers");
        for (Map<String, Object> allCustomer : allCustomers) {
            System.out.println(allCustomer);
        }

        System.out.println("====== GET FIRST CUSTOMER ======");
        Map<String, Object> firstCustomer = allCustomers.get(0);
        System.out.println(allCustomers.get(0));

        System.out.println("====== PRINT FIRST CUSTOMERS ID ======");
        System.out.println(firstCustomer.get("id"));

        System.out.println("====== PRINT CUSTOMERS IDs ======");
        List<Object> allIDs = allCustomers.stream().map(eachCustomer -> eachCustomer.get("id")).collect(Collectors.toList());

        //OR

        List<Integer> allID = new ArrayList<>();
        for (Map<String, Object> eachCustomer : allCustomers) {
            allID.add((Integer) eachCustomer.get("id"));
        }

        System.out.println("====== PRINT CUSTOMERS Names ======");
        List<Object> allName = allCustomers.stream().map(eachCustomerName -> eachCustomerName.get("name")).collect(Collectors.toList());

        //OR

        List<String> names = new ArrayList<>();
        for (Map<String, Object> eachName : allCustomers) {
            names.add((String) eachName.get("name"));
        }


    }
}
