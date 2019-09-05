package com.restAssuredTest.steps.qualityDailyControl;

import com.restAssuredTest.data.TestData;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetProblemByIdSteps {

    @Autowired
    private TestData testData;



    @Then("I should get the problem from {string}")
    public void iShouldGetTheProblemFrom(String problemId) {

        Response response = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/daily-control/load/problems/5d6ce9d3be41760001a4d023");

        Object resp = response.getBody().jsonPath().get("description");
        System.out.println(resp);

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        assertThat("fsffwfwfw", resp, is("Andrei s-a intors la lucru"));
        assertThat("fsffwfwfw", statusCode, is(200));
    }
}
