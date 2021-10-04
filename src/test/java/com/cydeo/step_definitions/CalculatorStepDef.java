package com.cydeo.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepDef {

    @Given("calculator app is open")
    public void calculator_app_is_open() {

        System.out.println("@Given calculator_app_is_open");

    }
    @When("I add {int} with {int}")
    public void i_add_with(Integer num1, Integer num2) {

        System.out.println("When I add "+num1+ " with "+ num2);

    }
    @Then("I should get result {int} displayed")
    public void i_should_get_result_displayed(Integer result) {

        System.out.println("@Then Result should be " + result);

    }

}
