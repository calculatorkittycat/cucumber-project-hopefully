
# BDD  behaviour Driven Development
    A process to ensure collaboration between 3 amigos : Business , Dev , QA 
    to have common shared understanding on whats the aceptence 

# Project Creation

1. Create a maven project with name `cucumber-project-b23`
    1. Select java 8
    2. group id : `com.cydeo`
    3. artifact id : leave it as is
    4. Click finish
2. create folder sturcture as below
    1. under `src/test/java` create below packages
    2. create a package `com.cydeo` ,under this package
        1.  create `utility` package
        2.  create `pages` package
        3.  create `step_definitions` package
        4.  create `runner` package
3. create a directory|folder under `src/test`
    1. when asked for the name , select `resources` from dropdown it provided
    2. under resources create a folder `features`
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
    1. cucumber junit dependency
    ```xml    
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>6.11.0</version>
    </dependency>
    ```
5. Add a feature file called `eating_cucumber.feature`
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
5. Create a new class `EatStepDef` under step definitions
6. Run the feature file by using run button beside feature scenario
7. Copy the code from the console error and remove the content of method with your own
8. run again to see the scenario run pass
