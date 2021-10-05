package com.cydeo.step_definitions;

import com.cydeo.pages.WLoginPage;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

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

        // cucumber-junit dependency already come with junit4
        // so we will be just using assertions methods coming from junit 4
        // it's extremely similar just coming from different dependency
        // so the package import will be different
        //This is where the assertions methods are coming from in junit4 already included in cucumber-junit dependency
        //import static org.junit.Assert.*;
//        assertEquals(4,4);
//        assertTrue(4>3) ;
        // check we are at the all order page by checking the title is Web Orders
        assertEquals("Web Orders", Driver.getDriver().getTitle() );

    }


}
