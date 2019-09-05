package com.restAssuredTest.steps.qualityDailyControl;

import com.restAssuredTest.data.TestData;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProblemSteps {

    @Autowired
    private TestData testData;

    @Then("the problem was created")
    public void theProblemWasCreated() throws InterruptedException {

        HashMap<String, String> createLoadProblem = new HashMap<>();
        createLoadProblem.put("description", "Andrei s-a intors la lucru");
        createLoadProblem.put("date", "2019-09-02");


        Response response = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .body(createLoadProblem)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .post("/daily-control/load/problems");
        Thread.sleep(3000);


        Object resp = response.getBody().jsonPath().get("description");
        System.out.println(resp);

        assertThat("problem can't be created", resp, is("Andrei s-a intors la lucru"));

    }
}
