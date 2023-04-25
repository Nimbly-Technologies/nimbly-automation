package web.web_page_objects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utlis.Web_Utlis.AppData;
import utlis.Web_Utlis.Library;
import utlis.Web_Utlis.TestDataGenerator;

public class SingleAudit {
	public static String questionnaireName = "";
	public static String departmentName = "";
	public static String siteName = "";
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();

	public SingleAudit(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;
	}

	// ------------------------------------------------------------------------------------------
	/**
	 * create questionnaire
	 * 
	 * @throws Exception
	 */
	// ------------------------------------------------------------------------------------------
	public void createQuestionnaire() throws Exception {
		// mouse hover on add Questionnaire
		library.dynamicWaitForVisibilityOfElement(webdriver, "add_questionnaire");
		WebElement addQuestionnaire = webdriver.findElement(By.xpath(Library.getXPath("add_questionnaire")));
		library.scrollToViewObject(webdriver, addQuestionnaire);

		// click on create new button
		library.click(webdriver, "txt_create_new");

		// enter dynamic questionnaire name
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		String questionnaire = "questionnaire" + library.getAlphaNumaricText();
		SingleAudit.questionnaireName = questionnaire;
		System.out.println("Print question: &&&&" + questionnaire);
		library.dynamicWaitForVisibilityOfElement(webdriver, "Questionnaire");
		library.enterText(webdriver, "Questionnaire", questionnaire);

		// select question type
		library.click(webdriver, "select_type");
		library.click(webdriver, "txt_question_type");

		// enter first question and select the question type as green/yellow/red flag
		String q1 = prop.getProperty("Question1");
		library.click(webdriver, "first_question");
		library.enterText(webdriver, "first_question", q1);
		library.click(webdriver, "First_Dropdown_Value");
		library.click(webdriver, "txt_green");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter second question and select the question type as yes/no
		String q2 = prop.getProperty("Question2");
		library.click(webdriver, "second_question");
		library.enterText(webdriver, "second_question", q2);
		library.click(webdriver, "Second_Dropdown_Value");
		library.click(webdriver, "value_yes_no");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter third question and select the question type as score
		String q3 = prop.getProperty("Question3");
		library.click(webdriver, "third_question");
		library.enterText(webdriver, "third_question", q3);
		library.click(webdriver, "Third_Dropdown_Value");
		library.click(webdriver, "value_score");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter four question and select the question type as open ended
		String q4 = prop.getProperty("Question4");
		library.click(webdriver, "forth_question");
		library.enterText(webdriver, "forth_question", q4);
		library.click(webdriver, "Forth_Dropdown_Value");
		library.click(webdriver, "value_open_ended");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter fifth question and select the question type as number
		String q5 = prop.getProperty("Question5");
		library.click(webdriver, "fifth_question");
		library.enterText(webdriver, "fifth_question", q5);
		library.click(webdriver, "Fifth_Dropdown_Value");
		library.click(webdriver, "value_number");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter sixth question and select the question type as yes/no
		String q6 = prop.getProperty("Question6");
		library.click(webdriver, "sixth_question");
		library.enterText(webdriver, "sixth_question", q6);
		library.click(webdriver, "Sixth_Dropdown_Value");
		library.click(webdriver, "value_yes_no");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter seven question and select the question type as multiple
		String q7 = prop.getProperty("Question7");
		library.click(webdriver, "seventh_question");
		library.enterText(webdriver, "seventh_question", q7);
		library.click(webdriver, "Seventh_Dropdown_Value");
		library.click(webdriver, "value_multiple");

		// click on add question
		library.click(webdriver, "txt_add_question");

		// enter eight question and select the question type as range with flags
		String q8 = prop.getProperty("Question7");
		library.click(webdriver, "eight_question");
		library.enterText(webdriver, "eight_question", q8);
		library.click(webdriver, "Eigth_Dropdown_Value");
		library.click(webdriver, "value_range_with_flags");
		WebElement slider = webdriver.findElement(By.xpath(Library.getXPath("slider")));
		library.drag(webdriver, slider);

		// click on publish
		WebElement publish = webdriver.findElement(By.xpath(Library.getXPath("txt_publish")));
		library.scrollToViewObject(webdriver, publish);
		library.click(webdriver, "txt_publish");

	}

