package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class GalleryPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public GalleryPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//GalleryPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//GalleryPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void navigateToGallery() throws InterruptedException {
		// Locators
		String hamburger = locators.getProperty("tap_on_hamburger");
		String select_gallery = locators.getProperty("tap_on_gallery");
		String download_info_popup = locators.getProperty("download_info_popup");

		// tap on hamburger
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(hamburger)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(hamburger)).click();
		} else {
			Assert.fail("Failed to tap on hamburger");
		}

		// select gallery
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(select_gallery)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_gallery)).click();
		} else {
			Assert.fail("Failed to select gallery");
		}

		// close the popup
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(download_info_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(download_info_popup)).click();
		} else {
			Assert.fail("Failed to close download info pop up");
		}

	}
	
	public void validateDefaultAllAndLibraryTabsSelected() throws InterruptedException {
		String library_tab = locators.getProperty("library_tab");
		String all_tab = locators.getProperty("all_tab");

		// verify by default library tab is selected
		WebElement library = appdriver.findElement(AppiumBy.xpath(library_tab));
		if (library.isDisplayed()) {
		} else {
			Assert.fail("Library tab is not selected by default");
		}

		// verify by default all tab is selected
		Thread.sleep(3000);
		WebElement all = appdriver.findElement(AppiumBy.xpath(all_tab));
		if (all.isDisplayed()) {
		} else {
			Assert.fail("All tab is not selected by default");
		}
	}
	
	public void downloadAttachment() throws InterruptedException {
		String tap_attachment = locators.getProperty("tap_attachment");
		String tap_show = locators.getProperty("tap_show");
		String site_name = locators.getProperty("site_name");
		String department_name = locators.getProperty("department_name");
		String download_image = locators.getProperty("download_image");
		String success_popup = locators.getProperty("success_popup");
		String back_button_from_image = locators.getProperty("back_button_from_image");

		// expected values
		String expSiteName = prop.getProperty("SiteName");
		String expDepartmentName = prop.getProperty("DepartmentName");
		String expSuccessMessage = prop.getProperty("Success_Message");

		// tap on first attachment
		waitAndClick(tap_attachment, "Failed to tap on attachment");
		// tap on show more details
		waitAndClick(tap_show, "Failed to tap on show more details");

		// validate site name
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Sucessfully validated site name");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate department name
		String actDepartmentName = appdriver.findElement(AppiumBy.xpath(department_name)).getText();
		if (actDepartmentName.equals(expDepartmentName)) {
			Assert.assertEquals(actDepartmentName, expDepartmentName, "Sucessfully validated department name");
		} else {
			Assert.fail("Failed to validate department name");
		}

		// tap on download button
		waitAndClick(download_image, "Failed to download attachment");

		// validate success message
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success_popup)));
		String actSuccessMessage = appdriver.findElement(AppiumBy.xpath(success_popup)).getText();
		if (actSuccessMessage.equals(expSuccessMessage)) {
			Assert.assertEquals(actSuccessMessage, expSuccessMessage,
					"Sucessfully validated download attachmnet success message");
		} else {
			Assert.fail("Failed to verify attachment download success message");
		}

		// tap on back button
		Thread.sleep(7000);
		waitAndClick(back_button_from_image, "Failed to tap on back button");

	}

	private void waitAndClick(String xpath, String failureMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (TimeoutException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void validateSelectAllPopup() {
		String over_flow_menu = locators.getProperty("over_flow_menu");
		String more_pop_up = locators.getProperty("more_pop_up");
		String select_attachments_txt = locators.getProperty("select_attachments_txt");

		// expected values
		String expMoreText = prop.getProperty("More_Text");
		String expSelectAttachments = prop.getProperty("Select_Attachments");

		// tap on over flow menu
		waitAndClick(over_flow_menu, "Failed to tap on over flow menu");

		// validate more text on pop up
		String actMoreText = appdriver.findElement(AppiumBy.xpath(more_pop_up)).getText();
		if (actMoreText.equals(expMoreText)) {
			Assert.assertEquals(actMoreText, expMoreText, "Sucessfully validated More text on popup");
		} else {
			Assert.fail("Failed to validate more text on popup");
		}

		// validate select attachments text on pop up
		String actSelectAttachments = appdriver.findElement(AppiumBy.xpath(select_attachments_txt)).getText();
		if (actSelectAttachments.equals(expSelectAttachments)) {
			Assert.assertEquals(actSelectAttachments, expSelectAttachments, "Sucessfully validated select attachments");
		} else {
			Assert.fail("Failed to validate select attachments text on popup");
		}

		// tap on select attachments
		waitAndClick(select_attachments_txt, "Failed to tap on select attachments");
	}
	
	public void navigateBackToIssues() {
		String all_tab = locators.getProperty("all_tab");
		String tap_attachment = locators.getProperty("tap_attachment");
		String tap_show = locators.getProperty("tap_show");
		String next_button = locators.getProperty("next_button");
		String go_to_issue = locators.getProperty("go_to_issue");
		String site_name = locators.getProperty("site_name");
		String department_name = locators.getProperty("department_name");

		// expected values
		String expSiteName = prop.getProperty("SiteName");
		String expDepartmentName = prop.getProperty("DepartmentName");
		
		waitAndClick(all_tab,"Failed to tap on all tab");

		// tap on first attachment
		waitAndClick(tap_attachment, "Failed to tap on attachment");
		// tap on show more details
		waitAndClick(tap_show, "Failed to tap on show more details");

		// Loop to find "Go to Issue" button
		boolean found = false;
		int maxAttempts = 10;
		int attempts = 0;

		while (!found && attempts < maxAttempts) {
			if (isElementDisplayed(go_to_issue, 6)) {
				waitAndClick(go_to_issue, "Failed to tap on 'Go to Issue'");
				found = true;
			} else {
				if (isElementDisplayed(next_button, 5)) {
					waitAndClick(next_button, "Failed to tap on the 'Next' button.");
				} else {
					break;
				}
			}
			attempts++;
		}

		// validate site name on issue details page
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
			Assert.assertEquals(actSiteName, expSiteName, "Sucessfully validated site name");
		} else {
			Assert.fail("Failed to validate site name");
		}

		// validate department name on issue details page
		String actDepartmentName = appdriver.findElement(AppiumBy.xpath(department_name)).getText();
		if (actDepartmentName.equals(expDepartmentName)) {
			Assert.assertEquals(actDepartmentName, expDepartmentName, "Sucessfully validated department name");
		} else {
			Assert.fail("Failed to validate department name");
		}
	}

	private boolean isElementDisplayed(String xpath, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(timeoutInSeconds));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) != null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void downloadAttachmentsUnderAllDaysAndMonths() throws InterruptedException {
		String days_tab = locators.getProperty("days_tab");
		String months_tab = locators.getProperty("months_tab");
		String select_first_attachment = locators.getProperty("select_first_attachment");
		String select_second_attachment = locators.getProperty("select_second_attachment");
		String download_attachment = locators.getProperty("download_attachment");
		String download_attachment_monthly = locators.getProperty("download_attachment_monthly");
		String success_popup = locators.getProperty("success_popup");
		
		//expected value
		String expSuccessMessage = prop.getProperty("Success_Message");
		
		// download attachments under all tab
		waitAndClick(select_first_attachment,"Failed to select first attachment");
		waitAndClick(select_second_attachment,"Failed to select second attachment");
		waitAndClick(download_attachment,"Failed to download attachment");
		// validate success message
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success_popup)));
		String actSuccessMessage = appdriver.findElement(AppiumBy.xpath(success_popup)).getText();
		if (actSuccessMessage.equals(expSuccessMessage)) {
			Assert.assertEquals(actSuccessMessage, expSuccessMessage,
					"Sucessfully validated download attachmnet success message");
		} else {
			Assert.fail("Failed to verify attachment download success message");
		}
		
		//download attachments under days tab
		waitAndClick(days_tab,"Failed to tap on days tab");
		Thread.sleep(5000);
		validateSelectAllPopup();
		waitAndClick(select_second_attachment,"Failed to select second attachment");
		waitAndClick(download_attachment,"Failed to download attachment");
		// validate success message
		WebDriverWait wait2 = new WebDriverWait(appdriver, Duration.ofSeconds(10));
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success_popup)));
		String actSuccessMessage2 = appdriver.findElement(AppiumBy.xpath(success_popup)).getText();
		if (actSuccessMessage.equals(expSuccessMessage)) {
			Assert.assertEquals(actSuccessMessage2, expSuccessMessage,
					"Sucessfully validated download attachmnet success message");
		} else {
			Assert.fail("Failed to verify attachment download success message");
		}
		
		//download attachments under months tab
		waitAndClick(months_tab,"Failed to tap on days tab");
		validateSelectAllPopupWhenDownloadAttachmentsUnderMonth();
		Thread.sleep(5000);
		waitAndClick(select_second_attachment,"Failed to select second attachment");
		waitAndClick(download_attachment_monthly,"Failed to download attachment");
		// validate success message
		WebDriverWait wait3 = new WebDriverWait(appdriver, Duration.ofSeconds(30));
		wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success_popup)));
		String actSuccessMessage3 = appdriver.findElement(AppiumBy.xpath(success_popup)).getText();
		if (actSuccessMessage.equals(expSuccessMessage)) {
			Assert.assertEquals(actSuccessMessage3, expSuccessMessage,
					"Sucessfully validated download attachmnet success message");
		} else {
			Assert.fail("Failed to verify attachment download success message");
		}

	}
	
	public void validateSelectAllPopupWhenDownloadAttachmentsUnderMonth() {
		String over_flow_menu = locators.getProperty("over_flow_menu");
		String more_pop_up = locators.getProperty("more_pop_up");
		String select_dates = locators.getProperty("select_dates");

		// expected values
		String expMoreText = prop.getProperty("More_Text");
		String expSelectAttachments = prop.getProperty("Select_Months");

		// tap on over flow menu
		waitAndClick(over_flow_menu, "Failed to tap on over flow menu");

		// validate more text on pop up
		String actMoreText = appdriver.findElement(AppiumBy.xpath(more_pop_up)).getText();
		if (actMoreText.equals(expMoreText)) {
			Assert.assertEquals(actMoreText, expMoreText, "Sucessfully validated More text on popup");
		} else {
			Assert.fail("Failed to validate more text on popup");
		}

		// validate select attachments text on pop up
		String actSelectAttachments = appdriver.findElement(AppiumBy.xpath(select_dates)).getText();
		if (actSelectAttachments.equals(expSelectAttachments)) {
			Assert.assertEquals(actSelectAttachments, expSelectAttachments, "Sucessfully validated select attachments");
		} else {
			Assert.fail("Failed to validate select attachments text on popup");
		}

		// tap on select attachments
		waitAndClick(select_dates, "Failed to tap on select attachments");
	}
}