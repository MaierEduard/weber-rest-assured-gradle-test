package com.restAssuredTest.steps.auditSteps;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.model.audit.Audit;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CreateAuditSteps {

    private RequestSpecification registrationRequest;


    @Autowired
    private TestData testData;


    @Given("a new audit")
    public void aNewAudit() throws InterruptedException {


        HashMap<String, String> createAudit = new HashMap<>();

        createAudit.put("date", "2019-08-08");
        createAudit.put("status", "PASSED");
        createAudit.put("externalAuthorName", "Bimba1234");

       Response response = RestAssured.given()

                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON)
                .body(createAudit)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .post("/audits");
               // .then().statusCode(isOneOf(HttpStatus.SC_OK, HttpStatus.SC_NOT_FOUND));
        Thread.sleep(3000);



        Object authorFirstName = response.getBody().jsonPath().get();
        System.out.println(authorFirstName);
        assertThat("Can't create audit", authorFirstName.toString(), containsString("externalAuthorName=Bimba1234"));
    }


    @Then("a new audit is created and externalAuthorName is populated")
    public void aNewAuditIsCreatedAndExternalAuthorNameIsPopulated() throws InterruptedException {

        Audit auditRequest = new Audit();

        auditRequest.setDate(LocalDate.of(2019, 9, 2));
        auditRequest.setStatus("PASSED");
        auditRequest.setExternalAuthorName("Bimba2019");


        Audit audit = RestAssured.given()

                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/audits")
                .contentType(ContentType.JSON)
                .body(auditRequest)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .post()
                .as(Audit.class);
        Thread.sleep(3000);



        assertThat("Can't create audit", audit.getAuthorAccountId(), is(nullValue()));
        assertThat("Can't create audit", audit.getDate(), notNullValue());
        assertThat("Can't create audit", audit.getExternalAuthorName(), is(auditRequest.getExternalAuthorName()));
        assertThat("Can't create audit", audit.getStatus(), is("PASSED"));
    }

    @Then("a new audit is created and externalAuthorName must be Null")
    public void aNewAuditIsCreatedAndExternalAuthorNameMustBeNull() throws InterruptedException {

        Audit auditRequest = new Audit();

        auditRequest.setDate(LocalDate.of(2019, 9, 2));
        auditRequest.setStatus("PASSED");

        Audit audit = RestAssured.given()

                .baseUri("https://192.168.1.2:7100/board")
                .basePath("/audits")
                .contentType(ContentType.JSON)
                .body(auditRequest)
                .auth().oauth2(testData.getAdmin().getAccessToken())
                .when()
                .post()
                .as(Audit.class);
        Thread.sleep(3000);

        assertThat("Can't create audit", audit.getAuthorAccountId(), notNullValue());
        assertThat("Can't create audit", audit.getDate(), notNullValue());
        assertThat("Can't create audit", audit.getId(), notNullValue());
        assertThat("Can't create audit", audit.getExternalAuthorName(), is(auditRequest.getExternalAuthorName()));
        assertThat("Can't create audit", audit.getDate(), is(auditRequest.getDate()));
        assertThat("Can't create audit", audit.getStatus(), is("PASSED"));
    }
}
