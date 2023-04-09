package utlis.Generic_Utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

public  class GenericTestBase {

public Properties prop;
public AppiumDriverLocalService service;
public AppiumDriver driver;
public WebDriver webdriver;
public UiAutomator2Options androidoptions;
public XCUITestOptions iosoptions;




public AppiumDriverLocalService StartAppiumService(String ipAddress,int port)
{	
	service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
	 service.start();
	
return service;
}

public Properties ConfigProperties() throws IOException
{		
	//FileInputStream fis = new FileInputStream("C:\\Users\\hello\\eclipse-workspace\\Nimbly_App\\src\\test\\resources\\config.properties");
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
			"//src//test//resources//config.properties");
	 prop =new Properties();
	prop.load(fis);
	return prop;
}

public AppiumDriver ConfigureDriver() throws MalformedURLException
{
	String driverType = prop.getProperty("driverType");

	
	if (driverType.equals("android")) {
		 String ipAddress = prop.getProperty("ipAddress");
		 String port = prop.getProperty("port");
		 String androidDeviceName  = prop.getProperty("AndroidDeviceName");
		 String androidAppBuildPath  = prop.getProperty("androidAppBuildPath");
		 
		service = StartAppiumService(ipAddress, Integer.parseInt(port));


		androidoptions = new UiAutomator2Options();
		//options.setApp(System.getProperty("user.dir")+"//src//test//java//app_resources_apk//Android_apk//stagingbuild1.apk");
		androidoptions.setDeviceName(androidDeviceName);
		androidoptions.setApp(androidAppBuildPath);
		androidoptions.setCapability("appium:noReset", true);

		//options.setCapability("appium:autoAcceptAlerts", true);
		//options.setCapability("appium:dontStopAppOnReset",true);
		//options.setCapability("appium:forceAppLaunch",false);

		driver= new AndroidDriver(service.getUrl(), androidoptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			return driver;
	}
	else if (driverType.equals("ios")) {
		String ipAddress = prop.getProperty("ipAddress");
		 String port = prop.getProperty("port");
		 String iosDeviceName  = prop.getProperty("iosDeviceName");
		 String iosAppBuildPath  = prop.getProperty("iosAppBuildPath");
		 
		service = StartAppiumService(ipAddress, Integer.parseInt(port));

		iosoptions= new XCUITestOptions();
		iosoptions.setPlatformName("IOS");
		iosoptions.setDeviceName(iosDeviceName);
		iosoptions.setApp(iosAppBuildPath);  
		//options.setApp(System.getProperty(iosAppBuildPath));
		iosoptions.setPlatformVersion("16.4");
		//options.setCapability("app", "https://drive.google.com/file/d/1Am0vJ8m1j9j-ADac5jqPsYj1XZfn4FuC/view?usp=sharing");
		iosoptions.setWdaLaunchTimeout(Duration.ofSeconds(20));
		  
		iosoptions.setCapability("appium:noReset", true); 
		//options.setCapability("appium:autoAcceptAlerts", true);
//			options.setCapability("appium:dontStopAppOnReset",true);
		//options.setCapability("appium:forceAppLaunch",false);

		driver= new IOSDriver(service.getUrl(), iosoptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



			return driver;
	}
	
	else if (driverType.equals("chrome")) {

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
		
		return driver;
	}

	return driver;
}



public ExtentReports ExtentReportsConfig() {
String path = System.getProperty("user.dir")+"\\reports\\index.html";
ExtentSparkReporter spark = new ExtentSparkReporter(path);
ExtentReports extent = new ExtentReports();
extent.attachReporter(spark);
return extent;
}

}
