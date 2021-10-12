Feature: Demonstrating the data table feature of cucumber

  Cucumber provide ability to organize repeating steps with different data
  using the data table feature and automatically convert the table into
  few known data types supported by cucumber

  Scenario: Petting zoo
    Given I have a "horse"
    And I have a "dog"
    And I have a "turtle"
    And I have a "zebra"
    When I call their names
    Then They come to me.

  Scenario: Petting zoo with table
    Given I have following animals
     # if you want it to nicely aligned , Command+Option+L  Control+Alt+L
      | horse  |
      | dog    |
      | turtle |
      | zebra  |
    When I call their names with below names
      | Kira    |
      | Doru    |
      | Tito    |
      | Zemfira |

    Then They come to me with below noise
      | horse  | Nai  |
      | dog    | Woof |
      | turtle | Hiss |
      | zebra  | Bro  |

  @ui
  Scenario: Login to weborder app by providing username password in 2 column table

    Given we are at web order login page
    When we provide below credentials
      | username | Tester |
      | password | test   |
    Then we should see all order page

  Scenario: Representing table with header and more than 2 columns with list of map
    # we want to represent each row as a map object with column name as key value as cell value
    # since we have multiple row , we want to store all row map into List of map
    Given this is the product reference
      | Product     | Price | Discount |
      | MyMoney     | 100   | 0.08     |
      | FamilyAlbum | 80    | 0.15     |
      | ScreenSaver | 20    | 0.1      |