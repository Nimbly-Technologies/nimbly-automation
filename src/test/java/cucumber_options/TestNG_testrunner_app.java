package cucumber_options;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Nimbly_2_0_Features", glue = "app/nimbly2/stepdefinitions", tags = "@sanity",
//		dryRun = true, 
		monochrome = true, plugin = { "pretty", "html:target/cucumberhtmltestruns/samplerun.html",
				"json:target/cucumberjsontestruns/samplerun.json", "junit:target/cucumberxmltestruns/samplerun.xml",
//				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//				"eventListeners = {MyListener.class}"
//				"rerun:target/failed_scenarios.txt"
		})

public class TestNG_testrunner_app extends AbstractTestNGCucumberTests {

//	for parallel browser testing
	@Override 
	@DataProvider(parallel = false)
	public Object[][]scenarios()
	{
		return super.scenarios();
		
	}

}
