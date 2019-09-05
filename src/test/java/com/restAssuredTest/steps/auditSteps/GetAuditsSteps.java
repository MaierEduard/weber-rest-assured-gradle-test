package com.restAssuredTest.steps.auditSteps;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.model.audit.AuditListResponse;
import com.restAssuredTest.model.audit.AuditResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetAuditsSteps {

    private Response response;

    @Autowired
    private TestData testData;


    @Then("get audits with status {string}")
    public void getAuditsWithStatus(String status) throws InterruptedException {

        AuditListResponse auditResponse = RestAssured.given()

                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/audits?status=" + status)
                .then()
                .statusCode(is(200))
                .extract()
                .as(AuditListResponse.class);

        for (AuditResponse auditAudit : auditResponse.getContent()) {

            assertThat(auditResponse.getContent(), is(not(empty())));
            assertThat(auditAudit.getStatus(), is("PASSED"));
            assertThat(auditAudit.getId(), notNullValue());
            assertThat(auditAudit.getDate(), notNullValue());
        }

        Thread.sleep(3000);
    }
}
