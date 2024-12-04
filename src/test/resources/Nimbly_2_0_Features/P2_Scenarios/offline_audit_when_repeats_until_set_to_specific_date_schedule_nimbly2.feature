Feature: Validate Audit Process For Offline Schedule when Repeats Until set to Specific Date

  @P2
  Scenario: Validate Audit Process For Offline Schedule when Repeats Until set to Specific Date
    Given I login to application with Offline_Specific_Date_UserEmail,Offline_Specific_Date_UserPassword
    And validate multiple login and version update popups
    When switch to offline mode
    When search for the Offline_Specific_Date_Schedule_With_Attachments
    And validate schedule card details for Offline_Specific_Date schedule With_Attachments
    And verify check in pop up details for Offline_Specific_Date schedule With_Attachments
    Then start the auditing process for Offline schedule with attachments
    And validate report review page for Offline schedule with attachments
    And checkout the report in offline mode
    And validate offline schedule status after checkout
    And switch to online mode
    When search for the Offline_Specific_Date_Schedule_With_Attachments
    Then validate schedule status after switching to online mode
    And validate modify and sync with server on checkin pop up
    And validate review report for Offline schedule with attachments after switching to online mode
    And sync your data with server
    And submit the report in online mode
    And validate report submitted page for Offline schedule
    And logout from nimbly2
