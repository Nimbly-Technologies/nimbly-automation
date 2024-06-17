package app.app_stepdefinitions.SchedulePage_StepDefinitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import app.app_page_objects.SchedulePage_PageObjects.AuditingWorkflow;
import utlis.UtlisManager;




public class AuditingWorkflowStepDefinitions {
	
public AppiumDriver driver; //making driver as global variable to use accross diggernet classes
public UiAutomator2Options options;
public UtlisManager utlisManager; //making utlis as global 	
public AuditingWorkflow auditing;


public AuditingWorkflowStepDefinitions (UtlisManager utlisManager)	//creating a constructor to use utlis class methods
{
	this.utlisManager = utlisManager; //accessing the global variable utlis by "this class" instance
}
	
@And("^answer the questions$")
public void answer_the_questions() throws InterruptedException {
	AuditingWorkflow auditing = utlisManager.appPageobjectManager.getauditingworkflow();
	auditing.answer_all_the_questions();
}

@And("^review and checkout the report$")
public void review_and_checkout_the_report() throws InterruptedException {
	AuditingWorkflow auditing = utlisManager.appPageobjectManager.getauditingworkflow();
	auditing.click_on_review_report();
	auditing.click_on_checkout();
	auditing.click_yes_on_checkout_popup();
	auditing.click_got_it();
	Thread.sleep(5000);
}

@And("^checkout the report$")
public void checkout_the_report() throws InterruptedException {
	AuditingWorkflow auditing = utlisManager.appPageobjectManager.getauditingworkflow();
	auditing.click_on_checkout();
	auditing.click_yes_on_checkout_popup();
	auditing.click_got_it();
	Thread.sleep(5000);
}
@Then("^select sections from teamaudit and answer the questions$")
public void select_section1_from_teamaudit_and_answer_the_questions() throws Throwable {
	AuditingWorkflow auditing = utlisManager.appPageobjectManager.getauditingworkflow();
	auditing.select_sections_and_answer_all_the_questions();
}

@And("^upload and finalize the report$")
public void upload_and_finalize_the_report() throws InterruptedException {
	AuditingWorkflow auditing = utlisManager.appPageobjectManager.getauditingworkflow();
	auditing.upload_report_and_accept_the_popup();
	auditing.finalize_report_and_accept_the_popup();
}
}

	
	

