//used to store all locators of login page 
//and required methods with their functionality as names will be declared 
//which can be used or usterstood easily in stepdefinition files


package app.app_page_objects.IssueTracker_PageObjects;

import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;


@SuppressWarnings("deprecation")
public class IssueTrackerPage {


public AppiumDriver driver;
public Properties prop;
//boolean canScrollMore;
String questiontitle;
String IssueID;
String useremail;
public ExtentReports extent;





	public IssueTrackerPage  (AppiumDriver driver, Properties prop)
	{
		this.driver = driver;	
		this.prop = prop;
		
		
		
	}
	//Uielements
	By issuetrackermaintablocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Issues']");
	By backarrow_buttonlocator = AppiumBy.accessibilityId("Navigate up");
	By filter_buttonlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Filter']");
	By sort_buttonlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Sort']");
	
			
	
	By enterquestiontitlelocator = AppiumBy.className("android.widget.EditText");
	By entercategorydropdownlocator = AppiumBy.xpath("(//android.widget.TextView)[6]");
	By categorysearchboxlocator = AppiumBy.className("android.widget.EditText");
	By categorydropdownoptionlocator = AppiumBy.xpath("(//android.widget.TextView)[2]");
	By sitedropdownlocator = AppiumBy.xpath("//android.widget.TextView[@text='Select Site']");
	By selectsitefromlistlocator = AppiumBy.xpath("(//android.widget.TextView)[10]");
	By assigneddepartmentdropdownlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Select the assigned department']");
	By selectassigneddepartmentfromlistlocator = AppiumBy.xpath("(//android.widget.TextView)[4]");
	By reporterdepartmentdropdownlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Select the reporter department']");
	By selectreporterdepartmentfromlistlocator = AppiumBy.xpath("(//android.widget.TextView)[4]");
	By scrolltoend = AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Video: .mp4, .mov, and .avi\"));");
	By assigneduserdropdownlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Select the assigned user']");
	By assignedusersearchboxlocator = AppiumBy.className("android.widget.EditText");
	By selectassigneduserfromlistlocator = AppiumBy.xpath("(//android.widget.TextView)[4]");
	By successfullyaddissuelocator = AppiumBy.className("android.widget.Button");
	By captureissueIDlocator = AppiumBy.xpath("(//android.widget.TextView)[3]");
	
	
	//edit issue workflow locators
	By issuepagesearchlocator = AppiumBy.className("android.widget.EditText");
	By selectissuefromsearchboxresultslocator = AppiumBy.xpath("(//android.view.ViewGroup)[23]");	
	By priorityboxmediumlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Medium']");
	By priorityboxmediumlocator2 = AppiumBy.xpath("(//android.widget.TextView)[@text='LOW']"); //failcaselocator
	By prioritypopuplowprioritylocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Low']");
	By prioritypopupsavelocator = AppiumBy.className("android.widget.Button");
	By statusboxopenlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Open']");
	By statusboxinprogresslocator = AppiumBy.xpath("(//android.widget.TextView)[@text='In Progress']");
	By statusboxinreviewlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='In Review']");
	By statuspopupinprogresslocator = AppiumBy.xpath("(//android.widget.TextView)[@text='In Progress']");
	By statuspopupinreviewlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='In Review']");
	By statuspopupresolvedlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Resolved']");
	By statuspopupupdatelocator = AppiumBy.className("android.widget.Button");
	By resolvedpopupgotitlocator = AppiumBy.className("android.widget.Button");
	By changestatusconfirmationpopuplocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Yes, proceed']");
	By addcommentboxlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Add a comment']");
	By editcommentboxlocator = AppiumBy.className("android.widget.EditText");
	By submitcommentlocator = AppiumBy.xpath("(//android.widget.TextView)[@text='Submit']");
	By addissuebuttonlocator = AppiumBy.xpath("//android.widget.TextView[@text='󰐕']");
	
	
	
	//defining random string
	String s = RandomStringUtils.randomAlphanumeric(6);	
	 
public void navigate_to_issuetrackermaintab () throws InterruptedException 
{
	Thread.sleep(3000);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(issuetrackermaintablocator).click();
	Thread.sleep(3000);
}
@SuppressWarnings({ "rawtypes" })
public void click_on_add_issue() throws InterruptedException
{
	Thread.sleep(3000);
	driver.findElement(addissuebuttonlocator).click();
//	TouchAction tapaction = new TouchAction(driver);
//	tapaction.tap(PointOption.point(944, 1910)).perform();
	Thread.sleep(3000);
}

public void select_questiontitle() throws InterruptedException
{
	Thread.sleep(2000);	
	questiontitle = "AppiumAutomationQuestionTitle"+s;
	driver.findElement(enterquestiontitlelocator).sendKeys(questiontitle);	
	}
