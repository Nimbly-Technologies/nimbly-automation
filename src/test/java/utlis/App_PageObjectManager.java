//used to store all the methods which were declared globally
//factory design pattern
//Also used to create objects for the defined methods in pageobject files
package utlis;

import java.io.IOException;
import java.util.Properties;

import app.app_page_objects.LoginPage_PageObjects.LoginPage;
import app.app_page_objects.SettingsPage_PageObjects.LogOutPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class App_PageObjectManager {
public AppiumDriver driver;
public  LoginPage loginpage ;
public LogOutPage logoutpage;
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
	
	
	
}
