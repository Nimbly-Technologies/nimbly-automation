package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class SchedulesPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public SchedulesPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//SchedulePage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//SchedulePage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void searchForSchedule(String scheduleName) throws InterruptedException {
		Thread.sleep(5000);
		String home_schedules_tab = locators.getProperty("home_page_schedules_tab");
		String search_bar = locators.getProperty("search_for_schedule");
		String schedule_name = prop.getProperty(scheduleName);
		if (appdriver.findElement(AppiumBy.xpath(home_schedules_tab)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(home_schedules_tab)).click();
		} else {
			Assert.fail("Schedule tab is not displayed on home page");
		}
		Thread.sleep(5000);
		if (appdriver.findElement(AppiumBy.xpath(search_bar)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(search_bar)).sendKeys(schedule_name);
		} else {
			Assert.fail("Failed to serach schedule name");
		}

	}

	public void validateScheduleCardDetails(String scheduleType, String attachmentType) throws InterruptedException {
		Thread.sleep(3000);
		// locators
		String schedule_name = locators.getProperty(scheduleType + "_" + "schedule_name" + "_" + attachmentType);
		String site_name = locators.getProperty("site_name");
		String schedule_type = locators.getProperty(scheduleType + "_" + "schedule_type");
		String schedule_status = locators.getProperty("schedule_status");
		String completed_text = locators.getProperty("completed_text");
		// Expected values
		String expScheduleName = prop.getProperty(scheduleType + "_" + "Schedule" + "_" + attachmentType);
		String expSiteName = prop.getProperty(scheduleType + "_" + "Site_Name");
		String scheduleStatus = prop.getProperty("Schedule_Status");
		String completedProgress = prop.getProperty("Completed_Text");
		String expScheduleType = prop.getProperty(scheduleType + "_" + "Scheduled_Type");

		// validate schedule name
		String actScheduleName = appdriver.findElement(AppiumBy.xpath(schedule_name)).getText();
		if (expScheduleName.equals(actScheduleName)) {
			Assert.assertEquals(actScheduleName, expScheduleName, "Successfully validated schedule name!");
		} else {
			Assert.fail("Failed to validate schedule name");
		}

		// validate site name
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Successfully validated site name!");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate completed progress
		String actcompletedProgress = appdriver.findElement(AppiumBy.xpath(completed_text)).getText();
		if (actcompletedProgress.equals(completedProgress)) {
			Assert.assertEquals(actcompletedProgress, completedProgress, "Successfully validated completed progress!");
		} else {
			Assert.fail("Failed to validate completed progress");
		}

		// validate schedule status
		String actScheduleStatus = appdriver.findElement(AppiumBy.xpath(schedule_status)).getText();
		if (actScheduleStatus.equals(scheduleStatus)) {
			Assert.assertEquals(actScheduleStatus, scheduleStatus, "Successfully validated schedule status!");
		} else {
			Assert.fail("Failed to validate schedule status");
		}

		// validate schedule type
		String actScheduleType = appdriver.findElement(AppiumBy.xpath(schedule_type)).getText();
		if (actScheduleType.equals(expScheduleType)) {
			Assert.assertEquals(actScheduleType, expScheduleType, "Successfully validated schedule type!");
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to validate schedule type");
		}
	}

	public void validateCheckinPopupDetails(String scheduleType, String attachmentType) throws InterruptedException {
		Thread.sleep(3000);
		// locators
		String schedule_name = locators
				.getProperty(scheduleType + "_" + "checkin_popup_schedule_name" + "_" + attachmentType);
		String schedule_type = locators.getProperty(scheduleType + "_" + "schedule_type");
		String site_name = locators.getProperty("checkin_popup_site_name");
		String completed_text = locators.getProperty("checkin_popup_completed_text");
		String checkin_button = locators.getProperty("checkin_button");
		// Expected values
		String expScheduleName = prop.getProperty(scheduleType + "_" + "Schedule" + "_" + attachmentType);
		String expSiteName = prop.getProperty(scheduleType + "_" + "Site_Name");
		String completedProgress = prop.getProperty("Completed_Text");
		String expScheduleType = prop.getProperty(scheduleType + "_" + "Scheduled_Type");
		String checkinButton = prop.getProperty("Checkin_Button");

		// Validate schedule name
		String actScheduleName = appdriver.findElement(AppiumBy.xpath(schedule_name)).getText();
		if (expScheduleName.equals(actScheduleName)) {
			Assert.assertEquals(actScheduleName, expScheduleName, "Successfully validated schedule name!");
		} else {
			Assert.fail("Failed to validate schedule name");
		}

		// Validate site name
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (expSiteName.equals(actSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Successfully validated site name!");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// Validate schedule type
		String actScheduleType = appdriver.findElement(AppiumBy.xpath(schedule_type)).getText();
		if (actScheduleType.equals(expScheduleType)) {
			Assert.assertEquals(actScheduleType, expScheduleType, "Successfully validated schedule type!");
		} else {
			Assert.fail("Failed to validate schedule type");
		}

		// Validate completed progress
		String actCompletedProgress = appdriver.findElement(AppiumBy.xpath(completed_text)).getText();
		if (actCompletedProgress.equals(completedProgress)) {
			Assert.assertEquals(actCompletedProgress, completedProgress, "Successfully validated completed progress!");
		} else {
			Assert.fail("Failed to validate completed progress");
		}

		// Validate check-in button text
		Thread.sleep(3000);
		String actCheckinButton = appdriver.findElement(AppiumBy.xpath(checkin_button)).getText();
		if (actCheckinButton.equals(checkinButton)) {
			Assert.assertEquals(actCheckinButton, checkinButton, "Successfully validated check-in button!");
			appdriver.findElement(AppiumBy.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to validate check-in button");
		}
	}

	public void auditingProcess(String scheduleType, String attachmentType) throws InterruptedException {
		answerYesAndNoQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerGreenYellowRedQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerScoreQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerOpenEndedQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerNumberQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerMultipleChoiceQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerMultipleChoiceWithScoreQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerChecklistQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerRangeWithFlagQuestion(scheduleType, attachmentType);
		tabOnNextButton();
		answerDateTimeQuestion(scheduleType, attachmentType);
		tabOnReviewButton();

	}

	public void answerYesAndNoQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("yes_no_question_text");
		String select_answer = locators.getProperty("yes_and_no_question_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expYesAndNoQuestion = prop.getProperty(scheduleType + "_" + "Question1");
		Thread.sleep(3000);

		// Validate Category Name
		Thread.sleep(3000);
		String actCategoryName = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (actCategoryName.equals(expCategoryName1)) {
			Assert.assertEquals(actCategoryName, expCategoryName1, "Successfully validated Category Name!");
		} else {
			Assert.fail("Failed to validate Category Name");
		}

		// Validate Yes and No Question Text
		String actQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (actQuestionText.equals(expYesAndNoQuestion)) {
			Assert.assertEquals(actQuestionText, expYesAndNoQuestion, "Successfully validated Question Text!");
		} else {
			Assert.fail("Failed to validate Yes and No Question Text");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

		// select yes and no question answer
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to clcik on Yes and No Question");
		}

		// Add attachments photo & doc
		if (attachmentType.equals("with")) {
			addAttachments();
		}
	}

	public void answerGreenYellowRedQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("green_yellow_red_text");
		String select_answer = locators.getProperty("green_yellow_red_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expGreenYellowRedQuestion = prop.getProperty(scheduleType + "_" + "Question2");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category Name
		String actCategoryName = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName1.equals(actCategoryName)) {
			Assert.assertEquals(actCategoryName, expCategoryName1, "Successfully validated Category Name!");
		} else {
			Assert.fail("Failed to validate Category Name");
		}

		// Validate second question text
		String actQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expGreenYellowRedQuestion.equals(actQuestionText)) {
			Assert.assertEquals(actQuestionText, expGreenYellowRedQuestion,
					"Successfully validated Second Question Name!");
		} else {
			Assert.fail("Failed to validate second question text");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

		// Select green, yellow & red question answer
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to answer green, yellow & red question");
		}

		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// Validate Issue Priority section
		if (scheduleType.equals("Adhoc") || scheduleType.equals("Daily") || scheduleType.equals("Weekly")
				|| scheduleType.equals("Monthly")) {
			validateIssuePrioritySection();
		}
	}

	public void answerMultipleChoiceQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name2");
		String question_text = locators.getProperty("multiple_choice_question_text");
		String select_answer = locators.getProperty("multiple_choice_answer");
		// Expected Values
		String expCategoryName2 = prop.getProperty("Category_Name2");
		String expMultipleChoice = prop.getProperty(scheduleType + "_" + "Question6");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 2
		String actCategoryName2 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName2.equals(actCategoryName2)) {
			Assert.assertEquals(actCategoryName2, expCategoryName2, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate Open-Ended question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expMultipleChoice.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expMultipleChoice,
					"Successfully validated multiple choice Question Text");
		} else {
			Assert.fail("Failed to validate multiple choice Question Text");
		}

		// add answer for open-ended question
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to answer multiple choice question");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

	}

	public void answerMultipleChoiceWithScoreQuestion(String scheduleType, String attachmentType)
			throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("multiple_choice_score_question_text");
		String select_answer = locators.getProperty("multiple_choice_score_answer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expMultipleChoiceScore = prop.getProperty(scheduleType + "_" + "Question7");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 3
		String actCategoryName3 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName3.equals(actCategoryName3)) {
			Assert.assertEquals(actCategoryName3, expCategoryName3, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate multiple choice with score question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expMultipleChoiceScore.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expMultipleChoiceScore,
					"Successfully validated multiple choice with score Question Text");
		} else {
			Assert.fail("Failed to validate multiple choice with score Question Text");
		}

		// add answer for multiple choice with score
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to answer multiple choice with score question");
		}

		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// Validate Issue Priority section
		if (scheduleType.equals("Adhoc") || scheduleType.equals("Daily") || scheduleType.equals("Weekly")
				|| scheduleType.equals("Monthly")) {
			validateIssuePrioritySection();
		}
	}

	public void answerOpenEndedQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name2");
		String question_text = locators.getProperty("open_ended_question_answer_text");
		String select_answer = locators.getProperty("open_ended_question_answer");
		String clear_text = locators.getProperty("open_ended_question_clear_text");
		// Expected Values
		String expCategoryName2 = prop.getProperty("Category_Name2");
		String expOpenEndedQuestion = prop.getProperty(scheduleType + "_" + "Question4");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 2
		String actCategoryName2 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName2.equals(actCategoryName2)) {
			Assert.assertEquals(actCategoryName2, expCategoryName2, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate Open-Ended question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expOpenEndedQuestion.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expOpenEndedQuestion,
					"Successfully validated Open Ended Question Text");
		} else {
			Assert.fail("Failed to validate Open Ended Question Text");
		}

		// add answer for open-ended question
		if (appdriver.findElement(AppiumBy.xpath(clear_text)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(clear_text)).clear();
			appdriver.findElement(AppiumBy.xpath(select_answer)).sendKeys(expOpenEndedQuestion);
		} else {
			Assert.fail("Failed to answer open ended question");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

	}

	public void answerNumberQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name2");
		String question_text = locators.getProperty("number_question_text");
		String select_dropdown = locators.getProperty("number_question_dropdown");
		String select_uom = locators.getProperty("number_question_select_uom");
		String add_number = locators.getProperty("number_question_enter_answer");
		String clear_text = locators.getProperty("number_question_clear_text");

		// Expected Values
		String expCategoryName2 = prop.getProperty("Category_Name2");
		String expNumberQuestion = prop.getProperty(scheduleType + "_" + "Question5");
		String addNumber = "10";
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 2
		String actCategoryName2 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName2.equals(actCategoryName2)) {
			Assert.assertEquals(actCategoryName2, expCategoryName2, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate Number question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expNumberQuestion.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expNumberQuestion, "Successfully validated Number Question Text");
		} else {
			Assert.fail("Failed to validate Number Question Text");
		}

		// add answer for number question
		appdriver.findElement(AppiumBy.xpath(select_dropdown)).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(select_uom)).click();
		Thread.sleep(1000);
		appdriver.findElement(AppiumBy.xpath(clear_text)).clear();
		appdriver.findElement(AppiumBy.xpath(add_number)).sendKeys(addNumber);

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

	}

	public void answerRangeWithFlagQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("range_with_flag_question_text");
		String select_answer = locators.getProperty("range_with_flag_answer");
		String clear_text = locators.getProperty("range_flag_clear_text");
		String green_flag_answer = locators.getProperty("green_flag_answer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expRangeWithFlagQuestion = prop.getProperty(scheduleType + "_" + "Question9");
		String flagNumber = "12";
		String expAnswer = "Answer is within Green Flag range";
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 3
		String actCategoryName3 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName3.equals(actCategoryName3)) {
			Assert.assertEquals(actCategoryName3, expCategoryName3, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate multiple choice with score question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expRangeWithFlagQuestion.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expRangeWithFlagQuestion,
					"Successfully validated Range with Flag Question Text");
		} else {
			Assert.fail("Failed to validate Range with Flag Question Text");
		}

		// add answer for multiple choice with score
		if (appdriver.findElement(AppiumBy.xpath(clear_text)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(clear_text)).clear();
			appdriver.findElement(AppiumBy.xpath(select_answer)).sendKeys(flagNumber);
		} else {
			Assert.fail("Failed to answer Range with flag question");
		}

		// validate answer
		String actAnswer = appdriver.findElement(AppiumBy.xpath(green_flag_answer)).getText();
		if (actAnswer.equals(expAnswer)) {
			Assert.assertEquals(actAnswer, expAnswer,
					"Successfully validated answered text for range with flag question");
		} else {
			Assert.fail("Failed to validate Answered text for range with flag question");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

	}

	public void answerScoreQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("score_question_text");
		String select_answer = locators.getProperty("score_question_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expScoreQuestion = prop.getProperty(scheduleType + "_" + "Question3");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category Name
		String actCategoryName = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName1.equals(actCategoryName)) {
			Assert.assertEquals(actCategoryName, expCategoryName1, "Successfully validated Category Name!");
		} else {
			Assert.fail("Failed to validate Category Name");
		}

		// Validate third question text
		String actQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expScoreQuestion.equals(actQuestionText)) {
			Assert.assertEquals(actQuestionText, expScoreQuestion, "Successfully validated Third Question Name!");
		} else {
			Assert.fail("Failed to validate Third question text");
		}

		// Validate Clear, Previous & Next buttons
		validateNextPreviousClearButtons();

		// Select score question answer
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to answer score question");
		}

		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// Validate Issue Priority section
		if (scheduleType.equals("Adhoc") || scheduleType.equals("Daily") || scheduleType.equals("Weekly")
				|| scheduleType.equals("Monthly")) {
			validateIssuePrioritySection();
		}
	}

	public void answerChecklistQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("checklist_question_text");
		String select_answer = locators.getProperty("checklist_answer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expChecklistQuestion = prop.getProperty(scheduleType + "_" + "Question8");
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 3
		String actCategoryName3 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName3.equals(actCategoryName3)) {
			Assert.assertEquals(actCategoryName3, expCategoryName3, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate multiple choice with score question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expChecklistQuestion.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expChecklistQuestion,
					"Successfully validated checklist Question Text");
		} else {
			Assert.fail("Failed to validate checklist Question Text");
		}

		// add answer for multiple choice with score
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to answer checklist question");
		}
		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// Validate Issue Priority section
		if (scheduleType.equals("Adhoc") || scheduleType.equals("Daily") || scheduleType.equals("Weekly")
				|| scheduleType.equals("Monthly")) {
			validateIssuePrioritySection();
		}
	}

	public void answerDateTimeQuestion(String scheduleType, String attachmentType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("date_time_question_text");
		String select_answer = locators.getProperty("date_time_questionanswer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expDateTimeQuestion = prop.getProperty(scheduleType + "_" + "Question10");
		String dateTime = "10-12-2024";
		Thread.sleep(3000);

		// Add photo & document attachments
		if (attachmentType.equals("with")) {
			addAttachments();
		}

		// Validate Category 3
		String actCategoryName3 = appdriver.findElement(AppiumBy.xpath(category_name)).getText();
		if (expCategoryName3.equals(actCategoryName3)) {
			Assert.assertEquals(actCategoryName3, expCategoryName3, "Successfully validated Category Name");
		} else {
			Assert.fail("Failed to validate category name");
		}

		// Validate multiple choice with score question text
		String ActQuestionText = appdriver.findElement(AppiumBy.xpath(question_text)).getText();
		if (expDateTimeQuestion.equals(ActQuestionText)) {
			Assert.assertEquals(ActQuestionText, expDateTimeQuestion, "Successfully validated Date Time Question Text");
		} else {
			Assert.fail("Failed to validate Range with Flag Question Text");
		}

		// add answer for multiple choice with score
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_answer)).sendKeys(dateTime);
		} else {
			Assert.fail("Failed to answer Date Time question");
		}

	}

	public void validateNextPreviousClearButtons() throws InterruptedException {
		// locators
		String next_button = locators.getProperty("next_button");
		String previous_button = locators.getProperty("previous_button");
		String clear_button = locators.getProperty("clear_button");
		// Expected Values
		String nextButton = prop.getProperty("Next_Button");
		String previousButton = prop.getProperty("Previous_Button");
		String clearButton = prop.getProperty("Clear_Button");
		Thread.sleep(3000);

		// Validate Next Button
		String actNextButton = appdriver.findElement(AppiumBy.xpath(next_button)).getText();
		if (nextButton.equals(actNextButton)) {
			Assert.assertEquals(actNextButton, nextButton, "Successfully validated Next Button!");
		} else {
			Assert.fail("Failed to validate Next Button");
		}

		// Validate Previous Button
		String actPreviousButton = appdriver.findElement(AppiumBy.xpath(previous_button)).getText();
		if (previousButton.equals(actPreviousButton)) {
			Assert.assertEquals(actPreviousButton, previousButton, "Successfully validated Previous Button!");
		} else {
			Assert.fail("Failed to validate Previous Button");
		}

		// Validate Clear Button
		String actClearButton = appdriver.findElement(AppiumBy.xpath(clear_button)).getText();
		if (clearButton.equals(actClearButton)) {
			Assert.assertEquals(actClearButton, clearButton, "Successfully validated Clear Button!");
		} else {
			Assert.fail("Failed to validate Clear Button");
		}

	}

	public void validateIssuePrioritySection() throws InterruptedException {
		// locators
		String low_priority = locators.getProperty("low_priority");
		String medium_priority = locators.getProperty("medium_priority");
		String high_priority = locators.getProperty("high_priority");
		// Expected Values
		String lowPriority = prop.getProperty("Low_Priority");
		String mediumPriority = prop.getProperty("Medium_Priority");
		String highPriority = prop.getProperty("High_Priority");
		Thread.sleep(3000);

		// Validate Low Priority Text
		String actLowPriority = appdriver.findElement(AppiumBy.xpath(low_priority)).getText();
		if (lowPriority.equals(actLowPriority)) {
			Assert.assertEquals(actLowPriority, lowPriority, "Successfully validated Low Priority Option!");
		} else {
			Assert.fail("Failed to validate Low Priority Option");
		}

		// Validate Medium Priority Text
		String actMediumPriority = appdriver.findElement(AppiumBy.xpath(medium_priority)).getText();
		if (mediumPriority.equals(actMediumPriority)) {
			Assert.assertEquals(actMediumPriority, mediumPriority, "Successfully validated Medium Priority Option!");
		} else {
			Assert.fail("Failed to validate Medium Priority Option");
		}

		// Validate High Priority Text
		String actHighPriority = appdriver.findElement(AppiumBy.xpath(high_priority)).getText();
		if (highPriority.equals(actHighPriority)) {
			Assert.assertEquals(actHighPriority, highPriority, "Successfully validated High Priority Option!");
		} else {
			Assert.fail("Failed to validate High Priority Option");
		}

	}

	public void validateCommentBoxAndEnterComments() throws InterruptedException {
		// locators
		String mandatory_comments = locators.getProperty("required_comment_box_message");
		String add_Comments = locators.getProperty("add_mandatory_comments");
		// Expected Value
		String mandatoryComments = prop.getProperty("Comment_Box_Message");
		Thread.sleep(3000);

		String actMandatoryComments = appdriver.findElement(AppiumBy.xpath(mandatory_comments)).getText();
		if (mandatoryComments.equals(actMandatoryComments)) {
			Assert.assertEquals(actMandatoryComments, mandatoryComments,
					"Successfully validated Mandatory Commnets Text!");
			appdriver.findElement(AppiumBy.xpath(add_Comments)).isDisplayed();
			appdriver.findElement(AppiumBy.xpath(add_Comments)).sendKeys("Successfully added comments");
		} else {
			Assert.fail("Failed to validate Mandatory Comments Text");
		}

	}

	public void addAttachments() throws InterruptedException {
		Thread.sleep(3000);
		String add_photo = locators.getProperty("add_photo");
		String capture_photo = locators.getProperty("capture_photo");
		String use_photo = locators.getProperty("use_photo");
		String pdf_attachment = locators.getProperty("add_pdf_attachment");
		String select_pdf_attachment = locators.getProperty("select_pdf_attachmnet");

		// add document attachment
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(pdf_attachment)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(pdf_attachment)).click();
			Thread.sleep(3000);
			appdriver.findElement(AppiumBy.xpath(select_pdf_attachment)).click();
		} else {
			Assert.fail("Failed to add document attachment");
		}
		// add photo attachment
		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
		if (appdriver.findElement(AppiumBy.xpath(add_photo)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(add_photo)).click();
			Thread.sleep(3000);
			appdriver.findElement(AppiumBy.xpath(capture_photo)).click();
			Thread.sleep(5000);
			appdriver.findElement(AppiumBy.xpath(use_photo)).click();
		} else {
			Assert.fail("Failed capture photo");
		}
		// // Scroll up the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward();"));
	}

	public void tabOnNextButton() {
		String next_button = locators.getProperty("next_button");
		if (appdriver.findElement(AppiumBy.xpath(next_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(next_button)).click();
		} else {
			Assert.fail("Failed to click on Next button");
		}
	}

	public void tabOnReviewButton() {
		String review_button = locators.getProperty("review_button");
		if (appdriver.findElement(AppiumBy.xpath(review_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(review_button)).click();
		} else {
			Assert.fail("Failed to click on Review button");
		}
	}

	public void validateReviewReport(String scheduleType, String attachmentType) throws InterruptedException {
		// locators
		String green_flags_count = locators.getProperty("review_dailytype_green_flags_count");
		String yellow_flags_count = locators.getProperty("review_dailytype_yellow_flags_count");
		String red_flags_count = locators.getProperty("review_dailytype_red_flags_count");
		String total_questions = locators.getProperty("review_dailytype_total_questions");
		String answered_questions = locators.getProperty("review_dailytype_answered");
		String skipped_questions = locators.getProperty("review_dailytype_skipped");
		String total_attachments = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "total_attachments");
		String photo_attachments = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "photo_attachments");
		String video_attachmnets = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "video_attachments");
		String document_attachmnets = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "document_attachments");
		String take_selfie = locators.getProperty("review_take_selfie");
		String capture_selfie = locators.getProperty("review_capture_button");
		String use_photo = locators.getProperty("review_use_photo");
		String tap_to_sign = locators.getProperty("review_tap_to_sign");
		String enter_user_name = locators.getProperty("enter_username");
		String enter_user_role = locators.getProperty("enter_user_role");
		String save_button = locators.getProperty("tap_on_save");
		// Expected values
		String expGreenFlagCount = prop.getProperty(scheduleType + "_" + "Green_Flags_Count");
		String expYellowFlagCount = prop.getProperty(scheduleType + "_" + "Yellow_Flag_Counts");
		String expRedFlagCount = prop.getProperty(scheduleType + "_" + "Red_Flag_Counts");
		String expTotalQuestions = prop.getProperty(scheduleType + "_" + "Total_Questions");
		String expAnsweredQuestions = prop.getProperty(scheduleType + "_" + "Answered_Questions");
		String expSkippedQuestions = prop.getProperty(scheduleType + "_" + "Skipped_Questions");
		String expTotalAttachments = prop.getProperty(scheduleType + "_" + attachmentType + "_" + "Total_Attachmnets");
		String expPhotoAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Photo_Attachmnets_Counts");
		String expVideoAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Video_Attachments_Counts");
		String expDocumentAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Document_Attachmnets_Counts");
		Thread.sleep(4000);

		// validate green flags count
		String actGreenFlagCount = appdriver.findElement(AppiumBy.xpath(green_flags_count)).getText();
		if (expGreenFlagCount.contains(actGreenFlagCount)) {
			Assert.assertEquals(actGreenFlagCount, expGreenFlagCount, "Successfully validated green flags count!");
		} else {
			Assert.fail("Failed to validate green flags count");
		}

		// validate yellow flags count
		String actYellowFlagCount = appdriver.findElement(AppiumBy.xpath(yellow_flags_count)).getText();
		if (expYellowFlagCount.contains(actYellowFlagCount)) {
			Assert.assertEquals(actYellowFlagCount, expYellowFlagCount, "Successfully validated yellow flags count!");
		} else {
			Assert.fail("Failed to validate yellow flags count");
		}

		// validate red flags count
		String actRedFlagCount = appdriver.findElement(AppiumBy.xpath(red_flags_count)).getText();
		if (expRedFlagCount.contains(actRedFlagCount)) {
			Assert.assertEquals(actRedFlagCount, expRedFlagCount, "Successfully validated red flags count!");
		} else {
			Assert.fail("Failed to validate red flags count");
		}

		// validate total questions
		String actTotalQuestionsCount = appdriver.findElement(AppiumBy.xpath(total_questions)).getText();
		if (expTotalQuestions.contains(actTotalQuestionsCount)) {
			Assert.assertEquals(actTotalQuestionsCount, expTotalQuestions,
					"Successfully validated total questions count!");
		} else {
			Assert.fail("Failed to validate total flags count");
		}

		// validate total answered questions
		String actAnsweredQuestions = appdriver.findElement(AppiumBy.xpath(answered_questions)).getText();
		if (expAnsweredQuestions.contains(actAnsweredQuestions)) {
			Assert.assertEquals(actAnsweredQuestions, expAnsweredQuestions,
					"Successfully validated answered questions!");
		} else {
			Assert.fail("Failed to validate answered questions");
		}

		// validate total skipped questions
		String actSkippedQuestions = appdriver.findElement(AppiumBy.xpath(skipped_questions)).getText();
		if (expSkippedQuestions.contains(actSkippedQuestions)) {
			Assert.assertEquals(actSkippedQuestions, expSkippedQuestions, "Successfully validated skipped questions!");
		} else {
			Assert.fail("Failed to validate skipped questions");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// validate total attachments count
		String actTotalAttachments = appdriver.findElement(AppiumBy.xpath(total_attachments)).getText();
		if (expTotalAttachments.contains(actTotalAttachments)) {
			Assert.assertEquals(actTotalAttachments, expTotalAttachments,
					"Successfully validated total attachments count!");
		} else {
			Assert.fail("Failed to validate total attachments count");
		}

		// validate photo attachments count
		String actPhotoAttachmnets = appdriver.findElement(AppiumBy.xpath(photo_attachments)).getText();
		if (expPhotoAttachments.contains(actPhotoAttachmnets)) {
			Assert.assertEquals(actPhotoAttachmnets, expPhotoAttachments,
					"Successfully validated photo attachments count!");
		} else {
			Assert.fail("Failed to validate photo attachments count");
		}

		// validate video attachments count
		String actVideoAttachmnets = appdriver.findElement(AppiumBy.xpath(video_attachmnets)).getText();
		if (expVideoAttachments.contains(actVideoAttachmnets)) {
			Assert.assertEquals(actVideoAttachmnets, expVideoAttachments,
					"Successfully validated video attachments count!");
		} else {
			Assert.fail("Failed to validate video attachments count");
		}

		// validate document attachments count
		String actDocumentAttachmnets = appdriver.findElement(AppiumBy.xpath(document_attachmnets)).getText();
		if (expDocumentAttachments.contains(actDocumentAttachmnets)) {
			Assert.assertEquals(actDocumentAttachmnets, expDocumentAttachments,
					"Successfully validated documnet attachments count!");
		} else {
			Assert.fail("Failed to validate document attachments count");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// take a selfie
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(take_selfie)).click();
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(capture_selfie)).click();
		Thread.sleep(5000);
		appdriver.findElement(AppiumBy.xpath(use_photo)).click();

		// add signature
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(tap_to_sign)).click();
		Thread.sleep(2000);
		digitalSignature();
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(enter_user_name)).sendKeys("Test User");
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(enter_user_role)).sendKeys("Test Role");
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(save_button)).click();

	}

	public void digitalSignature() {
		// Locate the signature canvas element
		String digital_sign = locators.getProperty("add_digital_sign");
		WebElement signatureCanvas = appdriver.findElement(AppiumBy.xpath(digital_sign));

		// Get the location and size of the signature canvas
		int startX = signatureCanvas.getLocation().getX();
		int startY = signatureCanvas.getLocation().getY();
		int endX = startX + signatureCanvas.getSize().getWidth();
		int endY = startY + signatureCanvas.getSize().getHeight();

		// Create a pointer input for the pen (or finger) action
		PointerInput pen = new PointerInput(PointerInput.Kind.PEN, "pen");

		// Create a sequence of actions
		Sequence signatureSequence = new Sequence(pen, 0)
				.addAction(pen
						.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX + 100, startY + 100))
				.addAction(pen.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 90,
						startY + 50))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 90,
						startY + 10))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 130,
						startY + 50))
				.addAction(pen.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		appdriver.perform(Arrays.asList(signatureSequence));

	}

	public void submitReport() throws InterruptedException {
		// locators
		String submit_report = locators.getProperty("submit_report");
		String submit_report_confirmation = locators.getProperty("submit_report_confirmation");

		// submit the report in online mode
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(submit_report)).click();
		Thread.sleep(2000);
		appdriver.findElement(AppiumBy.xpath(submit_report_confirmation)).click();
	}

	public void reportSubmittedPage(String scheduleType) throws InterruptedException {
		// locators
		String report_submitted_text = locators.getProperty("report_submitted_text");
		String report_submitted_page_site_name = locators.getProperty("report_submitted_page_site_name");
		String report_submitted_red_flag_count = locators.getProperty("report_submitted_page_red_flags_count");
		String report_submitted_yellow_flag_count = locators.getProperty("report_submitted_page_yellow_flags_count");
		String report_submitted_green_flags_count = locators.getProperty("report_submitted_page_green_flags_count");
		String report_submitted_got_it = locators.getProperty("report_submitted_page_got_it_text");
		String close_nps_popup = locators.getProperty("close_nps_pop_up");
		// expected values
		String expReportSubmittedText = prop.getProperty("Report_Submitted");
		String expSiteName = prop.getProperty(scheduleType + "_" + "Site_Name");
		String expRedFlagsCount = "0" + prop.getProperty(scheduleType + "_" + "Red_Flag_Counts");
		String expYellowFalgsCount = "0" + prop.getProperty(scheduleType + "_" + "Yellow_Flag_Counts");
		String expGreenFlagsCount = "0" + prop.getProperty(scheduleType + "_" + "Green_Flags_Count");
		Thread.sleep(80000);

		// validate report submitted text
		String actReportSubmittedText = appdriver.findElement(AppiumBy.xpath(report_submitted_text)).getText();
		if (actReportSubmittedText.contains(expReportSubmittedText)) {
			Assert.assertEquals(actReportSubmittedText, expReportSubmittedText,
					"Successfully validated report submitted text!");
		} else {
			Assert.fail("Failed to validate report submitted text");
		}

		// validated site name
		String actSiteName = appdriver.findElement(AppiumBy.xpath(report_submitted_page_site_name)).getText();
		if (actSiteName.contains(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Successfully validated site name");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate red flags count
		String actRedFlagsCount = appdriver.findElement(AppiumBy.xpath(report_submitted_red_flag_count)).getText();
		if (actRedFlagsCount.contains(expRedFlagsCount)) {
			Assert.assertEquals(actRedFlagsCount, expRedFlagsCount, "Successfully validated red flags count");
		} else {
			Assert.fail("Failed to validate red flags count");
		}

		// validate yellow flags count
		String actYellowFalgsCount = appdriver.findElement(AppiumBy.xpath(report_submitted_yellow_flag_count))
				.getText();
		if (actYellowFalgsCount.contains(expYellowFalgsCount)) {
			Assert.assertEquals(actYellowFalgsCount, expYellowFalgsCount, "Successfully validated yellow flags count");
		} else {
			Assert.fail("Failed to validate yellow flags count");
		}

		// validate green flags count
		String actGreenFlagsCount = appdriver.findElement(AppiumBy.xpath(report_submitted_green_flags_count)).getText();
		if (actGreenFlagsCount.contains(expGreenFlagsCount)) {
			Assert.assertEquals(actGreenFlagsCount, expGreenFlagsCount, "Successfully validated green flags count");
		} else {
			Assert.fail("Failed to validate green flags count");
		}

		// click on got it button
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(report_submitted_got_it)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(report_submitted_got_it)).click();
			Thread.sleep(3000);
		} else {
			Assert.fail("Failed to click on got it button");
		}

		// close nps pop up
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(close_nps_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(close_nps_popup)).click();
			Thread.sleep(3000);
		} else {
			Assert.fail("Failed to close nps pop up");
		}
	}

	public void switchToOfflineMode() throws InterruptedException {
		// locators
		String settings_page = locators.getProperty("tap_settings");
		String enable_offline_mode = locators.getProperty("turn_on_offline_mode");
		String home_page = locators.getProperty("tap_home");

		// tap on settings page
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(settings_page)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(settings_page)).click();
		} else {
			Assert.fail("Failed to click on settings page");
		}

		// turn on offline mode
		Thread.sleep(5000);
		if (appdriver.findElement(AppiumBy.xpath(enable_offline_mode)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(enable_offline_mode)).click();
		} else {
			Assert.fail("Failed to turn on offline mode page");
		}

		// tap on home page
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(home_page)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(home_page)).click();
		} else {
			Assert.fail("Failed to click on home page");
		}

	}

	public void checkoutReport() throws InterruptedException {
		// locators
		String checkout_button = locators.getProperty("checkout_button");
		String report_saved = locators.getProperty("report_saved");
		String return_to_home = locators.getProperty("return_to_home");

		// Expected values
		String expReportSaved = "Report Successfully Saved";

		// click on checkout button
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(checkout_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(checkout_button)).click();
		} else {
			Assert.fail("Failed to click on checkout button");
		}

		// validate report saved pop up
		Thread.sleep(2000);
		String actReportSaved = appdriver.findElement(AppiumBy.xpath(report_saved)).getText();
		if (expReportSaved.contains(actReportSaved)) {
			Assert.assertEquals(actReportSaved, expReportSaved, "Successfully validated report saved popup!");
		} else {
			Assert.fail("Failed to validate report saved popup");
		}

		// tap on return to home
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(return_to_home)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(return_to_home)).click();
		} else {
			Assert.fail("Failed to click on checkout button");
		}

	}

	public void validateOfflineScheduleStatus() throws InterruptedException {
		// locators
		String schedule_status = locators.getProperty("offline_schedule_status");

		// expected value
		String expOfflineSchduleStatus = prop.getProperty("Offline_Schedule_Status");
		Thread.sleep(3000);

		String actOfflineSchduleStatus = appdriver.findElement(AppiumBy.xpath(schedule_status)).getText();
		if (expOfflineSchduleStatus.contains(actOfflineSchduleStatus)) {
			Assert.assertEquals(actOfflineSchduleStatus, expOfflineSchduleStatus,
					"Successfully validated offline schedule status!");
		} else {
			Assert.fail("Failed to validate offline schedule");
		}
	}

	public void switchToOnlineMode() throws InterruptedException {
		// locators
		String settings_page = locators.getProperty("tap_settings");
		String disable_offline_mode = locators.getProperty("turn_off_offline_mode");
		String home_page = locators.getProperty("tap_home");

		// tap on settings page
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(settings_page)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(settings_page)).click();
		} else {
			Assert.fail("Failed to click on settings page");
		}

		// turn on offline mode
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(disable_offline_mode)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(disable_offline_mode)).click();
		} else {
			Assert.fail("Failed to turn on offline mode page");
		}

		// tap on home page
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(home_page)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(home_page)).click();
		} else {
			Assert.fail("Failed to click on home page");
		}

	}

	public void validateModifyAndSyncWithServer() throws InterruptedException {
		// locators
		String schedule_type = locators.getProperty("Offline_schedule_type");
		String modify_button = locators.getProperty("modify_button");
		String sync_with_server = locators.getProperty("sync_with_server");

		// expected values
		String expModifyButton = "Modify";
		String expSyncWithServer = "Sync with Server";

		// tap on schedule card
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tap on schedule card");
		}

		// validate modify button
		Thread.sleep(2000);
		String actModifyButton = appdriver.findElement(AppiumBy.xpath(modify_button)).getText();
		if (expModifyButton.contains(actModifyButton)) {
			Assert.assertEquals(actModifyButton, expModifyButton,
					"Successfully validated Modify button on checkin pop up!");
		} else {
			Assert.fail("Failed to validate Modify button on checkin pop up");
		}

		// validate sync with server button
		String actSyncWithServer = appdriver.findElement(AppiumBy.xpath(sync_with_server)).getText();
		if (expSyncWithServer.contains(actSyncWithServer)) {
			Assert.assertEquals(actSyncWithServer, expSyncWithServer,
					"Successfully validated Sync with Server on checkin pop up!");
			appdriver.findElement(AppiumBy.xpath(sync_with_server)).click();
			Thread.sleep(5000);
		} else {
			Assert.fail("Failed to validate Sync with Server on checkin pop up");
		}

	}

	public void validateReviewReportPageAfterSwitchingToOnlineMode(String scheduleType, String attachmentType)
			throws InterruptedException {
		// locators
		String green_flags_count = locators.getProperty("review_dailytype_green_flags_count");
		String yellow_flags_count = locators.getProperty("review_dailytype_yellow_flags_count");
		String red_flags_count = locators.getProperty("review_dailytype_red_flags_count");
		String total_questions = locators.getProperty("review_dailytype_total_questions");
		String answered_questions = locators.getProperty("review_dailytype_answered");
		String skipped_questions = locators.getProperty("review_dailytype_skipped");
		String total_attachments = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "total_attachments");
		String photo_attachments = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "photo_attachments");
		String video_attachmnets = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "video_attachments");
		String document_attachmnets = locators
				.getProperty(scheduleType + "_" + attachmentType + "_" + "document_attachments");
		// Expected values
		String expGreenFlagCount = prop.getProperty(scheduleType + "_" + "Green_Flags_Count");
		String expYellowFlagCount = prop.getProperty(scheduleType + "_" + "Yellow_Flag_Counts");
		String expRedFlagCount = prop.getProperty(scheduleType + "_" + "Red_Flag_Counts");
		String expTotalQuestions = prop.getProperty(scheduleType + "_" + "Total_Questions");
		String expAnsweredQuestions = prop.getProperty(scheduleType + "_" + "Answered_Questions");
		String expSkippedQuestions = prop.getProperty(scheduleType + "_" + "Skipped_Questions");
		String expTotalAttachments = prop.getProperty(scheduleType + "_" + attachmentType + "_" + "Total_Attachmnets");
		String expPhotoAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Photo_Attachmnets_Counts");
		String expVideoAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Video_Attachments_Counts");
		String expDocumentAttachments = prop
				.getProperty(scheduleType + "_" + attachmentType + "_" + "Document_Attachmnets_Counts");
		Thread.sleep(4000);

		// validate green flags count
		String actGreenFlagCount = appdriver.findElement(AppiumBy.xpath(green_flags_count)).getText();
		if (expGreenFlagCount.contains(actGreenFlagCount)) {
			Assert.assertEquals(actGreenFlagCount, expGreenFlagCount, "Successfully validated green flags count!");
		} else {
			Assert.fail("Failed to validate green flags count");
		}

		// validate yellow flags count
		String actYellowFlagCount = appdriver.findElement(AppiumBy.xpath(yellow_flags_count)).getText();
		if (expYellowFlagCount.contains(actYellowFlagCount)) {
			Assert.assertEquals(actYellowFlagCount, expYellowFlagCount, "Successfully validated yellow flags count!");
		} else {
			Assert.fail("Failed to validate yellow flags count");
		}

		// validate red flags count
		String actRedFlagCount = appdriver.findElement(AppiumBy.xpath(red_flags_count)).getText();
		if (expRedFlagCount.contains(actRedFlagCount)) {
			Assert.assertEquals(actRedFlagCount, expRedFlagCount, "Successfully validated red flags count!");
		} else {
			Assert.fail("Failed to validate red flags count");
		}

		// validate total questions
		String actTotalQuestionsCount = appdriver.findElement(AppiumBy.xpath(total_questions)).getText();
		if (expTotalQuestions.contains(actTotalQuestionsCount)) {
			Assert.assertEquals(actTotalQuestionsCount, expTotalQuestions,
					"Successfully validated total questions count!");
		} else {
			Assert.fail("Failed to validate total flags count");
		}

		// validate total answered questions
		String actAnsweredQuestions = appdriver.findElement(AppiumBy.xpath(answered_questions)).getText();
		if (expAnsweredQuestions.contains(actAnsweredQuestions)) {
			Assert.assertEquals(actAnsweredQuestions, expAnsweredQuestions,
					"Successfully validated answered questions!");
		} else {
			Assert.fail("Failed to validate answered questions");
		}

		// validate total skipped questions
		String actSkippedQuestions = appdriver.findElement(AppiumBy.xpath(skipped_questions)).getText();
		if (expSkippedQuestions.contains(actSkippedQuestions)) {
			Assert.assertEquals(actSkippedQuestions, expSkippedQuestions, "Successfully validated skipped questions!");
		} else {
			Assert.fail("Failed to validate skipped questions");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// validate total attachments count
		String actTotalAttachments = appdriver.findElement(AppiumBy.xpath(total_attachments)).getText();
		if (expTotalAttachments.contains(actTotalAttachments)) {
			Assert.assertEquals(actTotalAttachments, expTotalAttachments,
					"Successfully validated total attachments count!");
		} else {
			Assert.fail("Failed to validate total attachments count");
		}

		// validate photo attachments count
		String actPhotoAttachmnets = appdriver.findElement(AppiumBy.xpath(photo_attachments)).getText();
		if (expPhotoAttachments.contains(actPhotoAttachmnets)) {
			Assert.assertEquals(actPhotoAttachmnets, expPhotoAttachments,
					"Successfully validated photo attachments count!");
		} else {
			Assert.fail("Failed to validate photo attachments count");
		}

		// validate video attachments count
		String actVideoAttachmnets = appdriver.findElement(AppiumBy.xpath(video_attachmnets)).getText();
		if (expVideoAttachments.contains(actVideoAttachmnets)) {
			Assert.assertEquals(actVideoAttachmnets, expVideoAttachments,
					"Successfully validated video attachments count!");
		} else {
			Assert.fail("Failed to validate video attachments count");
		}

		// validate document attachments count
		String actDocumentAttachmnets = appdriver.findElement(AppiumBy.xpath(document_attachmnets)).getText();
		if (expDocumentAttachments.contains(actDocumentAttachmnets)) {
			Assert.assertEquals(actDocumentAttachmnets, expDocumentAttachments,
					"Successfully validated documnet attachments count!");
		} else {
			Assert.fail("Failed to validate document attachments count");
		}

	}

	public void syncDataWithServer() throws InterruptedException {
		// locators
		String sync_with_server = locators.getProperty("sync_with_server");
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(sync_with_server)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(sync_with_server)).click();
			Thread.sleep(30000);
		} else {
			Assert.fail("failed to sync data with server");
		}
	}

	public void saveAsDraft() throws InterruptedException {
		// locators
		String tap_on_schedule_card = locators.getProperty("Daily_schedule_type");
		String checkin_button = locators.getProperty("checkin_button");
		String tap_on_add_photo = locators.getProperty("add_photo");
		String tap_gallery_from_popup = locators.getProperty("tab_gallery");
		String select_photo = locators.getProperty("select_photo_from_gallery");
		String tap_add_vidoe = locators.getProperty("add_video");
		String start_recording = locators.getProperty("start_video_recording");
		String use_video = locators.getProperty("use_video");
		String add_document = locators.getProperty("add_document");
		String select_pdf = locators.getProperty("select_pdf_attachmnet");
		String select_answer = locators.getProperty("yes_and_no_question_answer");
		String select_green_and_yellow = locators.getProperty("green_yellow_red_answer");
		String tap_save_button = locators.getProperty("tap_save_button");
		String saved_button = locators.getProperty("saved_button");

		// Expected value
		String expSavedButton = prop.getProperty("Saved_Button");

		// tab on schedule card
		Thread.sleep(2000);
		if (appdriver.findElement(By.xpath(tap_on_schedule_card)).isDisplayed()) {
			appdriver.findElement(By.xpath(tap_on_schedule_card)).click();
		} else {
			Assert.fail("Failed to tab schedule card");
		}

		// tab on checkin button
		Thread.sleep(2000);
		if (appdriver.findElement(By.xpath(checkin_button)).isDisplayed()) {
			appdriver.findElement(By.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to tab checkin button");
		}

		// select yes and no question answer
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(select_answer)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_answer)).click();
		} else {
			Assert.fail("Failed to clcik on Yes and No Question");
		}

		// upload photo from gallery
		if (appdriver.findElement(AppiumBy.xpath(tap_on_add_photo)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(tap_on_add_photo)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(tap_gallery_from_popup)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_photo)).click();
		} else {
			Assert.fail("Failed to upload photo from gallery");
		}

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// record a video
		if (appdriver.findElement(AppiumBy.xpath(tap_add_vidoe)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_add_vidoe)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(start_recording)).click();
			Thread.sleep(6000);
			appdriver.findElement(AppiumBy.xpath(start_recording)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(use_video)).click();
		} else {
			Assert.fail("Failed to record video");
		}

		// upload a document
		if (appdriver.findElement(AppiumBy.xpath(add_document)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(add_document)).click();
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_pdf)).click();
		} else {
			Assert.fail("Failed to upload document");
		}

		// tap on next button
		tabOnNextButton();

		// add attachmnets
		addAttachments();

		// Select green, yellow & red question answer
		if (appdriver.findElement(AppiumBy.xpath(select_green_and_yellow)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(select_green_and_yellow)).click();
		} else {
			Assert.fail("Failed to answer green, yellow & red question");
		}

		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

		// validate sync successful
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(tap_save_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(tap_save_button)).click();
			Thread.sleep(25000);
		} else {
			Assert.fail("Failed to tap on save button");
		}
		String actSavedButton = appdriver.findElement(AppiumBy.xpath(saved_button)).getText();
		if (expSavedButton.equals(actSavedButton)) {
			Assert.assertEquals(expSavedButton, actSavedButton, "Successfully validated save button");
		} else {
			Assert.fail("Failed to validate Saved button");
		}
	}

	public void validateAttachments() throws InterruptedException {
		// locators
		String back_button = locators.getProperty("tap_back_button");
		String continue_button = locators.getProperty("tap_continue_button");
		String validate_image = locators.getProperty("validate_image");
		String validate_video = locators.getProperty("validate_video");
		String validate_document = locators.getProperty("validate_document");
		String validate_image2 = locators.getProperty("validate_image2");
		String validate_document2 = locators.getProperty("validate_document2");
		String reset_button = locators.getProperty("tap_reset_button");
		String delete_progress = locators.getProperty("delete_progress");
		String schedule_type = locators.getProperty("Daily_schedule_type");

		// tap on back button

		if (appdriver.findElement(AppiumBy.xpath(back_button)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(back_button)).click();
		} else {
			Assert.fail("Failed to tab on back button");
		}

		// tab on schedule card
		Thread.sleep(5000);
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tab on schedule card");
		}

		// tap on continue button
		if (appdriver.findElement(AppiumBy.xpath(continue_button)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(continue_button)).click();
		} else {
			Assert.fail("Failed to tab on continue button");
		}

		// validate photo attachment for first question
		Thread.sleep(8000);
		if (appdriver.findElement(AppiumBy.xpath(validate_image)).isDisplayed()) {
			Assert.assertTrue(true, "Successfully validated image attachmnet");

		} else {
			Assert.fail("Failed to validate image attachmnet");
		}

		// validate video attachment for first question
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(validate_video)).isDisplayed()) {
			Assert.assertTrue(true, "Successfully validated video attachmnet");

		} else {
			Assert.fail("Failed to validate video attachmnet");
		}

		// validate document attachment for first question
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(validate_document)).isDisplayed()) {
			Assert.assertTrue(true, "Successfully validated document attachmnet");

		} else {
			Assert.fail("Failed to validate document attachmnet");
		}

		// tap on next button
		tabOnNextButton();

		// validate photo attachment for second question
		Thread.sleep(7000);
		if (appdriver.findElement(AppiumBy.xpath(validate_image2)).isDisplayed()) {
			Assert.assertTrue(true, "Successfully validated image attachmnet");

		} else {
			Assert.fail("Failed to validate image attachmnet");
		}

		// validate document attachment for second question
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(validate_document2)).isDisplayed()) {
			Assert.assertTrue(true, "Successfully validated document attachmnet");

		} else {
			Assert.fail("Failed to validate document attachmnet");
		}

		// tap on back button

		if (appdriver.findElement(AppiumBy.xpath(back_button)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(back_button)).click();
		} else {
			Assert.fail("Failed to tab on back button");
		}

		// tab on schedule card
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tab on schedule card");
		}

		// tab on reset button
		if (appdriver.findElement(AppiumBy.xpath(reset_button)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(reset_button)).click();
		} else {
			Assert.fail("Failed to tab on reset button");
		}

		// tap on delete progress yes
		if (appdriver.findElement(AppiumBy.xpath(delete_progress)).isDisplayed()) {
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(delete_progress)).click();
			Thread.sleep(6000);
		} else {
			Assert.fail("Failed to delete schedule progress");
		}
	}

	public void startAuditAndSubmitReport() throws InterruptedException {
		// Locators
		String schedule_type = locators.getProperty("Daily_schedule_type");
		String checkin_button = locators.getProperty("checkin_button");
		String answer_as_no = locators.getProperty("answer_red_flag");
		String review_button = locators.getProperty("review_button");
		String got_it = locators.getProperty("report_submitted_page_got_it_text");
		String close_nps_popup = locators.getProperty("close_nps_pop_up");

		// tap on schedule card
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tap on schedule card");
		}

		// tap on check-in button
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(checkin_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to tap on checkin button");
		}

		// answer first question
		Thread.sleep(6000);
		if (appdriver.findElement(AppiumBy.xpath(answer_as_no)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(answer_as_no)).click();
		} else {
			Assert.fail("Failed to answer first question");
		}

		// add comments
		validateCommentBoxAndEnterComments();

		// tap on review button
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(review_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(review_button)).click();
		} else {
			Assert.fail("Failed to tap on review button");
		}

		// tap on submit button
		submitReport();

		// tap on got it button
		Thread.sleep(7000);
		if (appdriver.findElement(AppiumBy.xpath(got_it)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(got_it)).click();
		} else {
			Assert.fail("Failed to tap on got it button");
		}

		// close nps popup
		Thread.sleep(10000);
		if (appdriver.findElement(AppiumBy.xpath(close_nps_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(close_nps_popup)).click();
		} else {
			Assert.fail("Failed to tap on close nps popup button");
		}
	}

	public void validateCheckinRadius() throws InterruptedException {
		// Locators
		String schedule_type = locators.getProperty("Daily_schedule_type");
		String checkin_button = locators.getProperty("checkin_button");
		String checkin_radius = locators.getProperty("checkin_radius");

		// Expected Value
		String expCheckinRadiusMessage = prop.getProperty("Checkin_Radius_Message");

		// tap on schedule card
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tap on schedule card");
		}

		// tap on check-in button
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(checkin_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to tap on checkin button");
		}

		// verify check-in pop-up
		Thread.sleep(3000);
		String actCheckinRadiusMessage = appdriver.findElement(AppiumBy.xpath(checkin_radius)).getText();
		if (actCheckinRadiusMessage.equals(actCheckinRadiusMessage)) {
			Assert.assertEquals(actCheckinRadiusMessage, expCheckinRadiusMessage,
					"Successfully validated unable to checkin toast message");
		} else {
			Assert.fail("Failed to validate checkin toast message");
		}
	}

	public void answerMandatoryQuestions() throws InterruptedException {
		// Locators
		String schedule_type = locators.getProperty("Daily_schedule_type");
		String checkin_button = locators.getProperty("checkin_button");
		String preview_button = locators.getProperty("preview_button");
		String review_button = locators.getProperty("review_report");
		String yes_no_question = locators.getProperty("yes_and_no_question_answer");
		String green_yellow_red_question = locators.getProperty("green_yellow_red_answer");

		// tap on schedule card
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(schedule_type)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(schedule_type)).click();
		} else {
			Assert.fail("Failed to tap on schedule card");
		}

		// tap on check-in button
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(checkin_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to tap on checkin button");
		}

		// select yes and no question answer
		Thread.sleep(6000);
		if (appdriver.findElement(AppiumBy.xpath(yes_no_question)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(yes_no_question)).click();
		} else {
			Assert.fail("Failed to clcik on Yes and No Question");
		}

		tabOnNextButton();

		// Select green, yellow & red question answer
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(green_yellow_red_question)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(green_yellow_red_question)).click();
		} else {
			Assert.fail("Failed to answer green, yellow & red question");
		}

		// Validate Add comment box and enter comments
		validateCommentBoxAndEnterComments();

		// tap on preview button
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(preview_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(preview_button)).click();
		} else {
			Assert.fail("Failed to tap on preview icon");
		}

		// tap on review button from questionnaire preview
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(review_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(review_button)).click();
		} else {
			Assert.fail("Failed to tap on review report");
		}
	}

	public void validateReviewReportUseDeductions() throws InterruptedException {
		// Locators
		String total_questions = locators.getProperty("deduction_review_dailytype_total_questions");
		String answered_questions = locators.getProperty("deduction_review_dailytype_answered");
		String skipped_questions = locators.getProperty("deduction_review_dailytype_skipped");

		// Expected Values
		String expTotalQuestions = prop.getProperty("Deductions_Total_Questions");
		String expAnsweredQuestions = prop.getProperty("Deductions_Answered_Questions");
		String expSkippedQuestions = prop.getProperty("Deductions_Skipped_Questions");

		// validate total questions count
		Thread.sleep(3000);
		String actTotalQuestionsCount = appdriver.findElement(AppiumBy.xpath(total_questions)).getText();
		if (actTotalQuestionsCount.equals(expTotalQuestions)) {
			Assert.assertEquals(actTotalQuestionsCount, expTotalQuestions,
					"Successfully validated total questions count");
		} else {
			Assert.fail("Failed to validate total questions count");
		}

		// validate answered questions
		String actAnsweredQuestionsCount = appdriver.findElement(AppiumBy.xpath(answered_questions)).getText();
		if (actAnsweredQuestionsCount.equals(expAnsweredQuestions)) {
			Assert.assertEquals(actAnsweredQuestionsCount, expAnsweredQuestions,
					"Successfully validated answered questions count");
		} else {
			Assert.fail("Failed to validate answered questions count");
		}
		// validate skipped questions
		String actSkippedQuestionsCount = appdriver.findElement(AppiumBy.xpath(skipped_questions)).getText();
		if (actSkippedQuestionsCount.equals(expSkippedQuestions)) {
			Assert.assertEquals(actSkippedQuestionsCount, expSkippedQuestions,
					"Successfully validated total skipped count");
		} else {
			Assert.fail("Failed to validate skipped questions count");
		}
	}
	
	public void validateSiteAndQuestionnaireOnReviewPage() throws InterruptedException {
	    // Locators
		Map<String, String> locatorsMap = new LinkedHashMap<>();
		locatorsMap.put("siteName", locators.getProperty("review_page_site_name"));
		locatorsMap.put("questionnaireName", locators.getProperty("review_page_questionnaire_name"));
		locatorsMap.put("errorToastMessage", locators.getProperty("review_page_error_toast_message"));
		locatorsMap.put("signatureAndSelfieErrorMessage", locators.getProperty("review_page_signature_and_selfie_error_messages"));
		locatorsMap.put("takeSelfie", locators.getProperty("review_take_selfie"));
		locatorsMap.put("captureSelfie", locators.getProperty("review_capture_button"));
		locatorsMap.put("usePhoto", locators.getProperty("review_use_photo"));
		locatorsMap.put("tapToSign", locators.getProperty("review_tap_to_sign"));
		locatorsMap.put("userName", locators.getProperty("enter_username"));
		locatorsMap.put("userRole", locators.getProperty("enter_user_role"));
		locatorsMap.put("saveButton", locators.getProperty("tap_on_save"));

	    // Expected values
		Map<String, String> expectedValuesMap = new LinkedHashMap<>();
		expectedValuesMap.put("siteName", prop.getProperty("ReviewReport_Site_Name"));
		expectedValuesMap.put("questionnaireName", prop.getProperty("ReviewReport_Schedule_With_Attachments"));
		expectedValuesMap.put("errorToastMessage", prop.getProperty("ReviewReport_Toast_Message"));
		expectedValuesMap.put("signatureAndSelfieErrorMessage", prop.getProperty("ReviewReport_Error_Message"));

	    // Validate fields
	    validateElementText(locatorsMap.get("siteName"), expectedValuesMap.get("siteName"), "site name");
	    validateElementText(locatorsMap.get("questionnaireName"), expectedValuesMap.get("questionnaireName"), "questionnaire name");

	    // Submit report
	    submitReport();

	    // Validate error messages
	    validateElementText(locatorsMap.get("errorToastMessage"), expectedValuesMap.get("errorToastMessage"), "error toast pop-up");
	    validateElementText(locatorsMap.get("signatureAndSelfieErrorMessage"), expectedValuesMap.get("signatureAndSelfieErrorMessage"), "signature and selfie error message");

	    // Add selfie and signature
	    addSelfieAndSignature(locatorsMap);
	}

	private void validateElementText(String locator, String expectedValue, String elementName) {
	    String actualValue = appdriver.findElement(AppiumBy.xpath(locator)).getText();
	    Assert.assertEquals(actualValue, expectedValue, "Successfully validated " + elementName);
	}

	private void addSelfieAndSignature(Map<String, String> locatorsMap) throws InterruptedException {
	    // Scroll down
	    appdriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));

	    // Add selfie
	    takeSelfie(locatorsMap);

	    // Add signature
	    addSignature(locatorsMap);
	}

	private void takeSelfie(Map<String, String> locatorsMap) throws InterruptedException {
	    Thread.sleep(3000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("takeSelfie"))).click();
	    Thread.sleep(2000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("captureSelfie"))).click();
	    Thread.sleep(5000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("usePhoto"))).click();
	}

	private void addSignature(Map<String, String> locatorsMap) throws InterruptedException {
	    Thread.sleep(2000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("tapToSign"))).click();
	    Thread.sleep(2000);
	    digitalSignature();
	    Thread.sleep(2000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("userName"))).sendKeys("Test User");
	    Thread.sleep(2000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("userRole"))).sendKeys("Test Role");
	    Thread.sleep(2000);
	    appdriver.findElement(AppiumBy.xpath(locatorsMap.get("saveButton"))).click();
	}
	
	public void auditProcessForConditionalQuestions() throws InterruptedException {
		String tap_on_schedule_card = locators.getProperty("Daily_schedule_type");
		String checkin_button = locators.getProperty("checkin_button");
		// Map to store parent and child question XPaths
		Map<String, String> parentChildQuestionMap = new LinkedHashMap<>();
		parentChildQuestionMap.put(locators.getProperty("answer_first_parent_question"),
				locators.getProperty("answer_first_child_question"));
		parentChildQuestionMap.put(locators.getProperty("answer_second_parent_question"),
				locators.getProperty("answer_second_child_question"));
		parentChildQuestionMap.put(locators.getProperty("answer_third_parent_question"),
				locators.getProperty("answer_third_child_question"));
		
		// tab on schedule card
		Thread.sleep(2000);
		if (appdriver.findElement(By.xpath(tap_on_schedule_card)).isDisplayed()) {
			appdriver.findElement(By.xpath(tap_on_schedule_card)).click();
		} else {
			Assert.fail("Failed to tab schedule card");
		}

		// tab on checkin button
		Thread.sleep(2000);
		if (appdriver.findElement(By.xpath(checkin_button)).isDisplayed()) {
			appdriver.findElement(By.xpath(checkin_button)).click();
		} else {
			Assert.fail("Failed to tab checkin button");
		}

		// Answer all parent and child questions using the map
		for (Map.Entry<String, String> entry : parentChildQuestionMap.entrySet()) {
			String parentQuestionXPath = entry.getKey();
			String childQuestionXPath = entry.getValue();

			answerParentAndChildQuestionWithAttachments(parentQuestionXPath, childQuestionXPath);
			tabOnNextButton();
		}

		// Handle the range with flag question
		addAttachments();
		handleRangeWithFlagQuestion(locators.getProperty("range_with_flag_error_message"),
				locators.getProperty("answer_range_with_flag_question"));
		tabOnReviewButton();
	}

	private void answerParentAndChildQuestionWithAttachments(String parentQuestionXPath, String childQuestionXPath)
			throws InterruptedException {
		String add_Comments = locators.getProperty("add_mandatory_comments");

		// Attach files to the parent question
		addAttachments();
		try {
			// Answer the parent question
			Thread.sleep(2000);
			WebElement parentQuestionElement = appdriver.findElement(AppiumBy.xpath(parentQuestionXPath));
			parentQuestionElement.click();

			appdriver.findElement(AppiumBy.xpath(add_Comments)).sendKeys("Successfully added comments");

			// Scroll and answer the child question if displayed
			scrollToNextElement();
			// Attach files to the child question
			addAttachmentsForChildQuestion();
			Thread.sleep(2000);
			WebElement childQuestionElement = appdriver.findElement(AppiumBy.xpath(childQuestionXPath));
			if (childQuestionElement.isDisplayed()) {
				childQuestionElement.click();
				appdriver.findElement(AppiumBy.xpath(add_Comments)).sendKeys("Successfully added comments");
			} else {
			}
		} catch (NoSuchElementException e) {
		}
	}

	private void handleRangeWithFlagQuestion(String errorMessageXPath, String rangeQuestionXPath)
			throws InterruptedException {
		String expErrorMessage = "Answer must be within the range";
		try {
			// Check for the error message
			if (appdriver.findElement(AppiumBy.xpath(rangeQuestionXPath)).isDisplayed()) {
				appdriver.findElement(AppiumBy.xpath(rangeQuestionXPath)).sendKeys("14");
			} else {
				Assert.fail("Failed to input score");
			}

			// validate error message
			WebElement errorMessage = appdriver.findElement(AppiumBy.xpath(errorMessageXPath));
			String actErrorMessage = errorMessage.getText();
			if (actErrorMessage.equals(expErrorMessage)) {
				Assert.assertEquals(actErrorMessage, expErrorMessage, "Successfully validated error message");
			} else {
				Assert.fail("Failed to validate error message");
			}

			// answer range with flag question
			appdriver.findElement(AppiumBy.xpath(rangeQuestionXPath)).sendKeys("2");

		} catch (NoSuchElementException e) {
		}
	}

	private void scrollToNextElement() {
		try {
			appdriver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
			System.out.println("Scrolled down the page.");
		} catch (Exception e) {
			System.out.println("Scroll operation failed: " + e.getMessage());
		}
	}
	
	public void validateReviewPageForConditionalQuestions() {
		// Locators for conditional-based questions on the review page
		Map<String, String> locatorsMap = new HashMap<>();
		locatorsMap.put("Green Flags", locators.getProperty("conditional_green_flags_count"));
		locatorsMap.put("Yellow Flags", locators.getProperty("conditional_yellow_flags_count"));
		locatorsMap.put("Red Flags", locators.getProperty("conditional_red_flags_count"));
		locatorsMap.put("Total Questions", locators.getProperty("conditional_total_questions"));
		locatorsMap.put("Answered Questions", locators.getProperty("conditional_answered"));
		locatorsMap.put("Skipped Questions", locators.getProperty("conditional_skipped"));
		locatorsMap.put("Total Attachments", locators.getProperty("conditional_total_attachments"));
		locatorsMap.put("Photo Attachments", locators.getProperty("conditional_photo_attachments"));
		locatorsMap.put("Video Attachments", locators.getProperty("conditional_video_attachments"));
		locatorsMap.put("Document Attachments", locators.getProperty("conditional_document_attachments"));

		// Expected values for validation
		Map<String, String> expectedValuesMap = new HashMap<>();
		expectedValuesMap.put("Green Flags", prop.getProperty("ConditionalQuestion_Green_Flags_Count"));
		expectedValuesMap.put("Yellow Flags", prop.getProperty("ConditionalQuestion_Yellow_Flag_Counts"));
		expectedValuesMap.put("Red Flags", prop.getProperty("ConditionalQuestion_Red_Flag_Counts"));
		expectedValuesMap.put("Total Questions", prop.getProperty("ConditionalQuestion_Total_Questions"));
		expectedValuesMap.put("Answered Questions", prop.getProperty("ConditionalQuestion_Answered_Questions"));
		expectedValuesMap.put("Skipped Questions", prop.getProperty("ConditionalQuestion_Skipped_Questions"));
		expectedValuesMap.put("Total Attachments", prop.getProperty("ConditionalQuestion_Total_Attachments"));
		expectedValuesMap.put("Photo Attachments", prop.getProperty("ConditionalQuestion_Photo_Attachments_Counts"));
		expectedValuesMap.put("Video Attachments", prop.getProperty("ConditionalQuestion_Video_Attachments_Counts"));
		expectedValuesMap.put("Document Attachments",
				prop.getProperty("ConditionalQuestion_Document_Attachments_Counts"));

		// Validate each element on the review page
		for (String key : locatorsMap.keySet()) {
			String locator = locatorsMap.get(key);
			String expectedValue = expectedValuesMap.get(key);
			validate(key, locator, expectedValue);
		}
	}

	// Helper method for validation
	private void validate(String name, String locator, String expectedValue) {
		String actualValue = appdriver.findElement(AppiumBy.xpath(locator)).getText();

		if (actualValue.equals(expectedValue)) {
			Assert.assertEquals(actualValue, expectedValue, "Successfully validated: " + name);
		} else {
			Assert.fail("Failed to validated: " + name);
		}
	}

	public void validateReportSubmittedPageForConditionalQuestions() throws InterruptedException {
		// Create Maps for locators and expected values
		Thread.sleep(60000);
		Map<String, String> locatorsMap = new HashMap<>();
		locatorsMap.put("greenFlag", locators.getProperty("conditional_report_submitted_page_green_flags_count"));
		locatorsMap.put("yellowFlag", locators.getProperty("conditional_report_submitted_page_yellow_flags_count"));
		locatorsMap.put("redFlag", locators.getProperty("conditional_report_submitted_page_red_flags_count"));

		Map<String, String> expectedValuesMap = new HashMap<>();
		expectedValuesMap.put("greenFlag", prop.getProperty("Conditional_Report_Submitted_Green_Flags_Count"));
		expectedValuesMap.put("yellowFlag", prop.getProperty("Conditional_Report_Submitted_Yellow_Flag_Counts"));
		expectedValuesMap.put("redFlag", prop.getProperty("Conditional_Report_Submitted_Red_Flag_Counts"));

		// Loop through the locatorsMap to validate the flag counts
		for (String key : locatorsMap.keySet()) {
			String locator = locatorsMap.get(key);
			String expectedValue = expectedValuesMap.get(key);
			validate(key, locator, expectedValue);
		}
	}
	
	public void addAttachmentsForChildQuestion() throws InterruptedException {
		Thread.sleep(3000);
		String add_photo = locators.getProperty("child_question_add_photo");
		String capture_photo = locators.getProperty("child_question_capture_photo");
		String use_photo = locators.getProperty("use_photo");
		String pdf_attachment = locators.getProperty("child_question_add_pdf_attachment");
		String select_pdf_attachment = locators.getProperty("select_pdf_attachmnet");

		// add document attachment
		Thread.sleep(3000);
		if (appdriver.findElement(AppiumBy.xpath(pdf_attachment)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(pdf_attachment)).click();
			Thread.sleep(3000);
			appdriver.findElement(AppiumBy.xpath(select_pdf_attachment)).click();
		} else {
			Assert.fail("Failed to add document attachment");
		}
		// add photo attachment
		// scroll down the page
		appdriver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
		if (appdriver.findElement(AppiumBy.xpath(add_photo)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(add_photo)).click();
			Thread.sleep(3000);
			appdriver.findElement(AppiumBy.xpath(capture_photo)).click();
			Thread.sleep(5000);
			appdriver.findElement(AppiumBy.xpath(use_photo)).click();
		} else {
			Assert.fail("Failed capture photo");
		}
	}
}