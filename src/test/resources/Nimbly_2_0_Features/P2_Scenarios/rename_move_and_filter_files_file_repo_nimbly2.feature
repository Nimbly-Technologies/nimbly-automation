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
    And validate the file type filter functionality for files
    And validate the file type filter functionality for folders

  @P2
  Scenario: [Shared with Me] Validate Folder and File Management Features, Including Menu Access, Sharing, Filtering, Sorting, and Notifications
    Given I login to application with SharedWithMeUsername,SharedWithMePassword
    And validate multiple login and version update popups
    When navigates to file repo
    And verify user can share folder
    And validate the file type filter functionality for files
    And verify sort functionality under shared with me
    And validate the file type filter functionality for folders
    
