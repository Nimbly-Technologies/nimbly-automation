Feature: Validate User can rename, move to trash files and folders

  @P20
  Scenario: Validate User can rename, move to trash files and folders
    Given I login to application with RenameFileFolderUsername,RenameFileFolderPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And create new file and folder
    And rename file and folder names
    And move files and folders to trash
