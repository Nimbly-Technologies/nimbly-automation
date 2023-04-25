package web.web_stepdefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;
import utlis.Web_Utlis.Library;
import web.web_page_objects.Web_QuestionnairePage;

public class QuestionnairePage_StepDefinitions {
	public UtlisManager utlisManager;
	
	public QuestionnairePage_StepDefinitions(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}
	@Then("^Validate the Questionnaire Page details$")
	public void verifyQuestionnairePagedetails() throws Exception {
		Web_QuestionnairePage questionnairePage = utlisManager.webPageobjectManager.getQuestionnairePage();
		questionnairePage.verifyQuestionnairePagedetails();
		
	}
	@And("^verify bulk upload of questionnaire$")
	public void verifyBulkUploadOfQuestionnaire() throws Exception {
		Web_QuestionnairePage questionnairePage = utlisManager.webPageobjectManager.getQuestionnairePage();
		questionnairePage.bulkUploadOfQuestionnaire();
	}
	

}
