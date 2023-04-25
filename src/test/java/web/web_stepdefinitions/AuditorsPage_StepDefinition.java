package web.web_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utlis.UtlisManager;
import web.web_page_objects.Web_AuditorsPage;

public class AuditorsPage_StepDefinition {
	public UtlisManager utlisManager;

	public AuditorsPage_StepDefinition(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}

	@When("^create dynamic mail$")
	public void createMail() throws Exception {
		Web_AuditorsPage auditorsPage = utlisManager.webPageobjectManager.getAuditotsPage();
		auditorsPage.createMail();
	}

	@And("^Verify the functionality of Add User fron UI$")
	public void verifyAddUser() throws Exception {
		Web_AuditorsPage auditorsPage = utlisManager.webPageobjectManager.getAuditotsPage();
		auditorsPage.verifyAddUser();
	}

	@And("^Verify the search functionality of Auditors page$")
	public void auditorSearchFunctionality() throws Exception {
		Web_AuditorsPage auditorsPage = utlisManager.webPageobjectManager.getAuditotsPage();
		auditorsPage.auditorSearchFunctionality();
	}
	@When("^Verify the functionality of Activate account$")
	public void activateAccount() throws Exception {
		Web_AuditorsPage auditorsPage = utlisManager.webPageobjectManager.getAuditotsPage();
		auditorsPage.activateAccount();
	}
	@And("^Verify the onboarding of new user through Bulk Upload$")
	public void userBulkUpload() throws Exception {
		Web_AuditorsPage auditorsPage = utlisManager.webPageobjectManager.getAuditotsPage();
		auditorsPage.bulkUpload();
	}

}
