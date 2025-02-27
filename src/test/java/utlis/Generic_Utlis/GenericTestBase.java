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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.appium.java_client.remote.AutomationName;

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
					.withIPAddress(ipAddress)
					.usingPort(port)  // Port is now an integer
					.build();
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

		if (driverType.equals("android")) {
			androidoptions = new UiAutomator2Options();
			
			// Basic Capabilities
			androidoptions.setPlatformName("Android");
			androidoptions.setDeviceName("emulator-5554");
			androidoptions.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
			androidoptions.setApp(System.getProperty("user.dir") + "/src/test/java/app_resources_apk/Android_apk/Stagingbuild.apk");
			
			// Additional Capabilities
			androidoptions.setNoReset(true);
			androidoptions.setFullReset(false);
			androidoptions.setAutoGrantPermissions(true);
			
			// Create Appium service with port conflict handling
			AppiumServiceBuilder builder = new AppiumServiceBuilder()
			    .withIPAddress("127.0.0.1")
			    .usingPort(4723)
			    .withArgument(() -> "--base-path", "/wd/hub")
			    .withArgument(() -> "--allow-insecure", "chromedriver_autodownload");

			// Try to stop any existing service
			if (service != null && service.isRunning()) {
			    System.out.println("Stopping existing Appium service...");
			    service.stop();
			}

			try {
			    service = builder.build();
			    service.start();
			    System.out.println("Appium service started successfully on port 4723");
			} catch (Exception e) {
			    System.err.println("Failed to start Appium service on port 4723: " + e.getMessage());
			    // Try alternate port
			    builder.usingPort(4724);
			    service = builder.build();
			    service.start();
			    System.out.println("Appium service started successfully on alternate port 4724");
			}
			
			// Initialize driver
			appdriver = new AndroidDriver(
				new URL("http://127.0.0.1:4723/wd/hub"), 
				androidoptions
			);
			
			appdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return appdriver;
		} else if (driverType.equals("ios")) {
			String ipAddress = prop.getProperty("ipAddress");
			String portStr = prop.getProperty("port");
			int port = Integer.parseInt(portStr);
			String iosDeviceName = prop.getProperty("iosDeviceName");
			String iosAppBuildPath = prop.getProperty("iosAppBuildPath");
	
			service = StartAppiumService(ipAddress, port);
	
			iosoptions = new XCUITestOptions();
			iosoptions.setPlatformName("IOS");
			iosoptions.setDeviceName(iosDeviceName);
			iosoptions.setApp(iosAppBuildPath);
			iosoptions.setPlatformVersion("16.4");
			iosoptions.setWdaLaunchTimeout(Duration.ofSeconds(30));
	
			iosoptions.setCapability("appium:noReset", true);
	
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
