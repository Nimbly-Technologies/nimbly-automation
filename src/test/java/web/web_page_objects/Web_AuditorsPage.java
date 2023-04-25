package web.web_page_objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utlis.Web_Utlis.AppData;
import utlis.Web_Utlis.Library;
import utlis.Web_Utlis.TestDataGenerator;

public class Web_AuditorsPage {
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();
	public static String yopMail = "";
	public String projectPath = System.getProperty("user.dir");

	public Web_AuditorsPage(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;

	}

	// ---------------------------------------------------------------------------------------------------
	/**
	 * Create Dynamic mail
	 * 
	 * @throws Exception
	 */

	// ---------------------------------------------------------------------------------------------------
	public void createMail() throws Exception {
		String randomMail = library.getAlphaNumaricText() + "@yopmail.com";
		Web_AuditorsPage.yopMail = randomMail;
		System.out.println("Dynamic mail: " + randomMail);

		// generate the yop mail
		library.dynamicWaitForVisibilityOfElement(webdriver, "enter_text_yopmail");
		WebElement text = webdriver.findElement(By.xpath(Library.getXPath("enter_text_yopmail")));
		if (text.isDisplayed()) {
			library.enterText(webdriver, "enter_text_yopmail", randomMail);
			Assert.assertTrue(true, "new yopmail created");
		} else {
			Assert.fail("failed to create yopmail");
		}
		library.click(webdriver, "yopmail_arrow");
	}

	// --------------------------------------------------------------------------------------------------------
	/**
	 * Verify Add User Functionality
	 * 
	 * @throws Exception
	 */

	// --------------------------------------------------------------------------------------------------------
	public void verifyAddUser() throws Exception {
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_add_user");
		WebElement addUser = webdriver.findElement(By.xpath(Library.getXPath("auditor_add_user")));
		if (addUser.isDisplayed()) {
			Assert.assertTrue(true, "add user button is displayed");
		} else {
			Assert.fail("failed to displayed add user button");
		}
		library.click(webdriver, "auditor_add_user");

		// Verify The page title
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();

		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_page_title");
		String expTitle = prop.getProperty("Auditor_Page_Title");
		String actlTitle = webdriver.findElement(By.xpath(Library.getXPath("auditor_page_title"))).getText();
		if (expTitle.equals(actlTitle)) {
			Assert.assertEquals(actlTitle, expTitle, "Expected and Actual page title is matched");
		} else {
			Assert.fail("mismatches in expected and actual page title");
		}

		// verify the user name field
		WebElement username = webdriver.findElement(By.xpath(Library.getXPath("auditor_username_field")));
		if (username.isDisplayed()) {
			Assert.assertTrue(true, "user name field is displayed");
		} else {
			Assert.fail("failed to display user name field");
		}
		String rndUser = library.getAlphaNumaricText();
		library.enterText(webdriver, "auditor_username_field", rndUser);

		// verify the email field
		WebElement emailField = webdriver.findElement(By.xpath(Library.getXPath("auditor_mail")));
		if (emailField.isDisplayed()) {
			Assert.assertTrue(true, "enter email field is displayed");
		} else {
			Assert.fail("failed to display enter email field");
		}
		library.enterText(webdriver, "auditor_mail", yopMail);

		// verify the role field
		WebElement role = webdriver.findElement(By.xpath(Library.getXPath("auditor_role")));
		if (role.isDisplayed()) {
			Assert.assertTrue(true, "default auditor role is displayed");
		} else {
			Assert.fail("failed to display auditor role");
		}

		// verify the department field
		WebElement department = webdriver.findElement(By.xpath(Library.getXPath("auditor_department")));
		if (department.isDisplayed()) {
			Assert.assertTrue(true, "department field is displayed");
		} else {
			Assert.fail("failed to display department field");
		}
		String deptName = AppData.getProperty("Department_Name");
		library.click(webdriver, "auditor_department");
		library.enterText(webdriver, "auditor_enter_department", deptName);
		library.click(webdriver, "auditor_department_value");

		// Verify the phone number field
		WebElement phone = webdriver.findElement(By.xpath(Library.getXPath("auditor_phone")));
		if (phone.isDisplayed()) {
			Assert.assertTrue(true, "enter phone number field is displayed");
		} else {
			Assert.fail("failed to display enter phone numner field");
		}

		// verify save button and click on it
		WebElement btnSave = webdriver.findElement(By.xpath(Library.getXPath("auditor_btn_save")));
		if (btnSave.isDisplayed()) {
			Assert.assertTrue(true, "save button is dispalyed");
		} else {
			Assert.fail("failed to display save button");
		}
		library.click(webdriver, "auditor_btn_save");

		// verify the success message
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_success_message");
		String successMsg = webdriver.findElement(By.xpath(Library.getXPath("auditor_success_message"))).getText();
		String expMessage = yopMail + " " + "successfully added.";
		if (expMessage.equals(successMsg)) {
			Assert.assertEquals(successMsg, expMessage, "user creation message is displayed");
		} else {
			Assert.fail("failed to verify the user creation message");
		}
	}

