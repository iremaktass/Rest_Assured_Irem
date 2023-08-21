package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P10_SpartanPUTPATCH extends SpartanTestBase {

    @Test
    public void SpartanPUT(){

        Map<String, Object> putBody = new LinkedHashMap<>();

        putBody.put("name","Rest PUT");
        putBody.put("gender", "Male");
        putBody.put("phone", 1234567890l);

        System.out.println(putBody);

        given().contentType(ContentType.JSON)
                .pathParam("id", 100)
                .body(putBody)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);
    }


    @Test
    public void SpartanPATCH(){



        Map<String, Object> patchBody = new LinkedHashMap<>();

        patchBody.put("name","Rest PATCH");
        patchBody.put("gender", "Male");
        patchBody.put("phone", 1234567890l);

        given().contentType(ContentType.JSON)
                .pathParam("id", 100)
                .body(patchBody)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);


    }
}
