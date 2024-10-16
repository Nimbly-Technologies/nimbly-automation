Feature: Validate Audit Process For Weekly Schedule

  @sanity
  Scenario: Validate Weekly Schedule Audit Process
    Given I login to application with WeeklyUserEmail,WeeklyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Weekly_Schedule_With_Attachments
    And validate schedule card details for Weekly schedule With_Attachments
    And verify check in pop up details for Weekly schedule With_Attachments
    Then start the auditing process for Weekly schedule with attachments
    And validate report review page for Weekly schedule with attachments
    And submit the report in online mode
    And validate report submitted page for Weekly schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Weekly Schedule Audit Process Without Attachments
    Given I login to application with WeeklyUserEmail,WeeklyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Weekly_Schedule_Without_Attachments
    And validate schedule card details for Weekly schedule Without_Attachments
    And verify check in pop up details for Weekly schedule Without_Attachments
    Then start the auditing process for Weekly schedule without attachments
    And validate report review page for Weekly schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Weekly schedule
    And logout from nimbly2
