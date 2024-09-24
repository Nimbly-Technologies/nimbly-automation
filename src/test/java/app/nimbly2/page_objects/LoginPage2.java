package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
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
		Thread.sleep(3000);
		try {
			String update_button = locators.getProperty("update_button");
			if (!appdriver.findElement(AppiumBy.xpath(update_button)).getText().isEmpty()) {
				appdriver.findElement(AppiumBy.xpath(update_button)).click();
			}else {
				Assert.fail("No element found for locator: " + update_button);
			}
		} catch (NoSuchElementException e) {
			// Handle exception or log
		}
		// click on Ok button if data sync is successful
		String ok_button = locators.getProperty("sync_successful");
		appdriver.findElement(AppiumBy.xpath(ok_button)).click();
		// click on back button
		appdriver.navigate().back();
	}

	public void validateMultipleLogin() throws InterruptedException {
		Thread.sleep(2000);
		try {
			String multiple_login = locators.getProperty("multiple_login");
			if (!appdriver.findElement(AppiumBy.xpath(multiple_login)).getText().isEmpty()) {
				appdriver.findElement(AppiumBy.xpath(multiple_login)).click();
			}else {
				Assert.fail("No element found for locator: " + multiple_login);
			}
		} catch (NoSuchElementException e) {
			// Handle exception or log
		}
	}
	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		String logout_button = locators.getProperty("logout_button");
		String logout_confirmation = locators.getProperty("logout_confirmation");
		// click on logout button
		appdriver.findElement(AppiumBy.xpath(logout_button)).click();
		//click on yes from logout confirmation
		appdriver.findElement(AppiumBy.xpath(logout_confirmation)).click();
		
	}
}
