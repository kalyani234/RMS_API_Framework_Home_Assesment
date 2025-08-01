package com.bbc.runners;

import com.bbc.utils.RMS_API_BaseUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class RMS_API_Hooks {

    // Setup method to initialize base URI before each scenario
    @Before
    public void setup() {
        String baseUrl = RMS_API_BaseUtils.get("baseApiUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        }
    }

    // Teardown method to reset RestAssured after each scenario
    @After
    public void teardown() {
        RestAssured.reset();
    }
}
