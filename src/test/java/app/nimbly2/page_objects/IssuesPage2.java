package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	public void searchForIssue(String questionName) throws InterruptedException {
		// Locators
		String tab_on_hamburger = locators.getProperty("tap_hamburger");
		String tab_issues_from_options = locators.getProperty("select_issues");
		String search_issue = locators.getProperty("search_issue");

		// Expected values
		String expQuestionName = prop.getProperty(questionName);

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
			appdriver.findElement(AppiumBy.xpath(search_issue)).sendKeys(expQuestionName);
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
	
	public void validateIssueDetails() throws InterruptedException {
		// Locators
		String schedule_type = locators.getProperty("issue_card_schedule_type");
		Map<String, String> locatorsMap = new HashMap<>();
		locatorsMap.put("schedule_type", locators.getProperty("issue_details_schedule_type"));
		locatorsMap.put("questionnaire_name", locators.getProperty("issue_details_questionnaire_name"));
		locatorsMap.put("category_name", locators.getProperty("issue_details_category_name"));
		locatorsMap.put("question_name", locators.getProperty("issue_details_question_name"));
		locatorsMap.put("site_name", locators.getProperty("issue_details_site_name"));
		locatorsMap.put("reporter_department", locators.getProperty("issue_details_reporter_department"));
		locatorsMap.put("reported_by", locators.getProperty("issue_details_reported_by"));

		// Expected Values
		Map<String, String> expectedValuesMap = new HashMap<>();
		expectedValuesMap.put("schedule_type", prop.getProperty("Issue_Filter_Scheduled_Type"));
		expectedValuesMap.put("questionnaire_name", prop.getProperty("Issue_Filter_Schedule_Without_Attachments"));
		expectedValuesMap.put("category_name", prop.getProperty("Issue_Filter_Catrgory_Name"));
		expectedValuesMap.put("question_name", prop.getProperty("Issue_Filter_Question_Name"));
		expectedValuesMap.put("site_name", prop.getProperty("Issue_Filter_Site_Name"));
		expectedValuesMap.put("reporter_department", prop.getProperty("Issue_Filter_Department_Name"));
		expectedValuesMap.put("reported_by", prop.getProperty("Issue_Filter_Reported_By"));

		// Tap on issue card
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(schedule_type)).click();

		// WebDriverWait initialization
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(20));

		// Validate each field
		for (Map.Entry<String, String> entry : locatorsMap.entrySet()) {
			String locatorKey = entry.getKey();
			String locatorValue = entry.getValue();
			String expectedValue = expectedValuesMap.get(locatorKey);

			// Wait for the element to be visible before interacting with it
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));

			// Get actual value from the UI
			String actualValue = element.getText();

			// Validate values
			validateField(locatorKey, actualValue, expectedValue);
		}
	}

	// Helper method to validate individual fields
	private void validateField(String fieldName, String actualValue, String expectedValue) {
		if (actualValue.equals(expectedValue)) {
			Assert.assertEquals(actualValue, expectedValue, "Successfully validated " + fieldName);
		} else {
			Assert.fail("Failed to validate " + fieldName);
		}
	}

	public void validateAddCommentsAlongWithAttachments() throws InterruptedException {
		// Locators
		String schedule_type = locators.getProperty("issue_card_schedule_type");

		// Tap on issue card
		Thread.sleep(4000);
		appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		// Map to store the locators for various elements
		Map<String, String> locatorsMap = new HashMap<>();
		locatorsMap.put("issue_activity_tab", locators.getProperty("issue_activity_tab"));
		locatorsMap.put("issue_activity_comments_tab", locators.getProperty("issue_activity_comments_tab"));
		locatorsMap.put("issue_activity_tap_comment", locators.getProperty("issue_activity_tap_comment"));
		locatorsMap.put("issue_activity_add_comment", locators.getProperty("issue_activity_add_comment"));
		locatorsMap.put("issue_activity_tap_photo", locators.getProperty("issue_activity_tap_photo"));
		locatorsMap.put("issue_activity_add_video", locators.getProperty("issue_activity_add_video"));
		locatorsMap.put("issue_activity_add_document", locators.getProperty("issue_activity_add_document"));
		locatorsMap.put("issue_activity_upload_photo_from_gallery",
				locators.getProperty("issue_activity_upload_photo_from_gallery"));
		locatorsMap.put("issue_activity_select_photo", locators.getProperty("issue_activity_select_photo"));
		locatorsMap.put("issue_activity_add_photo", locators.getProperty("issue_activity_add_photo"));
		locatorsMap.put("issue_activity_start_recording", locators.getProperty("issue_activity_start_recording"));
		locatorsMap.put("issue_activity_stop_recording", locators.getProperty("issue_activity_stop_recording"));
		locatorsMap.put("issue_activity_use_video", locators.getProperty("issue_activity_use_video"));
		locatorsMap.put("issue_activity_select_document", locators.getProperty("issue_activity_select_document"));
		locatorsMap.put("issue_activity_submit_button", locators.getProperty("issue_activity_submit_button"));

		// Perform actions in sequence: Clicking elements and performing necessary steps
		// for comment and attachment

		// Tap on the activity tab to start the process
		clickElementIfDisplayed(locatorsMap.get("issue_activity_tab"));

		// Tap on the comments tab to navigate to the comment section
		clickElementIfDisplayed(locatorsMap.get("issue_activity_comments_tab"));

		// Tap on the comment box to enter a new comment
		clickElementIfDisplayed(locatorsMap.get("issue_activity_tap_comment"));

		// We specifically handle this locator here to add the comment to the system
		enterComment(locatorsMap.get("issue_activity_add_comment"), "Successfully Added Comments");

		// Tap on the photo icon to add a photo attachment
		clickElementIfDisplayed(locatorsMap.get("issue_activity_tap_photo"));

		// Upload photo from the gallery
		clickElementIfDisplayed(locatorsMap.get("issue_activity_upload_photo_from_gallery"));

		// Select the photo from the gallery
		clickElementIfDisplayed(locatorsMap.get("issue_activity_select_photo"));

		// Confirm photo selection by clicking "Add Photo"
		clickElementIfDisplayed(locatorsMap.get("issue_activity_add_photo"));

		// Tap on the video icon to prepare for adding a video attachment
		clickElementIfDisplayed(locatorsMap.get("issue_activity_add_video"));

		// Start video recording
		clickElementIfDisplayed(locatorsMap.get("issue_activity_start_recording"));

		// Adding sleep to record video
		Thread.sleep(4000);

		// Stop video recording after some time
		clickElementIfDisplayed(locatorsMap.get("issue_activity_stop_recording"));

		// Use the recorded video
		clickElementIfDisplayed(locatorsMap.get("issue_activity_use_video"));

		// Tap on the document icon to add a document attachment
		clickElementIfDisplayed(locatorsMap.get("issue_activity_add_document"));

		// Select the document to attach
		clickElementIfDisplayed(locatorsMap.get("issue_activity_select_document"));

		// Tap on the submit button to finalize the comment with attachments
		clickElementIfDisplayed(locatorsMap.get("issue_activity_submit_button"));
	}

	// Helper method to perform the click if element is displayed and wait for it to
	// be clickable
	private void clickElementIfDisplayed(String locator) {
		try {
			// Wait for the element to be visible and clickable
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(25));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

			// Additional check for element interactability
			if (element.isDisplayed() && element.isEnabled()) {
				try {
					element.click();
				} catch (Exception clickException) {
					// If regular click fails, attempt a JavaScript click
					JavascriptExecutor js = (JavascriptExecutor) appdriver;
					js.executeScript("arguments[0].click();", element);
				}
			} else {
				Assert.fail("Failed to tap on element: " + locator);
			}
		} catch (TimeoutException e) {
			Assert.fail("Element not clickable within timeout: " + locator);
		} catch (NoSuchElementException e) {
			Assert.fail("Element not found: " + locator);
		} catch (Exception e) {
			Assert.fail("Error while clicking element: " + locator + " | " + e.getMessage());
		}
	}

	// Helper method to enter text (comment) into a specified input field
	private void enterComment(String locator, String commentText) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

			// Clear any existing text and enter the comment
			commentField.clear();
			commentField.sendKeys(commentText);
		} catch (NoSuchElementException e) {
			Assert.fail("Comment input field not found: " + locator);
		} catch (Exception e) {
			Assert.fail("Error while entering comment into field: " + locator + " | " + e.getMessage());
		}
	}

	public void validateIssueFilterFunctionality() throws InterruptedException {
		// Expected Values
		String expQuestionnaireName = prop.getProperty("Issue_Filter_Schedule_Without_Attachments");
		String expSiteName = prop.getProperty("Issue_Filter_Site_Name");
		String expAssignedUser = prop.getProperty("Issue_Filter_Owner");
		String expReporterDepartment = prop.getProperty("Issue_Filter_Department_Name");

		applyAndValidateFilter("issue_tap_filter_by_flag", "issue_flag_filter_by_red_flag",
				new String[] { "Scheduled", "Adhoc" });
		resetAndApplyFilter("issue_tap_filter_by_questionnaire", "issue_filter_serach_questionnaire",
				expQuestionnaireName, "issue_filter_select_questionnaire", new String[] { "Scheduled", "Adhoc" });
		resetAndApplyFilter("issue_tap_filter_by_assigned_user", "issue_filter_search_by_user", expAssignedUser,
				"issue_filter_select_user", new String[] { "Scheduled", "Adhoc" });
		resetAndApplyFilter("issue_tap_filter_by_reporter_department", "issue_filter_serach_reporter_department",
				expReporterDepartment, "issue_filter_select_reporter_department",
				new String[] { "Scheduled", "Adhoc" });
		resetAndApplyFilter("issue_filter_tap_filter_by_site", "issue_filter_serach_site_name", expSiteName,
				"issue_filter_select_site_name", new String[] { "Scheduled", "Adhoc" });
		applyAndValidateFilter("issue_tap_filter_by_priority", "issue_filter_select_priority",
				new String[] { "Scheduled", "Adhoc" });
	}

	// Helper method to apply and validate a filter with only a tap and selection
	private void applyAndValidateFilter(String tapLocator, String selectLocator, String[] validResults)
			throws InterruptedException {
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_tap_filter"))).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty(tapLocator))).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty(selectLocator))).click();
		applyFilter();
		validateResults(validResults);
	}

	// Helper method to reset, apply, and validate filters involving a search action
	private void resetAndApplyFilter(String tapLocator, String searchLocator, String searchValue, String selectLocator,
			String[] validResults) throws InterruptedException {
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_tap_filter"))).click();
		Thread.sleep(1000);
		resetFilter();
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_tap_filter"))).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty(tapLocator))).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty(searchLocator))).sendKeys(searchValue);
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty(selectLocator))).click();
		applyFilter();
		validateResults(validResults);
	}

	// Method to reset filter
	private void resetFilter() throws InterruptedException {
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_filter_reset_button"))).click();
		Thread.sleep(1000);
	}

	// Method to apply filter
	private void applyFilter() throws InterruptedException {
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_filter_save_button"))).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(locators.getProperty("issue_filter_apply_button"))).click();
	}

	// Method to validate filter results
	private void validateResults(String[] validResults) {
		List<WebElement> resultsElements = appdriver
				.findElements(AppiumBy.xpath(locators.getProperty("issue_filter_results")));
		for (WebElement result : resultsElements) {
			String resultText = result.getText();
			boolean matchFound = false;

			for (String validResult : validResults) {
				if (validResult.equals(resultText)) {
					matchFound = true;
					break;
				}
			}

			if (!matchFound) {
				Assert.fail("Unexpected Result Found: " + resultText);
			}
		}
	}
	
	public void navigateToIssuesList() throws InterruptedException {
		String tab_back_button = locators.getProperty("issue_activity_back_button");
		
		//tap on back button
		Thread.sleep(5000);
		if(appdriver.findElement(AppiumBy.xpath(tab_back_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tab_back_button)).click();
		}else {
			Assert.fail("Failed to tap on back button");
		}
	}
	
	public void verifyDefaultFilter() {
		// Locators
		String my_saved_filters = locators.getProperty("issue_default_filter_my_saved_filters");
		String toggle = locators.getProperty("issue_default_filter_toggle");
		String close_popup = locators.getProperty("issue_default_close_popup");

		// scroll the filter headers
		try {
			// Locate the second instance of HorizontalScrollView using the specified XPath
			WebElement headerScrollView = appdriver
					.findElement(AppiumBy.xpath("(//android.widget.HorizontalScrollView)[2]"));

			// Check if the header scroll view is displayed and apply horizontal scroll
			// action
			if (headerScrollView.isDisplayed()) {
				appdriver.findElement(AppiumBy.androidUIAutomator(
						"new UiScrollable(new UiSelector().className(\"android.widget.HorizontalScrollView\").instance(1))"
								+ ".setAsHorizontalList().scrollForward()"));
			} else {
				System.out.println("Specified header scroll view is not visible.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Horizontal header not found: " + e.getMessage());
		}

		// tap on my filters
		if (appdriver.findElement(AppiumBy.xpath(my_saved_filters)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(my_saved_filters)).click();
		} else {
			Assert.fail("Failed to tap on my saved filters");
		}

		// verify default filters are applied or not
		boolean isEnabled = isFilterToggleEnabled(toggle);
		if (isEnabled) {
			Assert.assertTrue(true, "toggle enabled");
		} else {
			Assert.assertTrue(false, "toggle disabled");
		}

		// close pop up
		if (appdriver.findElement(AppiumBy.xpath(close_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(close_popup)).click();
		} else {
			Assert.fail("Failed to close pop up");
		}
	}

	public boolean isFilterToggleEnabled(String toggleXPath) {
		try {
			// Locate the filter toggle element using the provided XPath
			WebElement filterToggle = appdriver.findElement(By.xpath(toggleXPath));

			// Return true if the toggle is enabled, otherwise false
			return filterToggle.isEnabled();
		} catch (NoSuchElementException e) {
			// If the toggle is not found, log the error and return false
			System.out.println("Filter toggle not found: " + e.getMessage());
			return false;
		}
	}

}