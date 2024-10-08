Feature: Validate Audit Process For Daily Schedule Type

  @sanity
  Scenario: Validate Daily Schedule Audit Process
    Given I login to application with DailyUserEmail,DailyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Daily_Schedule
    And validate schedule card details for Daily schedule
    And verify check in pop up details for Daily schedule
    Then start the auditing process for Daily schedule
    And validate report review page for Daily schedule
    And submit the report in online mode
    And validate report submitted page for Daily schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Offline Scheduling Audit Process
    Given I login to application with OfflineUserEmail,OfflineUserPassword
    And validate in app update popup
    And validate multiple login popup
    When switch to offline mode
    When search for the Offline_Schedule
    And validate schedule card details for Offline schedule
    And verify check in pop up details for Offline schedule
    Then start the auditing process for Offline schedule
    And validate report review page for Offline schedule
    And checkout the report in offline mode
    And validate offline schedule status after checkout
    And switch to online mode
    When search for the Offline_Schedule
    Then validate schedule status after switching to online mode
    And validate modify and sync with server on checkin pop up
    And validate review report for Offline schedule after switching to online mode
    And sync your data with server
    And submit the report in online mode
    And validate report submitted page for Offline schedule
    And logout from nimbly2
