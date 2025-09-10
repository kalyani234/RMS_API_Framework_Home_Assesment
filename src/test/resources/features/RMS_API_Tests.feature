Feature: RMS API Automation Tests
  As a tester, I want to validate the RMS API endpoint functionality
  So that I can ensure reliable radio and music services for BBC Sounds
# Author


  Background:
    Given the RMS API is up and running
    When I send a GET request to the RMS API

  @Smoke
  Scenario: Verify GET request returns 200 status and response time
  #  When I send a GET request to the RMS API
    Then the response status code is 200
    And the response time is below the configured threshold

  Scenario: Verify id field is never null or empty for all items
   # When I send a GET request to the RMS API
    Then all 5 items in the elements array have a non-null and non-empty "id" field
    And all 5 items have episode type "episode"

  Scenario: Verify title field is never null or empty
   # When I send a GET request to the RMS API
    Then all 5 items in the elements array have a non-null and non-empty "title" field in episode

  Scenario: Verify only one episode has live field as true
  #  When I send a GET request to the RMS API
    Then exactly one item in the elements array has episode live field as true

  @Smoke
  Scenario: Verify transmission start is before transmission end
   # When I send a GET request to the RMS API
    Then all 5 items have transmission_start before transmission_end

  Scenario: Verify response header Date field
   # When I send a GET request to the RMS API
    Then the response header "Date" is present and valid

#  @Scenario1
#  Scenario: Verify invalid date returns 404 with error object
#    When I send a GET request with the following date:
#      | date       | expectedStatus |
#      | 2023-09-11 | 404            |
#    Then the error object has properties "details" and "http_response_code"