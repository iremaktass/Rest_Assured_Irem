package com.cydeo.Live.week3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.awt.image.RescaleOp;

public class P05_AuthTestWithApiKey {

    String apiKey="a6v39P9aam1Q7YaB-TS5u34p_AdSejb5RJoke0lA8YQIV6Ac";

    @Test
    void apiKeyQueryParam() {

        RestAssured
                .given().accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .get("https://api.currentsapi.services/v1/latest-news")
                .prettyPeek();
    }

    @Test
    void apiKeyWithHeader() {

        RestAssured
                .given().accept(ContentType.JSON)
                .header("Authorization", apiKey)
                .get("https://api.currentsapi.services/v1/latest-news")
                .prettyPeek();
    }
}
