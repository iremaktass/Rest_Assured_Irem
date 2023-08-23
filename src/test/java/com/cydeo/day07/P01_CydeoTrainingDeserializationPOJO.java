package com.cydeo.day07;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_CydeoTrainingDeserializationPOJO {

    @DisplayName("GET /student/2")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when()
                .get("/student/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("date"));

        assertEquals("envoy", response.header("server"));


        JsonPath jsonPath = response.jsonPath();

        Student student = jsonPath.getObject("students[0]", Student.class);
        System.out.println(student.getFirstName());
        System.out.println(student.getContact().getEmailAddress());

        assertEquals("Mark", student.getFirstName());
        assertEquals(13, student.getMajor());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());



        assertEquals("Mark",jsonPath.getString("students[0].firstName"));

        assertEquals(13,jsonPath.getInt("students[0].batch"));

        assertEquals("math",jsonPath.getString("students[0].major"));

        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

        assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));

        assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));

        assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));


    }


    public void test2(){


        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when()
                .get("/student/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("date"));

        assertEquals("envoy", response.header("server"));


        JsonPath jsonPath = response.jsonPath();

        Students students = jsonPath.getObject("", Students.class);

        Student student = students.getStudents().get(0);

    }
}
