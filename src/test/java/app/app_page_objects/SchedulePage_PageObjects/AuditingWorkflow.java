//used to store all locators of Auditing questions 
//and required methods with their functionality as names will be declared 
//which can be used or usderstood easily in stepdefinition files


package app.app_page_objects.SchedulePage_PageObjects;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;


@SuppressWarnings("deprecation")
public class AuditingWorkflow {


public AppiumDriver driver;
public Properties prop;


	public AuditingWorkflow (AppiumDriver driver, Properties prop)
	{
		this.driver = driver;
		this.prop = prop;

	}

//Single audit locators of Auditing questions
By next_question_locator = AppiumBy.className("android.widget.Button");
By review_report_button_locator = AppiumBy.className("android.widget.Button");
By checkout_button_locator = AppiumBy.className("android.widget.Button");
By checkout_popup_yes_button_locator = AppiumBy.id("android:id/button2");
By got_it_button_locator = AppiumBy.className("android.widget.Button");

By question_1_answer_locator = AppiumBy.xpath("//android.widget.TextView[@text='Yes']");
By question_2_answer_locator = AppiumBy.xpath("//android.widget.TextView[@text='Yes']");
By question_3_answer_priority_popup_locator = AppiumBy.xpath("//android.widget.TextView[@text='Low']");
By question_3_answer_priority_popup_save_locator = AppiumBy.className("android.widget.Button");
By question_3_answer_edit_comment_locator = AppiumBy.className("android.widget.EditText");	
By question_4_answer_edit_comment_locator = AppiumBy.className("android.widget.EditText");	
By question_5_answer_edit_text_locator = AppiumBy.className("android.widget.EditText");	
By question_6_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[11]");
By question_7_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
By question_8_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
By question_9_answer_locator = AppiumBy.xpath("//android.widget.EditText[@text='Type your answer here']");
By tab_on_slider = AppiumBy.xpath("//android.widget.SeekBar[@index='4']");

//team audit locators
By finish_section_button_locator = AppiumBy.className("android.widget.Button");
By upload_report_button_locator = AppiumBy.className("android.widget.Button");
By upload_report_popup_proceed_button_locator = AppiumBy.xpath("(//android.widget.TextView)[3]");
By finalize_report_button_locator = AppiumBy.className("android.widget.Button");
By finalize_report_popup_proceed_button_locator = AppiumBy.xpath("(//android.widget.TextView)[3]");

By teamaudit_section1_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
By teamaudit_section2_locator = AppiumBy.xpath("(//android.widget.TextView)[14]");

public void click_on_next_question() 
{
    driver.findElement(next_question_locator).click();
}
public void click_on_review_report() 
{
    driver.findElement(review_report_button_locator).click();
}
public void click_on_checkout() throws InterruptedException 
{
	Thread.sleep(3000);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(checkout_button_locator).click();
}
public void click_yes_on_checkout_popup() throws InterruptedException 
{
	Thread.sleep(3000);
	driver.findElement(checkout_popup_yes_button_locator).click();
}
public void click_got_it() throws InterruptedException 
{
	Thread.sleep(7000);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.findElement(got_it_button_locator).click();
    Thread.sleep(5000);
}





public void click_on_question_1_answer() 
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(question_1_answer_locator).click();
}
public void click_on_question_2_answer() throws InterruptedException 
{
	Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(question_2_answer_locator).click();	
}
@SuppressWarnings({ "rawtypes" })
public void slide_on_question_3_answer_with_priority() throws InterruptedException 
{
	Thread.sleep(3000);
//	TouchAction tapaction = new TouchAction(driver);
//	tapaction.tap(PointOption.point(708, 812)).perform();
	Thread.sleep(3000);
	driver.findElement(tab_on_slider).click();
	driver.findElement(question_3_answer_priority_popup_locator).click();
	Thread.sleep(3000);
	driver.findElement(question_3_answer_priority_popup_save_locator).click();	
	driver.findElement(question_3_answer_edit_comment_locator).sendKeys("Priority Set to Low");	
}
public void click_on_question_4_answer() throws InterruptedException 
{	
	Thread.sleep(3000);
	driver.findElement(question_4_answer_edit_comment_locator).sendKeys("question 4 answer");	
}
public void click_on_question_5_answer() throws InterruptedException 
{	
	Thread.sleep(3000);
	driver.findElement(question_5_answer_edit_text_locator).sendKeys("123");	
}
public void click_on_question_6_answer() throws InterruptedException 
{	
	Thread.sleep(2000);
	driver.findElement(question_6_answer_locator).click();
}
public void click_on_question_7_answer() throws InterruptedException 
{	
	Thread.sleep(2000);
	driver.findElement(question_7_answer_locator).click();
}
public void click_on_question_8_answer() throws InterruptedException 
{	
	Thread.sleep(2000);
	driver.findElement(question_8_answer_locator).click();
}
public void click_on_question_9_answer() throws InterruptedException 
{	
	Thread.sleep(2000);
	driver.findElement(question_9_answer_locator).sendKeys("1");
	Thread.sleep(2000);
}

//common method which comprises of answering all questions
public void answer_all_the_questions() throws InterruptedException {
	Thread.sleep(3000);
	
	click_on_question_1_answer();
	click_on_next_question();
	click_on_question_2_answer();
	click_on_next_question();
    slide_on_question_3_answer_with_priority();
	click_on_next_question();
	click_on_question_4_answer();
	click_on_next_question();
	click_on_question_5_answer();
	click_on_next_question();
	click_on_question_6_answer();
	click_on_next_question();
	click_on_question_7_answer();
	click_on_next_question();
	click_on_question_8_answer();
	click_on_next_question();
	click_on_question_9_answer();	
}


//team audit methods
public void select_sections_and_answer_all_the_questions() throws InterruptedException
{
	Thread.sleep(3000);
	driver.findElement(teamaudit_section1_locator).click();
	answer_all_the_questions();
	driver.findElement(finish_section_button_locator).click();
	Thread.sleep(3000);
	driver.findElement(teamaudit_section2_locator).click();	
	answer_all_the_questions();
	driver.findElement(finish_section_button_locator).click();
}
public void upload_report_and_accept_the_popup() throws InterruptedException
{
	Thread.sleep(3000);
	driver.findElement(upload_report_button_locator).click();
	Thread.sleep(3000);
	driver.findElement(upload_report_popup_proceed_button_locator).click();
}
public void finalize_report_and_accept_the_popup() throws InterruptedException
{
	Thread.sleep(3000);
	driver.findElement(finalize_report_button_locator).click();
	Thread.sleep(3000);
	driver.findElement(finalize_report_popup_proceed_button_locator).click();
}
}
