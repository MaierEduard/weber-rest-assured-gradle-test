package com.restAssuredTest.steps.auditSteps;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.model.audit.AuditResponse;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class GetAuditByIdSteps {

    private Response response;

    @Autowired
    private TestData testData;

    @Then("get audit by {string}")
    public void getAuditBy(String auditId) throws InterruptedException {

        AuditResponse auditResponse = RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .get("/audits/" + auditId)
                .as(AuditResponse.class);

        System.out.println(auditResponse.getExternalAuthorName());
        Thread.sleep(3000);


       assertThat( auditResponse.getExternalAuthorName(), is("Mama lor de importuri"));
       assertThat( auditResponse.getId(), notNullValue());


    }
}
