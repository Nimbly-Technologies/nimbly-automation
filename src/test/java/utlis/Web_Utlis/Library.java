package utlis.Web_Utlis;


import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utlis.Generic_Utlis.GenericTestBase;


public class Library {
	/**
	 * The Logger
	 */
	static Logger logger = Logger.getLogger(Library.class.getName());
	/**
	 * The driver
	 */
	public static WebDriverWait wait;

	// ******************************************************************************************************

	/**
	 * Enter text.
	 *
	 * @param object the object name
	 * @param text   the text
	 * @throws Exception the exception
	 */
	// ******************************************************************************************************
	public void enterText(WebDriver driver,String object, String text) throws Exception {
		try {
			logger.info("Tring to enter " + text + " on " + object);
			WebElement element = getElement(driver,object);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			throw e;
		}
	}

//******************************************************************************************************

	/**
	 * getElement : Get elements with parameters
	 *
	 * @param objectName
	 * @param strDynamicParameters
	 * @return
	 */
// ******************************************************************************************************
	public WebElement getElement(WebDriver driver,String objectName, String... strDynamicParameters) {
		try {
			objectName = objectName.trim().toUpperCase();
			String xpath = ObjectReader.getProperty(objectName + "_XPATH");
			String strDP1 = strDynamicParameters.length > 0 ? strDynamicParameters[0] : "";
			String strDP2 = strDynamicParameters.length > 1 ? strDynamicParameters[1] : "";
			String strDP3 = strDynamicParameters.length > 2 ? strDynamicParameters[2] : "";
			if (strDP1.length() > 0)
				xpath = xpath.replace("PAR1", strDP1);
			if (strDP2.length() > 0)
				xpath = xpath.replace("PAR2", strDP2);
			if (strDP3.length() > 0)
				xpath = xpath.replace("PAR3", strDP3);
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			logger.info("Dynamic XPath is : " + xpath);
			return scrollandFindElement(driver,By.xpath(xpath));
		} catch (Exception e) {
			return null;
		}
	}

	// ******************************************************************************************************
	public WebElement scrollandFindElement(WebDriver driver,By object) throws Exception {
		scrollToViewObject(driver,driver.findElement(object));
		return driver.findElement(object);
	}

	// ******************************************************************************************************

	/**
	 * Scroll to view object.
	 *
	 * @param element the element
	 * @throws Exception 
	 * @throws Exception the exception
	 */
	// ******************************************************************************************************
	public void scrollToViewObject(WebDriver driver,WebElement element) throws Exception {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	// ******************************************************************************************************

	/**
	 * Click.
	 *
	 * @param object the object
	 * @throws Exception the exception
	 */
	// ******************************************************************************************************
	public void click(WebDriver driver, String object) throws Exception {
		try {
			logger.info("Trying to click on " + object);
			if (object.contains(">")) {
				String[] objectNames = object.split(">");
				for (int i = 0; i < objectNames.length; i++) {
					getElement(driver,objectNames[i]).click();
				}
			} else {
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement webCtrl = wait.until(ExpectedConditions.elementToBeClickable(getElement(driver,object)));
				webCtrl.click();

			}
		} catch (Exception e) {
		}

	}

	// ******************************************************************************************************
	/**
	 * To generate Any Name with Alpha numeric characters.
	 *
	 * @return : Alphanumeric user name
	 */
	// **************************************************************************************
	public static String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < randomString.length(); k++) {

			char ch = randomString.charAt(k);

			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (n > 0)) {

				r.append(ch);
				n--;
			}
		}

		// return the resultant string
		return r.toString();
	}

	// ******************************************************************************************************

	/**
	 * getXPath : To get
	 *
	 * @param objectName
	 * @param strDynamicParameters
	 * @return
	 */
	// ******************************************************************************************************
	public static String getXPath(String objectName, String... strDynamicParameters) {
		try {
			objectName = objectName.trim().toUpperCase();
			String xpath = ObjectReader.getProperty(objectName + "_XPATH");
			String strDP1 = strDynamicParameters.length > 0 ? strDynamicParameters[0] : "";
			String strDP2 = strDynamicParameters.length > 1 ? strDynamicParameters[1] : "";
			String strDP3 = strDynamicParameters.length > 2 ? strDynamicParameters[2] : "";
			if (strDP1.length() > 0)
				xpath = xpath.replace("PAR1", strDP1);
			if (strDP2.length() > 0)
				xpath = xpath.replace("PAR2", strDP2);
			if (strDP3.length() > 0)
				xpath = xpath.replace("PAR3", strDP3);
			logger.info("Dynamic XPath is :" + xpath);
			return xpath;
		} catch (Exception e) {
			return null;
		}
	}
	// ******************************************************************************************************

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	// ******************************************************************************************************
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		Date time = c.getTime();
		String date = dateFormat.format(time);
		return date;
	}
	// ******************************************************************************************************

	/**
	 * Gets the date.
	 *
	 * @param format the format
	 * @return the date
	 */
	// ******************************************************************************************************
	public static String getDate(String format) {
		return getDate(format, 0);
	}

	// ******************************************************************************************************

	/**
	 * Gets the date.
	 *
	 * @param format the format
	 * @param future the future
	 * @return the date
	 */
	// ******************************************************************************************************
	public static String getDate(String format, int future) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, future);
		Date tomorrow = calendar.getTime();
		return simpleDateFormat.format(tomorrow);
	}

	// ---------------------------------------------------------------------------
	/**
	 * Add Dynamic wait for visibility of element
	 * 
	 * @param object
	 * @throws Exception 
	 */
	// ---------------------------------------------------------------------------
	public void dynamicWaitForVisibilityOfElement(WebDriver driver, String object) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Library.getXPath(object))));

	}

	// -------------------------------------------------------------------------------
	/**
	 * Generate Random AlphaNumeric Name
	 */

	// --------------------------------------------------------------------------------
	public String getAlphaNumaricText() {
		String username = RandomStringUtils.randomAlphanumeric(10);
		return username;
	}

	// -------------------------------------------------------------------------------
	/**
	 * Drag and drop
	 * @throws Exception 
	 */

	// --------------------------------------------------------------------------------
	public void drag(WebDriver driver,WebElement element) throws Exception
	{
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, 40, 0);
		actions.perform();
	}
}
