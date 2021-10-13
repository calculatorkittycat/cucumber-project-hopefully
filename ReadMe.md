
# Automation Framework with Cucumber

1. [BDD  Behaviour Driven Development](#bdd--behaviour-driven-development)
   1. [Benefit of BDD](#benefit-of-bdd)
   2. [Gherkin](#gherkin)
   3. [Step Definitions](#step-definitions)
2. [Cucumber Maven Project](#cucumber-maven-project)
   1. [Setting up project](#setting-up-project)
   2. [Adding Selenium Related Dependencies and classes](#adding-selenium-related-dependencies-and-classes)
3. [Cucumber Java with Selenium](#cucumber-java-with-selenium)
   1. [First Selenium Scenario](#first-selenium-scenario)
   2. [Hooks](#hooks)
   3. [More Scenarios and IntelliJ Step Definition generation](#more-scenarios-and-intellij-step-definition-generation)
   4. [Using Parameters with Cucumber Expression](#using-parameters-with-cucumber-expression)
   5. [Using `Background` to reuse `Given` condition](#using-background-to-reuse-given-condition)
   6. [Cucumber Tags](#cucumber-tags)
       1. [Tag expressions](#tag-expressions)
   7. [Creating TestRunner](#creating-testrunner)
   8. [Data tables](#data-tables)
   9. [Scenario Outline with Example](#scenario-outline-with-example)
   10. [Adding Screenshot to Failed Scenarios](#adding-screenshot-to-failed-scenarios)
   11. [Rerunning Failed Scenario](#rerunning-failed-scenario)
   12. [Using Third-Party HTML Reporter](#using-third-party-html-reporter)

# BDD  Behaviour Driven Development

![why_bdd](https://user-images.githubusercontent.com/59104509/136500909-2bb027b1-f6c9-42e0-a56c-bae74d307d67.png)

Behaviour Driven Development (or BDD) is a powerful collaborative methodology that helps teams focus on delivering high value features sooner and more reliably.

BDD builds on and extends standard agile practices such as sprint planning and backlog grooming, user stories and acceptance criteria, and makes them much more effective.

## Benefit of BDD
- Clear communication
- Fewer defects
- Higher quality, more innovative solutions
- Higher quality, easier to maintain automated test suites
- Documentation that is always up-to-date

Here is how typical process look like :
![bdd_process](https://user-images.githubusercontent.com/59104509/136500993-6afc1105-d44b-4fac-bc0f-58eac23e49f7.png)

Teams practicing BDD work together to discover and understand the real business needs behind a user story or feature.
- They explore the requirement by discussing examples and counter-examples of user and system behaviour with the business.
- This happens during a workshop often known as the "**Three-Amigos**" meeting
- The purpose of the **Three Amigos** workshop is not only to build up a deep shared understanding within the team, but also to uncover areas of uncertainty or incorrect assumptions that would typically only surface much later on.
- Teams often use techniques such as **Example Mapping** and **Feature Mapping** to facilitate the requirements discovery process.

The concrete examples that are created collaboratively in discovery workshops can be used to automatically verify that the software behaves consistently with these examples.

This happens by expressing those examples as executable software specifications in plain language that everyone on the team can understand using `Gerkin` Language.

## Gherkin
Gherkin is a set of grammar rules that makes plain text structured enough for Cucumber to understand.

Syntax usually follow this pattern

```gherkin
Given some condition is defined
When some actions are taken 
Then some result should be expected
```

For example:

```gherkin
Feature: Calculator feature
  As a user,
  I should be able to use the calculator to do
  arithmetic operations.

  Scenario: Add 2 numbers
    Given calculator app is open
    When I add 2 with 2
    Then I should get result 4 displayed
```

The specific examples (scenarios) of a Feature is written in `Gerkin`
in a `Feature` file (a file with .feature extension).

Here is the [Gherkin reference doc](https://cucumber.io/docs/gherkin/reference/).

These specifications (called scenarios) are then executed by tools (That's the reason it's also called executable specification)
and That's where **cucumber** comes in.

[Cucumber](https://cucumber.io/docs/cucumber/) is a tool to support the BDD Process
and write automated acceptance test to provide fast feedback.

Cucumber reads executable specifications written in plain text Gherkin
and validates that the software does what those specifications say.

<img width="300" alt="Gherkin" src="https://user-images.githubusercontent.com/59104509/136503381-c8823f3f-e4d8-43b6-8ca9-a8b8fd174c40.png">

Cucumber support couple languages java , js , ruby and so on.
we will be using cucumber for java cucumber-jvm.

## Step Definitions
A Step Definition is a Java method with an expression that links it to one or more Gherkin steps. When Cucumber executes a Gherkin step in a scenario, it will look for a matching step definition to execute.

A step definition carries out the action that should be performed by the step.
So step definitions hard-wire the specification to the implementation.

```
┌────────────┐                 ┌──────────────┐                 ┌───────────┐
│   Steps    │                 │     Step     │                 │           │
│ in Gherkin ├──matched with──>│ Definitions  ├───manipulates──>│  System   │
│            │                 │              │                 │           │
└────────────┘                 └──────────────┘                 └───────────┘
```

For example :

```feature
Given calculator app is open
```

will match a java method as below

```java
@Given("calculator app is open")
public void calculator_app_is_open() {
    System.out.println("@Given calculator_app_is_open");
}
```

And this step

```gherkin
When I add 2 with 2
```

will match a java method as below

```java
@When("I add 2 with 2")
public void i_add_2with_2() {
 // addition actions here
}
```

Steps can be reused as long as the definition matches.

The steps can be parameterized as well for example , instead of creating new steps for adding different number , we can use the cucumber expression to parameterize as below

```java
@When("I add {int} with {int}")
public void i_add_with(Integer num1, Integer num2) {
    System.out.println("When I add "+num1+ " with "+ num2);
}
```

Above step definition can be used for all steps below

```gherkin
When I add 2 with 2
When I add 10 with 12
When I add 200 with 500
```

Here are the other parameter type available :
- `{int}`	Matches integers, for example 71 or -19.
- `{float}`	Matches floats, for example 3.6, .8 or -9.2.
- `{byte}`, `{short}`, `{long}` and `{double}`.
- `{word}`	Matches words without whitespace, for example banana (but not banana split).
- `{string}`	Matches single-quoted or double-quoted strings, for example "banana split" or 'banana split' (but not banana split). Only the text between the quotes will be extracted. The quotes themselves are discarded.
- {} anonymous

Here is another example
![step-matching](https://user-images.githubusercontent.com/59104509/136514878-fa69d116-5f75-4e49-bb18-4d08ba8c3384.png)


--- 
# Cucumber Maven Project

## Setting up project

1. Create a maven project with name `cucumber-project-b23`
    1. Select java 8
    2. group id : `com.cydeo`
    3. artifact id : leave it as is
    4. Click finish
       ![maven-project](https://user-images.githubusercontent.com/59104509/136505891-474e0f17-5e4c-409b-8ecd-19807ccafccc.png)
2. create folder structure as below
    1. under `src/test/java` create below packages
    2. create a package `com.cydeo` ,under this package
        1.  create `utility` package
        2.  create `pages` package
        3.  create `step_definitions` package
        4.  create `runner` package
3. create a directory(folder) under `src/test`
    1. when asked for the name , select `resources` from dropdown it provided
    2. under resources create a folder `features`

   ![folder-structure](https://user-images.githubusercontent.com/59104509/136506116-447d77b2-6bda-426c-a4d5-50d09e58a006.png)
4. add dependencies into your `pom.xml`
    1. `cucumber java` dependency
    ```xml
       <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>6.11.0</version>
        </dependency>
    ```
   
    2. `cucumber junit` dependency
   
     ```xml    
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
      <dependency>
          <groupId>io.cucumber</groupId>
          <artifactId>cucumber-junit</artifactId>
          <version>6.11.0</version>
      </dependency>
     ```
   
5. Add a feature file called `eating_cucumber.feature` under `src/test/resources/feature`
    1. copy the example feature file from homepage
    2. add below content
   
    ```feature
    Feature: Eating too many cucumbers may not be good for you
    Eating too much of anything may not be good for you

    Scenario: Eating a few is no problem
        Given John is hungry
        When He eats 3 cucumbers
        Then he will be full
    ```
   
   ![creating_feature_file](https://user-images.githubusercontent.com/59104509/136507381-4c42b414-d4ae-48b6-b796-4c8ddf8361e1.gif)

6. Create a new class `EatStepDef` under step definitions
7. **Run the feature file by using run button beside feature scenario**
8. Copy the code from the console error and remove the content of method with your own
9. run again to see the scenario pass ,

Here is the full [EatStepDef](src/test/java/com/cydeo/step_definitions/EatStepDef.java) class.

Here is another example [jobs.feature](src/test/resources/features/jobs.feature) we added
and here is the step definition class [JobStepDef](src/test/java/com/cydeo/step_definitions/JobStepDef.java) class.

Here is [calculator.feature](src/test/resources/features/calculator.feature)
to demonstrate the parametrizing and reusing steps


## Adding Selenium Related Dependencies and classes

1. add selenium dependency

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
</dependency>
```

- add WebDriverManager Dependency

 ```xml
 <dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.0.2</version>
</dependency>
```


- add faker dependency (this is not selenium dependency, we will use it for random data at some point)

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

2. copy all `utility classes` from previous project under utility package.
3. copy all `pages` from previous project and place it under pages package.
4. add `config.properties` file from previous project.



# Cucumber Java with Selenium

## First Selenium Scenario

Here is the initial feature file we wrote for WebOrder login page

```gherkin
Feature: Web order app login
  As a user
  I should be able to login to web order app
 
  Scenario: User login successfully
    Given we are at web order login page
    When we provide valid credentials
    Then we should see all order page
```

Here is the step definition class we created to match steps

```java
    package com.cydeo.step_definitions;
    
    import com.cydeo.pages.WLoginPage;
    import com.cydeo.utility.Driver;
    import io.cucumber.java.en.And;
    import io.cucumber.java.en.Given;
    import io.cucumber.java.en.Then;
    import io.cucumber.java.en.When;
    // cucumber-junit is based on junit 4 
    // only thing different from what we do is this static import
    // all assertions are coming from this package instead
    import static org.junit.Assert.*;
    
    public class WebOrderLoginStepDef {
        // putting this at class level, so it can be accessible in all methods
        WLoginPage loginPage ;
    
        @Given("we are at web order login page")
        public void we_are_at_web_order_login_page() {
            loginPage = new WLoginPage() ;
            loginPage.goTo();
        }
        @When("we provide valid credentials")
        public void we_provide_valid_credentials() {
            loginPage.login("Tester", "test");
        }
        @Then("we should see all order page")
        public void we_should_see_all_order_page() {
            // check we are at the all order page by checking the title is Web Orders
            assertEquals("Web Orders", Driver.getDriver().getTitle() );
        }
    }

```

Unlike previous project ,
we do not have test base to automatically set up and teardown driver once we are done.
Browser will not automatically close after test and implicit wait will not be set before scenario run.

So how do we do it ? with cucumber hooks!

## Hooks
Hook is a commonly used term in cucumber
to illustrate the idea of running some code right before and after each scenario.

In our case, we wanted to set up driver and implicit wait before each scenario
and tear down driver after each scenario.

Here is how cucumber does it using **cucumber annotation**
- `@Before` coming from `import io.cucumber.java.Before;`
- `@After` coming from `import io.cucumber.java.After;`

All we have to do is create a class
and add methods with above annotation as below :

```java
package com.cydeo.step_definitions;
import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.util.concurrent.TimeUnit;

public class Hooks {
    // We can set up a hook class that contains
    // methods that run before each scenario and after each scenario
    // or even when we learn tags
    // we can run certain code before and after each scenario that tagged with certain tag
    @Before
    public void setupDriver(){
        System.out.println("THIS IS FROM @Before inside hooks class");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(){
        System.out.println("THIS IS FROM @After inside hooks class");
        Driver.closeBrowser();
    }
}
```

**No Inheritance needed anywhere! NEAT!**

Now we can assume it will set up and close the browser for each scenario everywhere.
> note that it will also do that for non-ui scenarios like `calculator.feature`. more on that later with tags.


## More Scenarios and IntelliJ Step Definition generation

Here is another scenario we added

```gherkin
  Scenario: User login fail
    Given we are at web order login page
    When we provide invalid credentials
    Then we should still be at login page
    And login error message should be present
```

IntelliJ has full support for cucumber and gherkin with pre-bundled plugins.

It's easy to generate step definition methods directly without running the feature.

![Create_Step_Def_Directly_From_IntelliJ (1)](https://user-images.githubusercontent.com/59104509/136510803-8f5e6b77-eae8-4a70-8f8d-e42865d328f0.gif)


and matching step definitions without duplicate

```java
    @When("we provide invalid credentials")
    public void weProvideInvalidCredentials() {
        loginPage.login("bla","bla");
    }
    
    @Then("we should still be at login page")
    public void weShouldStillBeAtLoginPage() {
         assertEquals("Web Orders Login", Driver.getDriver().getTitle()) ;
    }
    
    @And("login error message should be present")
    public void loginErrorMessageShouldBePresent() {
        assertTrue(   loginPage.loginErrorMsgPresent()  );
    }
```

## Using Parameters with Cucumber Expression

As we did with calculator example ,
it's easy to reuse steps using parameters.

Here is different style of scenario that contains credentials to demonstrate the point.

```gherkin
 Scenario: User login with specific credentials
    Given we are at web order login page  
    # whatever is enclosed inside quotation " " will be send as parameter value
    # step definition will look like this
    # @When("user provide username {string} and password {string}")
    When user provide username "Tester" and password "test"
    Then we should see all order page

  Scenario: User login with wrong credentials
    Given we are at web order login page
    When user provide username "BLA" and password "BLA"
    Then we should still be at login page
    And login error message should be present
```

So now step definitions will be as below without duplicate

```java
@When("user provide username {string} and password {string}")
public void userProvideUsernameAndPassword(String username, String password) {
    // username = "whatever provided from scenario step"
    // password = "whatever provided from scenario step"
    loginPage.login( username, password );
}
```

![parameters_with_cucumber_expression](https://user-images.githubusercontent.com/59104509/136516892-f1a6cdd1-b08d-46bf-9007-56a5673c7be4.jpg)

## Using **Background** to reuse Given condition
As we progress in above 4 scenarios,
We found that all of them repeating the below step in `Given` part

```gherkin
Given we are at web order login page
```

So we can reuse this step in each scenario using new keyword `Background`

```gherkin
Feature: Web order app login
  As a user
  I should be able to login to web order app
  # This is where repeating beginning code can go , this is how we comment in feature file
  Background:
    # This is a shared step for all scenarios so e can remove duplicate
    # by putting it in Background section of feature
    Given we are at web order login page
```

Now we can remove this repeating `Given` section in all scenarios

```gherkin
Feature: Web order app login
  As a user
  I should be able to login to web order app

  # This is where repeating beginning code can go , this is how we comment in feature file
  Background:
    # This is a shared step for all scenarios so e can remove duplicate
    # by putting it in Background section of feature
    Given we are at web order login page

  Scenario: User login successfully
#    Given we are at web order login page
    When we provide valid credentials
    Then we should see all order page

  Scenario: User login fail
#    Given we are at web order login page
    When we provide invalid credentials
    Then we should still be at login page
    And login error message should be present

## other scenarios omitted here
```

## Cucumber Tags
Tags are a great way to organise your features and scenarios.

They can be used for two purposes:

- Running a subset of scenarios
    - (using test runner in next section)
- Restricting hooks to a subset of scenarios
    - for example only open browser and close browser for scenarios tagged with `@ui`.
  
    - ```java
      @Before("@ui")
      public void setupDriver(){
          Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
          Driver.getDriver().manage().window().maximize();
      }

      @After("@ui")
      public void tearDown(){
          Driver.closeBrowser();
      }
      ```

      ![conditional_hook](https://user-images.githubusercontent.com/59104509/136515272-20471767-06ed-44c2-abc2-d9cb52b26950.jpg)

Tags are used in feature file at feature level or scenario level.

Since feature file is not source code,
any tag with any name (without space or reserved characters) can be used.

If a tag is used at feature level ,
all scenarios under same feature will inherit it.

```gherkin
## below line is how we add tag to a feature at feature level
  ## you can add more than one tags on anything
@calculator @non_ui
Feature: Calculator feature
  As a user,
  I should be able to use the calculator,
  so that I can do arithmetic operations.

  ## below line is how we add tag to a feature at scenario level
  @addition
  Scenario: Add 2 numbers
    Given calculator app is open
    When I add 2 with 2
    Then I should get result 4 displayed

  @smoke
  Scenario: Add 2 numbers another example
    Given calculator app is open
    When I add 5 with 4
    Then I should get result 9 displayed
```

### Tag expressions
A tag expression is a boolean expression. Below are some examples:

| Expression            | Description |
|       -----           |   ----      |
| `@fast`               | Scenarios tagged with `@fast` |
| `@wip and not @slow`  | Scenarios tagged with `@wip` that aren’t also tagged with `@slow` |
| `@smoke and @fast`    | Scenarios tagged with both `@smoke` and `@fast` |
| `@ui or @database`    | Scenarios tagged with either `@ui` or `@database` |


## Creating TestRunner
We have been running the feature directly from the feature file.
While it is convenient to get started,
It will limit ability to have more granular control over what we want to run and how.

Cucumber encourage the usage of separate Test runner class with many built in options

Runner class has only one purpose - run features according to instruction.
It's always empty and use special annotations as below

- `@RunWith(Cucumber.class)` to define this is for running cucumber
- `@cucumberOptions` to define all related configurations

for example :
- where are the feature files :
  - `features = "src/test/resources/features"`
  - alternatively `features = "classpath:features"` **SHORTER!**
    - anything under `src/test/resources` can be referred by `classpath:`
- where are the step definitions (glue code):
  - `glue = "com/cydeo/step_definitions"`
- quick check missing step definitions without actually running steps
  - `dryRun=true`
      - it will only check missing definition and provide it on console if any
      - it will save time by giving immediate check instead of wasting time running the whole thing and find out we have missing step definition.
      - `dryRun=false`
          - it's the default value and will run all scenarios and error out if missing step definition exists
- filter using tags
  - `tags = "@smoke"`
      - run any feature or scenario that tagged with `@smoke`
- html reports , json reports , pretty console report and so on with built-in plugins
  - `plugin = {"html:target/cucumber.html"}` 
    - This will generate simple html report under target folder with a name `cucumber.html`
    - ![Viewing_built_in_cucumber_report](https://user-images.githubusercontent.com/59104509/136936129-6e54eeaa-4c50-4e5c-b5c6-e3bdea3f4a71.gif)
  - `plugin = {"pretty", "html:target/cucumber.html"}`
    - `pretty` plugin will generate colorful console report
    - ![console_pretty_report](https://user-images.githubusercontent.com/59104509/136937765-03aac670-c5fa-4554-a6a5-1b074b5429d7.jpg)
  - `plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json}`
    - This will generate a report in a format known as `json` (Javascript Object notation)
    - ![viewing_built_in_json_report](https://user-images.githubusercontent.com/59104509/136938657-d6770387-da03-4697-a9fd-cf4fda766d16.gif)
    - Third party reporting tools can use this report to easily customize the report view with rich ui.
      - for example,  we will use third party reporting from `masterthought` later using this.

- publishing generated built-in html report 
  - since cucumber version 6.7+ , it can be published to temporary location publicly to share report with others
    - > It's relatively new service that still in development
  - It can be done simply adding one more cucumber option as `publish=true`
  - ![cucumber_html_report_publish](https://user-images.githubusercontent.com/59104509/137245096-d85afdf9-7c09-406d-997a-42f3c48ab8f7.jpg)
  - Here is the report after clicking on the link 
  - ![published_cucumber_report](https://user-images.githubusercontent.com/59104509/137245330-0ab46101-bbfe-4a4d-a0f6-390df525414b.jpg)

- storing the list of failed scenario for **rerun** later.(instead of running the whole thing). will talk about this separately in it's own section. 

```java
  package com.cydeo.runner;
      import io.cucumber.junit.Cucumber;
      import io.cucumber.junit.CucumberOptions;
      import org.junit.runner.RunWith;
    
      @RunWith(Cucumber.class)
      @CucumberOptions(  features = "src/test/resources/features" ,
                         glue = "com/cydeo/step_definitions" ,
                         dryRun = false, 
                         plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json"},
                         publish = true , 
                         tags = "@smoke"
                      )
      public class TestRunner {
          // NOTHING GOES HERE!
      }
```

If we run above test runner ,
it will only run the scenarios that tagged with `@smoke` tags 
and generate pretty console report , json report , html report. 


## Data tables

Instead of repeating same steps that have different data,
we can use table to represent the structure for readability

Data tables from Gherkin can be accessed by using the `DataTable` object as the last parameter 
in a step definition. 
This conversion can be done either by Cucumber or manually.

Depending on the table shape as one of the following collections:

- Table with single column `List<String>`
```gherkin
   Scenario: 
   Given I have following animals
   # if you want it to nicely aligned , Command+Option+L  Control+Alt+L
      | horse  |
      | dog    |
      | turtle |
      | zebra  |
```
- Table with first column as column name second column as value 
  - `Map<String, String> animalNoiseMap` or `List<List<String>> animalAndNoiseList`

```gherkin
    Then They come to me with below noise
      | horse  | Nai  |
      | dog    | Woof |
      | turtle | Hiss |
      | zebra  | Bro  |
```

- Table with headers and multiple columns : 
  - `List<Map<String, TypeGoesHere>> productMapList`

```gherkin
    Given this is the product reference
      | Product     | Price | Discount |
      | MyMoney     | 100   | 0.08     |
      | FamilyAlbum | 80    | 0.15     |
      | ScreenSaver | 20    | 0.1      |
```
> Extras as below 

- Table with no header , 2+ column , first column as header : 
  - `Map<String, List<String>> productMap`
  - `productMap.get("ScreenSaver").get(0)` => 20

```gherkin
    Given this is the product reference
      | MyMoney     | 100   | 0.08     |
      | FamilyAlbum | 80    | 0.15     |
      | ScreenSaver | 20    | 0.1      |
```

- Table with first column no header , 2+ column has header  
  - `Map<String, Map<String, String>> groupsMap`
  - first column as key for the map 
  - second map hold each column as key and cell as value 
  - `groupsMap.get("group3").get("heroName")` => `Wonder Woman`

```gherkin
    Given this is the group leader information
        |             | firstName | lastName | heroName     |
        | group1      | Clark     | Kent     | Superman     |
        | group2      | Bruce     | Wayne    | Batman       |
        | group3      | Diana     | Prince   | Wonder Woman | 
        | group4      | Barry     | Allen    | Flash        | 
```
  
Here is the [datatable.feature](src/test/resources/features/datatable.feature) file 
we used to demonstrate few examples above. 

Here is the step definition [DatatableStepDefs](src/test/java/com/cydeo/step_definitions/DatatableStepDefs.java). 

Here is web order app simple login scenario we practiced to use data table.

```gherkin
  @ui
    Scenario: Login to weborder app by providing username password in 2 column table
    
    Given we are at web order login page
    When we provide below credentials
    | username | Tester |
    | password | test   |
    Then we should see all order page
```

And here is the only step definition we do not have for  `When we provide below credentials` step. 

```java
@When("we provide below credentials")
public void weProvideBelowCredentials(Map<String, String> credentialMap) {

    System.out.println("credentialMap = " + credentialMap);
    String usernameFromTable = credentialMap.get("username");
    String passwordFromTable = credentialMap.get("password");

    WLoginPage loginPage = new WLoginPage();
    loginPage.login( usernameFromTable, passwordFromTable  );
}
```

Here is web order app simple scenario we practiced to check expected product list 

```gherkin
  Scenario: User should see all product from the list according to available product
    Given we are at web order login page
    And we provide valid credentials
    When we select "Order" tab from sidebar
    Then we should see below options in Product dropdown list
      | MyMoney     |
      | FamilyAlbum |
      | ScreenSaver |
```

We have first 2 steps already from previous classes. 

Here are the two step-definitions missing :

```gherkin
 When we select "Order" tab from sidebar
```
```java
 @When("we select {string} tab from sidebar")
    public void weSelectTabFromSidebar(String tabName) {
        System.out.println("tabName = " + tabName);
        WCommonArea commonArea = new WCommonArea();
        // Do not leave it here like this , create a method to select side bar
        // so we can select according to side bar tabName
        commonArea.orderTab.click();
        // it should look something this 
        // commonArea.selectSideBarTab( tabName ) ;
    }
```

```gherkin
  Then we should see below options in Product dropdown list
        | MyMoney     |
        | FamilyAlbum |
        | ScreenSaver |
``` 

```java
    @Then("we should see below options in Product dropdown list")
    public void weShouldSeeBelowOptionsInProductDropdownList(List<String> expectedOptions) {

        System.out.println("expectedOptions = " + expectedOptions);
        WOrderPage orderPage = new WOrderPage();
        // we have created this method to hide all selenium details in page object class
        List<String> actualOptions = orderPage.getAllProductOptionFromList();
        // assert these two list are equal
        // import static org.junit.Assert.assertEquals;
        assertEquals(expectedOptions, actualOptions) ;
    }
```

### More Practice on **Datatable** : 

Task 1 : 

```gherkin
  Scenario: User should see side bar tabs as expected
    Given we are at web order login page
    When we provide valid credentials
    Then we should see all order page
    And side bar tabs should be as below 
      | View all orders   |
      | View all products |
      | Order             |
```
Task 2 :

```gherkin
  Scenario: User should see product table as expected
    Given we are at web order login page
    And we provide valid credentials
    When we select "View all products" tab from sidebar
    And we should see table with below content
    # for the same of simplicity below table is modified to match exactly to actual table
        | Product     | Price | Discount |
        | MyMoney     | $100  | 8%       |
        | FamilyAlbum | $80   | 15%      |
        | ScreenSaver | $20   | 10%      |
```

Task 3 :

```gherkin
  Scenario: User should see correct product price generated
    Given we are at web order login page
    And we provide valid credentials
    When we select "Order" tab from sidebar
    Then we should see three section as below
        |Product Information|
        |Address Information|
        |Payment Information|
    And select each product from dropdown should change the unit price accordingly
        | ScreenSaver | 20   |
        | MyMoney     | 100  |
        | FamilyAlbum | 80   | 
      # you need to loop to select each item and assert unit price box
```

Task 4 :

```gherkin
  Scenario: User should see correct product price and discount generated with total price
    Given we are at web order login page
    And we provide valid credentials
    When we select "Order" tab from sidebar
    Then selecting blow product and quantity should show correct total and discount
        | Product     | Price | quantity | Discount | Total |
        | ScreenSaver | 20    | 5        | 0        | 100   |
        | MyMoney     | 100   | 5        | 0        | 500   |
        | FamilyAlbum | 80    | 5        | 0        | 400   |
        | ScreenSaver | 20    | 10       | 10       | 180   |
        | MyMoney     | 100   | 10       | 8        | 920   |
        | FamilyAlbum | 80    | 10       | 15       | 120   |
      # you need to loop to select each item and assert discount box and total box
```

Task 5 :

```gherkin
  Scenario: User should see correct error messages
    Given we are at web order login page
    And we provide valid credentials
    When we select "Order" tab from sidebar
    And submit the form 
    Then below error messages should be visible on screen
        | Quantity must be greater than zero.     | 
        | Field 'Customer name' cannot be empty. | 
        | Field 'Street' cannot be empty.     |
        | Field 'City' cannot be empty. | 
        | Field 'Zip' cannot be empty. | 
        | Field 'Card Nr' cannot be empty. |
        | Field 'Expire date' cannot be empty. |
```


## Scenario Outline with Example

It's common that sometime we run same set of steps against multiple different set of data. 

Few examples we have already seen till now are :
- logging in with different credentials 
- adding different numbers in calculator feature
- searching for different keywords in google 
- or entering different set of order data in web order app 

Out initial approach is to parameterize the data with cucumber expressions like 
- `{int}` for number 
- `{word}` for single word (without quotation)
- `{string}` for any string enclosed in quotation and so on

For example in this [Google search feature file](src/test/resources/features/google_search.feature) : 

```gherkin
  Scenario: User search by keyword
    Given user is at home page
    When user search for keyword "selenium"
    Then we should see result page
    And the title should start with "selenium"
  # New scenario to search something else
  Scenario: User search by keyword java
    Given user is at home page
    When user search for keyword "java"
    And the title should start with "java"
```

If we have to search for more keywords,  then we will have to write more scenarios.  
even thought we do not need to implement new step definitions. 

Cucumber provide a way to data drive same scenario with different set of data 
(also known as data driven testing) to remove duplicates and simplify the feature file. 

**Scenario Outline** is used to run same scenario against multiple different set of data.

The data is provided under `Examples :` section as table, and it's **required**. 

We can refer the table data using `<columnName>` syntax in gherkin steps
these data will be available in your step definitions as method params like before. 

Below is the `Scenario Outline: `[example for Google search ](src/test/resources/features/google_search_data_driven.feature):

```gherkin
Feature: Google Search Data Driven
  As a user ,
  I should be able to search by keyword
  and get my result for multiple set of data
  
## modify this to make it data driven scenario that search for multiple keywords
  Scenario Outline: User search by keyword "<keyword>"
    Given user is at home page
    When user search for keyword "<keyword>"
    Then the title should start with "<keyword>"
    Examples:
      | keyword                        |
      | cybertruck                     |
      | selenium                       |
      | steve jobs                     |
      | sdet jobs                      |
      | how to make 100k by being SDET |
```

When running above Scenario with example , 
it will run for 5 iteration with different keywords and show in report as below. 

![scenario_outline_example](https://user-images.githubusercontent.com/59104509/137189104-63b268e9-0feb-4107-9223-ba67df92b837.jpg)


Here is the [calculator example](src/test/resources/features/calculator_with_data.feature)
we did with multiple column 

```gherkin
@calculator @non_ui
Feature: Calculator feature with data
  As a user,
  I should be able to use the calculator,
  so that I can do arithmetic operations.

  Scenario Outline: Add 2 numbers multiple example
    Given calculator app is open
    When I add <num1> with <num2>
    Then I should get result <expectedResult> displayed
    Examples:
      | num1 | num2 | expectedResult |
      | 3    | 4    | 7              |
      | 4    | 7    | 1              |
      | 6    | 11   | 17             |
      | 13   | 4    | 17             |
      | 41   | 7    | 48             |
      | 60   | 11   | 71             |
```

Above scenario outline will run for 6 iteration with different set of data. 
data can be referred as `<columnName>` as mentioned above. 

Here is the report with failure included. 

![scenario_outline_with_example_failure](https://user-images.githubusercontent.com/59104509/137190023-f58e212d-9930-42ed-91be-5a12658b14d6.jpg)

Here is the library [library app login feature](src/test/resources/features/library_login.feature) we practiced : 

```gherkin
@ui @library_login
Feature: Logging into Library app
  As a user
  I should be able to login to library app

  Scenario Outline: Login with valid credentials

    Given user is at library login page
    When user use username "<email>" and passcode "<password>"
    Then user should be at dashboard page
    Examples:
      | email               | password  |
      | student42@library   | Sdet2022* |
      | student43@library   | Sdet2022* |
      | student44@library   | Sdet2022* |
      | librarian54@library | Sdet2022* |
      | librarian15@library | Sdet2022* |
```

Above scenario outline will run for 5 iteration with different set of data.
data can be referred as `<columnName>` as mentioned above.

![scenario_outline_example2](https://user-images.githubusercontent.com/59104509/137190758-8c6d7240-a03f-45c9-840d-2fc00ec7865e.jpg)

**It's possible to use data table and scenario outline together if it's needed.** 

Be sure to understand that table goes under `Example:` for running multiple iteration of tests. 
and data table are only for single(current) iteration.

```gherkin
@ui @library_login
Feature: Logging into Library app
  As a user
  I should be able to login to library app

  Scenario Outline: Login with valid credentials

    Given user is at library login page
#    When user use username "<email>" and passcode "<password>"
    # implement this new step with parameter Map<String,String>
    When we enter valid credentials as below
      | username | <email>    |
      | passcode | <password> |
    Then user should be at dashboard page
    Examples:
      | email               | password  |
      | student42@library   | Sdet2022* |
      | student43@library   | Sdet2022* |
      | student44@library   | Sdet2022* |
      | librarian54@library | Sdet2022* |
      | librarian15@library | Sdet2022* |
```

First table is a data table and first column is serving as header 
and can be easily converted to `Map<String,String>`

Second table is example table that run for multiple iteration of scenario for each row of data. 

First table is filling up second column data using the `Example` table of scenario outline using `<columnName>` syntax.
So each iteration the data table will have different credentials value.

![scenario_outline_data_table_together](https://user-images.githubusercontent.com/59104509/137199906-207071f7-82ed-45bb-9bf9-a15f8f094781.jpg)


Here is the missing step definition implementation 

```java
    @When("we enter valid credentials as below")
    public void weEnterValidCredentialsAsBelow(Map<String,String> credentialMap) {
        String email    = credentialMap.get("username");
        String password = credentialMap.get("passcode");
        loginPage.login(email, password);
    }
```

## Adding Screenshot to Failed Scenarios

We have used hook to run certain codes before and after each scenario 
using @Before and @After annotation from cucumber on top of methods in [Hooks](src/test/java/com/cydeo/step_definitions/Hooks.java) class. 

And we also used cucumber tags to restrict running of such methods for scenarios with certain tags. 

In our case , it only makes sense to set up and teardown driver if it's a UI test. 

So we have marked our browser related scenarios with `@ui` tag. 

When a scenario fail, only way to find out what fail at this moment is by looking at the log. 
And yet it would make sense for UI scenarios to 
have a screenshot of the moment of failure 
and attached to our html report with proper name.

It brings up two questions as below : 
- How to take screenshot in selenium (pure selenium functionality) ? 
- Where to put such screenshot code and how to access scenario information like pass fail and names ? 

Let's start with taking screenshot in selenium using `TakeScreenShot` interface. 

![WebDriver_UML_TakeScreenShot](https://user-images.githubusercontent.com/59104509/137210398-113206a0-3387-4bc8-a06e-c8b7744aab07.jpg)

Just like we used, `JavaScriptExecutor` interface for running javascript, 
`TakeScreenShot` interface from `import org.openqa.selenium.TakesScreenshot;` 
has single method called `getScreenshotAs` to save screenshot of current window.

Here is how it works : 
- get a `TakeScreenShot` reference out of `WebDriver` instance 
```java
TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
```
- save the file using `getScreenshotAs` method and define `OutputType`.
- here we are selecting output type as byte array because cucumber report expect byte array later when we attach the screenshot to report.
```java
final byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
```

Let's now take a look at where we can use this screenshot in code. 
Logical location will be `@After` hook only if scenario fail.

In all hook methods , we have option to inject a parameter with type of `Scenario` 
so we can get scenario information like pass fail and name and so on. 

Here is how it looks like : 

```java
@After(@ui)
public void tearDown(Scenario scenario){

      //  scenario.isFailed() 
        //  return true if scenario run failed false if not
      // scenario.getName() 
        // reture the name of current scenario
      // scenario.attach( byteArray, "mediaType", "name of screenshot")
        // this is the method that accept 3 arguments 
          //  byteArray represent image
          //  media type , image/png for picture
          //  name  :whatever name we define, we will use scenario name
}
```

We will take screenshot only when scenario fails, 
so here is how our final hook `@After(@ui)` method look like 

```java
@After("@ui")
public void tearDown(Scenario scenario){
    System.out.println("THIS IS FROM @After inside hooks class");
    if(scenario.isFailed()){
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver() ;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png",scenario.getName());
    }
    Driver.closeBrowser();
}s
```

So now if any UI scenario fails, we can view screenshot from the report as below. 

![errorScreenshotInReport](https://user-images.githubusercontent.com/59104509/137213835-e2c5182b-3fdf-44cd-8108-5073ce7d193b.jpg)

## Rerunning Failed Scenario

Sometimes scenarios can fail due to network issue or any unexpected temporary issues to cause scenario fail. 

Sometimes we have failure in code, and we fix the issue by adding new code. 

In both above scenario, it makes sense to just run failed scenarios instead of running entire test suite. 

In order to do that , we will need to know exactly what scenarios failed, 
so we can use that information for next run. 

And we will need separate runner just for running those failed scenario we captured from above step. 

It's rather simple to capture failed scenario and save it into a text file 
using cucumber built-in `rerun` plugin as below:

```
plugin = {"rerun:target/rerun.txt"}
```

In addition to the plugin list we already have, it will do :  
1. create a file called `rerun.txt` under target folder
2. store failed scenario inside the text file, so we can use it in separate failed test runner. 

So next step is creating `FailedTestRunner` class as below 

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt" , //@target/rerun.txt will instruct cucumber to use the content of rerun.txt as features to run
        glue = "com/cydeo/step_definitions" ,
        plugin = {"pretty", "html:target/failed_cucumber.html"  } // optionally
)
public class FailedTestRunner {
}
```

Now we have 2 runners classes
- one that run any scenario we specified 
- another that run only failed scenarios. 
**Neat!**

## Using Third-Party HTML Reporter

We learned to use built-in simple html report plugin and publish it to temporary location. 

There are a lot of third party tools that can take the result of cucumber run 
and generate much more gradual report with rich statistic and ui. 

Here is the [full list](https://cucumber.io/docs/cucumber/reporting/#third-party-plugins) from the official documentation. 

We will be using first one `masterthought` plugin 
, and yet we will use [cucumber reporting plugin](https://gitlab.com/jamietanna/cucumber-reporting-plugin) 
and make it even easier to generate same report using `masterthought` internally with few step below.

Steps : 
- Add the latest dependency into your `pom.xml` file

```xml
<dependency>
  <groupId>me.jvt.cucumber</groupId>
  <artifactId>reporting-plugin</artifactId>
  <version>5.3.1</version>
</dependency>
```

- As optionally recommended, create a file `cucumber-reporting.properties` under root directory with below content

```properties
# This will become the report name
projectName=Cucumber Automation Framework
# This is optional build number you can add to report
buildNumber=release 1.0
```

- Add the plugin into your `TestRunner` class as below 

```java
@RunWith(Cucumber.class)
@CucumberOptions(  features = "src/test/resources/features" , //alternatively "classpath:features"
                   glue = "com/cydeo/step_definitions" ,
                   plugin = {"pretty", "html:target/cucumber.html",
                                "json:target/cucumber.json", // this will generate json report
                                "rerun:target/rerun.txt", 
                                "me.jvt.cucumber.report.PrettyReports:target" // this is for detailed html report
                             } ,
                   dryRun = false
                   , tags = "@go_home"
                )
public class TestRunner {
}
```

Now, you can run using the `TestRunner` and expect below after test run: 
- a new folder called `cucumber-html-reports` will be created
- it will contain some html files and css, js to support the styling

You can open any of the html file to navigate to the report look as below


![viewing_html_report_masterthought](https://user-images.githubusercontent.com/59104509/137251284-741338b5-3c3e-45b1-8905-e8057b67450d.gif)

