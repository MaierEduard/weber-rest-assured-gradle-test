package com.restAssuredTest.steps;

import com.restAssuredTest.data.TestData;
import com.restAssuredTest.service.AccountService;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminSteps {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TestData testData;

    @Given("an authenticated admin")
    public void anAuthenticatedAdmin() {
        RestAssured.useRelaxedHTTPSValidation();
//        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig()
//                .trustStore("C:\\Program Files\\Java\\jre1.8.0_191\\lib\\security\\cacerts", "changeit").trustStoreType("JKS")
//                .keyStore(this.getClass().getResource(keyStore).getFile(), keyStorePassword).keystoreType("PKCS12"))
//        ) ;

testData.getAdmin().setAccessToken(accountService.getAdminAccessToken());

    }
}
