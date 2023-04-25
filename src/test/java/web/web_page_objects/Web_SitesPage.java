package web.web_page_objects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utlis.Web_Utlis.Library;
import utlis.Web_Utlis.TestDataGenerator;

public class Web_SitesPage {
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();
	public String projectPath = System.getProperty("user.dir");

	public Web_SitesPage(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;
	}

	// -----------------------------------------------------------------------------------------------------
	/**
	 * Verify the bulk upload of schedule
	 * 
	 * @throws Exception
	 */
	// -----------------------------------------------------------------------------------------------------
	public void bulkUploadSchedule() throws Exception {
		// select drop down option bulk schedule upload
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_site");
		WebElement bulkUpload = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_sites")));
		library.scrollToViewObject(webdriver, bulkUpload);

		// verify the bulk upload schedule
		WebElement schedule = webdriver.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites")));
		if (schedule.isDisplayed()) {
			Assert.assertTrue(true, "bulk schedule upload is displayed");
		} else {
			Assert.fail("failed to display bulk schedule upload");
		}
		library.click(webdriver, "bulk_schedule_upload_sites");

		// verify the bulk schedule upload page title
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_schedule_upload_sites_page_title");
		String actTitle = webdriver.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_page_title")))
				.getText();
		String expTitle = prop.getProperty("Bulk_Schedule_Upload_Page_Title");
		if (actTitle.equals(expTitle)) {
			Assert.assertEquals(actTitle, expTitle, "successfully verified bulk schedule upload page title");
		} else {
			Assert.fail("failed to verify bulk schedule upload page title");
		}

		// click on download template button
		WebElement scheduleDownload = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_download")));
		if (scheduleDownload.isDisplayed()) {
			Assert.assertTrue(true, "download button is displayed");
		} else {
			Assert.fail("failed to display download button");
		}
		library.click(webdriver, "bulk_schedule_upload_sites_download");

		// select the radio button
		WebElement radioButtonYes = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_radio_button")));
		if (radioButtonYes.isDisplayed()) {
			Assert.assertTrue(true, "radio button is dispalyed");
		} else {
			Assert.fail("failed to diaply radio button");
		}
		library.click(webdriver, "bulk_schedule_upload_sites_radio_button");

		// upload the schedule
		WebElement uploadSchedule = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_upload")));
		uploadSchedule.sendKeys(projectPath + "//src//test//resources//UploadFiles//site-schedule-template.xlsx");

		// click on upload button
		WebElement upload = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_upload_button")));
		if (upload.isDisplayed()) {
			Assert.assertTrue(true, "upload button is displayed");
		} else {
			Assert.fail("failed to click on upload button");
		}
		library.click(webdriver, "bulk_schedule_upload_sites_upload_button");

		// click on cancel button
		WebElement cancelButton = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_cancel")));
		if (cancelButton.isDisplayed()) {
			Assert.assertTrue(true, "cancel button is displayed");
		} else {
			Assert.fail("failed to click on cancel button");
		}
		library.click(webdriver, "bulk_schedule_upload_sites_cancel");

		// verify the success message
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_schedule_upload_sites_Message");
		String actMessage = webdriver.findElement(By.xpath(Library.getXPath("bulk_schedule_upload_sites_Message")))
				.getText();
		String expMessage = prop.getProperty("Bulk_Schedule_Upload_Message");
		if (actMessage.equals(expMessage)) {
			Assert.assertEquals(actMessage, expMessage, "success message is verified");
		} else {
			Assert.fail("failed to verify success message");
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	/**
	 * Verify the functionality of bulk edit schedule
	 * 
	 * @throws Exception
	 */
	// -----------------------------------------------------------------------------------------------------------
	public void bulkEditSchedule() throws Exception {
		// select drop down option bulk schedule upload
		library.dynamicWaitForVisibilityOfElement(webdriver, "txt_add_site");
		WebElement bulkUpload = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_sites")));
		library.scrollToViewObject(webdriver, bulkUpload);

		// verify the bulk upload schedule
		WebElement bulkEdit = webdriver.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_sites")));
		if (bulkEdit.isDisplayed()) {
			Assert.assertTrue(true, "bulk edit schedule is displayed");
		} else {
			Assert.fail("failed to display bulk edit schedule");
		}
		library.click(webdriver, "bulk_edit_schedule_sites");

		// verify the page title
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_edit_schedule_title");
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		String expPageTitle = webdriver.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_title"))).getText();
		String actPageTitle = prop.getProperty("Bulk_Edit_Schedule_Page_Title");
		if (expPageTitle.equals(actPageTitle)) {
			Assert.assertEquals(actPageTitle, expPageTitle, "successfully verified bulk edit schedule page title");
		} else {
			Assert.fail("failed to verify bulk edit schedule page title");
		}

		// click on download the bulk edit template
		WebElement downloadTemplate = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_sites_download")));
		if (downloadTemplate.isDisplayed()) {
			Assert.assertTrue(true, "download bulk edit template is displayed");
		} else {
			Assert.fail("failed to display download bulk edit template");
		}
		library.click(webdriver, "bulk_edit_schedule_sites_download");

		// upload bulk edit schedule template
		WebElement uploadTemplate = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_sites_upload_template")));
		uploadTemplate.sendKeys(projectPath + "//src//test//resources//UploadFiles//template_1680051184978.xlsx");

		// click on upload button
		WebElement uploadButton = webdriver
				.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_sites_upload_button")));
		if (uploadButton.isDisplayed()) {
			Assert.assertTrue(true, "upload button is displayed");
		} else {
			Assert.fail("failed to display upload button on bulk edit schedule popup");
		}
		library.click(webdriver, "bulk_edit_schedule_sites_upload_button");

		// click on cancel button
		WebElement cancelButton = webdriver.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_sites_cancel")));
		if (cancelButton.isDisplayed()) {
			Assert.assertTrue(true, "cancel button is displayed");
		} else {
			Assert.fail("failed to click on cancel button");
		}
		library.click(webdriver, "bulk_edit_schedule_sites_cancel");

		// verify the success message
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_edit_schedule_message");
		String actMessage = webdriver.findElement(By.xpath(Library.getXPath("bulk_edit_schedule_message"))).getText();
		String expMessage = prop.getProperty("Bulk_Edit_Schedule_Message");
		if (actMessage.equals(expMessage)) {
			Assert.assertEquals(actMessage, expMessage, "success message is verified");
		} else {
			Assert.fail("failed to verify success message");
		}

	}

}
