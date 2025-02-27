Feature: Validate User can rename, move to trash files and folders

  @P2
  Scenario: Validate User can rename, move to trash files and folders
    Given I login to application with RenameFileFolderUsername,RenameFileFolderPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And create new file and folder
    And rename file and folder names
    And move files and folders to trash

  @P2
  Scenario: Verify that the user can filter files and folders by file type and date range
    Given I login to application with FilterFileFolderUsername,FilterFileFolderPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And validate the file type filter functionality for files Filter_Video_Name Filter_Doc_Name Filter_Image_Name
    And validate the file type filter functionality for folders Filter_Video_Name Filter_Doc_Name Filter_Image_Name

  @P2
  Scenario: [Shared with Me] Validate Folder and File Management Features, Including Menu Access, Sharing, Filtering, Sorting, and Notifications
    Given I login to application with SharedWithMeUsername,SharedWithMePassword
    And validate multiple login and version update popups
    When navigates to file repo
    And verify user can share folder
    And validate the file type filter functionality for files Shared_Video_Name Shared_Doc_Name Shared_Image_Name
    And verify sort functionality under shared with me
    And validate the file type filter functionality for folders Shared_Video_Name Shared_Doc_Name Shared_Image_Name

  @P2
  Scenario: [Recent] File Management Operations – Verify that the user can rename, move to trash, and filter files
    Given I login to application with RecentFileManageUsername,RecentFileManagePassword
    And validate multiple login and version update popups
    When navigates to file repo
    And rename the file in recent tab
    And move file to trash in recent tab
    And validate the file type filter functionality for files Recent_Video_Name Recent_Doc_Name Recent_Image_Name
