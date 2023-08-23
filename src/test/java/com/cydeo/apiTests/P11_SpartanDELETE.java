package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P11_SpartanDELETE extends SpartanTestBase {

    @Test
    public void SpartanDELETE(){

        given().pathParam("id", 90)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);


        // get request to see 404 not found

        given().accept(ContentType.JSON)
                .pathParam("id", 90)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);
}

}
