@ui
Feature: Google Search

  As a user ,
  I should be able to search by keyword
  and get my result

  Background:
    Given user is at home page

  @wip
  Scenario: User search by keyword
#    Given user is at home page
    When user search for keyword "selenium"
    Then we should see result page
    And the title should start with "selenium"

  # New scenario to search something else
  @wip
  Scenario: User search by keyword java
#    Given user is at home page
    When user search for keyword "java"
    And the title should start with "java"
