
//used to store all locators of login page 
//and required methods with their functionality as names will be declared 
//which can be used or usterstood easily in stepdefinition files


package app.app_page_objects.LoginPage_PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;

public class LoginPage {


public AppiumDriver appdriver;
public Properties prop;
public String driverType;
public FileInputStream fis;
public Properties locators;


	public LoginPage (AppiumDriver appdriver, Properties prop) throws IOException
	{
		this.appdriver = appdriver;	
		this.prop = prop;
		initLocators();
	}

	public void initLocators() throws IOException {
	    this.locators = ConfigProperties();
	}

	public Properties ConfigProperties() throws IOException {	
	    String driverType = prop.getProperty("driverType");
	    
	    if (driverType.equals("android")) {
	        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
	                "//src//test//java//app//app_page_objects//LoginPage_PageObjects//LoginPage AndroidLocators.properties");
	         locators = new Properties();
	        locators.load(fis);
	        return locators;
	    }
	    
	    if (driverType.equals("ios")) {
	        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
	                "//src//test//java//app//app_page_objects//LoginPage_PageObjects//LoginPage IosLocators.properties");
	         locators = new Properties();
	        locators.load(fis);        
	        return locators;
	    }
	    
	    // return an empty Properties object if driverType is not android or ios
	    return locators;
	}

	

public  void enter_useremail() throws IOException, InterruptedException 
	{			
	Thread.sleep(1000);
	String useremail_locator = locators.getProperty("useremail_locator");
	String useremail = prop.getProperty("useremail");
	appdriver.findElement(AppiumBy.xpath(useremail_locator)).sendKeys(useremail);
	
	
	
}
	
public  void enter_userpassword () throws IOException, InterruptedException
	{	
		Thread.sleep(1000);
		String userpassword_locator = locators.getProperty("userpassword_locator");
		String userpassword = prop.getProperty("userpassword");
		
		appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		appdriver.findElement(AppiumBy.xpath(userpassword_locator)).sendKeys(userpassword);
		((HidesKeyboard) appdriver).hideKeyboard();
		}
		
public  void click_on_login () throws InterruptedException
{	
	Thread.sleep(1000);
	String loginbutton_locator = locators.getProperty("loginbutton_locator");
	appdriver.findElement(AppiumBy.className(loginbutton_locator)).click();
Thread.sleep(3000);
}

public void allow_location_permission () throws InterruptedException 
{
	Thread.sleep(2000);
	String allow_location_popup_yes_button_locator = locators.getProperty("allow_location_popup_yes_button_locator");
	if(appdriver.findElement(AppiumBy.className(allow_location_popup_yes_button_locator)).isDisplayed())
	{
		appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		appdriver.findElement(AppiumBy.className(allow_location_popup_yes_button_locator)).click();
	}
	Thread.sleep(5000);
}

public void validate_useremail_box() throws InterruptedException
{

	Thread.sleep(2000);
	String useremail_locator = locators.getProperty("useremail_locator");
	
	Assert.assertEquals(true, appdriver.findElement(AppiumBy.xpath(useremail_locator)).isEnabled());
	
}
public void validate_userpassword_box() throws InterruptedException
{
	Thread.sleep(2000);
	String userpassword_locator = locators.getProperty("userpassword_locator");
	Assert.assertEquals(true, appdriver.findElement(AppiumBy.xpath(userpassword_locator)).isEnabled());
}
public void validate_login_box() throws InterruptedException
{
	Thread.sleep(2000);
	String loginbutton_locator = locators.getProperty("loginbutton_locator");
	Assert.assertEquals(true, appdriver.findElement(AppiumBy.className(loginbutton_locator)).isEnabled());
}
}