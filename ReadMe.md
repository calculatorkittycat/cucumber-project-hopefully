
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
-  The purpose of the Three Amigos workshop is not only to build up a deep shared understanding within the team, but also to uncover areas of uncertainty or incorrect assumptions that would typically only surface much later on.
- Teams often use techniques such as **Example Mapping** and **Feature Mapping **to facilitate the requirements discovery process.

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
3. create a directory|folder under `src/test`
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

1. add faker dependency

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

2. add WebDriverManager Dependency

 ```xml
 <dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.0.2</version>
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

## Using `Background` to reuse `Given` condition
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
- where are the step definitions :
    - `features = "src/test/resources/features"`
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
- html reports , json reports and so on

```java
    package com.cydeo.runner;
    import io.cucumber.junit.Cucumber;
    import io.cucumber.junit.CucumberOptions;
    import org.junit.runner.RunWith;
    
    @RunWith(Cucumber.class)
    @CucumberOptions(  features = "src/test/resources/features" ,
                       glue = "com/cydeo/step_definitions" ,
                       dryRun = false,
                       tags = "@smoke"
                    )
    public class TestRunner {
        // NOTHING GOES HERE!
    }

```

If we run above test runner ,
it will only run the scenarios that tagged with `@smoke` tags. 

