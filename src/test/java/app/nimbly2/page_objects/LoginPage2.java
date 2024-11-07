package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	public void validatePopups() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));  // Explicit wait
	    boolean isPopupsHandled = false;  // Flag to track if both pop-ups are handled

	    // Retry loop with max retries and checking for both pop-ups
	    int maxRetries = 2;
	    int retryCount = 0;

	    while (!isPopupsHandled && retryCount < maxRetries) {
	        try {
	            // Increment retry count
	            retryCount++;

	            // 1. Check for Multiple Login Pop-up first
	            String multiple_login = locators.getProperty("multiple_login");
	            WebElement loginPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(multiple_login)));

	            if (loginPopup != null && !loginPopup.getText().isEmpty()) {
	                System.out.println("Multiple Login Pop-up found. Attempting to handle it.");
	                loginPopup.click();  // Click the login pop-up "Yes" button (or other action based on the app)
	                Thread.sleep(4000);  // Wait for the login pop-up to close

	                // Check if In-App Update Pop-up appears after the login pop-up
	                if (isInAppUpdatePresent(wait)) {
	                    // If In-App Update pop-up appears while handling login, handle it
	                    handleInAppUpdatePopup(wait);
	                    continue;  // After handling the In-App Update, recheck the login pop-up
	                }

	                // Mark that the login pop-up was handled
	                isPopupsHandled = true;
	                System.out.println("Handled Multiple Login Pop-up.");
	            }

	            // 2. Check for In-App Update Pop-up if the login pop-up isn't present or handled
	            if (!isPopupsHandled && isInAppUpdatePresent(wait)) {
	                handleInAppUpdatePopup(wait);
	                continue;  // After handling the In-App Update pop-up, recheck the login pop-up
	            }

	        } catch (Exception e) {
	            System.out.println("Attempt " + retryCount + " failed: " + e.getMessage());
	            e.printStackTrace();
	        }

	        // Wait for a brief period before retrying
	        if (!isPopupsHandled) {
	            System.out.println("Retrying... Attempt " + retryCount + " of " + maxRetries);
	            Thread.sleep(3000);  // Wait before retrying
	        }
	    }

	    if (isPopupsHandled) {
	        System.out.println("Both pop-ups handled successfully.");
	    } else {
	        System.out.println("Failed to handle pop-ups after " + maxRetries + " attempts.");
	    }
	}

	// Method to handle the In-App Update Pop-up
	private void handleInAppUpdatePopup(WebDriverWait wait) throws InterruptedException {
	    try {
	        String updateButton = locators.getProperty("update_button");
	        String versionPopUpText = locators.getProperty("version_pop_up_text");
	        String okButtonLocator = locators.getProperty("sync_successful");

	        // Wait until the in-app update pop-up is visible
	        WebElement versionPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(versionPopUpText)));
	        if (versionPopup != null && versionPopup.getText().equals("New App Version Available!")) {
	            System.out.println("Handling In-App Update Pop-up (App Version Available).");
	            appdriver.findElement(AppiumBy.xpath(updateButton)).click();
	            appdriver.findElement(AppiumBy.xpath(okButtonLocator)).click();
	            appdriver.navigate().back();  // Go back to the previous screen or app
	        } else if (versionPopup != null && versionPopup.getText().equals("New In-App Update Available!")) {
	            System.out.println("Handling In-App Update Pop-up (In-App Update Available).");
	            String in_app_update_later_button = locators.getProperty("in_app_update_later_button");
	            appdriver.findElement(AppiumBy.xpath(in_app_update_later_button)).click(); // Skip the update
	        }
	    } catch (Exception e) {
	        System.out.println("Error handling In-App Update pop-up: " + e.getMessage());
	    }
	}

	// Method to check if the In-App Update Pop-up is present
	private boolean isInAppUpdatePresent(WebDriverWait wait) {
	    try {
	        String app_update_pop_up = locators.getProperty("version_pop_up_text");
	        WebElement versionPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(app_update_pop_up)));
	        return versionPopup != null;
	    } catch (TimeoutException e) {
	        // If the In-App Update pop-up doesn't appear, return false
	        return false;
	    }
	}


	public void validateInAppUpdate() throws InterruptedException {
		try {
			String updateButton = locators.getProperty("update_button");
			String okButtonLocator = locators.getProperty("sync_successful");
			String in_app_update_later_button = locators.getProperty("in_app_update_later_button");
			String app_update_pop_up = locators.getProperty("version_pop_up_text");
			Thread.sleep(20000);
			String versionUpdatePopup = appdriver.findElement(AppiumBy.xpath(app_update_pop_up)).getText();
			if (versionUpdatePopup.equals("New App Version Available!")) {
				Thread.sleep(5000);
				appdriver.findElement(AppiumBy.xpath(updateButton)).click();
				appdriver.findElement(AppiumBy.xpath(okButtonLocator)).click();
				// click on back button
				appdriver.navigate().back();
			} else if (versionUpdatePopup.equals("New In-App Update Available!")) {
				Thread.sleep(4000);
				appdriver.findElement(AppiumBy.xpath(in_app_update_later_button)).click();
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
				Thread.sleep(4000);
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
