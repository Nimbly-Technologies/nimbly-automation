package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
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
		String download_button = locators.getProperty("download_button");
		String pop_up_message = locators.getProperty("report_generation_pop_up_message");

		// Expected values
		String expPopupMsg = prop.getProperty("Popup_Message");

		// validate download button
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(download_button)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(download_button)).click();
		} else {
			Assert.fail("Failed to tap on download report button");
		}

		// verify report generation popup
		Thread.sleep(2000);
		String actReportGenerationMessage = appdriver.findElement(AppiumBy.xpath(pop_up_message)).getText();
		if (actReportGenerationMessage.equals(expPopupMsg)) {
			Assert.assertEquals(actReportGenerationMessage, expPopupMsg,
					"Successfully validated report generation popup");

		} else {
			Assert.fail("Failed to validate report generation popup");
		}

	}

}