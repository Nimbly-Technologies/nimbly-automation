Feature: Validate Audit Process For Daily Schedule

  @sanity
  Scenario: Validate Daily Schedule Audit Process
    Given I login to application with DailyUserEmail,DailyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Daily_Schedule_With_Attachments
    And validate schedule card details for Daily schedule With_Attachments
    And verify check in pop up details for Daily schedule With_Attachments
    Then start the auditing process for Daily schedule with attachments
    And validate report review page for Daily schedule with attachments
    And submit the report in online mode
    And validate report submitted page for Daily schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Daily Schedule Audit Process Without Attachments
    Given I login to application with DailyUserEmail,DailyUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Daily_Schedule_Without_Attachments
    And validate schedule card details for Daily schedule Without_Attachments
    And verify check in pop up details for Daily schedule Without_Attachments
    Then start the auditing process for Daily schedule without attachments
    And validate report review page for Daily schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Daily schedule
    And logout from nimbly2
