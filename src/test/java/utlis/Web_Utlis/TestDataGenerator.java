package utlis.Web_Utlis;

import java.util.*;

public class TestDataGenerator {
	public static Properties testvalues = new Properties();

	public static void testGenerator() {
		testvalues.setProperty("Time Zone", "calcutta");
		testvalues.setProperty("Site Address", "Annamayya circle, Timmappa Colony, Yemmiganur, Andhra Pradesh, India");
		testvalues.setProperty("questionnaireName", Library.getAlphaNumericString(15));
		testvalues.setProperty("Question1", "Check The Stock Availability");
		testvalues.setProperty("Question2", "What are opening stock and closing stock");
		testvalues.setProperty("Question3", "Check the quality of stock");
		testvalues.setProperty("Question4", "Check the Pros of Stock Control");
		testvalues.setProperty("Question5", "Check the Pros of Stock Control");
		testvalues.setProperty("Question6", "Is a batch system usually used in a super market");
		testvalues.setProperty("Question7", "When an item is sold the stock in the database is");
		testvalues.setProperty("Question8",
				"Which strategy to use when consumers ask for a discount in a retail setting?");
		testvalues.setProperty("Question9", "How to improve in-store navigation for customers");
		testvalues.setProperty("Question10", "Attitudes and purchase behavior before and after the pandemic");
		testvalues.setProperty("Auditor_Page_Title", "Add User");
		testvalues.setProperty("Audit_Success_Message", "The password has been set. Your Nimbly account is ready to use.");
		testvalues.setProperty("Upload_Bulk_User", "Successfully upload users bulk");
		testvalues.setProperty("Bulk_Upload_Questionnaire_Page_Title", "Want to add in bulk?");
		testvalues.setProperty("Enter_Questionnaire_Name",  Library.getAlphaNumericString(15));
		testvalues.setProperty("Bulk_Upload_Questionnaire_Message", "Success upload bulk questionnaire");
		testvalues.setProperty("Bulk_Schedule_Upload_Page_Title", "Upload Schedule in bulk");
		testvalues.setProperty("Bulk_Schedule_Upload_Message", "Successfully upload site schedule bulk");
		testvalues.setProperty("Bulk_Edit_Schedule_Page_Title", "Edit Schedule in bulk");
		testvalues.setProperty("Bulk_Edit_Schedule_Message", "Successfully upload site schedule bulk");
		testvalues.setProperty("Audit_Schedule_Page_Title", "This Week");
	}

	public static Properties getTestProperties() {
		return testvalues;
	}
}
