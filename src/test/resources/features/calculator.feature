Feature: Calculator feature
  As a user,
  I should be able
  to use the calculator,
  de that I can do
  arithmetic operations.

  Scenario: Add 2 numbers
    Given calculator app is open
    When I add 2 with 2
    Then I should get result 4 displayed

  Scenario: Add 2 numbers another example
    Given calculator app is open
    When I add 5 with 4
    Then I should get result 9 displayed