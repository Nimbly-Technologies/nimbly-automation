Feature: Starred Files and Folders Management

  @P2
  Scenario: [Starred] Verify File and Folder Management Actions
    Given I login to application with StarredUsername,StarredPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And create new file and folder
    And add file and folder to starred
    And navigates to starred tab
    And rename file and folder names
    And validate user can be able to download file and folder
    And move files and folders to trash

  @P2
  Scenario: [Starred] Verify Filtering and Sorting of Files and Folders
    Given I login to application with StarredFilterUsername,StarredFilterPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And navigates to starred tab
    And validate the file type filter functionality for files Starred_Video_Name Starred_Doc_Name Starred_Image_Name
    And validate the file type filter functionality for folders Starred_Video_Name Starred_Doc_Name Starred_Image_Name
    And verify sort functionality under shared with me
