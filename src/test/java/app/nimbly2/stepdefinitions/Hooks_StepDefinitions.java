package app.nimbly2.stepdefinitions;

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
        try {
            System.out.println("Initializing Hooks_StepDefinitions with UtlisManager");
            this.utlisManager = utlisManager;
            this.testbase = utlisManager.genericTestBase; // Initialize the test base
            driver = this.testbase.appdriver; // Initialize the driver
            System.out.println("Driver initialized: " + driver);
        } catch (Exception e) {
            System.err.println("Error initializing Hooks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        try {
            if (driver != null) {
                driver.executeScript("lambda-name=" + scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Error in @Before hook: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (driver != null) {
                // Record test result
                driver.executeScript("lambda-status=" + (scenario.isFailed() ? "failed" : "passed"));
                System.out.println("Cleaning up session: " + driver.getSessionId());
                
                // Quit driver
                driver.quit();
                
                // Stop Appium service if it exists
                if (testbase != null && testbase.service != null && testbase.service.isRunning()) {
                    testbase.service.stop();
                    System.out.println("Appium service stopped successfully");
                }
            }
        } catch (Exception e) {
            System.err.println("Error in @After hook: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure driver reference is cleared
            driver = null;
        }
    }
}