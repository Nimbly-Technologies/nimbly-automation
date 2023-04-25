package web.web_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;
import web.web_page_objects.WebAuditPage;

public class WebAuditPage_StepDefinitions {
	public UtlisManager utlisManager;

	public WebAuditPage_StepDefinitions(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}
	@Then("^verify the schedule tab functionality$")
	public void verifyScheduleTab() throws Exception {
		WebAuditPage webAuditPage = utlisManager.webPageobjectManager.getWebAuditPage();
		webAuditPage.verifyScheduleTab();
	}
	@And("^verify schedule tab UI on web audit$")
	public void scheduleTabUI() throws Exception {
		WebAuditPage webAuditPage = utlisManager.webPageobjectManager.getWebAuditPage();
		webAuditPage.verifyScheduleTabUI();
	}
	@And("^verify site card contents on schedule tab$")
	public void siteCardContents() throws Exception {
		WebAuditPage webAuditPage = utlisManager.webPageobjectManager.getWebAuditPage();
		webAuditPage.siteCardContents();
	}
}
