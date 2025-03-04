Feature: File and Folder Management

  @P2
  Scenario: Verify file and folder sharing permissions and search result views
    Given I login to application with SearchUsername,SearchPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And verify that files and folders can be shared with multiple users simultaneously
    And verify the search functionality
    And Verify that files and folders cannot be shared outside the organization
    
    @P2
    Scenario: Verify file and folder management actions including uploads and nesting
    Given I login to application with UploadFolderUsername,UploadFolderPassword
    And validate multiple login and version update popups
    When navigates to file repo
    And create new file and folder
    And open a folder to upload files with the same name and add subfolders
    And create new file and folder
    And create new file and folder
    