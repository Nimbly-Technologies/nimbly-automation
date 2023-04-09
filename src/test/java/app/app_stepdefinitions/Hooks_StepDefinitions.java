package app.app_stepdefinitions;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utlis.UtlisManager;


public class Hooks_StepDefinitions {
	
public UtlisManager utlisManager; //making utlisManager as global
public ExtentReports extent;
//	
public Hooks_StepDefinitions (UtlisManager utlisManager)	//creating a constructor to use utlisManager class methods
{
	this.utlisManager = utlisManager; //accessing the global variable utlisManager by "this class" instance
}

//@Before (order =1)
//public void start_session_and_driver() throws MalformedURLException 
//{
// utlisManager.testbase.AndroidDriverManager();
//}	
//
//@After
//public void terminate_session_and_driver()
//{
// utlisManager.testbase.service.stop();
// utlisManager.testbase.driver.close();
//}
@After (order =1)
public void AddScreenShot(Scenario scenario) throws IOException {
	if(scenario.isFailed()) {
	
		File sourcepath = ((TakesScreenshot)utlisManager.genericutlis.driver).getScreenshotAs(OutputType.FILE);
		byte[] filecontent =FileUtils.readFileToByteArray(sourcepath);
		scenario.attach(filecontent, "image/png", "image");
	}
}
@Before
public void setUp(Scenario scenario) {
    ExtentCucumberAdapter.addTestStepLog(scenario.getName());
}

//@After
//public void tearDown(Scenario scenario) {
//    if (scenario.isFailed()) {
//        ExtentCucumberAdapter.addTestStepLog(scenario.getName() + " has failed because of wrong priority");
//    }
//}
//}
@After
public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
        Markup markup = MarkupHelper.createLabel(scenario.getName() + 
        		" has failed because of wrong priority Expected is Low Priority but the actual is Medium Priority", 
        		ExtentColor.RED);
         String markupString = markup.getMarkup();
			ExtentCucumberAdapter.addTestStepLog(markupString);
    }
}}
