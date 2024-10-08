package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.LoginPage2;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class LoginPage_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public LoginPage2 loginpage2;

	public LoginPage_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.loginpage2 = utlisManager.appPageobjectManager.getLoginPage2();
	}

	@Given("^validate and enter the useremail or userid$")
	public void validate_and_enter_the_useremail_or_userid() throws InterruptedException {
		loginpage2.validateEmailSection();
		loginpage2.enterUseremailORUserId();
	}

	@When("^validate and enter user password$")
	public void validate_and_enter_user_password() throws InterruptedException {
		loginpage2.validatePasswordSection();
		loginpage2.enterUserPassword();
	}

	@Then("^validate login button and click on it$")
	public void validate_login_button_and_click_on_it() throws InterruptedException {
		loginpage2.validateLoginButtonAndClick();
	}

	@And("^validate in app update popup$")
	public void validate_in_app_update_popup() throws InterruptedException {
		loginpage2.validateInAppUpdate();
	}

	@And("^validate multiple login popup$")
	public void validate_multiple_login_popup() throws InterruptedException {
		loginpage2.validateMultipleLogin();
	}

	@And("^logout from nimbly2$")
	public void logout_from_nimbly2() throws InterruptedException {
		loginpage2.logout();
	}

	@Given("^I login to application with (.*),(.*)$")
	public void login(String username, String password) throws InterruptedException {
		loginpage2.login(username, password);
	}

}
