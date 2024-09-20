package app.app_stepdefinitions.SettingsPage_StepDefinitions;

import app.app_page_objects.SettingsPage_PageObjects.LogOutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utlis.UtlisManager;



public class LogOutPage_StepDefinitions {
	
public UtlisManager utlisManager; //making utlisManager as global 	
public LogOutPage logoutpage;


public LogOutPage_StepDefinitions (UtlisManager utlisManage) throws Exception	//creating a constructor to use utlisManager class methods
{
	
    this.utlisManager = utlisManage;
	this.logoutpage = utlisManager.appPageobjectManager.getLogOutPage();
}
@And("navigate to settings page")
public void navigate_to_settings_page() throws InterruptedException {
	logoutpage.navigate_to_settingspage();
}
@Then("click on logout")
public void click_on_logout() throws InterruptedException {
	logoutpage.click_on_logout();
	logoutpage.accept_logout_popup();
}
//@Then("verify if successfully logged out.")
//public void verify_if_successfully_logged_out() {
//
//}
    }

	
	

