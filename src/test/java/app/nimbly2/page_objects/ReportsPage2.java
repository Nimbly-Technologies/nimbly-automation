package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class ReportsPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public ReportsPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//ReportsPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//ReportsPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void verifySearchFunctionality() throws InterruptedException {
		String reports_tap = locators.getProperty("reports_button");
		String serach_questionnaire = locators.getProperty("serach_questionnaire");

		// Expected values
		String questionnaireName = prop.getProperty("Report_Schedule_Without_Attachments");

		// Tab on Reports tab
		Thread.sleep(10000);
		if (appdriver.findElement(AppiumBy.xpath(reports_tap)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(reports_tap)).click();
		} else {
			Assert.fail("Failed to tab on Reports tab");
		}

		// verify search functionality
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(serach_questionnaire)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(serach_questionnaire)).sendKeys(questionnaireName);
		} else {
			Assert.fail("Failed to serach Questionnaire Name");
		}
	}

	public void verifyReportCardDetails() throws InterruptedException {
		String questionnaire_name = locators.getProperty("checklist_name");
		String schedule_type = locators.getProperty("schedule_type");
		String site_name = locators.getProperty("site_name");
		String green_flag_count = locators.getProperty("green_flag_count");
		String yellow_flag_count = locators.getProperty("yellow_flag_count");
		String red_flag_count = locators.getProperty("red_flag_count");
		String report_submitted_date = locators.getProperty("report_submitted_date");

		// Expected values
		String expquestionnaireName = prop.getProperty("Report_Schedule_Without_Attachments");
		String expSiteName = prop.getProperty("Report_Site_Name");
		String expGreenFlagsCount = prop.getProperty("Report_Green_Flags_Count");
		String expYellowFalgsCount = prop.getProperty("Report_Yellow_Flag_Counts");
		String expRedFlagsCount = prop.getProperty("Report_Red_Flag_Counts");
		String expScheduleType = prop.getProperty("Report_Scheduled_Type");

		// validate questionnaire name
		Thread.sleep(4000);
		String actQuestionnaireName = appdriver.findElement(AppiumBy.xpath(questionnaire_name)).getText();
		if (actQuestionnaireName.equals(expquestionnaireName)) {
			Assert.assertEquals(actQuestionnaireName, expquestionnaireName,
					"Successfully validated questionnaire name");
		} else {
			Assert.fail("Failed to validate questionnaire name");
		}

		// validate site name
		Thread.sleep(2000);
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Successfully validated site name");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate schedule type
		Thread.sleep(2000);
		String actscheduleType = appdriver.findElement(AppiumBy.xpath(schedule_type)).getText();
		if (actscheduleType.equals(expScheduleType)) {
			Assert.assertEquals(actscheduleType, expScheduleType, "Successfully validated schedule type");
		} else {
			Assert.fail("Failed to validate schedule type name");
		}

		// validate green flag count
		Thread.sleep(2000);
		String actGreenFlagCount = appdriver.findElement(AppiumBy.xpath(green_flag_count)).getText();
		if (actGreenFlagCount.equals(expGreenFlagsCount)) {
			Assert.assertEquals(actGreenFlagCount, expGreenFlagsCount, "Successfully validated green flags count");
		} else {
			Assert.fail("Failed to validate green flags count");
		}

		// validate yellow flags count
		Thread.sleep(2000);
		String actYellowCount = appdriver.findElement(AppiumBy.xpath(yellow_flag_count)).getText();
		if (actYellowCount.equals(expYellowFalgsCount)) {
			Assert.assertEquals(actYellowCount, expYellowFalgsCount, "Successfully validated yellow flags count");
		} else {
			Assert.fail("Failed to validate yellow flags count");
		}

		// validate red flag count
		Thread.sleep(2000);
		String actRedFlagsCount = appdriver.findElement(AppiumBy.xpath(red_flag_count)).getText();
		if (actRedFlagsCount.equals(expRedFlagsCount)) {
			Assert.assertEquals(actRedFlagsCount, expRedFlagsCount, "Successfully validated red flags count");
		} else {
			Assert.fail("Failed to validate red flags count");
		}

	}

	public void verifyDownloadReportAndReportGenerationPopup() throws InterruptedException {
		validateDownloadReport();
	}

	public void validateFilterFunctionalityAndDownloadReportsFromThisWeek() throws InterruptedException {
		// Locators
		String filter = locators.getProperty("tap_filter");
		String tapSiteName = locators.getProperty("tap_filter_by_site");
		String searchSiteName = locators.getProperty("serach_stite_name");
		String checkbox = locators.getProperty("checkbox");
		String saveButton = locators.getProperty("tap_save_button");
		String applyButton = locators.getProperty("tap_apply_button");
		String resetButton = locators.getProperty("tap_reset_button");
		String date = locators.getProperty("tap_date");
		String selectThisWeek = locators.getProperty("select_this_week");

		// Expected values
		String expSiteName = prop.getProperty("Report_Site_Name");

		// Click on the filter and perform actions
		Thread.sleep(3000);
		performAction(filter, "Failed to tap on report filter");
		performAction(tapSiteName, "Failed to tap on site name");
		performAction(searchSiteName, "Failed to search site name", expSiteName);
		performAction(checkbox, "Failed to select site name");
		performAction(saveButton, "Failed to save filter");
		performAction(applyButton, "Failed to apply filter");

		// Verify the report card details after applying the filter
		verifyReportCardDetails();

		// Reset the filter
		performAction(filter, "Failed to tap on report filter");
		resetFilter(resetButton);

		// Set the date filter to 'This Week'
		performAction(filter, "Failed to tap on report filter");
		performAction(date, "Failed to tap on date field");
		performAction(selectThisWeek, "Failed to select this week");

		// Save and apply the filter again
		performAction(saveButton, "Failed to save filter");
		performAction(applyButton, "Failed to apply filter");

		// Verify the report card details again
		verifyReportCardDetails();

		// Final reset of the filter
		performAction(filter, "Failed to tap on report filter");
		resetFilter(resetButton);
	}

	private void performAction(String locator, String errorMessage) {
		performAction(locator, errorMessage, null);
	}

	private void performAction(String locator, String errorMessage, String inputText) {
		try {
			WebElement element = waitForElementToBeClickable(locator);

			if (inputText != null) {
				element.sendKeys(inputText);
			} else {
				element.click();
			}

		} catch (TimeoutException e) {
			System.out.println("Timeout waiting for element: " + locator);
			Assert.fail(errorMessage);
		} catch (Exception e) {
			System.out.println("Error performing action on element: " + locator);
			e.printStackTrace();
			Assert.fail(errorMessage);
		}
	}

	private WebElement waitForElementToBeClickable(String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(locator)));
		} catch (TimeoutException e) {
			System.out.println("Timeout waiting for element to be clickable: " + locator);
			throw e; // Rethrow exception to be handled in the calling method
		}
	}

	private void resetFilter(String resetButtonLocator) {
		performAction(resetButtonLocator, "Failed to reset filter");
	}
	
	public void navigateToReportsTab() {
		String reports_button = locators.getProperty("reports_button");
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(20));
		WebElement reportsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reports_button)));
		if (reportsTab.isDisplayed()) {
			reportsTab.click();
		} else {
			Assert.fail("Failed to tap on reports tab");
		}
	}

	public void validateDownloadReportFunctionnalty() throws InterruptedException, IOException {
		String tap_filter = locators.getProperty("tap_filter");
		String tap_date = locators.getProperty("tap_date");
		String select_this_week = locators.getProperty("select_this_week");
		String select_last_30_days = locators.getProperty("select_last_30_days");
		String select_last_90_days = locators.getProperty("select_last_90_days");
		String select_last_6_months = locators.getProperty("select_last_6_months");
		String select_last_12_months = locators.getProperty("select_last_12_months");
		String select_month_to_date = locators.getProperty("select_month_to_date");
		String select_custom_date = locators.getProperty("select_custom_date");
		String saveButton = locators.getProperty("tap_save_button");
		String applyButton = locators.getProperty("tap_apply_button");

		// Common method to apply filter and download report
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_this_week, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_last_30_days, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_last_90_days, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_last_6_months, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_last_12_months, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_month_to_date, saveButton, applyButton);
		applyDateFilterAndDownloadReport(tap_filter, tap_date, select_custom_date, saveButton, applyButton);
	}

	// Method to apply filter and download report
	private void applyDateFilterAndDownloadReport(String tap_filter, String tap_date, String dateOption,
			String saveButton, String applyButton) throws InterruptedException, IOException {
		String select_custom_date = locators.getProperty("select_custom_date");
		// Tap on filter
		performAction(tap_filter, "Failed to tap on filter");

		// Tap on date option
		performAction(tap_date, "Failed to tap on date");

		// Select the desired date range or custom date
		if (dateOption.equals(select_custom_date)) {
			performAction(dateOption, "Failed to tap on custom date");
			Thread.sleep(3000);
			appdriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + getTodayDate() + "']")).click();
		} else {
			performAction(dateOption, "Failed to select date option: " + dateOption);
		}

		// Save and apply the filter
		performAction(saveButton, "Failed to save filter");
		performAction(applyButton, "Failed to apply filter");

		// Download report and verify
		validateDownloadReport();
	}

	public void validateDownloadReport() throws InterruptedException {
		String download_button = locators.getProperty("download_button");

		// Validate the download button
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(30)); // Explicit wait of 30 seconds
		WebElement downloadButtonElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(download_button)));

		if (downloadButtonElement.isDisplayed()) {
			downloadButtonElement.click();
		} else {
			Assert.fail("Failed to tap on download report button");
		}

		// Scenario 1: Check if "Report is being generated" toast is displayed
		if (isToastMessagePresent()) {
			return; // Exit the method if the toast is displayed
		}

		// Scenario 2: If no toast message, check for browser navigation (Chrome/Safari)
		handleDownloadAndBrowserNavigation();

		// Scenario 3: If a pop-up appears with message "Download report again?"
		handleDownloadConfirmationPopup();
	}

	// Method to check for toast message presence
	private boolean isToastMessagePresent() {
		String pop_up_message = locators.getProperty("report_generation_pop_up_message");
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(5)); // Explicit wait
			WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pop_up_message)));
			if (toast != null && toast.isDisplayed()) {
				return true; // Toast found
			} else {
				return false; // Toast is not found
			}
		} catch (Exception e) {
			return false; // Toast not found
		}
	}

	// Handle download button navigation to browser (Chrome/Safari)
	private void handleDownloadAndBrowserNavigation() {
		// Check if we navigated to Chrome/Safari (use appropriate locators for Chrome
		// or Safari)
		if (isElementPresent(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']"))) {
			navigateBackToReport();
		}
	}

	// Handle "Download report again?" confirmation pop-up
	private void handleDownloadConfirmationPopup() {
		// Check if the pop-up "Download report again?" appears
		if (isElementPresent(AppiumBy.xpath("//android.widget.TextView[@text='Download file again?']"))) {
			System.out.println("Popup displayed: 'Download report again?'");
			handleDownloadConfirmation();
		}
	}

	// Utility method to check if an element is present (with explicit wait)
	private boolean isElementPresent(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(5));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element != null; // Return true if element is present
		} catch (Exception e) {
			return false; // Element not found
		}
	}

	private void navigateBackToReport() {
		int maxAttempts = 3; // Max attempts to press back
		int attempts = 0;
		boolean isAtReportsTab = false;
		while (attempts < maxAttempts && !isAtReportsTab) {
			try {
				// Press back button
				appdriver.navigate().back();
				attempts++;

				// Optional: Sleep for a short time to ensure screen transitions
				Thread.sleep(2000); // Adjust sleep time as needed

				// Check if we're on the Reports tab
				isAtReportsTab = isReportsTabDisplayed();
			} catch (Exception e) {
				break; // Exit if there's an error
			}
		}
	}

	private boolean isReportsTabDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(5));
			WebElement reportsTab = wait.until(ExpectedConditions
					.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Reports']")));
			return reportsTab.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// Confirm the download in the "Download report again?" pop-up
	private void handleDownloadConfirmation() {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			WebElement confirmButton = wait.until(ExpectedConditions
					.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='Download']")));
			confirmButton.click();
			navigateBackToReport();
		} catch (Exception e) {
		}
	}

	public String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("d"); // Change format as needed
		return sdf.format(new Date());
	}

	public void validateUserCanDownloadReportsFromGroupByLocation() throws InterruptedException {
		String tap_location = locators.getProperty("tap_location");
		String tap_on_site_name = locators.getProperty("tap_on_site_name");
		String download_report_from_location = locators.getProperty("download_report_from_location");

		// tap on group by location
		performAction(tap_location, "Failed to tap on group by location");

		// tap on site name
		performAction(tap_on_site_name, "Failed to tap on site name");

		// tap on download report from location
		performAction(download_report_from_location, "Failed to download report from location");
		// Scenario 1: Check if "Report is being generated" toast is displayed
		if (isToastMessagePresent()) {
			return; // Exit the method if the toast is displayed
		}

		// Scenario 2: If no toast message, check for browser navigation (Chrome/Safari)
		handleDownloadAndBrowserNavigation();

		// Scenario 3: If a pop-up appears with message "Download report again?"
		handleDownloadConfirmationPopup();

	}
	
	public void verifyDownloadReportsForLastSevenAndThirtyDays() throws InterruptedException {
		String tap_last_seven_days = locators.getProperty("tap_last_seven_days");
		String tap_last_thirty_days = locators.getProperty("tap_last_thirty_days");
		String tap_group_by_none = locators.getProperty("tap_group_by_none");
		String tap_location = locators.getProperty("tap_location");
		String download_report_from_location = locators.getProperty("download_report_from_location");

		// tap on last seven days
		performAction(tap_last_seven_days, "Failed to tap on last seven days");

		// tap on group by none
		performAction(tap_group_by_none, "Failed to tap on group by none");
		validateDownloadReport();

		// tap on group by location
		performAction(tap_location, "Failed to tap on group by location");
		// tap on download report from location
		performAction(download_report_from_location, "Failed to download report from location");
		// Scenario 1: Check if "Report is being generated" toast is displayed
		if (isToastMessagePresent()) {
			return; // Exit the method if the toast is displayed
		}

		// Scenario 2: If no toast message, check for browser navigation (Chrome/Safari)
		handleDownloadAndBrowserNavigation();

		// Scenario 3: If a pop-up appears with message "Download report again?"
		handleDownloadConfirmationPopup();

		// tap on last 30 days
		performAction(tap_last_thirty_days, "Failed to tap on last thirty days");

		// tap on group by none
		performAction(tap_group_by_none, "Failed to tap on group by none");
		validateDownloadReport();

		// tap on group by location
		performAction(tap_location, "Failed to tap on group by location");
		// tap on download report from location
		performAction(download_report_from_location, "Failed to download report from location");
		// Scenario 1: Check if "Report is being generated" toast is displayed
		if (isToastMessagePresent()) {
			return; // Exit the method if the toast is displayed
		}

		// Scenario 2: If no toast message, check for browser navigation (Chrome/Safari)
		handleDownloadAndBrowserNavigation();

		// Scenario 3: If a pop-up appears with message "Download report again?"
		handleDownloadConfirmationPopup();

	}
}