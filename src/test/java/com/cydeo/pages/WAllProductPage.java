package com.cydeo.pages;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WAllProductPage {

    // you can store all elements one by one
    // or we can store by entire row
    @FindBy(xpath = "//table[@class='ProductsTable']//tr/th")
    private List<WebElement> allHeaderRowCells ;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[2]/td")
    private List<WebElement> firstRowCells ;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[3]/td")
    private List<WebElement> secondRowCells ;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[4]/td")
    private List<WebElement> thirdRowCells ;


    public WAllProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    // store all rows text value as List<String>
    public List<String> getAllHeaderText(){

//        List<String> allTextLst = new ArrayList<>();
//
//        for (WebElement eachElement : allHeaderRowCells) {
//            allTextLst.add(  eachElement.getText()  ) ;
//        }

        return BrowserUtil.getAllText(  allHeaderRowCells );
    }


}
