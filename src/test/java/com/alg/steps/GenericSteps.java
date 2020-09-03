package com.alg.steps;

import com.alg.webdriver.WebConnector;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GenericSteps {
    WebConnector connector;

    public GenericSteps(WebConnector conn){
        this.connector = conn;
    }

    @Before
    public void before(Scenario scenario){
        this.connector.initReports(scenario.getName());
    }

    @After
    public void after(){
        this.connector.quit();
    }

    @Given("^open (.*)$")
    public void openBrowser(String browserName){
        //System.out.println("Opening Browser " + connector.name);
        this.connector.infoLog("Opening Browser " + connector.name);
        connector.openBrowser(browserName);
    }

    @And("^Navigate to (.*)$")
    public void navigate(String url){
        this.connector.infoLog("Navigating to url " + url);
        connector.navigate(url);
    }

    @And("^Enter (.*) in (.*)$")
    public void login(String username,String cssSelector){
        connector.type(username,cssSelector);
        this.connector.infoLog("Entering the username "+ username);
    }

    @And("^Click on (.*)$")
    public void click(String cssSelector){
        connector.click(cssSelector);
        this.connector.infoLog("Click on button "+ cssSelector);
    }


}
