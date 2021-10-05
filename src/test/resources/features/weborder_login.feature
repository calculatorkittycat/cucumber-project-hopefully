Feature: Web order app login
  As a user
  I should be able to login to web order app

  Scenario: User login successfully
    Given we are at web order login page
    When we provide valid credentials
    Then we should see all order page
