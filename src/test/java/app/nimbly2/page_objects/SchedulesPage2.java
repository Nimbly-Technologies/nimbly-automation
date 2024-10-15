package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
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
		Thread.sleep(2000);
		String home_schedules_tab = locators.getProperty("home_page_schedules_tab");
		String search_bar = locators.getProperty("search_for_schedule");
		String schedule_name = prop.getProperty(scheduleName);
		if (appdriver.findElement(AppiumBy.xpath(home_schedules_tab)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(home_schedules_tab)).click();
		} else {
			Assert.fail("Schedule tab is not displayed on home page");
		}
		if (appdriver.findElement(AppiumBy.xpath(search_bar)).isDisplayed()) {
			Thread.sleep(1000);
			appdriver.findElement(AppiumBy.xpath(search_bar)).sendKeys(schedule_name);
		} else {
			Assert.fail("Failed to serach schedule name");
		}

	}

	public void validateScheduleCardDetails(String scheduleType) throws InterruptedException {
		Thread.sleep(3000);
		// locators
		String schedule_name = locators.getProperty(scheduleType + "_" + "schedule_name");
		String site_name = locators.getProperty("site_name");
		String schedule_type = locators.getProperty(scheduleType + "_" + "schedule_type");
		String schedule_status = locators.getProperty("schedule_status");
		String completed_text = locators.getProperty("completed_text");
		// Expected values
		String expScheduleName = prop.getProperty(scheduleType + "_" + "Schedule");
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

	public void validateCheckinPopupDetails(String scheduleType) throws InterruptedException {
		Thread.sleep(3000);
		// locators
		String schedule_name = locators.getProperty(scheduleType + "_" + "checkin_popup_schedule_name");
		String schedule_type = locators.getProperty(scheduleType + "_" + "schedule_type");
		String site_name = locators.getProperty("checkin_popup_site_name");
		String completed_text = locators.getProperty("checkin_popup_completed_text");
		String checkin_button = locators.getProperty("checkin_button");
		// Expected values
		String expScheduleName = prop.getProperty(scheduleType + "_" + "Schedule");
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

	public void auditingProcess(String scheduleType) throws InterruptedException {
		answerYesAndNoQuestion(scheduleType);
		tabOnNextButton();
		answerGreenYellowRedQuestion(scheduleType);
		tabOnNextButton();
		answerScoreQuestion(scheduleType);
		tabOnNextButton();
		answerOpenEndedQuestion(scheduleType);
		tabOnNextButton();
		answerNumberQuestion(scheduleType);
		tabOnNextButton();
		answerMultipleChoiceQuestion(scheduleType);
		tabOnNextButton();
		answerMultipleChoiceWithScoreQuestion(scheduleType);
		tabOnNextButton();
		answerChecklistQuestion(scheduleType);
		tabOnNextButton();
		answerRangeWithFlagQuestion(scheduleType);
		tabOnNextButton();
		answerDateTimeQuestion(scheduleType);
		tabOnReviewButton();

	}

	public void answerYesAndNoQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("yes_no_question_text");
		String select_answer = locators.getProperty("yes_and_no_question_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expYesAndNoQuestion = prop.getProperty(scheduleType + "_" + "Question1");
		Thread.sleep(3000);

		// Validate Category Name
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
		addAttachments();

	}

	public void answerGreenYellowRedQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("green_yellow_red_text");
		String select_answer = locators.getProperty("green_yellow_red_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expGreenYellowRedQuestion = prop.getProperty(scheduleType + "_" + "Question2");
		Thread.sleep(3000);

		// Add photo & document attachments
		addAttachments();

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

	public void answerMultipleChoiceQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name2");
		String question_text = locators.getProperty("multiple_choice_question_text");
		String select_answer = locators.getProperty("multiple_choice_answer");
		// Expected Values
		String expCategoryName2 = prop.getProperty("Category_Name2");
		String expMultipleChoice = prop.getProperty(scheduleType + "_" + "Question6");
		Thread.sleep(3000);

		// Add photo & document attachments
		addAttachments();

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

	public void answerMultipleChoiceWithScoreQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("multiple_choice_score_question_text");
		String select_answer = locators.getProperty("multiple_choice_score_answer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expMultipleChoiceScore = prop.getProperty(scheduleType + "_" + "Question7");
		Thread.sleep(3000);

		// Add photo & document attachments
		addAttachments();

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

	public void answerOpenEndedQuestion(String scheduleType) throws InterruptedException {
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
		addAttachments();

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

	public void answerNumberQuestion(String scheduleType) throws InterruptedException {
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
		addAttachments();

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

	public void answerRangeWithFlagQuestion(String scheduleType) throws InterruptedException {
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
		addAttachments();

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

	public void answerScoreQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name1");
		String question_text = locators.getProperty("score_question_text");
		String select_answer = locators.getProperty("score_question_answer");
		// Expected Values
		String expCategoryName1 = prop.getProperty("Category_Name1");
		String expScoreQuestion = prop.getProperty(scheduleType + "_" + "Question3");
		Thread.sleep(3000);

		// Add photo & document attachments
		addAttachments();

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

	public void answerChecklistQuestion(String scheduleType) throws InterruptedException {
		// Locators
		String category_name = locators.getProperty("category_name3");
		String question_text = locators.getProperty("checklist_question_text");
		String select_answer = locators.getProperty("checklist_answer");
		// Expected Values
		String expCategoryName3 = prop.getProperty("Category_Name3");
		String expChecklistQuestion = prop.getProperty(scheduleType + "_" + "Question8");
		Thread.sleep(3000);

		// Add photo & document attachments
		addAttachments();

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

	public void answerDateTimeQuestion(String scheduleType) throws InterruptedException {
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
		addAttachments();

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

	public void validateReviewReport(String scheduleType) throws InterruptedException {
		// locators
		String green_flags_count = locators.getProperty("review_dailytype_green_flags_count");
		String yellow_flags_count = locators.getProperty("review_dailytype_yellow_flags_count");
		String red_flags_count = locators.getProperty("review_dailytype_red_flags_count");
		String total_questions = locators.getProperty("review_dailytype_total_questions");
		String answered_questions = locators.getProperty("review_dailytype_answered");
		String skipped_questions = locators.getProperty("review_dailytype_skipped");
		String total_attachments = locators.getProperty("review_dailytype_total_attachments");
		String photo_attachments = locators.getProperty("review_dailytype_photo_attachments");
		String video_attachmnets = locators.getProperty("review_dailytype_video_attachments");
		String document_attachmnets = locators.getProperty("review_dailytype_document_attachments");
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
		String expTotalAttachments = prop.getProperty(scheduleType + "_" + "Total_Attachmnets");
		String expPhotoAttachments = prop.getProperty(scheduleType + "_" + "Photo_Attachmnets_Counts");
		String expVideoAttachments = prop.getProperty(scheduleType + "_" + "Video_Attachments_Counts");
		String expDocumentAttachments = prop.getProperty(scheduleType + "_" + "Document_Attachmnets_Counts");
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
		// expected values
		String expReportSubmittedText = prop.getProperty("Report_Submitted");
		String expSiteName = prop.getProperty(scheduleType + "_" + "Site_Name");
		String expRedFlagsCount = "0" + prop.getProperty(scheduleType + "_" + "Red_Flag_Counts");
		String expYellowFalgsCount = "0" + prop.getProperty(scheduleType + "_" + "Yellow_Flag_Counts");
		String expGreenFlagsCount = "0" + prop.getProperty(scheduleType + "_" + "Green_Flags_Count");
		Thread.sleep(70000);

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
		String schedule_type = locators.getProperty("schedule_type");
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

	public void validateReviewReportPageAfterSwitchingToOnlineMode(String scheduleType) throws InterruptedException {
		// locators
		String green_flags_count = locators.getProperty("review_dailytype_green_flags_count");
		String yellow_flags_count = locators.getProperty("review_dailytype_yellow_flags_count");
		String red_flags_count = locators.getProperty("review_dailytype_red_flags_count");
		String total_questions = locators.getProperty("review_dailytype_total_questions");
		String answered_questions = locators.getProperty("review_dailytype_answered");
		String skipped_questions = locators.getProperty("review_dailytype_skipped");
		String total_attachments = locators.getProperty("review_dailytype_total_attachments");
		String photo_attachments = locators.getProperty("review_dailytype_photo_attachments");
		String video_attachmnets = locators.getProperty("review_dailytype_video_attachments");
		String document_attachmnets = locators.getProperty("review_dailytype_document_attachments");
		// Expected values
		String expGreenFlagCount = prop.getProperty(scheduleType + "_" + "Green_Flags_Count");
		String expYellowFlagCount = prop.getProperty(scheduleType + "_" + "Yellow_Flag_Counts");
		String expRedFlagCount = prop.getProperty(scheduleType + "_" + "Red_Flag_Counts");
		String expTotalQuestions = prop.getProperty(scheduleType + "_" + "Total_Questions");
		String expAnsweredQuestions = prop.getProperty(scheduleType + "_" + "Answered_Questions");
		String expSkippedQuestions = prop.getProperty(scheduleType + "_" + "Skipped_Questions");
		String expTotalAttachments = prop.getProperty(scheduleType + "_" + "Total_Attachmnets");
		String expPhotoAttachments = prop.getProperty(scheduleType + "_" + "Photo_Attachmnets_Counts");
		String expVideoAttachments = prop.getProperty(scheduleType + "_" + "Video_Attachments_Counts");
		String expDocumentAttachments = prop.getProperty(scheduleType + "_" + "Document_Attachmnets_Counts");
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
}
