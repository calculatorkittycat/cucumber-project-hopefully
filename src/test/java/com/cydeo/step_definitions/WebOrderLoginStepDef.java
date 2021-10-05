package com.cydeo.step_definitions;

import com.cydeo.pages.WLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebOrderLoginStepDef {
    // putting this at class level, so it can be accessible in all methods
    WLoginPage loginPage ;

    @Given("we are at web order login page")
    public void we_are_at_web_order_login_page() {
        // navigate to login page
        loginPage = new WLoginPage() ;
        loginPage.goTo();

    }
    @When("we provide valid credentials")
    public void we_provide_valid_credentials() {

        loginPage.login("Tester", "test");

    }
    @Then("we should see all order page")
    public void we_should_see_all_order_page() {

    }


}
