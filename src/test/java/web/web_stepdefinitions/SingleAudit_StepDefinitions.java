package web.web_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;
import web.web_page_objects.SingleAudit;

public class SingleAudit_StepDefinitions {
	public UtlisManager utlisManager;

	public SingleAudit_StepDefinitions(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}

	@Then("^create a questionnaire$")
	public void createQuestionnaire() throws Exception {
		SingleAudit singleAudit = utlisManager.webPageobjectManager.singleAuditWorkflow();
		singleAudit.createQuestionnaire();
	}

	@And("^create a department$")
	public void createDepartment() throws Exception {
		SingleAudit singleAudit = utlisManager.webPageobjectManager.singleAuditWorkflow();
		singleAudit.createDepartment();

	}
	@And("^create a site and report single audit recurring schedule$")
	public void createSite() throws Exception {
		SingleAudit singleAudit = utlisManager.webPageobjectManager.singleAuditWorkflow();
		singleAudit.createSite();
	}
	@And("^validate single audit for recurring schedule$")
	public void verifySingleAudit() throws Exception {
		SingleAudit singleAudit = utlisManager.webPageobjectManager.singleAuditWorkflow();
		singleAudit.verifySingleAudit();
	}
}
