package com.restAssuredTest.steps;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.service.AccountService;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

public class CreateUserSteps {

    @Autowired
    private AccountService accountService;

    private RequestSpecification registrationRequest;


    @Autowired
    private TestData testData;

    @Given("a new user")
    public void aNewUser() throws InterruptedException {

        accountService.createAccount(testData.getAdmin().getAccessToken());

        registrationRequest = given()
                .baseUri("https://192.168.1.2:7100/board")
                .contentType(ContentType.JSON);
    }
}
