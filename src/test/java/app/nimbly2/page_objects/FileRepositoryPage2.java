package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class FileRepositoryPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public FileRepositoryPage2(AppiumDriver appdriver, Properties prop) throws IOException {
		this.appdriver = appdriver;
		this.prop = prop;
		initLocators();
	}

	private void initLocators() throws IOException {
		this.locators = loadLocators();
	}

	private Properties loadLocators() throws IOException {
		String driverType = prop.getProperty("driverType");
		String filePath = null;

		if (driverType.equals("android")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//FileRepositoryPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//FileRepositoryPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void navigateToFileRepo() throws InterruptedException {
		String hamburger = locators.getProperty("tap_on_hamburger");
		String select_file_repo = locators.getProperty("tap_on_file_repo");
		String my_file_txt = locators.getProperty("my_files_txt");

		// tap on hamburger
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(hamburger)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(hamburger)).click();
		} else {
			Assert.fail("Failed to tap on hamburger");
		}

		// select file repo
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(select_file_repo)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_file_repo)).click();
		} else {
			Assert.fail("Failed to tap on file repo");
		}

		// verify my files text
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(my_file_txt)).isDisplayed()) {
		} else {
			Assert.fail("Failed to find my files text on file repo");
		}
	}

}
