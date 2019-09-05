package com.restAssuredTest.steps.safetyDailyControl;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.model.safetydailycontrol.SafetyEvent;
import com.restAssuredTest.model.safetydailycontrol.SafetyEventContentList;
import com.restAssuredTest.model.safetydailycontrol.SafetyEventWithAccountResponse;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SafetyEventSteps {

    @Autowired
    private TestData testData;

    @Then("safety event is created")
    public void safetyEventIsCreated() {

        SafetyEvent safetyEventResponse = new SafetyEvent();

        safetyEventResponse.setType("CRITICAL_RISK");
        safetyEventResponse.setLine("Gestiune");
        safetyEventResponse.setTeam("Gestiune");

        SafetyEvent safetyEvent = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/daily-control/safety/events")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .body(safetyEventResponse)
                .post()
                .then()
                .log().ifError().statusCode(is(201)).extract()
                .as(SafetyEvent.class);

        System.out.println(safetyEvent.getId());

        assertThat(safetyEventResponse.getLine(), is(safetyEvent.getLine()));
        assertThat(safetyEventResponse.getTeam(), is(safetyEvent.getTeam()));
        assertThat(safetyEventResponse.getType(), is(safetyEvent.getType()));
        assertThat(safetyEvent.getId(), notNullValue());
        assertThat(safetyEvent.getReporterAccountId(), notNullValue());
    }

    @Then("get all safety events with line {string}")
    public void getAllSafetyEventsWithLine(String lineName) {

        SafetyEventContentList safetyEvents = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/daily-control/safety/events/groups?line=" + lineName + "&startDateTime=2000-07-25T09:30:18.744&endDateTime=2022-12-20T09:30:18.744")
                .then()
                .log().ifError().statusCode(is(200)).extract()
                .as(SafetyEventContentList.class);

        System.out.println(safetyEvents);

        //System.out.println(safetyEvents.getSafetyEvents().get(0));

    }
}
