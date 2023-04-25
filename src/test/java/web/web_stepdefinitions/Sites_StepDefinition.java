package web.web_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;
import web.web_page_objects.SingleAudit;
import web.web_page_objects.Web_SitesPage;

public class Sites_StepDefinition {
	public UtlisManager utlisManager;
	public Sites_StepDefinition(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}
	@And("^validate the edit functionality of site$")
	public void updateSite() throws Exception {
		SingleAudit singleAudit = utlisManager.webPageobjectManager.singleAuditWorkflow();
		singleAudit.updateSite();
	}
	@Then("^Verify the bulk upload schedule$")
	public void bulkUploadSchedule() throws Exception {
		Web_SitesPage sitesPage = utlisManager.webPageobjectManager.getSitesPage();
		sitesPage.bulkUploadSchedule();
	}
	@Then("^Verify the bulk edit schedule$")
	public void bulkEditSchedule() throws Exception {
		Web_SitesPage sitesPage = utlisManager.webPageobjectManager.getSitesPage();
		sitesPage.bulkEditSchedule();
		
	}

}

