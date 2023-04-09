package web.web_page_objects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class Web_LoginPage {

	public WebDriver webdriver;
	public Properties prop;
	
	public Web_LoginPage (WebDriver webdriver, Properties prop) throws IOException
	{
		this.webdriver = webdriver;	
		this.prop = prop;
		
	}
	public void navigate_to_web_url(){
	webdriver.get(prop.getProperty("ProductUrl"));
	webdriver.manage().getCookies();
	webdriver.manage().deleteAllCookies();
}
}