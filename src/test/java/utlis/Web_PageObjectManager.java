//used to store all the methods which were declared globally
//factory design pattern
//Also used to create objects for the defined methods in pageobject files
package utlis;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import app.app_page_objects.LoginPage_PageObjects.LoginPage;
import app.app_page_objects.SettingsPage_PageObjects.LogOutPage;
import io.appium.java_client.android.AndroidDriver;
import web.web_page_objects.Web_LoginPage;


public class Web_PageObjectManager {

public  Web_LoginPage webLoginPage ;

public Properties prop;
public UtlisManager utlisManager;

public WebDriver webdriver;

public  Web_PageObjectManager (WebDriver webdriver, Properties prop) //constructor
{

	this.webdriver = webdriver;
	this.prop = prop;
}

public Web_LoginPage getloginpage() throws IOException  //creating the object for thus defined loginpage POM file to use them in app.app_stepdefinitions
{
	webLoginPage = new Web_LoginPage(webdriver, prop);
	 return webLoginPage;
}

}

