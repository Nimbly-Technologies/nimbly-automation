package utlis.Generic_Utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericTestBase {

	public Properties prop;
	public AppiumDriverLocalService service;
	public AppiumDriver appdriver;
	public WebDriver webdriver;
	public UiAutomator2Options androidoptions;
	public XCUITestOptions iosoptions;

	public AppiumDriverLocalService StartAppiumService(String ipAddress, int port) {
		try {
			service = new AppiumServiceBuilder()
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
					.withIPAddress(ipAddress).usingPort(port).build();
			service.start();

			return service;
		} catch (Exception e) {
			System.err.println("Failed to start Appium service: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Properties ConfigProperties() throws IOException {
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//config.properties")) {
			prop = new Properties();
			prop.load(fis);
			return prop;
		} catch (IOException e) {
			System.err.println("Failed to load config properties: " + e.getMessage());
			throw e;
		}
	}

	public AppiumDriver ConfigureAppDriver() throws IOException {
		ConfigProperties();
		String driverType = prop.getProperty("driverType");
		String userName = "financehellonimbly";
		String accessKey = "lK7dh0JAn1rckQG9cmvfnvX72b4m4RL19wyzfAYN0W9BG5rY8y";

		if (driverType.equals("android")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("w3c", true);
			ltOptions.put("platformName", "android");
			ltOptions.put("deviceName", "Galaxy .*");
			ltOptions.put("platformVersion", "13");
			ltOptions.put("devicelog", true);
			ltOptions.put("visual", true);
			ltOptions.put("network", true);
			ltOptions.put("appProfiling", true);
			ltOptions.put("video", true);
			ltOptions.put("build", "Nimbly_Version 1");
			ltOptions.put("name", "POC Tests");
			ltOptions.put("app", "lt://APP1016038711726840215072206");
			ltOptions.put("isRealMobile", true);
			ltOptions.put("autoGrantPermissions", true);
			ltOptions.put("autoAcceptAlerts", true);
			ltOptions.put("unicodeKeyboard", true);
			ltOptions.put("resetKeyboard", true);
			ltOptions.put("uploadMedia", new String[] { "lt://MEDIA5df0bd19ef40448da7be5c447a97773d",
					"lt://MEDIA20bc18bac2da4f4c9a92ff7a2c92fd4f", "lt://MEDIA882051c6beda4a019421c96c563ea502" });
			// Accessing the uploadMedia array
			String[] uploadMediaArray = (String[]) ltOptions.get("uploadMedia");
			// You can now use the uploadMediaArray as needed

			capabilities.setCapability("LT:Options", ltOptions);

			appdriver = new AppiumDriver(
					new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
					capabilities);
			// local driver set up to execute scripts on emulator
//			String ipAddress = prop.getProperty("ipAddress");
//			String port = prop.getProperty("port");
//			String androidDeviceName = prop.getProperty("AndroidDeviceName");
//			String androidAppBuildPath = prop.getProperty("androidAppBuildPath");
//			service = StartAppiumService(ipAddress, Integer.parseInt(port));
//			androidoptions = new UiAutomator2Options();
//			androidoptions.setDeviceName(androidDeviceName);
//			androidoptions.setApp(androidAppBuildPath);
//			androidoptions.setCapability("appium:noReset", true);
//			androidoptions.setAutoGrantPermissions(true);
//			androidoptions.setAutomationName("UiAutomator2");
//			androidoptions.setNewCommandTimeout(Duration.ofSeconds(300));
//			appdriver = new AndroidDriver(service.getUrl(), androidoptions);
//			appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			return appdriver;
		} else if (driverType.equals("ios")) {
			String ipAddress = prop.getProperty("ipAddress");
			String port = prop.getProperty("port");
			String iosDeviceName = prop.getProperty("iosDeviceName");
			String iosAppBuildPath = prop.getProperty("iosAppBuildPath");

			service = StartAppiumService(ipAddress, Integer.parseInt(port));

			iosoptions = new XCUITestOptions();
			iosoptions.setPlatformName("IOS");
			iosoptions.setDeviceName(iosDeviceName);
			iosoptions.setApp(iosAppBuildPath);
			// options.setApp(System.getProperty(iosAppBuildPath));
			iosoptions.setPlatformVersion("16.4");
			// options.setCapability("app",
			// "https://drive.google.com/file/d/1Am0vJ8m1j9j-ADac5jqPsYj1XZfn4FuC/view?usp=sharing");
			iosoptions.setWdaLaunchTimeout(Duration.ofSeconds(30));

			iosoptions.setCapability("appium:noReset", true);
			// options.setCapability("appium:autoAcceptAlerts", true);
//			options.setCapability("appium:dontStopAppOnReset",true);
			// options.setCapability("appium:forceAppLaunch",false);

			appdriver = new IOSDriver(service.getUrl(), iosoptions);
			appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			return appdriver;
		}
		return appdriver;
	}

	public WebDriver ConfigureWebDriver() throws IOException {
		try {
			ConfigProperties();
			String driverType = prop.getProperty("driverType");

			if (driverType.equals("chrome")) {

				WebDriverManager.chromedriver().forceDownload().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				chromeOptions.addArguments("--disable-gpu");
				System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverPath"));
				webdriver = new ChromeDriver(chromeOptions);
				webdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
				webdriver.manage().window().maximize();
				webdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
				webdriver.manage().getCookies();
				webdriver.manage().deleteAllCookies();

				return webdriver;

			}
			return webdriver;
		} catch (Exception e) {
			System.err.println("Failed to configure WebDriver: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public ExtentReports ExtentReportsConfig() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		return extent;
	}

}
