package app.app_page_objects.LoginPage_PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class LoginPage {

	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public LoginPage(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//app_page_objects//LoginPage_PageObjects//LoginPage AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//app_page_objects//LoginPage_PageObjects//LoginPage IosLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void enter_useremail() throws IOException, InterruptedException {
		Thread.sleep(1000);
		String useremail_locator = locators.getProperty("useremail_locator");
		String useremail = prop.getProperty("useremail");
		appdriver.findElement(AppiumBy.xpath(useremail_locator)).sendKeys(useremail);
	}

	public void enter_userpassword() throws IOException, InterruptedException {
		Thread.sleep(3000);
		String userpassword_locator = locators.getProperty("userpassword_locator");
		String userpassword = prop.getProperty("userpassword");

		appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		appdriver.findElement(AppiumBy.xpath(userpassword_locator)).sendKeys(userpassword);
	}

	public void click_on_login() throws InterruptedException {
		Thread.sleep(3000);
		String loginbutton_locator = locators.getProperty("loginbutton_locator");
		appdriver.findElement(AppiumBy.xpath(loginbutton_locator)).click();
		Thread.sleep(3000);
	}

	public void allow_location_permission() throws InterruptedException {
		Thread.sleep(2000);
		String allow_location_popup_yes_button_locator = locators
				.getProperty("allow_location_popup_yes_button_locator");
		if (appdriver.findElement(AppiumBy.className(allow_location_popup_yes_button_locator)).isDisplayed()) {
			appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			appdriver.findElement(AppiumBy.className(allow_location_popup_yes_button_locator)).click();
		}
		Thread.sleep(5000);
	}

	public void validate_useremail_box() throws InterruptedException {
		Thread.sleep(2000);
		String useremail_locator = locators.getProperty("useremail_locator");
		Assert.assertTrue(appdriver.findElement(AppiumBy.xpath(useremail_locator)).isEnabled());
	}

	public void validate_userpassword_box() throws InterruptedException {
		Thread.sleep(2000);
		String userpassword_locator = locators.getProperty("userpassword_locator");
		Assert.assertTrue(appdriver.findElement(AppiumBy.xpath(userpassword_locator)).isEnabled());
	}

	public void validate_login_box() throws InterruptedException {
		Thread.sleep(2000);
		String loginbutton_locator = locators.getProperty("loginbutton_locator");
		Assert.assertTrue(appdriver.findElement(AppiumBy.className(loginbutton_locator)).isEnabled());
	}

	public void handleNotificationsOnHomePage() throws InterruptedException {
		Thread.sleep(2000);
		clickIfExists("existing_login");
		clickIfExists("allow_location");
		clickIfExists("update_available");
	}

	private void clickIfExists(String locatorKey) {
		try {
			String locator = locators.getProperty(locatorKey);
			if (!appdriver.findElement(AppiumBy.xpath(locator)).getText().isEmpty()) {
				appdriver.findElement(AppiumBy.xpath(locator)).click();
			} else {
				Assert.fail("No element found for locator: " + locatorKey);
			}
		} catch (NoSuchElementException e) {
			// Log if necessary
		}
	}

	public void allowNotifications() throws InterruptedException {
		// Handle pop-up if it appears
		Thread.sleep(2000);
		try {
			String allowNotifications = locators.getProperty("allow_notifications");
			if (!appdriver.findElement(AppiumBy.xpath(allowNotifications)).getText().isEmpty()) {
				appdriver.findElement(AppiumBy.xpath(allowNotifications)).click();
			}
		} catch (NoSuchElementException e) {
			// Handle exception or log
		}
	}
}
