package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
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
	    long startTime = System.currentTimeMillis();
	    long timeout = 60000; // 60 second timeout
	    WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
	    
	    while (System.currentTimeMillis() - startTime < timeout) {
	        try {
	            // First check for Multiple Login popup
	            if (handleMultipleLoginPopup(wait)) {
	                System.out.println("Multiple Login popup handled");
	                // Give time for any animations to complete
	                Thread.sleep(1000);
	            }
	            
	            // Then check for Update popup
	            if (isInAppUpdatePresent(wait)) {
	                handleInAppUpdatePopup(wait);
	                System.out.println("Update popup handled");
	                // Give time for any animations to complete
	                Thread.sleep(1000);
	            }
	            
	            // If no popups are visible, we're done
	            if (!isAnyPopupVisible()) {
	                System.out.println("No more popups detected");
	                return;
	            }
	            
	        } catch (Exception e) {
	            System.err.println("Error handling popups: " + e.getMessage());
	            // Short wait before retry
	            Thread.sleep(1000);
	        }
	    }
	    
	    System.err.println("Popup handling timed out after 60 seconds");
	}
	
	private boolean isAnyPopupVisible() {
	    try {
	        String multipleLoginXpath = locators.getProperty("multiple_login");
	        String versionPopUpXpath = locators.getProperty("version_pop_up_text");
	        
	        boolean loginPopup = !appdriver.findElements(AppiumBy.xpath(multipleLoginXpath)).isEmpty();
	        boolean updatePopup = !appdriver.findElements(AppiumBy.xpath(versionPopUpXpath)).isEmpty();
	        
	        return loginPopup || updatePopup;
	    } catch (Exception e) {
	        return false;
	    }
	}

	private boolean handleMultipleLoginPopup(WebDriverWait wait) throws InterruptedException {
		try {
			String multipleLoginXpath = locators.getProperty("multiple_login");
			WebElement loginPopup = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(multipleLoginXpath)));

			if (loginPopup.isDisplayed()) {
				System.out.println("Multiple Login Pop-up found. Attempting to handle it.");
				loginPopup.click(); // Click the login pop-up "Yes" button (or other action based on the app)
				Thread.sleep(2000); // Wait for the login pop-up to close (optional, depends on the behavior of the
									// app)
				return true;
			}
		} catch (Exception e) {
			// Handle exceptions gracefully
			System.out.println("No Multiple Login Pop-up detected.");
		}
		return false;
	}

	private void handleInAppUpdatePopup(WebDriverWait wait) throws InterruptedException {
		try {
			String updateButtonXpath = locators.getProperty("update_button");
			String versionPopUpTextXpath = locators.getProperty("version_pop_up_text");
			String okButtonXpath = locators.getProperty("sync_successful");
			String inAppUpdateLaterButtonXpath = locators.getProperty("in_app_update_later_button");

			// Wait until the In-App Update pop-up is visible
			WebElement versionPopup = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(versionPopUpTextXpath)));

			if (versionPopup.isDisplayed()) {
				String popupText = versionPopup.getText();
				if (popupText.equals("New App Version Available!")) {
					System.out.println("Handling In-App Update Pop-up (App Version Available).");
					appdriver.findElement(AppiumBy.xpath(updateButtonXpath)).click();
					appdriver.findElement(AppiumBy.xpath(okButtonXpath)).click();
					appdriver.navigate().back(); // Navigate back to the previous screen or app
				} else if (popupText.equals("New In-App Update Available!")) {
					System.out.println("Handling In-App Update Pop-up (In-App Update Available).");
					appdriver.findElement(AppiumBy.xpath(inAppUpdateLaterButtonXpath)).click(); // Skip the update
				}
			}
		} catch (Exception e) {
			System.out.println("Error handling In-App Update pop-up: " + e.getMessage());
		}
	}

	private boolean isInAppUpdatePresent(WebDriverWait wait) {
		try {
			String appUpdatePopUpXpath = locators.getProperty("version_pop_up_text");
			WebElement versionPopup = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(appUpdatePopUpXpath)));
			return versionPopup.isDisplayed();
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
	    WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(20));
	    try {
	        if (!username.isEmpty() && !password.isEmpty()) {
	            String emailField = locators.getProperty("enetr_email_userid");
	            String passwordField = locators.getProperty("enter_password");
	            String loginButton = locators.getProperty("login_button");
	            String useremail = prop.getProperty(username);
	            String userPassword = prop.getProperty(password);

	            // Wait for and enter email
	            WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(emailField)));
	            emailElement.clear();
	            emailElement.sendKeys(useremail);

	            // Wait for and enter password
	            WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(passwordField)));
	            passwordElement.clear();
	            passwordElement.sendKeys(userPassword);

	            // Wait for and click login button
	            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(loginButton))).click();
	            
	            // Wait for login to complete (adjust XPath based on your app's success indicator)
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.xpath(loginButton)));
	        } else {
	            throw new IllegalArgumentException("Username or password is empty");
	        }
	    } catch (Exception e) {
	        System.err.println("Login failed: " + e.getMessage());
	        throw e;
	    }
	}
	
	public void validateInvalidCredentialsErrorMessage() throws InterruptedException {
		String login_error_message = locators.getProperty("login_error_message");
		
		//Expected Value
		String expErrorMessage = prop.getProperty("Error_Message");
		
		// validate login error message
		Thread.sleep(4000);
		String actErrorMessage = appdriver.findElement(AppiumBy.xpath(login_error_message)).getText();
		if(actErrorMessage.equals(expErrorMessage)) {
			Assert.assertEquals(actErrorMessage, expErrorMessage, "Successfully validated login error message");
		}else {
			Assert.fail("Failed to validate login error message");
		}		
	}
	
	public void validateForgotPasswordFunctionality() throws InterruptedException {
		String forgot_password_label = locators.getProperty("forgot_password_label");
		String forgot_password_email_address = locators.getProperty("forgot_password_email_address");
		String get_reset_link = locators.getProperty("get_reset_link");
		String reset_password_message = locators.getProperty("reset_password_message");
		String ok_button = locators.getProperty("ok_button");

		// Expected value
		String expConfirmationPopup = prop.getProperty("Forgot_Password_Confirmation_Popup");
		String bulkUpdateIssuesUserEmail = prop.getProperty("BulkUpdateIssuesUserEmail");

		// tap on forgot password
		waitAndClick(forgot_password_label, "Failed to tap on forgot password");
		// enter email address
		Thread.sleep(3000);
		WebElement ele = appdriver.findElement(AppiumBy.xpath(forgot_password_email_address));
		ele.sendKeys(bulkUpdateIssuesUserEmail);

		// tap on get reset link
		waitAndClick(get_reset_link, "Failed to tap on get reset link");

		// validate pop up message
		Thread.sleep(5000);
		String actConfirmationPopup = appdriver.findElement(AppiumBy.xpath(reset_password_message)).getText();
		if (actConfirmationPopup.equals(expConfirmationPopup)) {
			Assert.assertEquals(actConfirmationPopup, expConfirmationPopup,
					"Successfully validated Confirmation popup");
		} else {
			Assert.fail("Failed to validate Confirmation popup");
		}

		// tap on ok button
		waitAndClick(ok_button, "Failed to tap on ok button");
	}

	public void validateActivateAccountFunctionality() throws InterruptedException {
		String verify_account = locators.getProperty("verify_account");
		String activate_account = locators.getProperty("activate_account");
		String forgot_password_email_address = locators.getProperty("forgot_password_email_address");
		String activate_account_message = locators.getProperty("activate_account_message");
		String ok_button = locators.getProperty("ok_button");
		
		// Expected values
		String activateAccount = prop.getProperty("Activate_Account");
		String expConfirmationPopup = prop.getProperty("Activate_Account_Confirmation_Popup");

		// tap on activate account
		waitAndClick(activate_account, "Failed to tap on activate account");

		// enter email address
		Thread.sleep(2000);
		WebElement ele = appdriver.findElement(AppiumBy.xpath(forgot_password_email_address));
		ele.sendKeys(activateAccount);

		// tap on get reset link
		waitAndClick(verify_account, "Failed to tap on verify account");

		// validate pop up message
		Thread.sleep(2000);
		String actConfirmationPopup = appdriver.findElement(AppiumBy.xpath(activate_account_message)).getText();
		if (actConfirmationPopup.equals(expConfirmationPopup)) {
			Assert.assertEquals(actConfirmationPopup, expConfirmationPopup,
					"Successfully validated Confirmation popup");
		} else {
			Assert.fail("Failed to validate Confirmation popup");
		}

		// tap on ok button
		waitAndClick(ok_button, "Failed to tap on ok button");
	}

	private void waitAndClick(String xpath, String failureMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (TimeoutException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void validateForgotPasswordErrorMessage() throws InterruptedException {
		String forgot_password_label = locators.getProperty("forgot_password_label");
		String forgot_password_email_address = locators.getProperty("forgot_password_email_address");
		String get_reset_link = locators.getProperty("get_reset_link");
		String forgot_password_error_message = locators.getProperty("forgot_password_error_message");
		String back_to_login = locators.getProperty("back_to_login");

		// Expected value
		String expErrorMessage = prop.getProperty("ForgotPassword_ErrorMessage");
		String invalidEmailAddress = prop.getProperty("InvalidForgotPasswordUserName");

		// tap on forgot password
		waitAndClick(forgot_password_label, "Failed to tap on forgot password");
		// enter email address
		Thread.sleep(3000);
		WebElement ele = appdriver.findElement(AppiumBy.xpath(forgot_password_email_address));
		ele.sendKeys(invalidEmailAddress);

		// tap on get reset link
		waitAndClick(get_reset_link, "Failed to tap on get reset link");

		// validate pop up message
		Thread.sleep(5000);
		String actErrorMessage = appdriver.findElement(AppiumBy.xpath(forgot_password_error_message)).getText();
		if (actErrorMessage.equals(expErrorMessage)) {
			Assert.assertEquals(actErrorMessage, expErrorMessage,
					"Successfully validated forgot password error message");
		} else {
			Assert.fail("Failed to validate forgot password error message");
		}

		// tap on ok button
		waitAndClick(back_to_login, "Failed to tap on back to login page");
		
	}
	
	public void validateActivateAccountErrorMessage() throws InterruptedException {
		String verify_account = locators.getProperty("verify_account");
		String activate_account = locators.getProperty("activate_account");
		String forgot_password_email_address = locators.getProperty("forgot_password_email_address");
		String activate_account_error_message = locators.getProperty("activate_account_error_message");
		String back_to_login = locators.getProperty("back_to_login");
		
		// Expected values
		String invalidEmailAddress = prop.getProperty("InvalidForgotPasswordUserName");
		String expErrorMessage = prop.getProperty("ActivateAccount_ErrorMessage");

		// tap on activate account
		waitAndClick(activate_account, "Failed to tap on activate account");

		// enter email address
		Thread.sleep(2000);
		WebElement ele = appdriver.findElement(AppiumBy.xpath(forgot_password_email_address));
		ele.sendKeys(invalidEmailAddress);

		// tap on get reset link
		waitAndClick(verify_account, "Failed to tap on verify account");

		// validate pop up message
		Thread.sleep(2000);
		String actErrorMessage = appdriver.findElement(AppiumBy.xpath(activate_account_error_message)).getText();
		if (actErrorMessage.equals(expErrorMessage)) {
			Assert.assertEquals(actErrorMessage, expErrorMessage,
					"Successfully validated error message");
		} else {
			Assert.fail("Failed to validate error message");
		}

		// tap on ok button
		waitAndClick(back_to_login, "Failed to tap on back button");
	}
	
	public void verifyLocalizationOnLoginPage() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));  // Wait for up to 10 seconds

	    // Define a map to store language locators and expected header texts
	    Map<String, String[]> languageData = new HashMap<>();
	    languageData.put("Indonesia", new String[] {
	        locators.getProperty("select_indonesia"),
	        locators.getProperty("txt_indonesia_header"),
	        prop.getProperty("Indonesia_Header")
	    });
	    languageData.put("Portugues", new String[] {
	        locators.getProperty("select_portugues"),
	        locators.getProperty("txt_portugues_header"),
	        prop.getProperty("Portugues_Header")
	    });
	    languageData.put("Spanish", new String[] {
	        locators.getProperty("select_spanish"),
	        locators.getProperty("txt_spanish_header"),
	        prop.getProperty("Spanish_Header")
	    });
	    languageData.put("Thai", new String[] {
	        locators.getProperty("select_thai"),
	        locators.getProperty("txt_thai_header"),
	        prop.getProperty("Thai_Header")
	    });
	    languageData.put("Korean", new String[] {
	        locators.getProperty("select_korean"),
	        locators.getProperty("txt_korean_header"),
	        prop.getProperty("Korean_Header")
	    });
	    languageData.put("Khmer", new String[] {
	        locators.getProperty("select_khmer"),
	        locators.getProperty("txt_khmer_header"),
	        prop.getProperty("Khmer_Header")
	    });

	    // Click on the language dropdown and wait for it to be clickable
	    Thread.sleep(5000);
	    String select_language_dropdown = locators.getProperty("select_language_dropdown");
	    waitAndClick(select_language_dropdown,"Failed to tap on select langiage dropdown");

	    // Loop through each language and verify the header text with WebDriver waits
	    for (Map.Entry<String, String[]> entry : languageData.entrySet()) {
	        String language = entry.getKey();
	        String[] locatorsAndHeader = entry.getValue();

	        String languageSelector = locatorsAndHeader[0];
	        String headerLocator = locatorsAndHeader[1];
	        String expectedHeader = locatorsAndHeader[2];

	        // Wait for the language option to be clickable and select the language
	        WebElement languageOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(languageSelector)));
	        languageOption.click();

	        // Wait for the header to be visible and get the actual header text
	        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerLocator)));
	        String actualHeader = headerElement.getText();

	        // Validate the header text
	        Assert.assertEquals(actualHeader, expectedHeader, language + " header text is incorrect");

	        // Click on the dropdown again to reset and wait for it to be clickable
	        waitAndClick(select_language_dropdown,"Failed to tap on select langiage dropdown");
	    }
	}

}