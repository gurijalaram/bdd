package com.alg.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        strict = false,
        monochrome = false,
        features = ("src/test/resources/"),
        glue = {"com.alg.steps"},
        plugin = {
                    "html:target/site/cucumber-html",
                     "json:target/cucumber1.json"},
        tags = {"@Portfolio"}
        )

public class TestRunner {

}
