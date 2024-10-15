package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class LoginPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public LoginPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//LoginPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//LoginPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void validateEmailSection() throws InterruptedException {
		String email_box = locators.getProperty("enetr_email_userid");
		Assert.assertTrue(appdriver.findElement(AppiumBy.xpath(email_box)).isDisplayed());
	}

	public void enterUseremailORUserId() throws InterruptedException {
		Thread.sleep(2000);
		String emil_or_userid = locators.getProperty("enetr_email_userid");
		String useremail = prop.getProperty("useremail");
		appdriver.findElement(AppiumBy.xpath(emil_or_userid)).sendKeys(useremail);

	}

	public void validatePasswordSection() {
		String password_box = locators.getProperty("enter_password");
		Assert.assertTrue(appdriver.findElement(AppiumBy.xpath(password_box)).isDisplayed());
	}

	public void enterUserPassword() throws InterruptedException {
		Thread.sleep(2000);
		String password_box = locators.getProperty("enter_password");
		String user_password = prop.getProperty("userpassword");
		appdriver.findElement(AppiumBy.xpath(password_box)).sendKeys(user_password);
	}

	public void validateLoginButtonAndClick() throws InterruptedException {
		Thread.sleep(3000);
		String login_button = locators.getProperty("login_button");
		Assert.assertTrue(appdriver.findElement(AppiumBy.xpath(login_button)).isDisplayed());
		appdriver.findElement(AppiumBy.xpath(login_button)).click();
		Thread.sleep(3000);
	}

	public void validateInAppUpdate() throws InterruptedException {
		try {
			String updateButton = locators.getProperty("update_button");
			String okButtonLocator = locators.getProperty("sync_successful");
			String new_app_update = locators.getProperty("new_app_version");
			String in_app_update = locators.getProperty("in_app_update");
			String in_app_update_restart_button = locators.getProperty("in_app_update_restart_button");

			String newAppUpdate = appdriver.findElement(AppiumBy.xpath(new_app_update)).getText();
			String inAppUpdate = appdriver.findElement(AppiumBy.xpath(in_app_update)).getText();
			if (newAppUpdate.contains("New App Version Available!")) {
				Thread.sleep(5000);
				appdriver.findElement(AppiumBy.xpath(updateButton)).click();
				appdriver.findElement(AppiumBy.xpath(okButtonLocator)).click();
				// click on back button
				appdriver.navigate().back();
			} else if (inAppUpdate.contains("New In-App Update Available!")) {
				Thread.sleep(5000);
				appdriver.findElement(AppiumBy.xpath(updateButton)).click();
				appdriver.findElement(AppiumBy.xpath(in_app_update_restart_button)).click();
			}
		} catch (Exception e) {

		}
	}

	public void validateMultipleLogin() throws InterruptedException {
		Thread.sleep(2000);
		try {
			String multiple_login = locators.getProperty("multiple_login");
			if (!appdriver.findElement(AppiumBy.xpath(multiple_login)).getText().isEmpty()) {
				appdriver.findElement(AppiumBy.xpath(multiple_login)).click();
			} else {
				Assert.fail("No element found for locator: " + multiple_login);
			}
		} catch (NoSuchElementException e) {
			// Handle exception or log
		}
	}

	public void logout() throws InterruptedException {
		Thread.sleep(5000);
		String logout_button = locators.getProperty("logout_button");
		String logout_confirmation = locators.getProperty("logout_confirmation");
		// click on logout button
		appdriver.findElement(AppiumBy.xpath(logout_button)).click();
		// click on yes from logout confirmation
		Thread.sleep(3000);
		appdriver.findElement(AppiumBy.xpath(logout_confirmation)).click();

	}

	public void login(String username, String password) throws InterruptedException {
		Thread.sleep(5000);
		if (!username.isEmpty() && !password.isEmpty()) {
			String emil_or_userid = locators.getProperty("enetr_email_userid");
			String useremail = prop.getProperty(username);
			String password_box = locators.getProperty("enter_password");
			String user_password = prop.getProperty(password);
			appdriver.findElement(AppiumBy.xpath(emil_or_userid)).sendKeys(useremail);
			Thread.sleep(2000);
			appdriver.findElement(AppiumBy.xpath(password_box)).sendKeys(user_password);
			Thread.sleep(2000);
			String login_button = locators.getProperty("login_button");
			appdriver.findElement(AppiumBy.xpath(login_button)).click();
		} else {
			Assert.fail("Failed to login to application !!");

		}
	}
}
