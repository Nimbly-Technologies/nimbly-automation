package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

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
		Thread.sleep(13000);
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
	
	public void navigateBackToIssues() throws InterruptedException {
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
		Thread.sleep(10000);
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
	
	public void verifyAttachmentsVisibilityUnderAllTab() throws InterruptedException {
		String attachments_list = locators.getProperty("attachments_list");
		Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(30));
		List<WebElement> imageElements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(attachments_list)));

		// Check if images are found
		Assert.assertTrue(imageElements.size() > 0, "No images found in the gallery.");

		// Validate each image element
		for (WebElement imageElement : imageElements) {
			// Assert that the image is displayed
			try {
				Assert.assertTrue(imageElement.isDisplayed(), "Image is not displayed: " + imageElement);

			} catch (Exception e) {

			}
		}
	}

	public void verifyAttachmnetsVisibilityUnderDaysTab() throws InterruptedException {
		String days_tab = locators.getProperty("days_tab");

		waitAndClick(days_tab, "failed to tap on days tap");

		// validate list of attachments under days tab
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();

	}

	public void verifyAttachmnetsVisibilityUnderMonthTab() throws InterruptedException {
		String months_tab = locators.getProperty("months_tab");
		String tap_on_month = locators.getProperty("tap_on_month");

		waitAndClick(months_tab, "Failed to tap on months tab");
		Thread.sleep(5000);
		waitAndClick(tap_on_month, "Failed to tap on any available months tabs");

		// validate list of attachments under months tab
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();

	}

	public void verifyAttachmnetsVisibilityUnderYearsTab() throws InterruptedException {
		String tap_on_gallery = locators.getProperty("tap_on_gallery");
		String years_tab = locators.getProperty("years_tab");
		String tap_on_year = locators.getProperty("tap_on_year");

		waitAndClick(tap_on_gallery, "Failed to tap on gallery");
		Thread.sleep(5000);
		waitAndClick(years_tab, "Failed to tap on years tab");
		Thread.sleep(5000);
		waitAndClick(tap_on_year, "Failed to tap on any available years tabs");

		// validate list of attachments under months tab
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();

	}
	
	public void verifyTileViewPerRow() throws InterruptedException {
		String tap_on_gallery = locators.getProperty("tap_on_gallery");
		String all_tab = locators.getProperty("all_tab");
		String tiles_per_row_icon = locators.getProperty("tiles_per_row_icon");
		String tiles_per_row_three = locators.getProperty("tiles_per_row_three");
		String close_tiles_per_row_pop_up = locators.getProperty("close_tiles_per_row_pop_up");

		waitAndClick(tap_on_gallery, "Failed to tap on gallery");
		Thread.sleep(5000);
		waitAndClick(all_tab, "Failed to tap on all tab");
		Thread.sleep(10000);
		waitAndClick(tiles_per_row_icon, "Failed to tap on tile to select rows");
		waitAndClick(tiles_per_row_three,"Failed to select tiles per row is five");
		waitAndClick(close_tiles_per_row_pop_up,"Failed to close tiles per row pop up");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
	}
	
	public void validateAttachmentsUnderAlbum() throws InterruptedException {
		String albums_tab = locators.getProperty("albums_tab");
		String tap_on_month = locators.getProperty("tap_on_month");
		String tap_on_gallery = locators.getProperty("tap_on_gallery");
		String site_name = locators.getProperty("gallery_site_name");

		// Expected value
		String expSiteName = prop.getProperty("Gallery_Site_Name");
		Thread.sleep(8000);
		waitAndClick(albums_tab, "Failed to tap on albums");
		// validate site name
		Thread.sleep(10000);
		String actSiteName = appdriver.findElement(AppiumBy.xpath(site_name)).getText();
		if (actSiteName.equals(expSiteName)) {
		} else {
			Assert.fail("Failed to validate site name under album");
		}
		Thread.sleep(5000);
		waitAndClick(tap_on_month, "Failed to tap on site attachments");
		// validate list of attachments for particular site under album
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();

		Thread.sleep(5000);
		waitAndClick(tap_on_gallery, "Failed to tap on gallery");
	}
	
	public void multiSelectAttachmentsUnderAlbum() throws InterruptedException {
		String tap_three_dots_menu = locators.getProperty("tap_three_dots_menu");
		String select_sites = locators.getProperty("select_sites");
		String multi_select_sites = locators.getProperty("multi_select_sites");
		String cancel_multi_select = locators.getProperty("cancel_multi_select");

		Thread.sleep(8000);
		waitAndClick(tap_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(select_sites, "Failed to tap on select sites");
		waitAndClick(multi_select_sites, "Failed to select sites");
		waitAndClick(cancel_multi_select,"Failed to tap on cancel");
	}
	
	public void verifyFilterAndSortFunctionality() throws InterruptedException {
		String library_tab = locators.getProperty("library_tab");
		String gallery_filter = locators.getProperty("gallery_filter");
		String gallery_filter_source = locators.getProperty("gallery_filter_source");
		String gallery_filter_source_reports = locators.getProperty("gallery_filter_source_reports");
		String gallery_filter_source_issue_tracker = locators.getProperty("gallery_filter_source_issue_tracker");
		String gallery_save_button = locators.getProperty("gallery_save_button");
		String gallery_apply_button = locators.getProperty("gallery_apply_button");
		String gallery_reset_button = locators.getProperty("gallery_reset_button");
		String gallery_filter_attachment_type = locators.getProperty("gallery_filter_attachment_type");
		String gallery_filter_attachment_type_image = locators.getProperty("gallery_filter_attachment_type_image");
		String gallery_filter_attachment_type_video = locators.getProperty("gallery_filter_attachment_type_video");
		String gallery_filter_site = locators.getProperty("gallery_filter_site");
		String gallery_filter_search_by_site_name = locators.getProperty("gallery_filter_search_by_site_name");
		String gallery_filter_select_site_name = locators.getProperty("gallery_filter_select_site_name");
		String gallery_filter_department = locators.getProperty("gallery_filter_department");
		String gallery_filter_serach_by_department_name = locators.getProperty("gallery_filter_serach_by_department_name");
		String gallery_filter_select_department = locators.getProperty("gallery_filter_select_department");
		String gallery_filter_auditor = locators.getProperty("gallery_filter_auditor");
		String gallery_filter_search_by_auditor = locators.getProperty("gallery_filter_search_by_auditor");
		String gallery_filter_select_auditor = locators.getProperty("gallery_filter_select_auditor");
		String gallery_filter_questionnaire = locators.getProperty("gallery_filter_questionnaire");
		String gallery_filter_serach_by_questionnaire = locators.getProperty("gallery_filter_serach_by_questionnaire");
		String gallery_filter_select_questionnaire = locators.getProperty("gallery_filter_select_questionnaire");
		String gallery_filter_date_range = locators.getProperty("gallery_filter_date_range");
		String gallery_filter_select_last_30_days = locators.getProperty("gallery_filter_select_last_30_days");
		String gallery_sort_ascending_order = locators.getProperty("gallery_sort_ascending_order");
		String gallery_sort_descending_oder = locators.getProperty("gallery_sort_descending_oder");
		
		// expected values
		String expSiteName = prop.getProperty("Gallery_Site_Name");
		String expDepartmentName = prop.getProperty("Gallery_Department_Name");
		String expQuestionnaireName = prop.getProperty("Gallery_Questionnaire_Name");
		String expAuditorName = prop.getProperty("Gallery_Auditor_Name");
		
		// navigate back to library
		Thread.sleep(8000);
		waitAndClick(library_tab,"Failed to tap on Library");
		
		//filter by source as reports
		waitAndClick(gallery_filter,"Failed to tap on filter");
		waitAndClick(gallery_filter_source,"Failed to tap on source");
		waitAndClick(gallery_filter_source_reports,"Failed to tap on source by reports");
		waitAndClick(gallery_save_button,"Failed to tap on save button");
		waitAndClick(gallery_apply_button,"Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter,"Failed to tap on filter");
		waitAndClick(gallery_reset_button,"Failed to tap on reset button");
		
		//filter by source as issue tracker
		waitAndClick(gallery_filter,"Failed to tap on filder");
		waitAndClick(gallery_filter_source,"Failed to tap on source");
		waitAndClick(gallery_filter_source_issue_tracker,"Failed to tap on source by issue tracker");
		waitAndClick(gallery_save_button,"Failed to tap on save button");
		waitAndClick(gallery_apply_button,"Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter,"Failed to tap on filter");
		waitAndClick(gallery_reset_button,"Failed to tap on reset button");
		
		// filter by attachment type as image
		waitAndClick(gallery_filter,"Failed to tap on filder");
		waitAndClick(gallery_filter_attachment_type,"Failed to tap on attachment type");
		waitAndClick(gallery_filter_attachment_type_image,"Failed to tap on attachment type as image");
		waitAndClick(gallery_save_button,"Failed to tap on save button");
		waitAndClick(gallery_apply_button,"Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter,"Failed to tap on filter");
		waitAndClick(gallery_reset_button,"Failed to tap on reset button");
		
		// filter by attachment type as video
		waitAndClick(gallery_filter, "Failed to tap on filder");
		waitAndClick(gallery_filter_attachment_type, "Failed to tap on attachment type");
		waitAndClick(gallery_filter_attachment_type_video, "Failed to tap on attachment type as video");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//filter by site name
		waitAndClick(gallery_filter, "Failed to tap on filder");
		waitAndClick(gallery_filter_site, "Failed to tap on site name");
		Thread.sleep(5000);
		WebElement enterSiteName = appdriver.findElement(AppiumBy.xpath(gallery_filter_search_by_site_name));
		enterSiteName.sendKeys(expSiteName);
		waitAndClick(gallery_filter_select_site_name, "Failed to select site name");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//filter by department name
		waitAndClick(gallery_filter, "Failed to tap on filder");
		waitAndClick(gallery_filter_department, "Failed to tap on department name");
		Thread.sleep(5000);
		WebElement enterDepartmentName = appdriver.findElement(AppiumBy.xpath(gallery_filter_serach_by_department_name));
		enterDepartmentName.sendKeys(expDepartmentName);
		waitAndClick(gallery_filter_select_department, "Failed to select department name");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//filter by auditor name
		waitAndClick(gallery_filter, "Failed to tap on filder");
		waitAndClick(gallery_filter_auditor, "Failed to tap on auditor name");
		Thread.sleep(5000);
		WebElement enterAuditorName = appdriver.findElement(AppiumBy.xpath(gallery_filter_search_by_auditor));
		enterAuditorName.sendKeys(expAuditorName);
		waitAndClick(gallery_filter_select_auditor, "Failed to select auditor name");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//filter by questionnaire name
		waitAndClick(gallery_filter, "Failed to tap on filder");
		waitAndClick(gallery_filter_questionnaire, "Failed to tap on questionnaire name");
		Thread.sleep(5000);
		WebElement enterQuestionnaireName = appdriver.findElement(AppiumBy.xpath(gallery_filter_serach_by_questionnaire));
		enterQuestionnaireName.sendKeys(expQuestionnaireName);
		waitAndClick(gallery_filter_select_questionnaire, "Failed to select questionnaire name");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		// filter by date range
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_filter_date_range, "Failed to tap on date range");
		waitAndClick(gallery_filter_select_last_30_days, "Failed to select last 30 days");
		waitAndClick(gallery_save_button, "Failed to tap on save button");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//sort by ascending order
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_sort_ascending_order, "Failed to tap on ascending order");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");
		
		//sort by descending order
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_sort_descending_oder, "Failed to tap on descending order");
		waitAndClick(gallery_apply_button, "Failed to tap on apply button");
		Thread.sleep(5000);
		verifyAttachmentsVisibilityUnderAllTab();
		waitAndClick(gallery_filter, "Failed to tap on filter");
		waitAndClick(gallery_reset_button, "Failed to tap on reset button");

	}
	
	public void validateSelectDeselectAndCollapseAttachmentsDayWise() throws InterruptedException {
		String days_tab = locators.getProperty("days_tab");
		String tap_three_dots_menu_days = locators.getProperty("tap_three_dots_menu_days");
		String select_attachments_txt = locators.getProperty("select_attachments_txt");
		String select_first_attachment = locators.getProperty("select_first_attachment");
		String deselect_checkbox_days = locators.getProperty("deselect_checkbox_days");
		String collapse_days = locators.getProperty("collapse_days");
		String cancel_multi_select = locators.getProperty("cancel_multi_select");
		
		//tap on days tab
		waitAndClick(days_tab,"Failed to tap on Days tab");
		Thread.sleep(10000);
		//tap on three dots menu
		waitAndClick(tap_three_dots_menu_days,"Failed to tap on three dots menu on days tab");
		//tap on select attachments
		waitAndClick(select_attachments_txt,"Failed select attachments");
		// do bulk select attachments
		waitAndClick(select_first_attachment,"Failed to select bulk attachmnets under days tab");
		//deselect attachments
		waitAndClick(deselect_checkbox_days,"Failed to de select bulk attachments under days tab");
		// collapse days
		waitAndClick(collapse_days,"failed to tap on collapse attachments under days tab");
		
		// after collapse validate attachment
		Thread.sleep(10000);
		verifyAttachmentsVisibilityUnderAllTab();
		
		//tap on cancel button
		waitAndClick(cancel_multi_select,"failed to tap on cancel button on Days tab");			
	}
	
	public void validateSelectDeselectAndCollapseAttachmentsMonthWise() throws InterruptedException {
		String months_tab = locators.getProperty("months_tab");
		String tap_three_dots_menu = locators.getProperty("tap_three_dots_menu");
		String select_dates = locators.getProperty("select_dates");
		String select_first_attachment = locators.getProperty("select_first_attachment");
		String deselect_checkbox_days = locators.getProperty("deselect_checkbox_days");
		String collapse_months = locators.getProperty("collapse_months");
		String cancel_multi_select = locators.getProperty("cancel_multi_select");
		
		//tap on months
		waitAndClick(months_tab,"Failed to tap on month tab");
		Thread.sleep(10000);
		//tap on three dots menu
		waitAndClick(tap_three_dots_menu,"Failed to tap on three dots menu under months tab");
		//tap on select dates
		waitAndClick(select_dates,"Failed to select dates under months tab");
		//tap on multi select attachments
		waitAndClick(select_first_attachment,"Failed to select multiple attachmnets under months tab");
		//deselect attachmnets
		waitAndClick(deselect_checkbox_days,"Failed to de select attachments");
		// tap on collapse attachments
		waitAndClick(collapse_months,"Failed to collapse attachments under month tab");
		// tap on cancel button
		waitAndClick(cancel_multi_select,"Failed to tap on cancel button");
		
	}
	
	public void validateSelectDeselectAttachmentsYearWise() throws InterruptedException {
		String years_tab = locators.getProperty("years_tab");
		String tap_three_dots_menu = locators.getProperty("tap_three_dots_menu");
		String select_attachments_txt = locators.getProperty("select_attachments_txt");
		String select_first_attachment = locators.getProperty("select_first_attachment");
		String deselect_checkbox_days = locators.getProperty("deselect_checkbox_days");
		String cancel_multi_select = locators.getProperty("cancel_multi_select");
		
		//tap on years tab
		waitAndClick(years_tab,"Failed to tap on years tab");
		Thread.sleep(10000);
		//tap on three dots
		waitAndClick(tap_three_dots_menu,"Failed to tap on three dots menu");
		//tap on select attachments
		waitAndClick(select_attachments_txt,"Failed to tap on select attachments");
		//tap select multiple attachments
		waitAndClick(select_first_attachment,"Failed to select attachments under years tab");
		//tap on de select attachments
		waitAndClick(deselect_checkbox_days,"Failed to de select attachments");
		//tap on cancel button
		waitAndClick(cancel_multi_select,"Failed to tap cancel button");	
	}
	
	public void validateSearchAndSuggestionAsPerQuestion() throws InterruptedException {
		String all_tab = locators.getProperty("all_tab");
		String search_question_and_category = locators.getProperty("search_question_and_category");
		String question_serach_results = locators.getProperty("question_serach_results");

		// expected values
		String Gallery_QuestionaName = prop.getProperty("Gallery_QuestionaName");
		String expSuggestion = prop.getProperty("Gallery_QuestionResults");

		// tap on all tab
		waitAndClick(all_tab, "Failed to tap on all tab");

		// search with question name
		Thread.sleep(10000);
		waitAndClick(search_question_and_category, "Failed to tap on serach bar");
		WebElement question = appdriver.findElement(AppiumBy.xpath(search_question_and_category));
		question.sendKeys(Gallery_QuestionaName);

		// validate suggestion for question
		Thread.sleep(10000);
		String actSuggestion = appdriver.findElement(AppiumBy.xpath(question_serach_results)).getText();
		if (actSuggestion.equals(expSuggestion)) {
		} else {
			Assert.fail("Failed to validate suggestion for question after serach");
		}

		// tap on question results
		waitAndClick(question_serach_results, "Failed to tap on question search results");

		// validate question results attachments
		Thread.sleep(10000);
		verifyAttachmentsVisibilityUnderAllTab();
	}

	public void validateSearchAndSuggestionAsPerCategory() throws InterruptedException {
		String tap_on_gallery = locators.getProperty("tap_on_gallery");
		String search_question_and_category = locators.getProperty("search_question_and_category");
		String category_search_results = locators.getProperty("category_search_results");

		// expected values
		String Gallery_CategoryName = prop.getProperty("Gallery_CategoryName");
		String expSuggestion = prop.getProperty("Gallery_CategoryResults");

		// tap on gallery
		waitAndClick(tap_on_gallery, "Failed to tap on gallery");

		// search with question name
		Thread.sleep(10000);
		waitAndClick(search_question_and_category, "Failed to tap on serach bar");
		WebElement question = appdriver.findElement(AppiumBy.xpath(search_question_and_category));
		question.sendKeys(Gallery_CategoryName);

		// validate suggestion for question
		Thread.sleep(10000);
		String actSuggestion = appdriver.findElement(AppiumBy.xpath(category_search_results)).getText();
		if (actSuggestion.equals(expSuggestion)) {
		} else {
			Assert.fail("Failed to validate suggestion for question after serach");
		}

		// tap on question results
		waitAndClick(category_search_results, "Failed to tap on question search results");

		// validate question results attachments
		Thread.sleep(10000);
		verifyAttachmentsVisibilityUnderAllTab();

	}
}