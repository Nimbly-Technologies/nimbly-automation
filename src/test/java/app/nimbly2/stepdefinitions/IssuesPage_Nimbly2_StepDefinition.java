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

}
