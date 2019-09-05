package com.restAssuredTest.steps.auditSteps;

import com.restAssuredTest.data.TestData;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteAuditByIdSteps {

    @Autowired
    private TestData testData;


    @Then("delete audit by {string}")
    public void deleteAuditBy(String auditId) {

         RestAssured.given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .delete("/audits/" + auditId)
                .then().statusCode(is(204))
                .log().ifError().extract();

    }
}
