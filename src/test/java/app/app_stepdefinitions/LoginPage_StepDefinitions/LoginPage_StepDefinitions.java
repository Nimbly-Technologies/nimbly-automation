package app.app_stepdefinitions.LoginPage_StepDefinitions;

import java.io.IOException;

import app.app_page_objects.LoginPage_PageObjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utlis.UtlisManager;


public class LoginPage_StepDefinitions {

    public UtlisManager utlisManager; // Making utlisManager as global
    public LoginPage loginPage;

    public LoginPage_StepDefinitions(UtlisManager utlisManager) throws Exception {
        this.utlisManager = utlisManager;
        this.loginPage = utlisManager.appPageobjectManager.getLoginPage();
    }

    @Then("^validate and enter the useremail$")
    public void validate_and_enter_the_useremail() throws IOException, InterruptedException {
        loginPage.validate_useremail_box();
        loginPage.enter_useremail();
    }

    @Then("validate and Enter the userpassword")
    public void validate_and_enter_the_userpassword() throws IOException, InterruptedException {
        loginPage.validate_userpassword_box();
        loginPage.enter_userpassword();
    }

    @And("^validate and click on login button$")
    public void validate_and_click_on_login_button() throws InterruptedException, IOException {
        loginPage.click_on_login();
    }

    @And("^click on Allow Nimbly to access this device location$")
    public void click_on_allow_nimbly_to_access_this_device_location() throws InterruptedException, IOException {
        loginPage.validate_login_box();
        loginPage.allow_location_permission();
    }

    @When("^handle notifications on home page$")
    public void handleNotificationsOnHomePage() throws Exception {
        loginPage.handleNotificationsOnHomePage();
    }

    @Given("^Enter useremail and password$")
    public void enterUserEmailAndPassword() throws IOException, Exception {
        loginPage.enter_useremail();
        loginPage.click_on_login();
        loginPage.enter_userpassword();
    }

    @And("^click on login button$")
    public void clickOnLogin() throws InterruptedException, IOException {
        loginPage.click_on_login();
    }

    @Given("^handle allow notification pop up$")
    public void allowNotifications() throws InterruptedException, IOException {
        loginPage.allowNotifications();
    }
}
