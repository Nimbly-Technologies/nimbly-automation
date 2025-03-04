Feature: Validate User can Download,Upload and Share Folder & File

  @P1
  Scenario: Validate User can Download,Upload and Share Folder & File
    Given I login to application with UploadFileUserName,UploadFileUserPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And validate files and folders under list and grid view
    And validate user can be able to download file and folder
    And verify user can share file and folder
    And verify user can create new folder and upload new file
    
    @P1
    Scenario: Verify that the user can download and share files and folders in the Shared with Me and Recent sections
    Given I login to application with RecentUserName,RecentPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And switch to shared with me tab
    And share file and folder in list and grid view under shared with me
    And share and download recently uploaded file
    
    
