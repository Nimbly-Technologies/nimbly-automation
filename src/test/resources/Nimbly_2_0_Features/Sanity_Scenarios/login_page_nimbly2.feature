Feature: Login to Nimbly2 App

  @sanity
  Scenario: Validate Login Page Functionality
    Given validate and enter the useremail or userid
    When validate and enter user password
    Then validate login button and click on it
    And validate multiple login and version update popups
    And logout from nimbly2
