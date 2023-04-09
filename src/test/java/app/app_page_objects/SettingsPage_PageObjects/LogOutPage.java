//used to store all locators of logout page 
//and required methods with their functionality as names will be declared 
//which can be used or understood easily in stepdefinition files


package app.app_page_objects.SettingsPage_PageObjects;
import java.util.Properties;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class LogOutPage {


public AppiumDriver driver;
public Properties prop;


	public LogOutPage (AppiumDriver driver, Properties prop)
	{
		this.driver = driver;	
		this.prop = prop;
	}
			
By settingsmaintablocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Settings']");
By logoutbuttonlocator = AppiumBy.className("android.widget.Button");
By logoutpopupyesbuttonlocator = AppiumBy.id("android:id/button2");

public void navigate_to_settingspage() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(settingsmaintablocator).click();
	Thread.sleep(2000);	
}
public void click_on_logout() throws InterruptedException
{
	driver.findElement(logoutbuttonlocator).click();
	Thread.sleep(2000);
}
public void accept_logout_popup() throws InterruptedException
{
	driver.findElement(logoutpopupyesbuttonlocator).click();
	Thread.sleep(3000);
}
}