	// -----------------------------------------------------------------------------------------------------
	/**
	 * Verify the search functionality of auditors page
	 * 
	 * @throws Exception
	 */
	// -----------------------------------------------------------------------------------------------------
	public void auditorSearchFunctionality() throws Exception {
		// verify the search button
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_add_user");
		WebElement searchField = webdriver.findElement(By.xpath(Library.getXPath("auditors_search_field")));
		if (searchField.isDisplayed()) {
			Assert.assertTrue(true, "search field is displayed on auditors page");
		} else {
			Assert.fail("failed to display search field on auditors page");
		}
		library.enterText(webdriver, "auditors_search_field", yopMail);

		// verify the search result
		Thread.sleep(3000);
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditors_user_search_record");
		String expMail = webdriver.findElement(By.xpath(Library.getXPath("auditors_user_search_record"))).getText();
		String actMail = yopMail;
		actMail = yopMail.toLowerCase();
		if (expMail.equals(actMail)) {
			Assert.assertEquals(actMail, expMail, "successfully verified user after search");
		} else {
			Assert.fail("failed to verify user after serach in auditors page");
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Verify the Activate account
	 * 
	 * @throws Exception
	 */
	// ---------------------------------------------------------------------------------------------------------------------------------
	public void activateAccount() throws Exception {
		// load the test data
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();

		// navigate to yop mail and verify the mail
		library.dynamicWaitForVisibilityOfElement(webdriver, "enter_text_yopmail");
		WebElement text = webdriver.findElement(By.xpath(Library.getXPath("enter_text_yopmail")));
		if (text.isDisplayed()) {
			library.enterText(webdriver, "enter_text_yopmail", yopMail);
			Assert.assertTrue(true, "new yopmail created");
		} else {
			Assert.fail("failed to create yopmail");
		}
		library.click(webdriver, "yopmail_arrow");

		// Switch to iframe
		WebElement frame = webdriver.findElement(By.xpath(Library.getXPath("txt_frame")));
		webdriver.switchTo().frame(frame);
		library.dynamicWaitForVisibilityOfElement(webdriver, "title_activate_account");

		// click on Activate account
		WebElement accountLink = webdriver.findElement(By.xpath(Library.getXPath("title_activate_account")));
		if (accountLink.isDisplayed()) {
			library.click(webdriver, "title_activate_account");
			Assert.assertTrue(true, "Successfully clicked on activate account link");
		} else {
			Assert.fail("failed to click on activate account link");
		}

		// switch to set up password
		Set<String> handels = webdriver.getWindowHandles();
		Iterator it = handels.iterator();
		String yopmailPage = (String) it.next();
		String nimblyAuditPage = (String) it.next();
		webdriver.switchTo().window(nimblyAuditPage);

		// verify the audit page title
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_page_title");
		WebElement auditPageTitle = webdriver.findElement(By.xpath(Library.getXPath("audit_page_title")));
		if (auditPageTitle.isDisplayed()) {
			Assert.assertTrue(true, "Verified audit page title");
		} else {
			Assert.fail("failed to verify audit page title");
		}

		// reset the password
		String password = AppData.getProperty("password");
		WebElement newPassword = webdriver.findElement(By.xpath(Library.getXPath("audit_new_password")));
		if (newPassword.isDisplayed()) {
			Assert.assertTrue(true, "new password field is displayed");
		} else {
			Assert.fail("Failed to dispaly enter new password field");
		}
		library.enterText(webdriver, "audit_new_password", password);

		// enter confirm password
		WebElement confirmPassword = webdriver.findElement(By.xpath(Library.getXPath("audit_confirm_password")));
		if (confirmPassword.isDisplayed()) {
			Assert.assertTrue(true, "confirm password field is dispalyed ");
		} else {
			Assert.fail("failed to display confirm password field");
		}
		library.enterText(webdriver, "audit_confirm_password", password);

		// verify the submit button
		WebElement submit = webdriver.findElement(By.xpath(Library.getXPath("audit_submit_button")));
		if (submit.isDisplayed()) {
			Assert.assertTrue(true, "submit button is displayed");
		} else {
			Assert.fail("submit button is not displayed");
		}
		library.click(webdriver, "audit_submit_button");

		// verify the success message
		library.dynamicWaitForVisibilityOfElement(webdriver, "audit_txt_message");
		String expectedMsg = prop.getProperty("Audit_Success_Message");
		String actualMsg = webdriver.findElement(By.xpath(Library.getXPath("audit_txt_message"))).getText();
		actualMsg = actualMsg.replaceAll("[\r\n]+", " ");
		if (expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(actualMsg, expectedMsg, "set up password message is verified");
		} else {
			Assert.fail("failed to verify the set up password message");
		}

		// switch back to parent window
		webdriver.switchTo().window(nimblyAuditPage).close();
		webdriver.switchTo().window(yopmailPage);
	}

	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Bulk upload users
	 * 
	 * @throws Exception
	 */

	// ----------------------------------------------------------------------------------------------------------
	public void bulkUpload() throws Exception {
		// load the test data
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();

		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_bulk_upload");
		WebElement uploadbutton = webdriver.findElement(By.xpath(Library.getXPath("auditor_bulk_upload")));
		if (uploadbutton.isDisplayed()) {
			Assert.assertTrue(true, "upload button is displayed");
		} else {
			Assert.fail("failed to display upload button");
		}
		library.click(webdriver, "auditor_bulk_upload");

		// verify the upload pop up page title
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_upload_title");
		WebElement uploadTitle = webdriver.findElement(By.xpath(Library.getXPath("auditor_upload_title")));
		if (uploadTitle.isDisplayed()) {
			Assert.assertTrue(true, "upload user pop up is displayed");
		} else {
			Assert.fail("failed to display upload user pop up");
		}

		// click on download template
		WebElement download = webdriver.findElement(By.xpath(Library.getXPath("auditor_download_template")));
		if (download.isDisplayed()) {
			Assert.assertTrue(true, "download template is displayed");
		} else {
			Assert.fail("failed to display download template");
		}
		library.click(webdriver, "auditor_download_template");

		// write data into xlsx
		File file = new File(projectPath + "//src//test//resources//UploadFiles//user-template.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);
		XSSFSheet sheet = xsf.getSheetAt(0);
		Row r = null;
		Cell c = null;
		c = sheet.getRow(1).getCell(0);
		c.setCellValue(yopMail);
		FileOutputStream fos = new FileOutputStream(file);
		xsf.write(fos);
		xsf.close();

		// upload a file
		WebElement uploadFile = webdriver.findElement(By.xpath(Library.getXPath("auditor_upload_file")));
		uploadFile.sendKeys(projectPath + "//src//test//resources//UploadFiles//user-template.xlsx");

		// click on upload button
		WebElement popupUpload = webdriver.findElement(By.xpath(Library.getXPath("auditor_button_upload")));
		if (popupUpload.isDisplayed()) {
			Assert.assertTrue(true, "upload button is dispalyed on pop up");
		} else {
			Assert.fail("failed to display upload button on pop up");
		}
		library.click(webdriver, "auditor_button_upload");

		// click on cancel button
		library.click(webdriver, "auditor_button_cancel");

		// verify the success message
		library.dynamicWaitForVisibilityOfElement(webdriver, "auditor_bulk_upload_message");
		String successMsg = webdriver.findElement(By.xpath(Library.getXPath("auditor_bulk_upload_message"))).getText();
		String expectedMsg = prop.getProperty("Upload_Bulk_User");
		if (successMsg.equals(expectedMsg)) {
			Assert.assertEquals(successMsg, expectedMsg, "Bulk upload user Success message is verified");
		} else {
			Assert.fail("failed to verify Bulk upload user Success message");
		}

	}

}