	// ------------------------------------------------------------------------------------------
	/**
	 * create department
	 * 
	 * @throws Exception
	 */
	// ------------------------------------------------------------------------------------------
	public void createDepartment() throws Exception {
		// click on add department button
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_department");
		WebElement department = webdriver.findElement(By.xpath(Library.getXPath("txt_add_department")));
		if (department.isDisplayed()) {
			library.click(webdriver, "txt_add_department");
			Assert.assertTrue(true, "Add department is Displayed Successfully");
		} else {
			Assert.assertFalse(false, "Add department Element not found");
		}
		// select questionnaire from the list
		Thread.sleep(8000);
		String tempQuetion = questionnaireName;
		System.out.println("Print Question:" + tempQuetion);
		library.click(webdriver, "txt_drop_down");
		WebElement dropDown = webdriver.findElement(By.xpath(Library.getXPath("select_questionnaire")));
		if (dropDown.isDisplayed()) {
			library.enterText(webdriver, "select_questionnaire", tempQuetion);
			library.dynamicWaitForVisibilityOfElement(webdriver, "txt_department_option");
			library.click(webdriver, "txt_department_option");
			Assert.assertTrue(true, "Successfully Entered Questionnaire Name: " + tempQuetion);
		} else {
			Assert.fail("Failed to Enter Questionnaire Name: " + tempQuetion);
		}

		// enter Department name
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_dept_name");
		String deptName = "department" + library.getAlphaNumaricText();
		SingleAudit.departmentName = deptName;

		WebElement text = webdriver.findElement(By.xpath(Library.getXPath("txt_dept_name")));
		if (text.isDisplayed()) {
			library.enterText(webdriver, "txt_dept_name", deptName);
			Assert.assertTrue(true, "Department Name Entered Successfully" + deptName);
		} else {
			Assert.assertFalse(false, "Failed to enter Department Name: " + deptName);
		}

		// enter department description
		WebElement description = webdriver.findElement(By.xpath(Library.getXPath("txt_description")));
		if (description.isDisplayed()) {
			library.enterText(webdriver, "txt_description", deptName);
			Assert.assertTrue(true, "Successfully Entered Description: " + deptName);
		} else {
			Assert.fail("Failed to enter description: " + deptName);
		}
		// enter Auditor email for the department
		String username = AppData.getProperty("username");
		WebElement email = webdriver.findElement(By.xpath(Library.getXPath("txt_email")));
		if (email.isDisplayed()) {
			library.enterText(webdriver, "txt_email", username);
			Assert.assertTrue(true, "Successfully Entered Email: " + username);
		} else {
			Assert.fail("Failed to Enter Email:  " + username);
		}

		// enter unique key
		String uniqueKey = library.getAlphaNumaricText();
		WebElement key = webdriver.findElement(By.xpath(Library.getXPath("txt_user_key")));
		if (key.isDisplayed()) {
			library.enterText(webdriver, "txt_user_key", uniqueKey);
			library.click(webdriver, "Check");
			Assert.assertTrue(true, "Successfully Entered User Key: " + uniqueKey);
		} else {
			Assert.fail("Failed to Enter Unique Key: " + uniqueKey);
		}

		// add user to the department
		String auditorName = AppData.getProperty("AuditorName");
		library.click(webdriver, "drop_down_add_user");
		WebElement user = webdriver.findElement(By.xpath(Library.getXPath("txt_drop_down_add_user")));
		if (user.isDisplayed()) {
			library.enterText(webdriver, "txt_drop_down_add_user", auditorName);
			library.click(webdriver, "drop_down_add_user_option");
			Assert.assertTrue(true, "Added escalation user: " + auditorName);
		} else {
			Assert.fail("Failed to Add User: " + auditorName);
		}

		// click on save button
		library.click(webdriver, "txt_save");
		Thread.sleep(4000);
	}

