package com.cydeo.step_definitions;

import com.cydeo.pages.WAllProductPage;
import com.cydeo.pages.WCommonArea;
import com.cydeo.pages.WOrderPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class WebOrderStepDefs {
    @When("we select {string} tab from sidebar")
    public void weSelectTabFromSidebar(String tabName) {
        System.out.println("tabName = " + tabName);
        WCommonArea commonArea = new WCommonArea();
       switch (tabName) {
           case "View all products" :
               commonArea.viewAllProductTab.click();
               break;
           case "View all orders" :
                commonArea.viewAllOrderTab.click();
               break;
           case "Order":
                commonArea.orderTab.click();
               break;
       }

    }





    @Then("we should see below options in Product dropdown list")
    public void weShouldSeeBelowOptionsInProductDropdownList(List<String> expectedOptions) {

        System.out.println("expectedOptions = " + expectedOptions);
        WOrderPage orderPage = new WOrderPage();

        List<String> actualOptions = orderPage.getAllProductOptionFromList();

        // assert these two list are equal
        // import static org.junit.Assert.assertEquals;
        assertEquals(expectedOptions, actualOptions) ;
    }

    @Then("we should see table with below content")
    public void weShouldSeeTableWithBelowContent(List<Map<String,String>> productMapLst) {

        System.out.println("productMapLst = " + productMapLst);

        WAllProductPage allProductPage = new WAllProductPage();

        // get expected headers :
        Set<String> headerSet = productMapLst.get(0).keySet() ;
        System.out.println("headerSet = " + headerSet);

        // get actual header :
        List<String> actualHeaders = allProductPage.getAllHeaderText();
        System.out.println("actualHeaders = " + actualHeaders);

        // check the size is the same
        assertEquals(headerSet.size() , actualHeaders.size() );

        // list to set equality check will not work
        // so we need to turn the set into list
        List<String> expectedHeaders = new ArrayList<>( headerSet );
        assertEquals(expectedHeaders, actualHeaders);


    }
}
