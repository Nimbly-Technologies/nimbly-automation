package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class FileRepositoryPage2 {
	private AppiumDriver appdriver;
	private Properties prop;
	private Properties locators;

	public FileRepositoryPage2(AppiumDriver appdriver, Properties prop) throws IOException {
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
					+ "//src//test//java//app//nimbly2//page_objects//FileRepositoryPage2_AndroidLocators.properties";
		} else if (driverType.equals("ios")) {
			filePath = System.getProperty("user.dir")
					+ "//src//test//java//app//nimbly2//page_objects//FileRepositoryPage2_IOSLocators.properties";
		}

		Properties locators = new Properties();
		if (filePath != null) {
			try (FileInputStream fis = new FileInputStream(filePath)) {
				locators.load(fis);
			}
		}

		return locators;
	}

	public void navigateToFileRepo() throws InterruptedException {
		String hamburger = locators.getProperty("tap_on_hamburger");
		String select_file_repo = locators.getProperty("tap_on_file_repo");
		String my_file_txt = locators.getProperty("my_files_txt");

		// tap on hamburger
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(hamburger)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(hamburger)).click();
		} else {
			Assert.fail("Failed to tap on hamburger");
		}

		// select file repo
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(select_file_repo)).isDisplayed()) {
			appdriver.findElement(AppiumBy.xpath(select_file_repo)).click();
		} else {
			Assert.fail("Failed to tap on file repo");
		}

		// verify my files text
		Thread.sleep(4000);
		if (appdriver.findElement(AppiumBy.xpath(my_file_txt)).isDisplayed()) {
		} else {
			Assert.fail("Failed to find my files text on file repo");
		}
	}
	
	public void validateFilesAndFoldersUnderListAndGridView() {
		// Locators
		String folder_name = locators.getProperty("folder_name");
		String file_name = locators.getProperty("file_name");
		String grid_view = locators.getProperty("grid_view");
		String list_view = locators.getProperty("list_view");

		// Expected Values
		String expFolderName = prop.getProperty("Folder_Name");
		String expFileName = prop.getProperty("File_Name");

		// validate folder name
		String actFolderName = appdriver.findElement(AppiumBy.xpath(folder_name)).getText();
		if (actFolderName.equals(expFolderName)) {
			Assert.assertEquals(actFolderName, expFolderName, "Successfully validated folder name under list view");
		} else {
			Assert.fail("Failed to validate folder name under list view");
		}

		// validate file name
		String actFileName = appdriver.findElement(AppiumBy.xpath(file_name)).getText();
		if (actFileName.equals(expFileName)) {
			Assert.assertEquals(actFileName, expFileName, "Successfully validated file name under list view");
		} else {
			Assert.fail("Failed to validate file name under list view");
		}

		// tap on grid view
		waitAndClick(grid_view, "Failed to tap on grid view");

		// validate folder name
		String actFolderNameGrid = appdriver.findElement(AppiumBy.xpath(folder_name)).getText();
		if (actFolderNameGrid.equals(expFolderName)) {
			Assert.assertEquals(actFolderNameGrid, expFolderName, "Successfully validated folder name under grid view");
		} else {
			Assert.fail("Failed to validate folder name under grid view");
		}

		// validate file name
		String actFileNameGrid = appdriver.findElement(AppiumBy.xpath(file_name)).getText();
		if (actFileNameGrid.equals(expFileName)) {
			Assert.assertEquals(actFileNameGrid, expFileName, "Successfully validated file name under grid view");
		} else {
			Assert.fail("Failed to validate file name under grid view");
		}

		// tap on list view
		waitAndClick(list_view, "Failed to list view");
	}

	private void waitAndClick(String xpath, String failureMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (TimeoutException e) {
			Assert.fail(failureMessage);
		}
	}
	
	public void downloadFileAndFolder() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String download_file = locators.getProperty("download_file");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");

		// tap on folder overflow menu
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download folder");

		// wait for some time and navigate back
		Thread.sleep(10000);
		appdriver.navigate().back();

		// tap on file overflow menu
		waitAndClick(tap_file_overflow_menu, "Failed to tap on file overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download file");

		// wait for some time and navigate back
		Thread.sleep(10000);
		appdriver.navigate().back();
	}
	
	public void shareFileAndFolder() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");

		// share folder
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");
		shareFilesAndFolders();

		// share file
		Thread.sleep(8000);
		waitAndClick(tap_file_overflow_menu, "Failed to tap on file overflow menu");
		shareFilesAndFolders();
	}
	
	public void shareFilesAndFolders() throws InterruptedException {
		String tap_on_share_option = locators.getProperty("tap_on_share_option");
		String select_user_drop_down = locators.getProperty("select_user_drop_down");
		String search_user_name = locators.getProperty("search_user_name");
		String select_user = locators.getProperty("select_user");
		String save_button = locators.getProperty("save_button");
		String share_file = locators.getProperty("share_file");

		// expected values
		String username = prop.getProperty("UserName");

		// tap on share option
		waitAndClick(tap_on_share_option, "Failed to tap on share option");

		// tap on select users drop down
		waitAndClick(select_user_drop_down, "Failed to tap on select users drop down");

		// search for user
		Thread.sleep(2000);
		WebElement user = appdriver.findElement(AppiumBy.xpath(search_user_name));
		user.sendKeys(username);

		// tap on select user check box
		waitAndClick(select_user, "Failed to tap on checkbox");

		// tap on save button
		waitAndClick(save_button, "Failed to tap on save button");

		// tap on share button
		waitAndClick(share_file, "Failed to tap on share button");
	}
	
	public void createNewFolderAndUploadNewFile() throws InterruptedException {
		// Locators
		String folder_name = locators.getProperty("folder_name");
		String adhoc_tap_add_issue = locators.getProperty("adhoc_tap_add_issue");
		String tap_on_new_folder = locators.getProperty("tap_on_new_folder");
		String add_new_folder = locators.getProperty("add_new_folder");
		String create_button = locators.getProperty("create_button");
		String file_upload = locators.getProperty("file_upload");
		String upload_from_gallery = locators.getProperty("upload_from_gallery");
		String select_file_from_gallery = locators.getProperty("select_file_from_gallery");
		String add_file_from_gallery = locators.getProperty("add_file_from_gallery");

		// tap on folder name
		Thread.sleep(5000);
		waitAndClick(folder_name, "Failed to tap on folder name");

		// tap on add button
		waitAndClick(adhoc_tap_add_issue, "Failed to tap on add button");

		// tap on new folder
		waitAndClick(tap_on_new_folder, "Failed to tap on new folder");

		// enter folder name
		Thread.sleep(2000);
		WebElement folderName = appdriver.findElement(AppiumBy.xpath(add_new_folder));
		folderName.sendKeys(generateRandomFolderName());

		// tap on create button
		waitAndClick(create_button, "Failed to tap on creat button");

		// upload file
		// tap on add button
		Thread.sleep(5000);
		waitAndClick(adhoc_tap_add_issue, "Failed to tap on add button");

		// tap on file upload
		waitAndClick(file_upload, "Failed to tap on file upload");

		// tap on file upload from gallery
		Thread.sleep(3000);
		waitAndClick(upload_from_gallery, "Failed to tap on gallery");

		// tap on select file
		waitAndClick(select_file_from_gallery, "Failed to select file");

		// tap on add button
		waitAndClick(add_file_from_gallery, "Failed to tap on add button");
		Thread.sleep(2000);
	}

	public String generateRandomFolderName() {
		String folderName = RandomStringUtils.randomAlphanumeric(5);
		return "Lambda" + folderName;
	}
	
	public String generateRandomFileName() {
		String folderName = RandomStringUtils.randomAlphanumeric(5);
		return "File" + folderName;
	}
	
	public void switchToSharedWithMe() throws InterruptedException {
		String shared_with_me = locators.getProperty("shared_with_me");
		Thread.sleep(3000);
		waitAndClick(shared_with_me, "Failed to tab on shared with me");
	}

	public void shareFileAndFolderInListAndGridViewUnderSharedWithMe() throws InterruptedException {
		String grid_view = locators.getProperty("grid_view");

		// share files & folders in list view under shared with me
		Thread.sleep(2000);
		shareFileAndFolder();

		// switch to grid view
		Thread.sleep(8000);
		waitAndClick(grid_view, "Failed to switch grid view");

		// share files & folders in grid view under shared with me
		Thread.sleep(2000);
		shareFileAndFolder();
	}

	public void shareAndDownloadRecentlyUploadedFile() throws InterruptedException {
		String overflow_menu = locators.getProperty("overflow_menu");
		String tap_recent = locators.getProperty("tap_recent");
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String download_file = locators.getProperty("download_file");

		// tap on overflow menu
		Thread.sleep(14000);
		waitAndClick(overflow_menu, "Failed to tap on overflow menu");

		// tap on recent option
		waitAndClick(tap_recent, "Failed to tap on recent option");

		// tap on folder overflow menu
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download folder");

		// wait for some time and navigate back
		Thread.sleep(4000);
		appdriver.navigate().back();

		// share file
		Thread.sleep(4000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on file overflow menu");
		shareFilesAndFolders();
	}
	
	public void renameFileAndFolder() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");
		String clear_folder_file_txt = locators.getProperty("clear_folder_file_txt");
		String enter_new_name = locators.getProperty("enter_new_name");
		String save_name = locators.getProperty("save_name");
		String rename_file_folder_name = locators.getProperty("rename_file_folder_name");

		// change the folder name
		Thread.sleep(6000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");
		waitAndClick(rename_file_folder_name, "Failed to tap on rename folder");
		WebElement clearText = appdriver.findElement(AppiumBy.xpath(clear_folder_file_txt));
		clearText.clear();
		Thread.sleep(2000);
		WebElement folderName = appdriver.findElement(AppiumBy.xpath(enter_new_name));
		folderName.sendKeys(generateRandomFolderName());

		// save new folder name
		waitAndClick(save_name, "Failed to save folder name");

		// change the file name
		Thread.sleep(10000);
		waitAndClick(tap_file_overflow_menu, "Failed to tap on folder overflow menu");
		waitAndClick(rename_file_folder_name, "Failed to tap on rename folder");
		WebElement clearFileText = appdriver.findElement(AppiumBy.xpath(clear_folder_file_txt));
		clearFileText.clear();
		Thread.sleep(2000);
		WebElement fileNameName = appdriver.findElement(AppiumBy.xpath(enter_new_name));
		fileNameName.sendKeys(generateRandomFileName());

		// save new folder name
		waitAndClick(save_name, "Failed to save folder name");

	}

	public void moveFilesAndFoldersToTrash() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");
		String move_to_trash = locators.getProperty("move_to_trash");
		String move_to_trash_pop_up = locators.getProperty("move_to_trash_pop_up");
		
		//move file to trash
		Thread.sleep(10000);
		waitAndClick(tap_file_overflow_menu,"Failed to tap on file overflow menu");
		waitAndClick(move_to_trash,"Failed to tap on move to transh option");
		waitAndClick(move_to_trash_pop_up,"Failed to click on Ok button");
		
		//move folder to trash
		Thread.sleep(10000);
		waitAndClick(tap_folder_overflow_menu,"Failed to tap on folder overflow menu");
		waitAndClick(move_to_trash,"Failed to tap on move to transh option");
		waitAndClick(move_to_trash_pop_up,"Failed to click on Ok button");
		Thread.sleep(5000);
	}
	public void createNewFolderAndFile() throws InterruptedException {
		// Locators
		String adhoc_tap_add_issue = locators.getProperty("adhoc_tap_add_issue");
		String tap_on_new_folder = locators.getProperty("tap_on_new_folder");
		String add_new_folder = locators.getProperty("add_new_folder");
		String create_button = locators.getProperty("create_button");
		String file_upload = locators.getProperty("file_upload");
		String upload_from_gallery = locators.getProperty("upload_from_gallery");
		String select_file_from_gallery = locators.getProperty("select_file_from_gallery");
		String add_file_from_gallery = locators.getProperty("add_file_from_gallery");

		// tap on add button
		waitAndClick(adhoc_tap_add_issue, "Failed to tap on add button");

		// tap on new folder
		waitAndClick(tap_on_new_folder, "Failed to tap on new folder");

		// enter folder name
		Thread.sleep(2000);
		WebElement folderName = appdriver.findElement(AppiumBy.xpath(add_new_folder));
		folderName.sendKeys(generateRandomFolderName());

		// tap on create button
		waitAndClick(create_button, "Failed to tap on creat button");

		// upload file
		// tap on add button
		Thread.sleep(5000);
		waitAndClick(adhoc_tap_add_issue, "Failed to tap on add button");

		// tap on file upload
		waitAndClick(file_upload, "Failed to tap on file upload");

		// tap on file upload from gallery
		Thread.sleep(3000);
		waitAndClick(upload_from_gallery, "Failed to tap on gallery");

		// tap on select file
		waitAndClick(select_file_from_gallery, "Failed to select file");

		// tap on add button
		waitAndClick(add_file_from_gallery, "Failed to tap on add button");
		Thread.sleep(20000);
	}
}