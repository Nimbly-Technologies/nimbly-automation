package app.app_stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;
import utlis.App_PageObjectManager;
import utlis.UtlisManager;
import utlis.Generic_Utlis.GenericTestBase;

public class Hooks_StepDefinitions {
	public static AppiumDriver driver;
	public GenericTestBase testbase;
	public UtlisManager utlisManager;
	public App_PageObjectManager pageObjectManager;

	public Hooks_StepDefinitions(UtlisManager utlisManager) {
		System.out.println("Initializing Hooks_StepDefinitions with UtlisManager");
		this.utlisManager = utlisManager;
		this.testbase = utlisManager.genericTestBase; // Initialize the test base
		driver = this.testbase.appdriver; // Initialize the driver
		System.out.println("Driver initialized: " + driver);
	}

	@Before
	public void updateName(Scenario scenario) throws Exception {
		Thread.sleep(30);
		driver.executeScript("lambda-name=" + scenario.getName());
	}

	@After
	public void close_the_browser(Scenario scenario) {
		if (driver != null) {
			driver.executeScript("lambda-status=" + (scenario.isFailed() ? "failed" : "passed"));
			System.out.println(driver.getSessionId());
//			driver.close();
			driver.quit();
		} else {
			System.out.println("Driver is null. Cannot close the browser.");
		}
	}
}