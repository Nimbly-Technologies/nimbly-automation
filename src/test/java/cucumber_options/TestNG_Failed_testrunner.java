//package cucumber_options;
//
//import org.testng.annotations.DataProvider;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//		features = "@target/failed_scenarios.txt",
//		glue = "app.app_stepdefinitions",
//		//tags = "@buildsetup", 
//		//dryRun = true,
//		monochrome = true,
//		plugin = {"pretty", 
//				"html:target/cucumberhtmltestruns/samplerun.html",
//				"json:target/cucumberjsontestruns/samplerun.json",
//				"junit:target/cucumberxmltestruns/samplerun.xml",
//				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//				//"rerun:target/failed_scenarios.txt"
//				}
//		)
//
//public class TestNG_Failed_testrunner extends AbstractTestNGCucumberTests {
//
//	//for parallel browser testing
//	//@Override
//	//@DataProvider(parallel = true)
//	//public Object[][]scenarios()
//	{
//		//return super.scenarios();
//	}
//	
//}
