package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class P01_SpartanXMLTest extends SpartanTestBase {

    /**
     * Given accept type is application/xml
     * and basic auth is admin admin
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     *   print first spartan name
     *   .....
     *   ...
     */

    @Test
    public void test1() {

        XmlPath xmlPath = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .extract().xmlPath();

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        System.out.println(xmlPath.getString("List.item[0].name"));  //first spartan name

        System.out.println(xmlPath.getString("List.item[-1].name"));  //last spartan name

        List<String> allNames = xmlPath.getList("List.item.name");  // all names
        System.out.println(allNames);

    }
}
