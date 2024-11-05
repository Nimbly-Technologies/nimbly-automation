Feature: Validate User Navigation to File Repo

  @smoke
  Scenario: Validate User Navigation to File Repo
    Given I login to application with FileRepoUserEmail,FileRepoUserPassword
    And validate in app update popup
    And validate multiple login popup
    When navigates to file repo