//used to store all the methods which were declared globally
//factory design pattern
//Also used to create objects for the defined methods in pageobject files
package utlis;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import web.web_page_objects.SingleAudit;
import web.web_page_objects.WebAuditPage;
import web.web_page_objects.Web_AuditorsPage;
import web.web_page_objects.Web_Common_Steps;
import web.web_page_objects.Web_QuestionnairePage;
import web.web_page_objects.Web_SitesPage;

public class Web_PageObjectManager {

	public SingleAudit singleAudit;
	public Web_Common_Steps webCommonSteps;
	public Web_QuestionnairePage webQuestionnairePage;
	public Web_SitesPage webSitesPage;
	public Web_AuditorsPage webAuditorsPage;
	public WebAuditPage webAuditPage;

	public Properties prop;
	public UtlisManager utlisManager;

	public WebDriver webdriver;

	public Web_PageObjectManager(WebDriver webdriver, Properties prop) // constructor
	{

		this.webdriver = webdriver;
		this.prop = prop;
	}

	public SingleAudit singleAuditWorkflow() {
		singleAudit = new SingleAudit(webdriver, prop);
		return singleAudit;
	}

	public Web_Common_Steps getCommonSteps() {
		webCommonSteps = new Web_Common_Steps(webdriver, prop);
		return webCommonSteps;
	}

	public Web_QuestionnairePage getQuestionnairePage() {
		webQuestionnairePage = new Web_QuestionnairePage(webdriver, prop);
		return webQuestionnairePage;
	}

	public Web_SitesPage getSitesPage() {
		webSitesPage = new Web_SitesPage(webdriver, prop);
		return webSitesPage;
	}

	public Web_AuditorsPage getAuditotsPage() {
		webAuditorsPage = new Web_AuditorsPage(webdriver, prop);
		return webAuditorsPage;
	}

	public WebAuditPage getWebAuditPage() {
		webAuditPage = new WebAuditPage(webdriver, prop);
		return webAuditPage;
	}

}
