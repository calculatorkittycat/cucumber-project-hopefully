Feature: Eating too many cucumbers may not be good for you
  Eating too much of anything may not be good for you

  Scenario: Eating a few is no problem
    Given John is hungry
    When He eats 3 cucumbers
    Then he will be full

  Scenario: Eating too many is problem
    Given Ivan is hungry
    When He eats 10 cucumbers
    Then he faints

  Scenario Outline: eating
    Given there are <start> cucumbers
    When I eat <eat> cucumbers
    Then I should have <left> cucumbers

    Examples:
      | start | eat | left |
      |    12 |   5 |    7 |
      |    20 |  12 |   10 |
      |    33 |   5 |   15 |
      |    44 |  12 |   32 |