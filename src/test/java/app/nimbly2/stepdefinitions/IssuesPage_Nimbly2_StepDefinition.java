package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.IssuesPage2;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class IssuesPage_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public IssuesPage2 issuespage2;

	public IssuesPage_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.issuespage2 = utlisManager.appPageobjectManager.getIssuesPage2();
	}

	@Then("^navigate back to issues page and search for (.*)$")
	public void navigate_back_to_issues_page_and_search_for_issues(String questionName) throws InterruptedException {
		issuespage2.searchForIssue(questionName);
	}

	@And("^validate issue card details$")
	public void validate_issue_card_details() throws InterruptedException {
		issuespage2.validateIssueCardDetails();
	}
	@And("^validate edit issue details$")
	public void validate_edit_issue_details() throws InterruptedException {
		issuespage2.editIssueDetails();
	}
	@When("^add adhoc issue$")
	public void add_adhoc_issue() throws InterruptedException {
		issuespage2.createAdhocIssue();
	}
	@And("^validate issue details$")
	public void validate_issue_details() throws InterruptedException {
		issuespage2.validateIssueDetails();
	}
	@And("^validate add comments along with attachments under issue activity$")
	public void validate_add_comments_along_with_attachments() throws InterruptedException {
		issuespage2.validateAddCommentsAlongWithAttachments();
	}
	@And("^validate filter functionality$")
	public void validate_filter_functionality() throws InterruptedException {
		issuespage2.validateIssueFilterFunctionality();
	}
	@And("^navigate back to issues list$")
	public void navigate_back_to_issues_list() throws InterruptedException {
		issuespage2.navigateToIssuesList();	
	}
	@And("^verify whether default filters are applied$")
	public void verify_default_filter() {
		issuespage2.verifyDefaultFilter();
	}
	@When("^navigates to issues$")
	public void navigates_to_issues() throws InterruptedException {
		issuespage2.navigateToIssues();
	}
	@And("^validate issue sort functionality$")
	public void validate_issue_sort_functionality() throws InterruptedException {
		issuespage2.validateIssueSortFunctionality();
	}
	@Then("^verify sorting in ascending and descending order$")
	public void verify_sorting_in_ascending_and_descending_order() throws InterruptedException {
		issuespage2.verifyAscendingAndDescendingSorting();
	}
	@And("^verify all, overdue, and my issues, along with the saved filters$")
	public void verify_all_overdue_and_my_issues() throws InterruptedException {
		issuespage2.verifyAllOverdueMyIssuesAlongWithSavedFilters();
	}
	@And("^validate user able to add new issue member in issue details$")
	public void validate_user_able_to_add_new_issue_member_in_issue_details() throws InterruptedException {
		issuespage2.addNewIssueMemberInIssueDetails();
	}
	@And("^validate issue history and comments under All tab$")
	public void validate_issue_history_and_comments_under_all_tab() throws InterruptedException {
		issuespage2.validateIssueHistoryAndComments();
	}
	@And("^validate user can upload ten attachments$")
	public void validate_user_can_upload_ten_attachments() throws InterruptedException {
		issuespage2.VerifyUserCanUploadTenAttachments();
	}
	@And("^navigates back to issues tab$")
	public void navigates_back_to_issues_tab() throws InterruptedException {
		issuespage2.navigateToIssuesTab();
	}
	@And("^select issues and perform bulk update$")
	public void select_issues_and_perform_bulk_update() throws InterruptedException {
		issuespage2.bulkUpdateIssues();
	}
	@And("^validate changes after bulk updates$")
	public void validate_changes_after_bulk_updates() throws InterruptedException {
		issuespage2.validateChangesAfterBulkUpdates();	
	}
	

}
