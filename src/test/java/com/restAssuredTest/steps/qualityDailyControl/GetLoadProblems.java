package com.restAssuredTest.steps.qualityDailyControl;

import com.restAssuredTest.data.TestData;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class GetLoadProblems {

    @Autowired
    private TestData testData;

    @Then("I should get all the problems")
    public void iShouldGetAllTheProblems() {

        Response response = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/daily-control/load/problems?startDate=2018-08-08&endDate=2019-09-09");

        Object resp = response.getBody().jsonPath().get();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode+"sfskjfnsjkfnsjkfnsjkfnsjfnsdjfnsjk");
        System.out.println(resp);
    }
}
