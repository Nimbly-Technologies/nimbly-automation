package web.web_stepdefinitions;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utlis.UtlisManager;
import web.web_page_objects.Web_Common_Steps;

public class Common_StepDefinition {
	public UtlisManager utlisManager;

	public Common_StepDefinition(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}

	@Given("^Navigate to the Nimbly Web Admin$")
	public void navigate_to_the_nimbly_web() throws IOException {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.navigate_to_web_url();
	}

	@When("^enter the username$")
	public void enterUsername() throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.enterUsername();
	}

	@And("^enter the user password$")
	public void enterPassword() throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.enterPassword();
	}

	@And("^navigate to (.*) (.*)$")
	public void clickOnLogin(String object, String text) throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.clickOnLogin(object, text);
	}

	@And("^Accept the (.*)$")
	public void acceptCookies(String object) throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.acceptCookies(object);

	}

	@Given("^Navigate to the Nimbly Web Audit$")
	public void navigateToTheNimblyAudit() {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.navigateToTheNimblyAudit();
	}

	@When("^enter the audit username$")
	public void enterAuditUserName() throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.enterAuditUserName();
	}

	@And("^enter the audit user password$")
	public void enterAuditUserPassword() throws Exception {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.enterAuditUserPassword();
	}

	@Given("^Navigate to yopmail$")
	public void NavigateToYopmail() {
		Web_Common_Steps commonSteps = utlisManager.webPageobjectManager.getCommonSteps();
		commonSteps.NavigateToYopmail();

	}

}
