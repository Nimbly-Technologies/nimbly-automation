package web.web_page_objects;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import utlis.Web_Utlis.Library;
import utlis.Web_Utlis.TestDataGenerator;

public class Web_QuestionnairePage {
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();
	public String projectPath = System.getProperty("user.dir");

	public Web_QuestionnairePage(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;

	}

	// --------------------------------------------------------------------------------------------------------
	/**
	 * verify questionnaire page details
	 * 
	 * @throws Exception
	 */
	// --------------------------------------------------------------------------------------------------------
	public void verifyQuestionnairePagedetails() throws Exception {
		String formulaText = "Formula Library";
		String publishedText = "Publis";
		library.dynamicWaitForVisibilityOfElement(webdriver, "add_questionnaire");
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(10));
		WebElement addQuestionnaire = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("add_questionnaire"))));

		// Verify Add Questionnaire Button
		addQuestionnaire.isDisplayed();
		Assert.assertTrue(true, "Add Questionnaire Button Displayed Successfully");
		library.scrollToViewObject(webdriver, addQuestionnaire);

		// verify Create New Options from Drop down
		WebElement createNew = webdriver.findElement(By.xpath(Library.getXPath("txt_create_new")));
		if (createNew.isDisplayed()) {
			Assert.assertTrue(true, "Failed to find Create New Option from Dropdown list");
		}

		// Verify Upload Questions
		WebElement uploadQuestions = webdriver.findElement(By.xpath(Library.getXPath("txt_upload_quetions")));
		if (uploadQuestions.isDisplayed()) {
			Assert.assertTrue(true, "Failed to find Upload Question option from dropdown list");
		}
		// Verify Formula Library
		String formula = webdriver.findElement(By.xpath(Library.getXPath("txt_formula_library"))).getText();
		if (formula.contains(formulaText)) {
			Assert.assertEquals(formulaText, formula, "Failed to verify text : ");

		}

		// Verify Published Table
		String published = webdriver.findElement(By.xpath(Library.getXPath("txt_published"))).getText();
		if (published.contains(publishedText)) {
			Assert.assertEquals(published, publishedText, "Failed to verify text :");
		} else {
			ExtentCucumberAdapter.addTestStepLog("Failed to Verify Public Table on UI");
		}

		// Verify Deleted Table
		WebElement deleted = webdriver.findElement(By.xpath(Library.getXPath("txt_deleted")));
		if (deleted.isDisplayed()) {
			Assert.assertTrue(true, "Failed to find Delete table");
		}

		// Verify Search Field
		WebElement searchField = webdriver.findElement(By.xpath(Library.getXPath("search_field")));
		if (searchField.isEnabled()) {
			Assert.assertTrue(true, "Failed to find Search Field");
		}

		// Verify Pagination
		WebElement pagination = webdriver.findElement(By.xpath(Library.getXPath("pagination_container")));
		if (pagination.isDisplayed()) {
			Assert.assertTrue(true, "Failed to find Pagination");
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	/**
	 * Verify the bulk upload of Questionnaire
	 * 
	 * @throws Exception
	 */
	// -------------------------------------------------------------------------------------------------------
	public void bulkUploadOfQuestionnaire() throws Exception {
		library.dynamicWaitForVisibilityOfElement(webdriver, "add_questionnaire");
		// Verify Add Questionnaire Button
		WebElement addQuestionnaire = webdriver.findElement(By.xpath(Library.getXPath("add_questionnaire")));
		if (addQuestionnaire.isDisplayed()) {
			Assert.assertTrue(true, "Add Questionnaire Button Displayed Successfully");
		} else {
			Assert.fail("failed to display Add Questionnaire button");
		}
		library.scrollToViewObject(webdriver, addQuestionnaire);

		// Verify Upload Questions
		WebElement uploadQuestions = webdriver.findElement(By.xpath(Library.getXPath("txt_upload_quetions")));
		if (uploadQuestions.isDisplayed()) {
			Assert.assertTrue(true, "Failed to find Upload Question option from dropdown list");
		} else {
			Assert.fail("failed to display upload questions");
		}
		library.click(webdriver, "txt_upload_quetions");

		// Verify the page title of bulk upload questionnaire
		TestDataGenerator.testGenerator();
		Properties prop = TestDataGenerator.getTestProperties();
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_upload_questionnaire_page_title");
		String actPageTitle = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_questionnaire_page_title")))
				.getText();
		String expPageTitle = prop.getProperty("Bulk_Upload_Questionnaire_Page_Title");
		if (expPageTitle.equals(actPageTitle)) {
			Assert.assertEquals(actPageTitle, expPageTitle, "bulk upload questinnaire page title is dispayed");
		} else {
			Assert.fail("failed to verify the bulk upload questinnaire page title: " + actPageTitle);
		}

		// enter questionnaire name
		String questionName = prop.getProperty("Enter_Questionnaire_Name");
		WebElement enterName = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_questionnaire_name")));
		if (enterName.isDisplayed()) {
			Assert.assertTrue(true, "enter questionnaire name text field is displayed");
		} else {
			Assert.fail("failed to display enter questionnaire name text field");
		}
		library.enterText(webdriver, "bulk_upload_questionnaire_name", questionName);

		// download template
		WebElement template = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_dropdown")));
		if (template.isDisplayed()) {
			Assert.assertTrue(true, "dropdown is displayed");
		} else {
			Assert.fail("failed to display dropdown");
		}
		library.click(webdriver, "bulk_upload_dropdown");
		library.click(webdriver, "bulk_upload_questions_options");

		// upload a questionnaire template
		WebElement upload = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_questionnaire_select_file")));
		upload.sendKeys(projectPath + "//src//test//resources//UploadFiles/FMCG - Bed Bug Report (English).xlsx");

		// click on upload button
		WebElement uploadButton = webdriver.findElement(By.xpath(Library.getXPath("button_bulk_upload_questionnaire")));
		if (uploadButton.isDisplayed()) {
			Assert.assertTrue(true, "upload button is dispalyed");
		} else {
			Assert.fail("failed to display upload button");
		}
		library.click(webdriver, "button_bulk_upload_questionnaire");

		// verify the success message for bulk upload questionnaire
		library.dynamicWaitForVisibilityOfElement(webdriver, "bulk_upload_questionnaire_message");
		String actMessage = webdriver.findElement(By.xpath(Library.getXPath("bulk_upload_questionnaire_message")))
				.getText();
		String expMesssage = prop.getProperty("Bulk_Upload_Questionnaire_Message");
		if (expMesssage.equals(actMessage)) {
			Assert.assertEquals(actMessage, expMesssage, "successfully verified questionnaire creation message");
		} else {
			Assert.fail("failed to verify questionnaire creation message");
		}

	}

}
