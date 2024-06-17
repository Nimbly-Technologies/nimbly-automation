//used to store all locators of login page 
//and required methods with their functionality as names will be declared 
//which can be used or usterstood easily in stepdefinition files


package app.app_page_objects.HomePage_PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {


public AppiumDriver driver;
public Properties prop;
public HomePage homepage;


	public HomePage (AppiumDriver driver, Properties prop)
	{
		this.driver = driver;	
		PageFactory.initElements((new AppiumFieldDecorator(driver)), this); // to make better showcase of locator objects
		this.prop = prop;
	}
	
	@AndroidFindBy(xpath="(//android.widget.TextView)[@text='Home']")
	private WebElement homepagemaintablocator;
//	By homepagemaintablocator = AppiumBy.xpath("((//android.widget.TextView)[@text='Home'])");
	
	@AndroidFindBy(xpath="((//android.widget.TextView)[@text='THIS WEEK'])")
	private WebElement percentagewidgetlocator;
//	By percentagewidgetlocator = AppiumBy.xpath("((//android.widget.TextView)[@text='THIS WEEK'])");
	
	@AndroidFindBy(xpath="((//android.widget.TextView)[2])")
	private WebElement notificationbelliconlocator;
//	By notificationbelliconlocator = AppiumBy.xpath("((//android.widget.TextView)[2])");
	
	By notificationbell_topheaderlocator = AppiumBy.xpath("((//android.widget.TextView)[@text='Notifications'])");
	By notificationbell_closebuttonlocator = AppiumBy.className("android.widget.TextView");
	By salesiconiconlocator = AppiumBy.className("android.widget.TextView");
	By salespopup_headerlocator = AppiumBy.xpath("((//android.widget.TextView)[@text='SALES'])");
	By downloadbuttonlocator = AppiumBy.xpath("((//android.widget.TextView)[@text='Download'])");
	By downloadbutton_popup_cancelbuttonlocator = AppiumBy.xpath("((//android.widget.TextView)[@text='Cancel'])");
	By sitecard_locator = AppiumBy.xpath("((//android.widget.TextView)[@text='Appium Automation Single Audit Site'])"); 
	
	
public void navigate_to_homepage () throws InterruptedException 
{
	Thread.sleep(1000);
//	driver.findElement(homepagemaintablocator).click();
	homepagemaintablocator.click();
	Thread.sleep(2000);
//	Assert.assertEquals(true, driver.findElement(percentagewidgetlocator).isEnabled());
	Assert.assertEquals(true, percentagewidgetlocator.isEnabled());
	Thread.sleep(1000);
	}
public void click_on_navigation_to_bell_icon () throws InterruptedException 
{
	Thread.sleep(1000);
//	driver.findElement(notificationbelliconlocator).click();
	notificationbelliconlocator.click();
	Thread.sleep(2000);
	Assert.assertEquals(true, driver.findElement(notificationbell_topheaderlocator).isDisplayed());
	Thread.sleep(1000);
}
public void click_on_close_notifications_x_button() throws InterruptedException
{
	driver.findElement(notificationbell_closebuttonlocator).click();
	Thread.sleep(2000);
}
public void click_on_sales_icon() throws InterruptedException
{
	driver.findElement(salesiconiconlocator).click();
	Thread.sleep(2000);
	Assert.assertEquals(true, driver.findElement(salespopup_headerlocator).isDisplayed());
	Thread.sleep(1000);	
}
public void click_on_download_toggle_button() throws InterruptedException
{
	driver.findElement(downloadbuttonlocator).click();
	Thread.sleep(2000);
	Assert.assertEquals(true, driver.findElement(downloadbutton_popup_cancelbuttonlocator).isEnabled());
	Thread.sleep(1000);	
}
public void click_on_cancel_in_download_toggle_button_popup() throws InterruptedException
{
	driver.findElement(downloadbutton_popup_cancelbuttonlocator).click();
	Thread.sleep(2000);
}
public void click_on_site_card_in_homepage() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(sitecard_locator).click();
}
}





