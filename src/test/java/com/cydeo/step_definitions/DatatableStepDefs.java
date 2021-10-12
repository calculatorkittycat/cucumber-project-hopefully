package com.cydeo.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class DatatableStepDefs {

    @Given("I have a {string}")
    //@Given("I have a {word}")
    public void i_have_a(String animal) {

        System.out.println("Given I have a " + animal);

    }
    @When("I call their names")
    public void i_call_their_names() {
        System.out.println(" When I call their names ");
    }
    @Then("They come to me.")
    public void they_come_to_me() {
        System.out.println(" Then they come to me ");
    }

    @Given("I have following animals")
    public void i_have_following_animals(List<String> animalLst) {

        System.out.println("animalLst = " + animalLst);

    }


    @When("I call their names with below names")
    public void iCallTheirNamesWithBelowNames(List<String> nameLst) {

        System.out.println("nameLst = " + nameLst);

    }
}
