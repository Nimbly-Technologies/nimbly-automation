package app.nimbly2.stepdefinitions;

import java.io.IOException;

import app.nimbly2.page_objects.ReportsPage2;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class ReportsPage_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public ReportsPage2 reportspage2;

	public ReportsPage_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.reportspage2 = utlisManager.appPageobjectManager.getReportsPage2();
	}

	@When("^access the Reports tab and checks the search functionality$")
	public void access_reports_tab_verify_search_functionality() throws InterruptedException {
		reportspage2.verifySearchFunctionality();
	}

	@And("^verify report card details$")
	public void verify_report_card_details() throws InterruptedException {
		reportspage2.verifyReportCardDetails();
	}
	@Then("^validate download report button and report generation popup$")
	public void validate_download_report_button_and_report_generation_popup() throws InterruptedException {
		reportspage2.verifyDownloadReportAndReportGenerationPopup();
	}
	
	@And("^validate filter functionality and download this week reports$")
	public void validate_filter_functionalty_and_download_this_week_reports() throws InterruptedException {
		reportspage2.validateFilterFunctionalityAndDownloadReportsFromThisWeek();
	}
	@When("^navigates to reports tab$")
	public void navigates_to_reports_tab() {
		reportspage2.navigateToReportsTab();
	}
	@Then("^validate download report functionalty for different time periods$")
	public void validate_report_download_functionality() throws InterruptedException, IOException {
		reportspage2.validateDownloadReportFunctionnalty();
	}
	@And("^verify that the user can successfully download reports from the group by location section$")
	public void verify_that_user_can_successfully_download_reports_from_the_group_by_locatio() throws InterruptedException {
		reportspage2.validateUserCanDownloadReportsFromGroupByLocation();
	}
	@And("^validate user can successfully download reports from last seven days and last thirty days$")
	public void validate_user_can_successfully_download_reports_from_last_seven_days_and_last_thirty_days() throws InterruptedException {
		reportspage2.verifyDownloadReportsForLastSevenAndThirtyDays();
	}

}
