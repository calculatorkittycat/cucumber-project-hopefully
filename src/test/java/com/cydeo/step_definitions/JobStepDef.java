package com.cydeo.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobStepDef {

    @Given("student attend all the classes")
    public void student_attend_all_the_classes() {
        System.out.println("Given code goes here :student attend all the classes");
    }
    @When("student learn all the topics")
    public void student_learn_all_the_topics() {
        System.out.println("When code goes here :student learn all the topics");
    }
    @Then("student should be ready for the job")
    public void student_should_be_ready_for_the_job() {
        System.out.println("Then code goes here :student should be ready for the job");
    }
    @Given("student is prepared")
    public void student_is_prepared() {
        System.out.println("Given student is prepared");
    }
    @When("student upload their resume")
    public void student_upload_their_resume() {
        System.out.println("When student upload their resume");
    }
    @Then("student will be in the market")
    public void student_will_be_in_the_market() {
        System.out.println("Then student will be in the market");
    }

}