public void select_category() throws InterruptedException
{
	Thread.sleep(1000);
	driver.findElement(entercategorydropdownlocator).click();
	Thread.sleep(4000);
	driver.findElement(categorysearchboxlocator).sendKeys("Automation Category 1");
	Thread.sleep(1000);
//	driver.hideKeyboard();
	driver.findElement(categorydropdownoptionlocator).click();
}
public void select_site() throws InterruptedException
{
	Thread.sleep(1000);
	driver.findElement(sitedropdownlocator).click();
	Thread.sleep(1000);
	driver.findElement(selectsitefromlistlocator).click();
	Thread.sleep(1000);
}	
public void select_assigned_department() throws InterruptedException
{
	driver.findElement(assigneddepartmentdropdownlocator).click();
	Thread.sleep(1000);
	driver.findElement(selectassigneddepartmentfromlistlocator).click();
	Thread.sleep(1000);
}	
public void select_reporter_department() throws InterruptedException
{
	Thread.sleep(1000);
	driver.findElement(reporterdepartmentdropdownlocator).click();
	Thread.sleep(1000);
	driver.findElement(selectreporterdepartmentfromlistlocator).click();
	Thread.sleep(1000);
}
public void scroll_down() throws InterruptedException
{
	Thread.sleep(1000);
driver.findElement(scrolltoend);
Thread.sleep(3000);
}
public void select_assigned_user( String useremail ) throws InterruptedException
{
driver.findElement(assigneduserdropdownlocator).click();
Thread.sleep(1000);
useremail = prop.getProperty("useremail");
driver.findElement(assignedusersearchboxlocator).sendKeys(useremail);
Thread.sleep(1000);
//driver.hideKeyboard();
driver.findElement(selectassigneduserfromlistlocator).click();
Thread.sleep(1000);
}
public void add_all_the_required_fields_in_add_issue () throws InterruptedException
{
	Thread.sleep(1000);
	select_questiontitle();
	Thread.sleep(1000);
	select_category();
	Thread.sleep(1000);
	select_site();
	Thread.sleep(1000);
	select_assigned_department();
	Thread.sleep(1000);
	select_reporter_department();
	Thread.sleep(1000);
	scroll_down();
	Thread.sleep(1000);
	select_assigned_user(useremail);	
	Thread.sleep(1000);
}
public void click_on_successfully_add_issue() throws InterruptedException
{
	Thread.sleep(5000);
	driver.findElement(successfullyaddissuelocator).click();
	Thread.sleep(3000);
}


public void change_priority_failcase() throws InterruptedException
{ 
	Thread.sleep(3000);
	driver.findElement(priorityboxmediumlocator2).click(); //trying to fail
	Thread.sleep(1000);
	driver.findElement(prioritypopuplowprioritylocator).click();	
	Thread.sleep(1000);
	driver.findElement(prioritypopupsavelocator).click();
}
public void change_priority() throws InterruptedException
{ 
	Thread.sleep(3000);
	driver.findElement(priorityboxmediumlocator).click(); //trying to fail
	Thread.sleep(1000);
	driver.findElement(prioritypopuplowprioritylocator).click();	
	Thread.sleep(1000);
	driver.findElement(prioritypopupsavelocator).click();
}
public void change_status_from_open_to_inprogress() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(statusboxopenlocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupinprogresslocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupupdatelocator).click();
	Thread.sleep(2000);
	driver.findElement(changestatusconfirmationpopuplocator).click();
	Thread.sleep(3000);
}
public void change_status_from_inprogress_to_inreview() throws InterruptedException
{
	driver.findElement(statusboxinprogresslocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupinreviewlocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupupdatelocator).click();
	Thread.sleep(2000);
	driver.findElement(changestatusconfirmationpopuplocator).click();
	Thread.sleep(3000);
}
public void change_status_from_inreview_to_resolved() throws InterruptedException
{
	driver.findElement(statusboxinreviewlocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupresolvedlocator).click();
	Thread.sleep(2000);
	driver.findElement(statuspopupupdatelocator).click();
	Thread.sleep(2000);
	driver.findElement(changestatusconfirmationpopuplocator).click();
	Thread.sleep(3000);
	driver.findElement(resolvedpopupgotitlocator).click();
	Thread.sleep(3000);	
}

public void add_comment() throws InterruptedException
{
	driver.findElement(addcommentboxlocator).click();
	Thread.sleep(2000);
	driver.findElement(editcommentboxlocator).sendKeys("appium automation edit issue operations successfully performed");
//	driver.hideKeyboard();
	driver.findElement(submitcommentlocator).click();
	driver.findElement(submitcommentlocator).click();
	Thread.sleep(2000);
}
public void click_on_backarrowbutton() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(backarrow_buttonlocator).click();
	Thread.sleep(2000);
}

public void perform_edit_operations () throws InterruptedException
{

	Thread.sleep(2000);
	change_priority();
	Thread.sleep(2000);
	change_status_from_open_to_inprogress();
	Thread.sleep(2000);
	change_status_from_inprogress_to_inreview();
	Thread.sleep(2000);
	change_status_from_inreview_to_resolved();
	Thread.sleep(2000);
	add_comment();
	Thread.sleep(2000);
}
public void perform_edit_operations_fail_case () throws InterruptedException
{

	Thread.sleep(2000);
	change_priority_failcase();
	Thread.sleep(2000);
	change_status_from_open_to_inprogress();
	Thread.sleep(2000);
	change_status_from_inprogress_to_inreview();
	Thread.sleep(2000);
	change_status_from_inreview_to_resolved();
	Thread.sleep(2000);
	add_comment();
	Thread.sleep(2000);
}

public void click_on_filter_functionality() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(filter_buttonlocator).click();
	Thread.sleep(2000);
}
public void click_on_sort_functionality() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(sort_buttonlocator).click();
	Thread.sleep(2000);
}
}

