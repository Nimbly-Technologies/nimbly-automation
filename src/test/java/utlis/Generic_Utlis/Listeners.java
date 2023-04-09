package utlis.Generic_Utlis;
//package utlisManager;
//
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import io.cucumber.java.Scenario;
//import io.cucumber.plugin.EventListener;
//import io.cucumber.plugin.event.EventPublisher;
//import io.cucumber.plugin.event.TestCaseFinished;
//import io.cucumber.plugin.event.TestCaseStarted;
//import io.cucumber.plugin.event.TestRunFinished;
//import io.cucumber.plugin.event.TestRunStarted;
//import io.cucumber.plugin.event.TestStepFinished;
//import io.cucumber.plugin.event.TestStepStarted;
//
//public class Listeners implements EventListener {
//	
//	public UtlisManager utlisManager;
//	
//	public Listeners (UtlisManager utlisManager)	//creating a constructor to use utlisManager class methods
//	{
//		this.utlis = utlisManager; //accessing the global variable utlisManager by "this class" instance
//	}
//    // Implement the methods from the EventListener interface
//    
//    @Override
//    public void setEventPublisher(EventPublisher eventPublisher) {
//        // Register the event handlers
//        eventPublisher.registerHandlerFor(TestRunStarted.class, this::beforeTestRun);
//        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::beforeTestCase);
//        eventPublisher.registerHandlerFor(TestStepStarted.class, this::beforeTestStep);
//        eventPublisher.registerHandlerFor(TestStepFinished.class, this::afterTestStep);
//        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::afterTestCase);
//        eventPublisher.registerHandlerFor(TestRunFinished.class, this::afterTestRun);
//    }
//    
//    public void beforeTestRun(TestRunStarted event) {
//        // Code to run before the test run starts
//    }
//    
//    public void beforeTestCase(TestCaseStarted event) {
//        // Code to run before each test case starts
//    }
//    
//    public void beforeTestStep(TestStepStarted event) {
//        // Code to run before each test step starts
//    }
//    
//    public void afterTestStep(TestStepFinished event) {
//        // Code to run after each test step finishes
//    }
//    
//    public void afterTestCase(TestCaseFinished event) throws IOException {
//        // Code to run after each test case finishes
//    	if(event.scenario.isFailed()) {
//    		
//    		File sourcepath = ((TakesScreenshot)utlisManager.androidtestbase.driver).getScreenshotAs(OutputType.FILE);
//    		byte[] filecontent =FileUtils.readFileToByteArray(sourcepath);
//    		scenario.attach(filecontent, "image/png", "image");
//    	}
//    }
//    
//    public void afterTestRun(TestRunFinished event) {
//        // Code to run after the test run finishes
//    }
//}
//							