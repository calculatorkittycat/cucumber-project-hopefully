package com.cydeo.utility;


import org.openqa.selenium.By;

public class WebOrderUtil {


    /**
     * Simply navigate to WebOrderApp
     */
    public static void openWebOrderApp(){

//        Driver.getDriver().get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx") ;
        Driver.getDriver().get( com.cybertek.utility.ConfigReader.read("weborder_url")  ) ;

    }

    /**
     * A method for logging into Web Order practice site from login page
     */
    public static void login(){

        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }


    /**
     * A method for logging into Web Order practice site from login page
     *
     * @param username username for weborder app
     * @param password password for weborder app
     */
    public static void login(String username, String password){

        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    /**
     * Check for login error message is visible or not , by calling the BrowserUtil method we created
     * @return true if error message displayed , false if not
     */
    public static boolean loginErrorMsgVisible(){

        boolean elementFound =
                com.cybertek.utility.BrowserUtil.checkVisibilityOfElement(By.xpath("//span[. ='Invalid Login or Password.']"),2);
        return elementFound ;
    }

}
