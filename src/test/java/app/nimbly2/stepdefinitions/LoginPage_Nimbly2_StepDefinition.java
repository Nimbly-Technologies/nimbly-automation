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
	
	@And("^validate multiple login and version update popups$")
	public void validate_popups() throws InterruptedException {
		loginpage2.validatePopups();
	}
	@When("^validate invalid credentials error message$")
		public void validate_invalid_credentials_error_message() throws InterruptedException {
		loginpage2.validateInvalidCredentialsErrorMessage();
	}
	@Then("^validate forgot password functionality$")
	public void validate_forgot_password_functionality() throws InterruptedException {
		loginpage2.validateForgotPasswordFunctionality();
	}
	@And("^validate activate account functionality$")
	public void validate_activate_account_functionality() throws InterruptedException {
		loginpage2.validateActivateAccountFunctionality();
	}
	@Given("^validate error message for forgot password functionality$")
	public void validate_error_message_for_forgot_password_functionality() throws InterruptedException {
		loginpage2.validateForgotPasswordErrorMessage();
	}
	@When("^validate error message for activate account functionality$")
	public void validate_error_mesage_for_activate_account_functionality() throws InterruptedException {
		loginpage2.validateActivateAccountErrorMessage();
	}
	@Then("^verify localization on login page$")
	public void verify_localization_on_login_page() throws InterruptedException {
		loginpage2.verifyLocalizationOnLoginPage();
	}

}
