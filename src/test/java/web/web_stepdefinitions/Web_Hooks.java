package web.web_stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utlis.UtlisManager;
import utlis.Generic_Utlis.GenericTestBase;

public class Web_Hooks {
	public static WebDriver driver;
	public GenericTestBase testbase = new GenericTestBase();
	public UtlisManager utlisManager;

	public Web_Hooks(UtlisManager utlisManager) {
		this.utlisManager = utlisManager;
	}

	@Before
	public void browserSetup(Scenario scenario) throws Exception {
				driver = utlisManager.genericTestBase.webdriver;
	}

	@AfterStep
	public void takeScreenshoot(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) utlisManager.genericTestBase.webdriver)
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
		}
	}
	@After
	public void tearDown() {
		utlisManager.genericTestBase.webdriver.close();
		utlisManager.genericTestBase.webdriver.quit();
	}
}
