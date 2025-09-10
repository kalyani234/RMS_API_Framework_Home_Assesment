#Feature: Manual Testing Scenarios for RMS API
#
#  # 1. Category Label Display
#  Scenario: Category label visibility depends on categories list
#    Given I have a list of episodes provided by the RMS API
#    When I examine the metadata of each episode
#    Then if the "categories" list contains one or more items
#    And the "labels" field should have a "category" value that is not empty
#    And if the "categories" list is empty or missing
#    Then the "labels.category" field should be either absent or present with an empty value
#
#  # 2. Episode Availability Period
#  Scenario: Check availability period for episode versions
#    Given I have access to the RMS API endpoint
#    When I send a GET request to the RMS API
#    Then the response should include at least one episode version with an "availability" object
#    And the "start" date in "availability" should be before today’s date
#    And the "end" date in "availability" should be after today’s date
#
#  # 3. 'Childrens' Field Validation
#  Scenario: Confirm 'childrens' field in every episode
#    Given I have access to the RMS API endpoint
#    When I send a GET request to the RMS API
#    Then the response should contain exactly 5 episodes
#    And each episode should include a "childrens" field
#    And the value of "childrens" should be either true or false
