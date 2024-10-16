Feature: Validate Audit Process For Monthly Schedule

  @sanity
  Scenario: Validate Monthly Schedule Audit Process
    Given I login to application with MonthlyUserEmail,MonthlyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Monthly_Schedule_With_Attachments
    And validate schedule card details for Monthly schedule With_Attachments
    And verify check in pop up details for Monthly schedule With_Attachments
    Then start the auditing process for Monthly schedule with attachments
    And validate report review page for Monthly schedule with attachments
    And submit the report in online mode
    And validate report submitted page for Monthly schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Monthly Schedule Audit Process Without Attachments
    Given I login to application with MonthlyUserEmail,MonthlyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Monthly_Schedule_Without_Attachments
    And validate schedule card details for Monthly schedule Without_Attachments
    And verify check in pop up details for Monthly schedule Without_Attachments
    Then start the auditing process for Monthly schedule without attachments
    And validate report review page for Monthly schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Monthly schedule
    And logout from nimbly2
