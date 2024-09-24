Feature: Login to Nimbly2 App

  @sanity
  Scenario: Validate Login Page Functionality
    Given validate and enter the useremail or userid
    When validate and enter user password
    Then validate login button and click on it
    And validate in app update popup
    And validate multiple login popup
    And logout from nimbly2