	// ------------------------------------------------------------------------------------------
	/**
	 * create site
	 * 
	 * @throws Exception
	 */
	// ------------------------------------------------------------------------------------------
	public void createSite() throws Exception {
		// click on add site button
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_site");
		WebElement site = webdriver.findElement(By.xpath(Library.getXPath("txt_add_site")));
		if (site.isDisplayed()) {
			library.click(webdriver, "txt_add_site");
			Assert.assertTrue(true, "Add site is Displayed Successfully");
		} else {
			Assert.assertFalse(false, "Add site Element not found");
		}
		// refresh the browser
		webdriver.navigate().refresh();
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_name");

		// enter site name
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_name");
		String siteName = "site" + library.getAlphaNumaricText();
		SingleAudit.siteName = siteName;
		System.out.println("Site Name:  " + siteName);
		WebElement textSite = webdriver.findElement(By.xpath(Library.getXPath("txt_site_name")));
		if (textSite.isDisplayed()) {
			library.enterText(webdriver, "txt_site_name", siteName);
			Assert.assertTrue(true, "Successfully Entered Site Name: " + siteName);
		} else {
			Assert.fail("Failed to Enter Site Name: " + siteName);
		}

		// enter site address
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		String siteAddress = prop.getProperty("Site Address");
		WebElement address = webdriver.findElement(By.xpath(Library.getXPath("txt_site_address")));
		if (address.isDisplayed()) {
			library.enterText(webdriver, "txt_site_address", siteAddress);
			library.click(webdriver, "txt_site_address_option");
			Assert.assertTrue(true, "Successfully Entered Site Address: " + siteAddress);
		} else {
			Assert.fail("Failed to Enter Site Address: " + siteAddress);
		}

		// enter time zone
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_timezone");
		library.click(webdriver, "txt_timezone");
		String timeZone = prop.getProperty("Time Zone");
		WebElement time = webdriver.findElement(By.xpath(Library.getXPath("txt_dropdown_timezone")));
		if (time.isDisplayed()) {
			library.enterText(webdriver, "txt_dropdown_timezone", timeZone);
			library.click(webdriver, "txt_timezone_option");
			Assert.assertTrue(true, "Successfully Entered timezone: " + timeZone);
		} else {
			Assert.fail("Failed to Enter timezone: " + timeZone);
		}

		// enter city name
		library.click(webdriver, "txt_site_toggle");
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_city_name");
		WebElement city = webdriver.findElement(By.xpath(Library.getXPath("txt_site_city_name")));
		String cityName = AppData.getProperty("CityName");
		if (city.isDisplayed()) {
			library.enterText(webdriver, "txt_site_city_name", cityName);
			Assert.assertTrue(true, "City name is displayed");
		} else {
			Assert.fail("failed to display city name");
		}

		// enter department name
		String tempDepartmentName = AppData.getProperty("Department_Name");
		System.out.println("departmentName:" + tempDepartmentName);
		library.click(webdriver, "site_drop_down");
		WebElement department = webdriver.findElement(By.xpath(Library.getXPath("select_department")));
		if (department.isDisplayed()) {
			library.enterText(webdriver, "select_department", tempDepartmentName);
			Assert.assertTrue(true, "Successfully Selected Department Name: " + tempDepartmentName);
		} else {
			Assert.fail("Failed to Enter Department Name: " + tempDepartmentName);
		}
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_department_option");
		library.click(webdriver, "txt_site_department_option");

		// Select supervisor department
		WebElement supervisor = webdriver.findElement(By.xpath(Library.getXPath("txt_supervisor_department")));
		if (supervisor.isDisplayed()) {
			library.click(webdriver, "txt_supervisor_department");
			library.click(webdriver, "txt_supervisor_department_option");
			Assert.assertTrue(true, "Select Supervisor is dispalyed");
		} else {
			Assert.fail("Failed to display Select Supervisor");
		}
		// enter the supervisor name
		library.click(webdriver, "txt_supervisor");
		String supervisorName = AppData.getProperty("Supervisor_Name");
		library.enterText(webdriver, "enter_supervisor_name", supervisorName);
		library.click(webdriver, "txt_supervisor_option");

		// click on add to list
		library.click(webdriver, "txt_supervisor_add_list");

		// Select Auditor department
		WebElement auditor = webdriver.findElement(By.xpath(Library.getXPath("dropdown_auditor")));
		if (auditor.isDisplayed()) {
			library.click(webdriver, "dropdown_auditor");
			library.click(webdriver, "dropdown_option_auditor");
			Assert.assertTrue(true, "Select Auditor is displayed");
		} else {
			Assert.fail("Failed to display Auditor");
		}
		// enter auditor name
		String auditorName = AppData.getProperty("AuditorName");
		library.click(webdriver, "txt_auditor");
		library.enterText(webdriver, "enter_auditor_name", auditorName);
		library.click(webdriver, "txt_auditor_option");

		// click on add to list
		library.click(webdriver, "txt_auditor_add_list");

		// click on save button
		WebElement save = webdriver.findElement(By.xpath(Library.getXPath("txt_site_save")));
		if (save.isDisplayed()) {
			library.scrollToViewObject(webdriver, save);
			library.click(webdriver, "txt_site_save");
			Assert.assertTrue(true, "save button is Displayed");
		} else {
			Assert.fail("save button is not Displayed");
		}

		// verify the site creation message
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_create_site");
		String actualMessage = webdriver.findElement(By.xpath(Library.getXPath("txt_create_site"))).getText();
		String expMessage = AppData.getProperty("Site_Creation_Message");
		if (expMessage.equals(expMessage)) {
			Assert.assertEquals(actualMessage, expMessage, "Successfully Verified Site Creation message:"
					+ "Actual Message is: " + actualMessage + "Expected Message is: " + expMessage);
		} else {
			Assert.fail("Failed to verify Site Creation message is: " + expMessage);
		}

		// Report single Audit recurring schedule
		Thread.sleep(9000);
		WebElement audit = webdriver.findElement(By.xpath(Library.getXPath("txt_report_schedule")));
		if (audit.isDisplayed()) {
			library.scrollToViewObject(webdriver, audit);
			library.dynamicWaitForVisibilityOfElement(webdriver, "txt_report_schedule");
			library.click(webdriver, "txt_report_schedule");
			Assert.assertTrue(true, "navigate to report schedule");
		} else {
			Assert.fail("Failed to navigate report schedule");
		}

		// Verify Report Schedule Title
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_single_audit");
		String reportTile = webdriver.findElement(By.xpath(Library.getXPath("txt_single_audit"))).getText();
		System.out.println("Title: " + reportTile);
		String expTitle = AppData.getProperty("Single_Audit");
		if (expTitle.equals(reportTile)) {
			Assert.assertEquals(reportTile, expTitle, "Successfully verified Page Title: " + expTitle);
		} else {
			Assert.fail("Failed to verify page title: " + expTitle);
		}

		// Select questionnaire
		library.click(webdriver, "report_dropdown_questionnaire");
		library.click(webdriver, "report_dropdown_questionnaire_option");
		library.click(webdriver, "report_container");

		// Save Schedule
		WebElement saveReport = webdriver.findElement(By.xpath(Library.getXPath("txt_report_save")));
		if (saveReport.isDisplayed()) {
			library.scrollToViewObject(webdriver, saveReport);
			library.click(webdriver, "txt_report_save");
			Assert.assertTrue(true, "Save Button is Displayed on Report Page");
		} else {
			Assert.fail("Save Button is not Displayed on Report Page");
		}

		// again save the changes at site level
		library.click(webdriver, "site_txt_details");
		WebElement saveSite = webdriver.findElement(By.xpath(Library.getXPath("txt_site_save")));
		if (saveSite.isDisplayed()) {
			library.scrollToViewObject(webdriver, saveSite);
			library.click(webdriver, "txt_site_save");
			Assert.assertTrue(true, "Save Button is Displayed on Site Page");
		} else {
			Assert.fail("Save Button is not Displayed on Site Page");
		}
	}

