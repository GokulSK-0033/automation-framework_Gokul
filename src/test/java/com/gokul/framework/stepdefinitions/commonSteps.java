package com.gokul.framework.stepdefinitions;

import com.gokul.framework.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class commonSteps {
    HomePage homePage=new HomePage();
    @Given("I navigate to Homepage")
        public void navigatetoAmazon() {
            homePage.navigateAmazon();
        }
    @When("I search {string}")
    public void iSearch(String search) {
        homePage.enterTextSearchbox(search);
    }


    }



