Feature: Trash Files and Folders Management

  @P2
  Scenario: [Trash] Verify Management, Restoration, and Filtering of Files and Folders
    Given I login to application with TrashUsername,TrashPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And move files and folders to trash
    And restore files and folders from trash
