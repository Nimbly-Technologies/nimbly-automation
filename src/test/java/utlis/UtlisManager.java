//used to declare all the common methods which are used all over the Nimbly App automation


package utlis;

import java.io.IOException;

import utlis.Generic_Utlis.GenericTestBase;

public class UtlisManager {

	
//public AndroidDriver driver; //making driver as global variable to use accross differnet classes
//public Properties prop;
//public UiAutomator2Options options;
public App_PageObjectManager appPageobjectManager;
public GenericTestBase genericTestBase;
public Web_PageObjectManager webPageobjectManager;




public UtlisManager() throws IOException 
{
	genericTestBase = new GenericTestBase();
	appPageobjectManager = new App_PageObjectManager(genericTestBase.ConfigureAppDriver(), genericTestBase.ConfigProperties());
	webPageobjectManager = new Web_PageObjectManager(genericTestBase.ConfigureWebDriver(), genericTestBase.ConfigProperties());
	
}
}


















