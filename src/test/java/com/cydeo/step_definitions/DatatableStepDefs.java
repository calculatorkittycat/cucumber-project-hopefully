package com.cydeo.step_definitions;

import com.cydeo.pages.WLoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

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

    @Then("They come to me with below noise")
    public void theyComeToMeWithBelowNoise(Map<String,String> animalNoiseMap) {
        System.out.println("animalNoiseMap = " + animalNoiseMap);
    }

    // This can be represented by List<List<String>> as well like below
//    @Then("They come to me with below noise")
//    public void theyComeToMeWithBelowNoise(List< List<String> > animalRowLst) {
//
//        System.out.println("animalRowLst = " + animalRowLst);
//
//    }

    @When("we provide below credentials")
    public void weProvideBelowCredentials(Map<String, String> credentialMap) {

        System.out.println("credentialMap = " + credentialMap);
        String usernameFromTable = credentialMap.get("username");
        String passwordFromTable = credentialMap.get("password");

        WLoginPage loginPage = new WLoginPage();
        loginPage.login( usernameFromTable, passwordFromTable  );


    }
    
    
}
