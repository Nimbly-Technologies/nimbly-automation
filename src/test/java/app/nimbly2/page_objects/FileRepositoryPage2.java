package app.nimbly2.page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
		Thread.sleep(10000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download folder");

		// wait for some time and navigate back
		Thread.sleep(10000);
		navigateBackToFileRepo();

		// tap on file overflow menu
		waitAndClick(tap_file_overflow_menu, "Failed to tap on file overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download file");

		// wait for some time and navigate back
		Thread.sleep(10000);
		navigateBackToFileRepo();
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
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		String tap_recent = locators.getProperty("tap_recent");
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String download_file = locators.getProperty("download_file");

		// tap on overflow menu
		Thread.sleep(14000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on overflow menu");

		// tap on recent option
		waitAndClick(tap_recent, "Failed to tap on recent option");

		// tap on folder overflow menu
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");

		// tap on download folder
		waitAndClick(download_file, "Failed to download folder");

		// wait for some time and navigate back
		Thread.sleep(4000);
		navigateBackToFileRepo();

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
	
	public void validateFileTypeFilter(String videoFile, String docFile, String imageFile) throws InterruptedException {
	    Map<String, String> fileTypes = new HashMap<>();
	    fileTypes.put("image", locators.getProperty("select_file_type_image"));
	    fileTypes.put("video", locators.getProperty("select_file_type_video"));
	    fileTypes.put("document", locators.getProperty("select_file_type_document"));

	    Map<String, String> expectedFileNames = new HashMap<>();
	    expectedFileNames.put("image", prop.getProperty(imageFile));
	    expectedFileNames.put("video", prop.getProperty(videoFile));
	    expectedFileNames.put("document", prop.getProperty(docFile));

	    Map<String, String> fileLocators = new HashMap<>();
	    fileLocators.put("image", locators.getProperty("image_file_type"));
	    fileLocators.put("video", locators.getProperty("video_file_type"));
	    fileLocators.put("document", locators.getProperty("doc_file_type"));

	    for (String fileType : fileTypes.keySet()) {
	        applyFilter(fileTypes.get(fileType));
	        validateFileType(fileLocators.get(fileType), expectedFileNames.get(fileType));
	        resetFilter();
	    }
	}

	private void applyFilter(String fileTypeLocator) throws InterruptedException {
	    waitAndClick(locators.getProperty("file_repo_filter"), "Failed to tap on filter icon");
	    waitAndClick(locators.getProperty("filter_by_file_type"), "Failed to tap on file type");
	    waitAndClick(fileTypeLocator, "Failed to select file type");
	    waitAndClick(locators.getProperty("save_button"), "Failed to tap on save button");
	    waitAndClick(locators.getProperty("apply_button"), "Failed to tap on apply button");
	    Thread.sleep(5000);
	}

	private void validateFileType(String fileLocator, String expectedFileName) {
	    String actualFileName = appdriver.findElement(AppiumBy.xpath(fileLocator)).getText();
	    if (!actualFileName.equals(expectedFileName)) {
	        Assert.fail("Failed to validate file type: " + expectedFileName);
	    }
	}

	private void resetFilter() {
	    waitAndClick(locators.getProperty("file_repo_filter"), "Failed to tap on filter icon");
	    waitAndClick(locators.getProperty("reset_button"), "Failed to reset filter");
	}
	
	public void validateFileTypeFilterForFolder(String videoFile, String docFile, String imageFile) throws InterruptedException {
		String folder_name = locators.getProperty("folder_name");
		
		//tap on folder
		waitAndClick(folder_name,"Failed to tap on folder name");
		validateFileTypeFilter(videoFile,docFile,imageFile);
	}
	
	public void shareFolderUnderSharedWithMe() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String shared_with_me = locators.getProperty("shared_with_me");
		
		waitAndClick(shared_with_me,"Failed to tap on Share with me tab");
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on file overflow menu");
		shareFilesAndFolders();
		 Thread.sleep(8000);
		
	}
	
	public void searchFileOrFolder(String locator, String searchText) {
		WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(locator)));
		searchBox.sendKeys(searchText);
	}
	
	public void verifySortFunctionalityUnderSharedWithMe() throws InterruptedException{
		String file_repo_filter = locators.getProperty("file_repo_filter");
		String sort_by_name = locators.getProperty("sort_by_name");
		String sort_by_last_modified = locators.getProperty("sort_by_last_modified");
		String sort_by_last_modified_by_me = locators.getProperty("sort_by_last_modified_by_me");
		String sort_by_last_opened_by_me = locators.getProperty("sort_by_last_opened_by_me");
		String video_file_type = locators.getProperty("video_file_type");
		String image_file_type = locators.getProperty("image_file_type");
		String doc_file_type = locators.getProperty("doc_file_type");
		String folder_name = locators.getProperty("folder_name");
		String apply_button = locators.getProperty("apply_button");
		
		
		//expected values
		String expVideoName = prop.getProperty("Video_Name");
		String expDocName = prop.getProperty("Doc_Name");
		String expImageName = prop.getProperty("Image_Name");
		String expFolderName = prop.getProperty("Shared_Folder_Name");
		
		//sort by name
		waitAndClick(file_repo_filter,"Failed to tap on file repo filter");
		waitAndClick(sort_by_name,"Failed to tap on sort by name");
		waitAndClick(apply_button,"Failed to tap on apply button");
		Thread.sleep(3000);
		validateFileType(folder_name,expFolderName);
		
		//sort by last modified
		waitAndClick(file_repo_filter,"Failed to tap on file repo filter");
		waitAndClick(sort_by_last_modified,"Failed to tap on sort by last modified");
		waitAndClick(apply_button,"Failed to tap on apply button");
		Thread.sleep(5000);
		validateFileType(video_file_type,expVideoName);
		
		//sort by last modified by me
		waitAndClick(file_repo_filter,"Failed to tap on file repo filter");
		waitAndClick(sort_by_last_modified_by_me,"Failed to tap on sort by last modified by me");
		waitAndClick(apply_button,"Failed to tap on apply button");
		Thread.sleep(3000);
		validateFileType(video_file_type,expVideoName);
		
		// sort by last opened by me
		waitAndClick(file_repo_filter,"Failed to tap on file repo filter");
		waitAndClick(sort_by_name,"Failed to tap on sort by name");
		waitAndClick(apply_button,"Failed to tap on apply button");
		Thread.sleep(3000);
		validateFileType(folder_name,expFolderName);
		
		
	}
	
	public void renameFileInRecentTab() throws InterruptedException {
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		String recent_option = locators.getProperty("recent_option");
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String clear_folder_file_txt = locators.getProperty("clear_folder_file_txt");
		String enter_new_name = locators.getProperty("enter_new_name");
		String save_name = locators.getProperty("save_name");
		String rename_file_folder_name = locators.getProperty("rename_file_folder_name");
		String adhoc_tap_add_issue = locators.getProperty("adhoc_tap_add_issue");
		String file_upload = locators.getProperty("file_upload");
		String upload_from_gallery = locators.getProperty("upload_from_gallery");
		String select_file_from_gallery = locators.getProperty("select_file_from_gallery");
		String add_file_from_gallery = locators.getProperty("add_file_from_gallery");

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

		// navigate to recent tab
		Thread.sleep(6000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(recent_option, "Failed to tap on Recent Option from pop up");

		// rename file name under recent tab
		Thread.sleep(6000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on file overflow menu");
		waitAndClick(rename_file_folder_name, "Failed to tap on rename folder");
		WebElement clearText = appdriver.findElement(AppiumBy.xpath(clear_folder_file_txt));
		clearText.clear();
		Thread.sleep(2000);
		WebElement folderName = appdriver.findElement(AppiumBy.xpath(enter_new_name));
		folderName.sendKeys(generateRandomFolderName());

		// save new file name
		waitAndClick(save_name, "Failed to save folder name");
	}
	
	public void moveFileToTrashInRecentTab() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String move_to_trash = locators.getProperty("move_to_trash");
		String move_to_trash_pop_up = locators.getProperty("move_to_trash_pop_up");
		
		// move file to trash in recent tab
		Thread.sleep(10000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");
		waitAndClick(move_to_trash, "Failed to tap on move to transh option");
		waitAndClick(move_to_trash_pop_up, "Failed to click on Ok button");
		Thread.sleep(5000);
	}
	
	public void navigateToStarredTab() throws InterruptedException {
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		String starred_option = locators.getProperty("starred_option");
		
		Thread.sleep(5000);
		waitAndClick(file_repo_three_dots_menu,"Failed to tap on three dots menu");
		waitAndClick(starred_option,"Failed to select Starred Option from pop up");
	}
	
	public void addFileAndFolderToStarred() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");
		String add_to_starred = locators.getProperty("add_to_starred");
		String star_ok_button = locators.getProperty("star_ok_button");
		
		//add folder to starred
		Thread.sleep(10000);
		waitAndClick(tap_folder_overflow_menu,"Failed to tap on folder overflow menu");
		waitAndClick(add_to_starred,"Failed to add folder to starred");
		waitAndClick(star_ok_button,"Failed to tap on Okay button");
		
		//add folder to starred
		Thread.sleep(10000);
		waitAndClick(tap_file_overflow_menu,"Failed to tap on file overflow menu");
		waitAndClick(add_to_starred,"Failed to add file to starred");
		waitAndClick(star_ok_button,"Failed to tap on Okay button");
	}
	
	public void restoreFileAndFolderFromTrash() throws InterruptedException {
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		String trash_option = locators.getProperty("trash_option");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");
		String restore_files_folders = locators.getProperty("restore_files_folders");
		String restore_ok_button = locators.getProperty("restore_ok_button");
		String doc_file_type = locators.getProperty("doc_file_type");
		String trash_folder_name = locators.getProperty("trash_folder_name");
		String recent_option = locators.getProperty("recent_option");

		// expected values
		String expDocName = prop.getProperty("Trash_Doc_Name");
		String expFolderName = prop.getProperty("Trash_Folder_Name");

		Thread.sleep(5000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(trash_option, "Failed to select Trash Option from pop up");

		// validate file name
		String actDocName = appdriver.findElement(AppiumBy.xpath(doc_file_type)).getText();
		if (!actDocName.equals(expDocName)) {
			Assert.fail("Failed to validate doc names in Trash folder");
		}

		// validate folder name
		String actFolderName = appdriver.findElement(AppiumBy.xpath(trash_folder_name)).getText();
		if (!actFolderName.equals(expFolderName)) {
			Assert.fail("Failed to validate Folder name in Trash folder");
		}

		// restore file
		waitAndClick(tap_file_overflow_menu, "Failed to tap on overflow menu");
		waitAndClick(restore_files_folders, "Failed to restore file");
		waitAndClick(restore_ok_button, "Failed to click on Ok button ");

		// refresh the page
		Thread.sleep(5000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(recent_option,"Failed to tap on recent option");
		Thread.sleep(5000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(trash_option, "Failed to select Trash Option from pop up");

		// restore folder
		Thread.sleep(9000);
		waitAndClick(tap_file_overflow_menu, "Failed to tap on overflow menu");
		waitAndClick(restore_files_folders, "Failed to restore folder");
		waitAndClick(restore_ok_button, "Failed to click on Ok button ");

	}
	
	private void navigateBackToFileRepo() throws InterruptedException {
	    int maxAttempts = 4; // Max attempts to press back
	    int attempts = 0;
	    
	    while (attempts < maxAttempts) {
	        try {
	            // Press back button
	        	Thread.sleep(3000);
	            appdriver.navigate().back();

	            // Wait for File Repository tab to be visible
	            if (isFileRepoTabDisplayed()) {
	                return; // Exit once we reach the File Repo tab
	            }

	            // Increment attempt count
	            attempts++;

	        } catch (NoSuchElementException | TimeoutException e) {
	            
	        }
	    }
	}

	private boolean isFileRepoTabDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(appdriver, Duration.ofSeconds(10));
	        WebElement fileRepoTab = wait.until(ExpectedConditions
	                .visibilityOfElementLocated(AppiumBy.xpath("//*[@text='File Repository']")));
	        return fileRepoTab.isDisplayed();
	    } catch (TimeoutException e) {
	        return false;
	    }
	}
	
	public void createNewFolder() throws InterruptedException {
		// Locators
		String adhoc_tap_add_issue = locators.getProperty("adhoc_tap_add_issue");
		String tap_on_new_folder = locators.getProperty("tap_on_new_folder");
		String add_new_folder = locators.getProperty("add_new_folder");
		String create_button = locators.getProperty("create_button");

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
	}
	
	public void moveFolderToTrash() throws InterruptedException {
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");
		String move_to_trash = locators.getProperty("move_to_trash");
		String move_to_trash_pop_up = locators.getProperty("move_to_trash_pop_up");
		
		// move file to trash in recent tab
		Thread.sleep(10000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");
		waitAndClick(move_to_trash, "Failed to tap on move to transh option");
		waitAndClick(move_to_trash_pop_up, "Failed to click on Ok button");
		Thread.sleep(5000);
	}
	
	public void deleteFolderPermanentlyFromTrash() throws InterruptedException {
		String trash_option = locators.getProperty("trash_option");
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		String tap_file_overflow_menu = locators.getProperty("tap_file_overflow_menu");
		String okay_button_delete_forever = locators.getProperty("okay_button_delete_forever");
		String delete_folder_permanently = locators.getProperty("delete_folder_permanently");
		
		// delete folder permanently from trash
		Thread.sleep(10000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(trash_option, "Failed to select Trash Option from pop up");
		waitAndClick(tap_file_overflow_menu,"Failed to tap on file repo overflow menu");
		waitAndClick(delete_folder_permanently,"Failed to click on delete folder option");
		waitAndClick(okay_button_delete_forever,"Failed to tap on ok button from delete forever pop up");
	}
	
	public void navigatesToTrash() throws InterruptedException {
		String trash_option = locators.getProperty("trash_option");
		String file_repo_three_dots_menu = locators.getProperty("file_repo_three_dots_menu");
		
		// delete folder permanently from trash
		Thread.sleep(10000);
		waitAndClick(file_repo_three_dots_menu, "Failed to tap on three dots menu");
		waitAndClick(trash_option, "Failed to select Trash Option from pop up");
	}
	
	public void verifySearchFunctionality() throws InterruptedException {
		String search_folder_file_name = locators.getProperty("search_folder_file_name");
		String folder_name = locators.getProperty("folder_name");
		
		//expected values
		String expFolderName = prop.getProperty("Search_Folder_Name");
		
		//search for folder
		Thread.sleep(3000);
		WebElement folder = appdriver.findElement(AppiumBy.xpath(search_folder_file_name));
		folder.sendKeys(expFolderName);
		
		// validate folder name
		Thread.sleep(3000);
		String actFolderName = appdriver.findElement(AppiumBy.xpath(folder_name)).getText();
		if(actFolderName.contains(expFolderName)) {
			Assert.assertEquals(actFolderName, expFolderName, "Folder name successfully found in search results");
		}else {
			Assert.fail("Failed to validate folder name");
		}
		
	}
	
	public void shareFilesAndFoldersToMultipleUsers() throws InterruptedException {
		String shareOption = locators.getProperty("tap_on_share_option");
		String userDropdown = locators.getProperty("select_user_drop_down");
		String userSearchField = locators.getProperty("search_user_name");
		String userCheckbox = locators.getProperty("select_user");
		String saveButton = locators.getProperty("save_button");
		String shareButton = locators.getProperty("share_file");
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");

		// Expected values (Assuming multiple usernames are comma-separated)
		String[] usernames = prop.getProperty("UserNames").split(",");
		
		// tap on folder overflow menu
		waitAndClick(tap_folder_overflow_menu,"Failed to tap on folder overflow menu");

		// Open the share options
		waitAndClick(shareOption, "Failed to tap on share option");

		// Loop through the list of usernames and select each user
		for (String username : usernames) {
			Thread.sleep(4000); // Add a short delay to avoid issues in UI interaction
			
			// Open user selection dropdown
			Thread.sleep(3000);
			waitAndClick(userDropdown, "Failed to tap on select users drop down");

			// Search for the user
			Thread.sleep(3000);
			WebElement userSearch = appdriver.findElement(AppiumBy.xpath(userSearchField));
			userSearch.sendKeys(username); // Enter the username

			Thread.sleep(2000); // Wait for search results to load

			// Select the user checkbox
			waitAndClick(userCheckbox, "Failed to select user: " + username);

			// Save selection
			waitAndClick(saveButton, "Failed to tap on save button");
		}
		// Confirm sharing
		waitAndClick(shareButton, "Failed to tap on share button");
	}

	public void verifyFilesAndFoldersCannotBeSharedOutsideOrganization() throws InterruptedException {
		String tap_on_share_option = locators.getProperty("tap_on_share_option");
		String select_user_drop_down = locators.getProperty("select_user_drop_down");
		String search_user_name = locators.getProperty("search_user_name");
		String no_results_found = locators.getProperty("no_results_found");
		String tap_folder_overflow_menu = locators.getProperty("tap_folder_overflow_menu");

		// expected values
		String username = prop.getProperty("User_From_Another_Org");
		String expErrorMessage = prop.getProperty("Search_Error_Message");

		// tap on folder overflow menu
		Thread.sleep(4000);
		waitAndClick(tap_folder_overflow_menu, "Failed to tap on folder overflow menu");

		// tap on share option
		waitAndClick(tap_on_share_option, "Failed to tap on share option");

		// tap on select users drop down
		waitAndClick(select_user_drop_down, "Failed to tap on select users drop down");

		// search for user
		Thread.sleep(2000);
		WebElement user = appdriver.findElement(AppiumBy.xpath(search_user_name));
		user.sendKeys(username);

		// validate no results found text
		Thread.sleep(2000);
		String actErrorMessage = appdriver.findElement(AppiumBy.xpath(no_results_found)).getText();
		if (actErrorMessage.equals(expErrorMessage)) {
			Assert.assertEquals(actErrorMessage, expErrorMessage, "Successfully validated error message");
		} else {
			Assert.fail("Failed to validate error message");
		}
	}
	
	public void openFolderToUploadMoreFiles() throws InterruptedException {
		String tap_on_folder = locators.getProperty("tap_on_folder");
		
		// tap on folder name
		Thread.sleep(3000);
		waitAndClick(tap_on_folder,"Failed to tap on folder name");
	}
}