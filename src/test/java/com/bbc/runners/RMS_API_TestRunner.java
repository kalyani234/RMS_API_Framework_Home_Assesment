package com.bbc.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.bbc.stepdefinitions", "com.bbc.runners"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports"
//                "json:target/cucumber/cucumber.json",
//                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        monochrome = true,
        tags ="@Smoke"
)

public class RMS_API_TestRunner {
}
