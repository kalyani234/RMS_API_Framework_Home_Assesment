package com.bbc.stepdefinitions;

import com.bbc.model.*;
import com.bbc.utils.RMS_API_BaseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class RMS_API_StepDefs {

    private Response actualApiResponse;
    private RMSResponse parsedRmsResponse;

    @Given("the RMS API is up and running")
    public void theRmsApiIsUpAndRunning() {
        System.out.println("RMS API is assumed to be up and running via hooks.");
    }

    @When("I send a GET request to the RMS API")
    public void sendGetRequestToRmsApi() throws Exception {
        actualApiResponse = given().when().get(RMS_API_BaseUtils.getRmsApiEndpointUrl());
        System.out.println("Actual API Response: " + actualApiResponse.asString());
        if (actualApiResponse.getStatusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            parsedRmsResponse = mapper.readValue(actualApiResponse.asString(), RMSResponse.class);
            System.out.println("Parsed RMS Response: " + parsedRmsResponse.toString());
        } else {
            parsedRmsResponse = null;
        }
    }

    @When("I send a GET request with the following date:")
    public void sendGetRequestWithInvalidDate(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String date = row.get("date");
            int expectedStatus = Integer.parseInt(row.get("expectedStatus"));
            String invalidScheduleUrl = RMS_API_BaseUtils.getRmsApiEndpointUrl() + RMS_API_BaseUtils.getPathSeparator() + date;
            // System.out.println(invalidScheduleUrl);
            actualApiResponse = given().when().get(invalidScheduleUrl);

            assertEquals("Unexpected status code", expectedStatus, actualApiResponse.getStatusCode());
        }
    }

    @Then("the response status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, actualApiResponse.getStatusCode());
    }

    @Then("the response time is below the configured threshold")
    public void verifyResponseTime() {
        String thresholdStr = RMS_API_BaseUtils.get("responseTimeThreshold");
        assertNotNull("responseTimeThreshold not found in config", thresholdStr);
        long threshold = Long.parseLong(thresholdStr);
        long actual = actualApiResponse.getTime();
        System.out.println("Response time: actual=" + actual + "ms, threshold=" + threshold + "ms");
        assertTrue("Response time too high: " + actual + "ms (limit: " + threshold + "ms)", actual < threshold);
    }

    @Then("all 5 items in the elements array have a non-null and non-empty {string} field")
    public void verifyFieldNotNull(String fieldName) {
        List<Element> elements = parsedRmsResponse.getSchedule().getElements();
        assertEquals(5, elements.size());
        for (Element e : elements) {
            String value = null;
            if ("id".equals(fieldName)) {
                value = e.getId();
            } else {
                throw new IllegalArgumentException("Unsupported field: " + fieldName);
            }
            assertNotNull(value);
            assertFalse(value.trim().isEmpty());
        }
    }

    @Then("all 5 items have episode type {string}")
    public void verifyEpisodeType(String expectedType) {
        for (Element e : parsedRmsResponse.getSchedule().getElements()) {
            assertEquals(expectedType, e.getEpisode().getType());
        }
    }

    @Then("all 5 items in the elements array have a non-null and non-empty {string} field in episode")
    public void verifyEpisodeFieldNotNull(String fieldName) {
        for (Element e : parsedRmsResponse.getSchedule().getElements()) {
            String value = null;
            if ("title".equals(fieldName)) {
                value = e.getEpisode().getTitle();
            } else {
                throw new IllegalArgumentException("Unsupported field: " + fieldName);
            }
            assertNotNull(value);
            assertFalse(value.trim().isEmpty());
        }
    }

    @Then("exactly one item in the elements array has episode live field as true")
    public void verifySingleLiveEpisode() {
        long count = parsedRmsResponse.getSchedule().getElements().stream()
                .filter(e -> e.getEpisode() != null && e.getEpisode().isLive())
                .count();
        assertEquals(1, count);
    }

    @Then("all 5 items have transmission_start before transmission_end")
    public void verifyTransmissionOrder() {
        for (Element e : parsedRmsResponse.getSchedule().getElements()) {
            assertNotNull("transmission_start is null", e.getTransmission_start());
            assertNotNull("transmission_end is null", e.getTransmission_end());

            // Print raw values from JSON
            System.out.println("Raw transmission_start: " + e.getTransmission_start());
            System.out.println("Raw transmission_end:   " + e.getTransmission_end());

            Instant start = Instant.parse(e.getTransmission_start());
            Instant end = Instant.parse(e.getTransmission_end());

            // Print parsed values
            System.out.println("Parsed transmission_start: " + start);
            System.out.println("Parsed transmission_end:   " + end);

            // Duration between start and end
            Duration duration = Duration.between(start, end);
            System.out.println("Duration: " + duration.toMinutes() + " minutes (" + duration.toHours() + " hours)");


            assertTrue("Start not before end: " + start + " → " + end, start.isBefore(end));
            break;
        }
    }

    @Then("the response header {string} is present and valid")
    public void verifyHeaderPresent(String headerName) {
        // System.out.println(actualApiResponse.getHeaders());
        String header = actualApiResponse.getHeader(headerName);
        assertNotNull("Header " + headerName + " not found", header);
        assertFalse(header.trim().isEmpty());
    }

    @Then("the error object has properties {string} and {string}")
    public void verifyErrorObjectFields(String errorMessageField, String statusCodeField) {
        System.out.println(actualApiResponse.getBody().asPrettyString());
        Map<String, ?> errorMap = actualApiResponse.jsonPath().getMap("error");

        assertNotNull("Error object missing", errorMap);
        assertTrue("Missing error message: " + errorMessageField, errorMap.containsKey(errorMessageField));
        assertTrue("Missing status code: " + statusCodeField, errorMap.containsKey(statusCodeField));
    }

}