	public void verifySingleAudit() throws Exception {
		Thread.sleep(8000);
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_audit_site_search");
		WebElement search = webdriver.findElement(By.xpath(Library.getXPath("txt_audit_site_search")));
		if (search.isDisplayed()) {
			Assert.assertTrue(true, "search field is display");
		} else {
			Assert.fail("failed to display search field");
		}
		String tempSite = siteName;
		library.enterText(webdriver, "txt_audit_site_search", tempSite);

		// click on site card
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_card");
		WebElement siteCard = webdriver.findElement(By.xpath(Library.getXPath("txt_site_card")));
		if (siteCard.isDisplayed()) {
			Assert.assertTrue(true, "site card is displayed");
		} else {
			Assert.fail("failed to display sitecard");
		}
		library.scrollToViewObject(webdriver, siteCard);
		library.click(webdriver, "txt_site_card");

		// click on site
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_questionnaire_not_started");
		WebElement questionnaire = webdriver.findElement(By.xpath(Library.getXPath("txt_questionnaire_not_started")));
		if (questionnaire.isDisplayed()) {
			library.click(webdriver, "txt_questionnaire_not_started");
			Assert.assertTrue(true, "questionnaire card is displayed");
		} else {
			Assert.fail("failed to display the questionnaire");
		}

		// verify the check in and click on it
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_checkin");
		WebElement checkin = webdriver.findElement(By.xpath(Library.getXPath("txt_checkin")));
		if (checkin.isDisplayed()) {
			library.click(webdriver, "txt_checkin");
			Assert.assertTrue(true, "check in is displayed");
		} else {
			Assert.fail("failed to display the checkin");
		}
		// click on yes button
		library.click(webdriver, "popup_checkin");

		// first answer
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_first_answer");
		WebElement firstAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_first_answer")));
		if (firstAnswer.isDisplayed()) {
			library.click(webdriver, "audit_first_answer");
			Assert.assertTrue(true, "first question is answered");
		} else {
			Assert.fail("failed to answer first question");
		}
		library.click(webdriver, "audit_btn_next");

		// second
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_second_answer");
		WebElement secondAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_second_answer")));
		if (secondAnswer.isDisplayed()) {
			library.click(webdriver, "audit_second_answer");
			Assert.assertTrue(true, "second question is answered");
		} else {
			Assert.fail("failed to answer second question");
		}
		library.click(webdriver, "audit_btn_next");

		// third
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_third_answer");
		WebElement thirdAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_third_answer")));
		if (thirdAnswer.isDisplayed()) {
			library.click(webdriver, "audit_third_answer");
			Assert.assertTrue(true, "third question is answered");
		} else {
			Assert.fail("failed to answer third question");
		}

		// enter comments
		String comments = library.getAlphaNumaricText();
		library.enterText(webdriver, "audit_third_answer_comments", comments);
		library.click(webdriver, "audit_btn_next");

		// fourth - open ended
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_fourth_answer");
		WebElement fourthAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_fourth_answer")));
		if (fourthAnswer.isDisplayed()) {
			Assert.assertTrue(true, "fourth question is answered");
		} else {
			Assert.fail("failed to answer fourth question");
		}
		library.enterText(webdriver, "audit_fourth_answer", comments);
		library.click(webdriver, "audit_btn_next");

		// fifth - number
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_fifth_answer");
		WebElement fifthAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_fifth_answer")));
		if (fifthAnswer.isDisplayed()) {
			Assert.assertTrue(true, "fifth question is answered");
		} else {
			Assert.fail("failed to answer fifth question");
		}
		library.enterText(webdriver, "audit_fifth_answer", "3");
		library.click(webdriver, "audit_btn_next");

		// sixth - Green/Yellow/Red
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_sixth_answer");
		WebElement sixthAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_sixth_answer")));
		if (sixthAnswer.isDisplayed()) {
			library.click(webdriver, "audit_sixth_answer");
			Assert.assertTrue(true, "sixth question is answered");
		} else {
			Assert.fail("failed to answer sixth question");
		}
		library.click(webdriver, "audit_btn_next");

		// seventh - multiple choice
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_seventh_answer");
		WebElement seventhAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_seventh_answer")));
		if (seventhAnswer.isDisplayed()) {
			library.click(webdriver, "audit_seventh_answer");
			Assert.assertTrue(true, "seventh question is answered");
		} else {
			Assert.fail("failed to answer seventh question");
		}
		library.click(webdriver, "audit_btn_next");

		// Eighth - Range with Flags
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_eighth_answer");
		WebElement eighthAnswer = webdriver.findElement(By.xpath(Library.getXPath("audit_eighth_answer")));
		if (eighthAnswer.isDisplayed()) {
			Assert.assertTrue(true, "eighth question is answered");
		} else {
			Assert.fail("failed to answer eighth question");
		}
		library.enterText(webdriver, "audit_eighth_answer", "3");

		// click on review report
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_review_report");
		WebElement submitReport = webdriver.findElement(By.xpath(Library.getXPath("audit_review_report")));
		if (submitReport.isDisplayed()) {
			Assert.assertTrue(true, "review report button is displayed");
		} else {
			Assert.fail("failed to display review report button");
		}
		library.click(webdriver, "audit_review_report");

		// click on check out
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_check_out");
		WebElement checkOut = webdriver.findElement(By.xpath(Library.getXPath("audit_check_out")));
		if (checkOut.isDisplayed()) {
			Assert.assertTrue(true, "checkout button is displayed");
		} else {
			Assert.fail("failed to display checkout button");
		}
		library.click(webdriver, "audit_check_out");

		// click on yes button
		library.click(webdriver, "audit_checkout_yes");

		// verify review complete page
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_review_complete");
		WebElement reviewComplete = webdriver.findElement(By.xpath(Library.getXPath("audit_review_complete")));
		if (reviewComplete.isDisplayed()) {
			Assert.assertTrue(true, "review complete page is displayed");
		} else {
			Assert.fail("failed to display review complete page");
		}

		// click on got it button
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_got_it");
		WebElement gotIt = webdriver.findElement(By.xpath(Library.getXPath("audit_got_it")));
		if (gotIt.isDisplayed()) {
			Assert.assertTrue(true, "got it button is displayed");
		} else {
			Assert.fail("failed to display got it button");
		}
		library.click(webdriver, "audit_got_it");
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Edit the site
	 * 
	 * @throws Exception
	 */
	// -------------------------------------------------------------------------------------------------------------------------------
	public void updateSite() throws Exception {

		// click on sites tab from left panel
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_site");
		WebElement addsite = webdriver.findElement(By.xpath(Library.getXPath("txt_add_site")));

		// Verify the search field
		WebElement search = webdriver.findElement(By.xpath(Library.getXPath("site_search_field")));
		if (search.isDisplayed()) {
			Assert.assertTrue(true, "search field is displayed");
		} else {
			Assert.fail("failed to dispaly search field");
		}

		// enter site name
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_supervisior");
		String tempSite = siteName;
		library.enterText(webdriver, "site_search_field", tempSite);

		// click on site link
		Thread.sleep(7000);
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_site");
		WebElement siteLink = webdriver.findElement(By.xpath(Library.getXPath("txt_site_supervisior")));
		if (siteLink.isDisplayed()) {
			library.click(webdriver, "txt_site_supervisior");
			Assert.assertTrue(true, "supervisor name is displayed on UI");
		} else {
			Assert.fail("failed to display supervisor name");
		}

		// edit the site name
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_site_name");
		WebElement site = webdriver.findElement(By.xpath(Library.getXPath("txt_site_name")));
		if (site.isDisplayed()) {
			String rndSite = library.getAlphaNumaricText();
			library.enterText(webdriver, "txt_site_name", rndSite);
			Assert.assertTrue(true, "Successfully edited site name: " + rndSite);
		} else {
			Assert.fail("Failed to edit site name");
		}

		// edit the site address
		WebElement address = webdriver.findElement(By.xpath(Library.getXPath("txt_site_address")));
		if (address.isDisplayed()) {
			String siteAddress = "BLR Domestic Lounge";
			library.enterText(webdriver, "txt_site_address", siteAddress);
			library.click(webdriver, "txt_site_address_option");
			Assert.assertTrue(true, "successfully edited site address: " + siteAddress);
		} else {
			Assert.fail("Failed to edit the site address");
		}

		// edit the supervisor
		library.click(webdriver, "txt_super_removeall");
		library.click(webdriver, "txt_removeall");
		Thread.sleep(2000);
		// Select supervisor
		WebElement supervisor = webdriver.findElement(By.xpath(Library.getXPath("txt_supervisor_department")));
		if (supervisor.isDisplayed()) {
			library.click(webdriver, "txt_supervisor_department");
			library.click(webdriver, "txt_supervisor_department_option");
			Assert.assertTrue(true, "Select Supervisor is dispalyed");
		} else {
			Assert.fail("Failed to display Select Supervisor");
		}
		// enter the supervisor name
		library.click(webdriver, "txt_supervisor");
		String supervisorName = AppData.getProperty("Supervisor_Name");
		library.enterText(webdriver, "enter_supervisor_name", supervisorName);
		library.click(webdriver, "txt_supervisor_option");

		// click on add to list
		library.click(webdriver, "txt_supervisor_add_list");

		// Select Auditor

		library.click(webdriver, "txt_auditor_removeall");
		library.click(webdriver, "popup_txt_remove");
		Thread.sleep(2000);
		WebElement auditor = webdriver.findElement(By.xpath(Library.getXPath("dropdown_auditor")));
		if (auditor.isDisplayed()) {
			library.click(webdriver, "dropdown_auditor");
			library.click(webdriver, "dropdown_option_auditor");
			Assert.assertTrue(true, "Select Auditor is displayed");
		} else {
			Assert.fail("Failed to display Auditor");
		}
		// enter auditor name
		String auditorName = AppData.getProperty("AuditorName");
		library.click(webdriver, "txt_auditor");
		library.enterText(webdriver, "enter_auditor_name", auditorName);
		library.click(webdriver, "txt_auditor_option");

		// click on add to list
		library.click(webdriver, "txt_auditor_add_list");

		// click on save button
		WebElement save = webdriver.findElement(By.xpath(Library.getXPath("txt_site_save")));
		if (save.isDisplayed()) {
			library.scrollToViewObject(webdriver, save);
			library.click(webdriver, "txt_site_save");
			Assert.assertTrue(true, "save button is Displayed");
		} else {
			Assert.fail("save button is not Displayed");
		}

		// verify the update message
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_create_site");
		String actualMessage = webdriver.findElement(By.xpath(Library.getXPath("txt_create_site"))).getText();
		String expMessage = AppData.getProperty("Site_Creation_Message");
		if (expMessage.equals(actualMessage)) {
			Assert.assertEquals(actualMessage, expMessage, "Successfully Verified Site Creation message:"
					+ "Actual Message is: " + actualMessage + "Expected Message is: " + expMessage);
		} else {
			Assert.fail("Failed to verify Site Creation message is: " + expMessage);
		}
	}
}
