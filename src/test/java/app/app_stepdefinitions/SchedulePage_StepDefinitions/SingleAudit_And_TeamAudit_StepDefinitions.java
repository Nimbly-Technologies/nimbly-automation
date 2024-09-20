package app.app_stepdefinitions.SchedulePage_StepDefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import app.app_page_objects.SchedulePage_PageObjects.SingleAudit_And_TeamAudit;
import utlis.UtlisManager;

public class SingleAudit_And_TeamAudit_StepDefinitions {

	public AndroidDriver driver; // making driver as global variable to use accross diggernet classes
	public UiAutomator2Options options;
	public UtlisManager utlisManager; // making utlis as global
	public SingleAudit_And_TeamAudit schedulepage;
	public String Singleauditsitename;
	public String Teamauditsitename;
	public String questionnairename;
	public String siteNameWithAttachments;
	public String questionnaireNameWithAttachments;

	public SingleAudit_And_TeamAudit_StepDefinitions(UtlisManager utlisManager) throws Exception // creating a constructor to use utlis
																				// class methods
	{
	    this.utlisManager = utlisManager;
		this.schedulepage  = utlisManager.appPageobjectManager.getSchedulePage();
	}

	@When("^navigates to app schedule page$")
	public void navigate_to_schedule_page() throws InterruptedException {
		schedulepage.navigate_to_schedulepage();
	}

	@Then("search the adhoc single audit site in search tab and select the site")
	public void search_the_adhoc_single_audit_site_in_search_tab_and_select_the_site() throws InterruptedException {
		schedulepage.search_for_adhoc_single_audit_site_in_schedulepage(Singleauditsitename);
		// schedulepage.select_site_from_searchbox_results();
	}

	@Then("search the adhoc team audit site in search tab and select the site")
	public void search_the_adhoc_team_audit_site_in_search_tab_and_select_the_site() throws InterruptedException {
		schedulepage.search_for_adhoc_team_audit_site_in_schedulepage(Teamauditsitename);
		// schedulepage.select_site_from_searchbox_results();
	}

	@Then("^search the questionnaire in search tab and select the questionnaire$")
	public void search_the_questionnaire_in_search_tab_and_select_the_questionnaire() throws InterruptedException {
		schedulepage.search_for_questionnaire_in_schedulepage(questionnairename);
	}

	@Then("^click on adhoc report and start adhoc report for single audit$")
	public void click_on_adhoc_report_and_start_adhoc_report_for_single_audit() throws InterruptedException {
		schedulepage.click_on_adhoc_button_and_click_on_checkin_for_single_audit();
	}

	@Then("^click on adhoc report and start adhoc report for team audit$")
	public void click_on_adhoc_report_and_start_adhoc_report_for_team_audit() throws InterruptedException {
		schedulepage.click_on_adhoc_button_and_click_on_checkin_for_team_audit();
	}

	@And("^accept the checkin popup$")
	public void accept_the_checkin_popup() throws InterruptedException {
		schedulepage.click_yes_on_checkin_popup();
	}

	@Then("^search the site and choose$")
	public void search_site() throws InterruptedException {
		schedulepage.search_site(siteNameWithAttachments);
	}

	@And("^search the adhoc schedule and tab on it$")
	public void search_adhoc_schedule() throws InterruptedException {
		schedulepage.search_adhoc_schedule(questionnaireNameWithAttachments);
	}
}
