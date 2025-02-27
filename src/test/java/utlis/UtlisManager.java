//used to declare all the common methods which are used all over the Nimbly App automation

package utlis;

import utlis.Generic_Utlis.GenericTestBase;

public class UtlisManager {

//public AndroidDriver driver; //making driver as global variable to use accross differnet classes
//public Properties prop;
//public UiAutomator2Options options;
	public App_PageObjectManager appPageobjectManager;
	public GenericTestBase genericTestBase;
	public Web_PageObjectManager webPageobjectManager;

	public UtlisManager() throws Exception {
	    System.out.println("Initializing UtlisManager");
	    genericTestBase = new GenericTestBase();
	    // Initialize only app driver for mobile tests and store it in genericTestBase
	    genericTestBase.appdriver = genericTestBase.ConfigureAppDriver();
	    appPageobjectManager = new App_PageObjectManager(
	        genericTestBase.appdriver,
	        genericTestBase.ConfigProperties()
	    );

	}
}
