package web.web_page_objects;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utlis.Web_Utlis.AppData;
import utlis.Web_Utlis.Library;
import utlis.Web_Utlis.TestDataGenerator;

public class WebAuditPage {
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();
	public static String siteCardSatus = "PRIORITY";

	public WebAuditPage(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;

	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * Verify the functionalities of Web Audit
	 * 
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void verifyScheduleTab() throws Exception {
		// verify the page title of schedule tab functionality
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_schedule_page_title");
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		String pageTitle = webdriver.findElement(By.xpath(Library.getXPath("audit_schedule_page_title"))).getText();
		String expTitle = prop.getProperty("Audit_Schedule_Page_Title");
		if (pageTitle.equals(expTitle)) {
			Assert.assertEquals(pageTitle, expTitle, "Successfully verified page title of schedule tab");
		} else {
			Assert.fail("failed to verify page title of schedule tab");
		}
	}

	// ------------------------------------------------------------------------------------------------------------
	/**
	 * verify the schedule tab UI
	 * 
	 * @throws Exception
	 */
	// ------------------------------------------------------------------------------------------------------------
	public void verifyScheduleTabUI() throws Exception {
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_schedule_search_bar");
		WebElement searchBar = webdriver.findElement(By.xpath(Library.getXPath("audit_schedule_search_bar")));
		if (searchBar.isDisplayed()) {
			Assert.assertTrue(true, "search bar is displayed on schedule tab");
		} else {
			Assert.fail("failed to display search bar on schedule tab");
		}

		// verify the site cards are displayed on schedule tab
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_schedule_site_cards");
		Thread.sleep(4000);
		boolean displayed = false;
		List<WebElement> sitecards = webdriver.findElements(By.xpath(Library.getXPath("audit_schedule_site_cards")));
		int count = sitecards.size();
		for (int i = 0; i < count; i++) {
			if (displayed = sitecards.get(i).isDisplayed()) {
				Assert.assertTrue(true, "site cards are displayed on schedule tab");
			} else {
				Assert.fail("failed to display the site cards on schedule page");
			}
		}

		// verify the in-progress site cards status
//		library.dynamicWaitForVisibilityOfElement("audit_schedule_site_cards_status");
//		Thread.sleep(4000);
//		boolean status = false;
//		List<WebElement> sitecardsStatus = driver.findElements(By.xpath(Library.getXPath("audit_schedule_site_cards_status")));
//		int statusCount = sitecardsStatus.size();
//		for (int i = 0; i < statusCount; i++) {
//			if (displayed = sitecardsStatus.get(i).isDisplayed()) {
//				Assert.assertTrue(true, "site card status are displayed on schedule tab");
//			} else {
//				Assert.fail("failed to display the site card status on schedule page");
//			}
//		}

	}

	// -------------------------------------------------------------------------------------------------------------
	/**
	 * Verify the site card contents on schedule page
	 * 
	 * @throws Exception
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void siteCardContents() throws Exception {
		// search for the site card on schedule tab
		String sitecard = AppData.getProperty("Site_Name");
		library.enterText(webdriver, "audit_schedule_search_bar", sitecard);

		// verify the site card name
		WebElement siteName = webdriver.findElement(By.xpath(Library.getXPath("audit_schedule_site_card_name")));
		if (siteName.isDisplayed()) {
			Assert.assertTrue(true, "site card name is displayed on schedule tab");
		} else {
			Assert.fail("failed to verify site card on schedule tab");
		}

		// verify the progress of site card
		String siteStatus = webdriver.findElement(By.xpath(Library.getXPath("audit_schedule_site_card_status")))
				.getText();
		if (siteStatus.equals(siteCardSatus)) {
			Assert.assertEquals(siteStatus, siteCardSatus, "successfully verified site card status on schedule tab");
		} else {
			Assert.fail("failed to verify site card status on schedule tab");
		}

	}

}
