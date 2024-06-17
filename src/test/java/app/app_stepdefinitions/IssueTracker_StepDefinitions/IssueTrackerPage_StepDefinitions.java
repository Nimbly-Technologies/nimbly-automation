package app.app_stepdefinitions.IssueTracker_StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import app.app_page_objects.HomePage_PageObjects.HomePage;
import app.app_page_objects.IssueTracker_PageObjects.IssueTrackerPage;
import utlis.UtlisManager;



public class IssueTrackerPage_StepDefinitions {
	
public UtlisManager utlisManager; //making utlis as global 	
public IssueTrackerPage issuetrackerpage;
 String questiontitle;
 public HomePage homepage;
 

public IssueTrackerPage_StepDefinitions (UtlisManager utlisManager)	//creating a constructor to use utlis class methods
{
	this.utlisManager = utlisManager; //accessing the global variable utlis by "this class" instance
}

@Given("^navigate to issue tracker main tab$")
public void navigate_to_issue_tracker_main_tab() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.navigate_to_issuetrackermaintab();
}

@And("^click on add issue$")
public void click_on_add_issue() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.click_on_add_issue();
}

@And("^Add all the required fields$")
public void add_all_the_required_fields() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.add_all_the_required_fields_in_add_issue();
}

@And("^successfully add the issue$")
public void successfully_add_the_issue() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.click_on_successfully_add_issue();
}

@Then("^perform edit operations$")
public void perform_edit_operations() throws InterruptedException, ClassNotFoundException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.perform_edit_operations();	
}
@Then("^perform edit operations fail case$")
public void perform_edit_operations_fail_case() throws InterruptedException, ClassNotFoundException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.perform_edit_operations_fail_case();	
}
@And("click on backarrow$")
public void click_on_backarrow_button() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.click_on_backarrowbutton();
}
@Then("^click on filter$")
public void click_on_filter() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.click_on_filter_functionality();
}

@Then("^click on sort$")
public void click_on_sort() throws InterruptedException  {
	IssueTrackerPage issuetrackerpage = utlisManager.appPageobjectManager.getissuetrackerpage();
	issuetrackerpage.click_on_sort_functionality();
}
}

	
	

