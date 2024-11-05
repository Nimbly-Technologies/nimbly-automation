package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class IssuesPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public IssuesPage2(AppiumDriver appdriver, Properties prop) throws IOException {
		this.appdriver = appdriver;
		this.prop = prop;
		initLocators();
	}

	private void initLocators() throws IOException {
		this.locators = loadLocators();
	}

	private Properties loadLocators() throws IOException {
		String driverType = prop.getProperty("driverType");
		String filePath = null;

		if (driverType.equals("android")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//IssuesPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//IssuesPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void searchForIssue() throws InterruptedException {
		// Locators
		String tab_on_hamburger = locators.getProperty("tap_hamburger");
		String tab_issues_from_options = locators.getProperty("select_issues");
		String search_issue = locators.getProperty("search_issue");

		// Expected values
		String questionName = prop.getProperty("Issue_Question_Name");

		// tab on hamburger
		Thread.sleep(12000);
		if (appdriver.findElement(AppiumBy.xpath(tab_on_hamburger)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tab_on_hamburger)).click();
		} else {
			Assert.fail("Failed to tab on hamburger");
		}

		// click on issues from from hamburger
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(tab_issues_from_options)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tab_issues_from_options)).click();
		} else {
			Assert.fail("Failed to selec Issues from hamburger");
		}

		// search for issue
		Thread.sleep(10000);
		if (appdriver.findElement(AppiumBy.xpath(search_issue)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(search_issue)).sendKeys(questionName);
		} else {
			Assert.fail("Failed to search Questionnaire Name");
		}

	}

	public void validateIssueCardDetails() throws InterruptedException {
		// Locators
		String site_name = locators.getProperty("issue_card_site_name");
		String question_name = locators.getProperty("issue_card_questionnaire_name");
		String issue_status = locators.getProperty("issue_card_issue_status");
		String schedule_type = locators.getProperty("issue_card_schedule_type");
		String issue_owner = locators.getProperty("issue_card_issue_assignee");

		// Expected Values
		String expSiteName = prop.getProperty("Issue_Site_Name");
		String expQuestionName = prop.getProperty("Issue_Question_Name");
		String expIssueStatus = prop.getProperty("Issue_Status");
		String expScheduleType = prop.getProperty("Issue_Scheduled_Type");
		String expIssueOwner = prop.getProperty("Issue_Owner");

		// validate site name
		Thread.sleep(5000);
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Successfully validated site name");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate questionnaire name
		Thread.sleep(2000);
		String actQuestionName = appdriver.findElement(AppiumBy.xpath(question_name)).getText();
		if (actQuestionName.equals(expQuestionName)) {
			Assert.assertEquals(actQuestionName, expQuestionName, "Successfully validated questionnaire name");
		} else {
			Assert.fail("Failed to validate questionnaire name");
		}

		// validate issue status
		Thread.sleep(2000);
		String actIssueStatus = appdriver.findElement(AppiumBy.xpath(issue_status)).getText();
		if (actIssueStatus.equals(expIssueStatus)) {
			Assert.assertEquals(actIssueStatus, expIssueStatus, "Successfully validated issue status");
		} else {
			Assert.fail("Failed to validate issue status");
		}

		// validate Issue Owner
		Thread.sleep(2000);
		String actIssueOwner = appdriver.findElement(AppiumBy.xpath(issue_owner)).getText();
		if (actIssueOwner.equals(expIssueOwner)) {
			Assert.assertEquals(actIssueOwner, expIssueOwner, "Successfully validated Issue Owner");
		} else {
			Assert.fail("Failed to validate issue owner");
		}

		// validate schedule type
		Thread.sleep(2000);
		String actScheduleType = appdriver.findElement(AppiumBy.xpath(schedule_type)).getText();
		if (actScheduleType.equals(expScheduleType)) {
			Assert.assertEquals(actScheduleType, expScheduleType, "Successfully validated schedule type");
		} else {
			Assert.fail("Failed to validate schedule type");
		}

		// tap on issue card
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
	}

	public void editIssueDetails() throws InterruptedException {
		// Locators
		String change_priority = locators.getProperty("edit_issue_priority");
		String tap_issue_status = locators.getProperty("tap_issue_status");
		String select_issue_status = locators.getProperty("edit_issue_status");
		String tap_issue_severity = locators.getProperty("tap_issue_severity");
		String change_severity = locators.getProperty("edit_issue_severity");
		String tap_assigned_department = locators.getProperty("tap_issue_assigned_department");
		String search_department = locators.getProperty("edit_issue_search_deparment_name");
		String select_department = locators.getProperty("edit_issue_select_department");
		String tap_assigned_user = locators.getProperty("tap_issue_assigned_user");
		String search_user = locators.getProperty("edit_issue_search_user_name");
		String select_user = locators.getProperty("edit_issue_select_user");
		String resolve_issue = locators.getProperty("resolve_issue");
		String resolve_confirmation_popup = locators.getProperty("resolve_issue_confirmation_popup");
		String close_nps = locators.getProperty("issue_close_nps_popup");
		String back_button = locators.getProperty("issue_back_button");

		// Expected Values
		String expDepartmentName = prop.getProperty("Issue_Department_Name");
		String expUserName = prop.getProperty("Issue_User_Name");

		// change priority
		Thread.sleep(5000);
		if (appdriver.findElement(AppiumBy.xpath(change_priority)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(change_priority)).click();
		} else {
			Assert.fail("Failed to change issue priority");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// change severity
		Thread.sleep(6000);
		if (appdriver.findElement(AppiumBy.xpath(tap_issue_severity)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_issue_severity)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(change_severity)).click();
		} else {
			Assert.fail("Failed to change issue severity");
		}

		// change assigned department
		Thread.sleep(7000);
		if (appdriver.findElement(AppiumBy.xpath(tap_assigned_department)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_assigned_department)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(search_department)).sendKeys(expDepartmentName);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_department)).click();

		} else {
			Assert.fail("Failed to change assigned department");
		}

		// change assigned user
		Thread.sleep(7000);
		if (appdriver.findElement(AppiumBy.xpath(tap_assigned_user)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_assigned_user)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(search_user)).sendKeys(expUserName);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_user)).click();

		} else {
			Assert.fail("Failed to change assigned department");
		}

		// change issue status
		Thread.sleep(6000);
		if (appdriver.findElement(AppiumBy.xpath(tap_issue_status)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_issue_status)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_issue_status)).click();
		} else {
			Assert.fail("Failed to change issue status");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// resolve the issue
		Thread.sleep(5000);
		if (appdriver.findElement(AppiumBy.xpath(resolve_issue)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(resolve_issue)).click();
		} else {
			Assert.fail("Failed to resolve the issue");
		}

		// resolve issue confirmation popup
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(resolve_confirmation_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(resolve_confirmation_popup)).click();
		} else {
			Assert.fail("Failed to tab on resolve button");
		}

		// close nps pop up
		Thread.sleep(10000);
		if (appdriver.findElement(AppiumBy.xpath(close_nps)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(close_nps)).click();
		} else {
			Assert.fail("Failed to close nps pop up");
		}

		// tap on back button
		Thread.sleep(7000);
		if (appdriver.findElement(AppiumBy.xpath(back_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(back_button)).click();
		} else {
			Assert.fail("Failed to tap on back button");
		}
	}

	public void createAdhocIssue() throws InterruptedException {
		// Locators
		String add_issue = locators.getProperty("adhoc_tap_add_issue");
		String new_issue = locators.getProperty("adhoc_tap_new_issue");
		String question_title = locators.getProperty("adhoc_question_title");
		String tap_category_dropdown = locators.getProperty("adhoc_tap_category_dropdown");
		String serach_category = locators.getProperty("adhoc_serach_category_name");
		String select_category = locators.getProperty("adhoc_select_category");
		String tap_site_dropdown = locators.getProperty("adhoc_tap_site_dropdown");
		String serach_site = locators.getProperty("adhoc_search_site_name");
		String select_site = locators.getProperty("adhoc_select_site_name");
		String choose_priority = locators.getProperty("adhoc_choose_priority");
		String tap_due_date = locators.getProperty("adhoc_tap_due_date");
		String save_button = locators.getProperty("adhoc_save_date");
		String tap_assign_department = locators.getProperty("adhoc_tap_assign_department");
		String serach_assign_department = locators.getProperty("adhoc_serach_department");
		String select_assign_department = locators.getProperty("adhoc_select_department_name");
		String tap_reporter_department = locators.getProperty("adhoc_tap_reporter_department");
		String select_reporter_department = locators.getProperty("adhoc_select_reporter_department");
		String assigned_user_dropdown = locators.getProperty("adhoc_tap_asigned_user_dropdown");
		String search_user = locators.getProperty("adhoc_search_user");
		String select_user = locators.getProperty("adhoc_select_user");
		String add_issue_button = locators.getProperty("adhoc_add_issue");
		String close_nps = locators.getProperty("adhoc_close_nps");

		// Expected Values
		String siteName = prop.getProperty("Adhoc_Issue_Site_Name");
		String categoryName = prop.getProperty("Ahoc_Issue_Category_Name");
		String assignedDepartment = prop.getProperty("Adhoc_Issue_Assigned_Department");
		String reporterDepartment = prop.getProperty("Adhoc_Issue_Reporter_Department");
		String userName = prop.getProperty("Adhoc_Issue_User");
		String questionTitle = "Adhoc" + getAlphaNumaricQuestionText();

		// tap on add issue
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(add_issue)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(add_issue)).click();
		} else {
			Assert.fail("Failed to tap on add issue");
		}

		// tap on new issue
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(new_issue)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(new_issue)).click();
		} else {
			Assert.fail("Failed to tap on new issue");
		}

		// enter question title
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(question_title)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(question_title)).sendKeys(questionTitle);
		} else {
			Assert.fail("Failed to enter question title");
		}

		// select category name
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_category_dropdown)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_category_dropdown)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(serach_category)).sendKeys(categoryName);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_category)).click();
		} else {
			Assert.fail("Failed to enter caterogy name");
		}

		// select site name
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_site_dropdown)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_site_dropdown)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(serach_site)).sendKeys(siteName);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_site)).click();
		} else {
			Assert.fail("Failed to enter site name");
		}

		// choose priority
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(choose_priority)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(choose_priority)).click();
		} else {
			Assert.fail("Failed to choose priority");
		}

		// select due date
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_due_date)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_due_date)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + getTodayDate() + "']")).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(save_button)).click();
		} else {
			Assert.fail("Failed to select date");
		}

		// select assigned department
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_assign_department)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_assign_department)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(serach_assign_department)).sendKeys(assignedDepartment);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_assign_department)).click();
		} else {
			Assert.fail("Failed to select assigned department");
		}

		// select reporter department
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_reporter_department)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_reporter_department)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(serach_assign_department)).sendKeys(reporterDepartment);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_reporter_department)).click();
		} else {
			Assert.fail("Failed to select reporter department");
		}
		
		// scroll down the page
				appdriver.findElement(
						AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// select assignee
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(assigned_user_dropdown)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(assigned_user_dropdown)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(search_user)).sendKeys(userName);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_user)).click();
		} else {
			Assert.fail("Failed to select assignee");
		}

		// tap on add issue button
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(add_issue_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(add_issue_button)).click();
		} else {
			Assert.fail("Failed to add issue button");
		}

		// close nps pop up
		Thread.sleep(10000);
		if (appdriver.findElement(AppiumBy.xpath(close_nps)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(close_nps)).click();
		} else {
			Assert.fail("Failed to close nps pop up");
		}

	}

	public String getAlphaNumaricQuestionText() {
		String questionTitle = RandomStringUtils.randomAlphanumeric(10);
		return questionTitle;
	}

	private String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("d"); // Change format as needed
		return sdf.format(new Date());
	}
}