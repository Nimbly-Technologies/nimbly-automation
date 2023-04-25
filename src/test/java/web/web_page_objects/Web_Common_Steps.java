package web.web_page_objects;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utlis.Web_Utlis.AppData;
import utlis.Web_Utlis.Library;

public class Web_Common_Steps {
	public WebDriver webdriver;
	public Properties prop;
	public Library library = new Library();

	public Web_Common_Steps(WebDriver webdriver, Properties prop) {
		this.webdriver = webdriver;
		this.prop = prop;

	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * navigate to web url
	 */
	// ----------------------------------------------------------------------------------------------------
	public void navigate_to_web_url() {
		webdriver.get(prop.getProperty("ProductUrl"));
		webdriver.manage().getCookies();
		webdriver.manage().deleteAllCookies();
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * enter the user name
	 * 
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void enterUsername() throws Exception {
		String username = AppData.getProperty("username");
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(200));
		WebElement user = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("UserName"))));
		library.enterText(webdriver, "UserName", username);
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * Enter user password
	 * 
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void enterPassword() throws Exception {
		String password = AppData.getProperty("password");
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(200));
		WebElement pass = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("Password"))));
		library.enterText(webdriver, "Password", password);
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param object
	 * @param text
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void clickOnLogin(String object, String text) throws Exception {
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(200));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath(object))));
		library.click(webdriver, object);

	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * accept the cookies
	 * 
	 * @param object
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void acceptCookies(String object) throws Exception {
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(200));
		WebElement cookie = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("cookie_page_title"))));
		if (cookie.isDisplayed()) {
			library.dynamicWaitForVisibilityOfElement(webdriver, "cookie_page_title");
			library.click(webdriver, object);
		} else {
			return;
		}

	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * navigate to nimbly audit
	 */
	// ----------------------------------------------------------------------------------------------------
	public void navigateToTheNimblyAudit() {
		webdriver.get(AppData.getProperty("ProductAuditUrl"));
		webdriver.manage().getCookies();
		webdriver.manage().deleteAllCookies();
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * enter auditor name
	 * 
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void enterAuditUserName() throws Exception {
		String username = AppData.getProperty("AuditUsername");
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(20));
		WebElement user = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("Audit_Username"))));
		library.enterText(webdriver, "Audit_Username", username);
		library.click(webdriver, "AuditLogin");
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * enter auditor password
	 * 
	 * @throws Exception
	 */
	// ----------------------------------------------------------------------------------------------------
	public void enterAuditUserPassword() throws Exception {
		String password = AppData.getProperty("AuditPassword");
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(20));
		WebElement pass = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath("Audit_Password"))));
		library.enterText(webdriver, "Audit_Password", password);
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * navigate to yopmail
	 */
	// ----------------------------------------------------------------------------------------------------
	public void NavigateToYopmail() {
		webdriver.get(AppData.getProperty("YopmailUrl"));
		webdriver.manage().deleteAllCookies();
	}
}
