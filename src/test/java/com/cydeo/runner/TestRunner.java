package com.cydeo.runner;

// This class has only one purpose
// Instructing how and what feature we want to run
   // features = "src/test/resources/features"
// where are the step definitions
//  glue = "com/cydeo/step_definitions"
// do we want to just generate missing step definitions
    //dryRun=true will run the test without failing for missing steps
    // so you can copy all the missing steps if there is any

// do we want to get json , html report
// do we want to filter the test run according to certain tags

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(  features = "src/test/resources/features" ,
                   glue = "com/cydeo/step_definitions"    )
public class TestRunner {
}
