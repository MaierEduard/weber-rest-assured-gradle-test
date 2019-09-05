package com.restAssuredTest.service;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isOneOf;

@Component
@RequiredArgsConstructor
public class AccountService {

    public void createAccount(String adminAccessToken) throws InterruptedException {
//        RestAssured.keyStore("C:\\Program Files\\Java\\jre1.8.0_191\\lib\\security\\cacerts", "changeit");
        given()
//                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                //.queryParam("grant_type", "password")
                .queryParam("firstName", "Flaviu1989")
                .queryParam("lastName", "123456")
                .auth().oauth2(adminAccessToken)
                .when()
                .post("/accounts")
                .then().statusCode(isOneOf(HttpStatus.SC_OK, HttpStatus.SC_NOT_FOUND));
        Thread.sleep(3000);

    }


    public String getAdminAccessToken() {
        Response response = given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .queryParam("grant_type", "password")
                .queryParam("username", "admin")
                .queryParam("password", "123456")
                .auth().preemptive().basic("weber.swagger.local", "weber.swagger.local")
                .when()
                .post("/oauth/token")
                ;

        response.then()
                .log().ifError().and()
                .statusCode(HttpStatus.SC_OK);

        return getAccessToken(response);
    }

    private String getAccessToken(Response response) {
        return response.jsonPath().getString("access_token");
    }
}
