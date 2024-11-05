package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class GalleryPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public GalleryPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//GalleryPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//GalleryPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void navigateToGallery() throws InterruptedException {
		// Locators
		String hamburger = locators.getProperty("tap_on_hamburger");
		String select_gallery = locators.getProperty("tap_on_gallery");
		String download_info_popup = locators.getProperty("download_info_popup");

		// tap on hamburger
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(hamburger)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(hamburger)).click();
		} else {
			Assert.fail("Failed to tap on hamburger");
		}

		// select gallery
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(select_gallery)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_gallery)).click();
		} else {
			Assert.fail("Failed to select gallery");
		}

		// close the popup
		Thread.sleep(2000);
		if (appdriver.findElement(AppiumBy.xpath(download_info_popup)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(download_info_popup)).click();
		} else {
			Assert.fail("Failed to close download info pop up");
		}

	}
}
