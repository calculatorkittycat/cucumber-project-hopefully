package com.cydeo.step_definitions;

import com.cydeo.pages.WCommonArea;
import com.cydeo.pages.WOrderPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class WebOrderStepDefs {
    @When("we select {string} tab from sidebar")
    public void weSelectTabFromSidebar(String tabName) {
        System.out.println("tabName = " + tabName);
        WCommonArea commonArea = new WCommonArea();
        commonArea.orderTab.click();
    }

    @Then("we should see below options in Product dropdown list")
    public void weShouldSeeBelowOptionsInProductDropdownList(List<String> expectedOptions) {

        System.out.println("expectedOptions = " + expectedOptions);
        WOrderPage orderPage = new WOrderPage();

        WebElement dropdown = orderPage.productDropdown ;
        Select selectObj = new Select(dropdown);

//        List<String> actualOptions =
//                selectObj.getOptions().stream()
//                        .map(eachOption-> eachOption.getText())
//                        .collect(Collectors.toList());

        //getOptions method from select class is used
        // to return all dropdown options as List of webelement
        List<WebElement> allProductOptions = selectObj.getOptions();
        // this is the list to store actual option so we can compare with expected
        List<String> actualOptions = new ArrayList<>();

        for (WebElement eachOption : allProductOptions) {
            System.out.println("eachOption.getText() = " + eachOption.getText());
            actualOptions.add(eachOption.getText()) ;
        }
        // assert these two list are equal
        // import static org.junit.Assert.assertEquals;
        assertEquals(expectedOptions, actualOptions) ;
    }
}
