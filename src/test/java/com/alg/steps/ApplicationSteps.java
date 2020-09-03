package com.alg.steps;

import com.alg.webdriver.WebConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Map;


public class ApplicationSteps {
    WebConnector connector;

    public ApplicationSteps(WebConnector conn){
        this.connector = conn;
    }

    @And("^Login into Application$")
    public void login(Map<String,String> data){
        System.out.println(data.get("Username"));
        System.out.println(data.get("Password"));
        this.connector.login(data.get("Username"), data.get("Password"));
    }

    @Then("^Login should be (.*)$")
    public void validateLogin(String expectedResult){
        this.connector.validateLogin(expectedResult);
    }

    @Then("^verify (.*)")
    public void verifyPage(String cssSelector){
        this.connector.isElementPresent(cssSelector);
    }


}
