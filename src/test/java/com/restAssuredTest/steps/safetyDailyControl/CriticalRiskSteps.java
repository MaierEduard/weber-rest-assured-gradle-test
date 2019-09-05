package com.restAssuredTest.steps.safetyDailyControl;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.model.safetydailycontrol.CriticalRiskObjective;
import com.restAssuredTest.model.safetydailycontrol.CriticalRiskObjectiveList;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CriticalRiskSteps {

    @Autowired
    private TestData testData;


    @Then("get all critical risk")
    public void getAllCriticalRisk() {

        CriticalRiskObjectiveList criticalResponseList = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                //.basePath("/daily-control/safety/critical-risks/objectives?endMonth=11&startMonth=1&endYear=2020&startYear=2000")
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/daily-control/safety/critical-risks/objectives?endMonth=11&startMonth=4&endYear=2020&startYear=2000")
                .then()
                .statusCode(is(200))
                .log().ifError().extract()
                .as(CriticalRiskObjectiveList.class);

        System.out.println(criticalResponseList.getContent().get(0));

        for (CriticalRiskObjective criticalRisk : criticalResponseList.getContent()) {

            assertThat(criticalRisk.getYear(), greaterThanOrEqualTo(2000));
            assertThat(criticalRisk.getYear(), lessThanOrEqualTo(2020));
            assertThat(criticalRisk.getMonth(), lessThanOrEqualTo(11));
            assertThat(criticalRisk.getMonth(), greaterThanOrEqualTo(4));
            assertThat(criticalRisk.getDescription(), is(not(emptyString())));
            assertThat(criticalRisk.getId(), is(not(emptyOrNullString())));

        }
    }

    @Then("get critical risk by {string}")
    public void getCriticalRiskBy(String criticalRiskObjectiveId) {

        CriticalRiskObjective criticalRiskObjective = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/daily-control/safety/critical-risks/objectives/" + criticalRiskObjectiveId)
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .get()
                .then()
                .log().ifError().statusCode(is(200)).extract()
                .as(CriticalRiskObjective.class);

        System.out.println(criticalRiskObjective.getId());

        assertThat(criticalRiskObjective.getId(), is(criticalRiskObjectiveId));
        assertThat(criticalRiskObjective.getDescription(), is("Fara accidente"));
        assertThat(criticalRiskObjective.getYear(), is(2019));

    }

    @Then("delete critical risk by {string}")
    public void deleteCriticalRiskBy(String criticalRiskObjectiveId) {

        RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/daily-control/safety/critical-risks/objectives/" + criticalRiskObjectiveId)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .delete()
                .then()
                .log().ifError().statusCode(is(204)).extract();

    }

    @Then("critical risk is created")
    public void criticalRiskIsCreated() throws InterruptedException {

        CriticalRiskObjective criticalRequest = new CriticalRiskObjective();

        criticalRequest.setMonth(11);
        criticalRequest.setYear(2020);
        criticalRequest.setCount(1);
        criticalRequest.setDescription("Dan si papagalii");


        CriticalRiskObjective criticalRiskObjective = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/daily-control/safety/critical-risks/objectives")
                .contentType(ContentType.JSON)
                .body(criticalRequest)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .post()
                .then().log().ifError().extract()
                .as(CriticalRiskObjective.class);
        Thread.sleep(3000);

        System.out.println(criticalRiskObjective.getDescription());

        assertThat(criticalRiskObjective.getDescription(), is(criticalRequest.getDescription()));
        
    }
}
