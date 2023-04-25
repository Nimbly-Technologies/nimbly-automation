Feature: Verify Questionnaire Page Details

  @sanity
  Scenario: Verify UI of Questionnaire Page
    Given Navigate to the Nimbly Web Admin
    When enter the username
    And enter the user password
    And navigate to Login buton
    And Accept the cookies
    And navigate to AdminTabFromleftbar button
    And navigate to Manage button
    And navigate to questionnaires button
    Then Validate the Questionnaire Page details
    And navigate to Logout button
