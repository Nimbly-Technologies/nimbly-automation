Feature: Trash Management

  @P2
  Scenario: [Trash] Permanently delete a folder from Trash
    Given I login to application with DeleteFolderUsername,DeleteFolderPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And create new folder
    And move folder to trash
    Then delete a folder permanently from trash

  @P2
  Scenario: [Trash]Validate Filter & Sort Functionality in Trash
    Given I login to application with TrashFilterSortUsername,TrashFilterSortPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And navigates to trash
    And validate the file type filter functionality for files TrashFilterSort_Video_Name TrashFilterSort_Doc_Name TrashFilterSort_Image_Name
    And verify sort functionality under shared with me
