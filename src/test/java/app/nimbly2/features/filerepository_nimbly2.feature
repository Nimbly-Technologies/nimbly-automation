Feature: Validate User Navigation to File Repo

  @smoke
  Scenario: Validate User Navigation to File Repo
    Given I login to application with FileRepoUserEmail,FileRepoUserPassword
    And validate multiple login and version update popups
    When navigates to file repo
