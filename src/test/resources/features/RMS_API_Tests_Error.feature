Feature: RMS API Automation Error Tests
  As a tester, I want to validate the RMS API endpoint functionality
  So that I can ensure reliable radio and music services for BBC Sounds


  Scenario: Verify invalid date returns 404 with error object
    When I send a GET request with the following date:
      | date       | expectedStatus |
      | 2023-09-11 | 404            |
    Then the error object has properties "details" and "http_response_code"