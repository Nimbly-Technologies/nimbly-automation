//used to declare all the common methods which are used all over the Nimbly App automation


package utlis;

import java.io.IOException;

import utlis.Generic_Utlis.GenericTestBase;

public class UtlisManager {

	
//public AndroidDriver driver; //making driver as global variable to use accross differnet classes
//public Properties prop;
//public UiAutomator2Options options;
public App_PageObjectManager appPageobjectManager;
public GenericTestBase genericutlis;
public Web_PageObjectManager webPageobjectManager;




public UtlisManager() throws IOException 
{
	genericutlis = new GenericTestBase();
	appPageobjectManager = new App_PageObjectManager(genericutlis.ConfigureDriver(), genericutlis.ConfigProperties());
	webPageobjectManager = new Web_PageObjectManager(genericutlis.ConfigureDriver(), genericutlis.ConfigProperties());
	
}
}


















