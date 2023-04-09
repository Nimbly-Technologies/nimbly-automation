package web.web_stepdefinitions;

import java.io.IOException;

import io.cucumber.java.en.Given;
import utlis.UtlisManager;
import web.web_page_objects.Web_LoginPage;



public class LoginPage_StepDefinitions {
	
public UtlisManager utlisManager; //making utlisManager as global 	


//public AndroidDriver driver; //making driver as global variable to use accross diggernet classes
//public UiAutomator2Options options;
//public App_PageObjectManager pageobjectmanager;

public LoginPage_StepDefinitions (UtlisManager utlisManager)	//creating a constructor to use utlisManager class methods
{
	this.utlisManager = utlisManager; //accessing the global variable utlisManager by "this class" instance
}

@Given("^Navigate to the Nimbly Web Admin$")
public void navigate_to_the_nimbly_web() throws IOException {
	Web_LoginPage webLoginPage = utlisManager.webPageobjectManager.getloginpage();
	webLoginPage.navigate_to_web_url();
}




    }

	
	

