//used to store all the methods which were declared globally
//factory design pattern
//Also used to create objects for the defined methods in pageobject files
package utlis;

import java.io.IOException;
import java.util.Properties;

import app.app_page_objects.HomePage_PageObjects.HomePage;
import app.app_page_objects.IssueTracker_PageObjects.IssueTrackerPage;
import app.app_page_objects.LoginPage_PageObjects.LoginPage;
import app.app_page_objects.SchedulePage_PageObjects.AuditingWorkflow;
import app.app_page_objects.SchedulePage_PageObjects.SingleAudit_And_TeamAudit;
import app.app_page_objects.SettingsPage_PageObjects.LogOutPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class App_PageObjectManager {
public AppiumDriver driver;
public  LoginPage loginpage ;
public LogOutPage logoutpage;
public HomePage homepage;
public IssueTrackerPage issuetrackerpage;
public AuditingWorkflow auditing;
public SingleAudit_And_TeamAudit schedulepage;
public Properties prop;
public UtlisManager utlisManager;

public  App_PageObjectManager (AppiumDriver driver, Properties prop) //constructor
{

	this.driver = driver;
	this.prop = prop;
}

public LoginPage getloginpage() throws IOException  //creating the object for thus defined loginpage POM file to use them in app.app_stepdefinitions
{
	 loginpage = new LoginPage(driver, prop);
	 return loginpage;
}
public LogOutPage getlogoutpage()  //creating the object for thus defined logoutpage POM file to use them in app.app_stepdefinitions
{
	logoutpage = new LogOutPage(driver, prop);
	 return logoutpage;
}
public HomePage gethomepage()
{
	homepage = new HomePage(driver,prop);
	return homepage;
}
public IssueTrackerPage getissuetrackerpage()
{
	issuetrackerpage = new IssueTrackerPage(driver,prop);
	return issuetrackerpage;
}
public AuditingWorkflow getauditingworkflow()
{
	auditing = new AuditingWorkflow(driver,prop);
	return auditing;
}
public SingleAudit_And_TeamAudit getschedulepage()
{
	schedulepage = new SingleAudit_And_TeamAudit(driver, prop);
	return schedulepage;
}
	
	
	
}
