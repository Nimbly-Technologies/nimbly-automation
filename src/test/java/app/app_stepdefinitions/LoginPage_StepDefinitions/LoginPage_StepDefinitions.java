package app.app_stepdefinitions.LoginPage_StepDefinitions;

import java.io.IOException;

import app.app_page_objects.LoginPage_PageObjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;



public class LoginPage_StepDefinitions {
	
public UtlisManager utlisManager; //making utlisManager as global 	
public LoginPage loginpage;
public String useremail;
public String userpassword;

//public AndroidDriver driver; //making driver as global variable to use accross diggernet classes
//public UiAutomator2Options options;
//public App_PageObjectManager pageobjectmanager;

public LoginPage_StepDefinitions (UtlisManager utlisManager)	//creating a constructor to use utlisManager class methods
{
	this.utlisManager = utlisManager; //accessing the global variable utlisManager by "this class" instance
}


@Then("^validate and enter the useremail$")
public void validate_and_enter_the_useremail() throws IOException, InterruptedException {
//	utlisManager.appiumutlis.driver.resetApp() ;
	LoginPage loginpage = utlisManager.appPageobjectManager.getloginpage();
	loginpage.validate_useremail_box();
	loginpage.enter_useremail();
	

  }

@Then("validate and Enter the userpassword")
public void validate_and_enter_the_userpassword() throws IOException, InterruptedException {

	LoginPage loginpage = utlisManager.appPageobjectManager.getloginpage();
	loginpage.validate_userpassword_box();
	loginpage.enter_userpassword();
}

@And("^validate and click on login button$")
public void validate_and_click_on_login_button() throws InterruptedException, IOException  {
   
	LoginPage loginpage = utlisManager.appPageobjectManager.getloginpage();
	loginpage.click_on_login();

}     

@And("^click on Allow Nimbly to access this device location$")
public void click_on_allow_nimbly_to_access_this_device_location() throws InterruptedException, IOException  {
	
	LoginPage loginpage = utlisManager.appPageobjectManager.getloginpage();
	loginpage.validate_login_box();
	loginpage.allow_location_permission();
}
    }

	
	

