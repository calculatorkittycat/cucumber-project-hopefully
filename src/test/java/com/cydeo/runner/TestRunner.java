package com.cydeo.runner;

// This class has only one purpose
// Instructing how and what feature we want to run
   // features = "src/test/resources/features"
// where are the step definitions
//  glue = "com/cydeo/step_definitions"
// do we want to just generate missing step definitions
    //dryRun=true will run the test without running all scenario for missing steps
    // so you can copy all the missing steps if there is any
    // it's like a quick scan of all your feature steps has step definitions or not

// do we want to get json , html report
// do we want to filter the test run according to certain tags

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(  features = "src/test/resources/features" , //alternatively "classpath:features"
                   glue = "com/cydeo/step_definitions" ,
                   plugin = {"pretty", "html:target/cucumber.html"  } ,
                   dryRun = false
//                   , tags = "@wip"
                )
public class TestRunner {
}
