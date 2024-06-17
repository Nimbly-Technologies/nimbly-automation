//used to store all locators of login page 
//and required methods with their functionality as names will be declared 
//which can be used or usterstood easily in stepdefinition files

package app.app_page_objects.SchedulePage_PageObjects;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class SingleAudit_And_TeamAudit {

	public AppiumDriver driver;
	public Properties prop;

	public SingleAudit_And_TeamAudit(AppiumDriver driver, Properties prop) {
		this.driver = driver;
		this.prop = prop;

	}

//locators of schedule page    //android.widget.TextView[@text="Tap to start audit"]

	By schedulepage_locator = AppiumBy.xpath("(//android.widget.TextView)[@text='Schedule']");
	By schedulepage_search_loacator = AppiumBy.className("android.widget.EditText");
	By select_site_from_searchbox_results = AppiumBy.xpath("(//android.widget.TextView)[@text='Go to site lobby ']");
	By schedulePage_questionnaire_searchbox_locator = AppiumBy.className("android.widget.EditText");
	By select_questionnaire_from_searchbox_results = AppiumBy
			.xpath("//android.widget.TextView[@text='Tap to start audit']");
	By adhoc_button_locator = AppiumBy.xpath("(//android.widget.TextView)[6]");
	By start_adhoc_report_button_locator = AppiumBy.className("android.widget.Button");
	By checkin_popup_yes_button_locator = AppiumBy.id("android:id/button2");

	public void navigate_to_schedulepage() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.findElement(homepage_locator).click();
//		Thread.sleep(3000);
		driver.findElement(schedulepage_locator).click();
	}

	public void search_for_adhoc_single_audit_site_in_schedulepage(String Singleauditsitename)
			throws InterruptedException {
		Thread.sleep(3000);
		Singleauditsitename = prop.getProperty("Singleauditsitename");
		driver.findElement(schedulepage_search_loacator).sendKeys(Singleauditsitename);
		Thread.sleep(2000);
//		driver.hideKeyboard();
		driver.findElement(select_site_from_searchbox_results).click();

	}

	public void search_for_adhoc_team_audit_site_in_schedulepage(String Teamauditsitename) throws InterruptedException {
		Thread.sleep(3000);
		Teamauditsitename = prop.getProperty("Teamauditsitename");
		driver.findElement(schedulepage_search_loacator).sendKeys(Teamauditsitename);
//		driver.hideKeyboard();
		Thread.sleep(2000);
		driver.findElement(select_site_from_searchbox_results).click();
	}

	public void search_for_questionnaire_in_schedulepage(String questionnairename) throws InterruptedException {
		Thread.sleep(3000);
		questionnairename = prop.getProperty("questionnairename");
		driver.findElement(schedulePage_questionnaire_searchbox_locator).sendKeys(questionnairename);
//		driver.hideKeyboard();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(select_questionnaire_from_searchbox_results).click();
	}

	public void click_on_adhoc_button_and_click_on_checkin_for_single_audit() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(adhoc_button_locator).click();
		driver.findElement(start_adhoc_report_button_locator).click();
	}

	public void click_on_adhoc_button_and_click_on_checkin_for_team_audit() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(start_adhoc_report_button_locator).click();
		Thread.sleep(2000);
	}

	public void click_yes_on_checkin_popup() {
		driver.findElement(checkin_popup_yes_button_locator).click();
	}

}