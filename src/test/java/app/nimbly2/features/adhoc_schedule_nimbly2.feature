Feature: Validate Audit Process For Adhoc Schedule

  @sanity
  Scenario: Validate Adhoc Schedule Audit Process
    Given I login to application with AdhocUserEmail,AdhocUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Adhoc_Schedule_With_Attachments
    And validate schedule card details for Adhoc schedule With_Attachments
    And verify check in pop up details for Adhoc schedule With_Attachments
    Then start the auditing process for Adhoc schedule with attachments
    And validate report review page for Adhoc schedule with attachments
    And submit the report in online mode
    And validate report submitted page for Adhoc schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Adhoc Schedule Audit Process Without Attachments
    Given I login to application with AdhocUserEmail,AdhocUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Adhoc_Schedule_Without_Attachments
    And validate schedule card details for Adhoc schedule Without_Attachments
    And verify check in pop up details for Adhoc schedule Without_Attachments
    Then start the auditing process for Adhoc schedule without attachments
    And validate report review page for Adhoc schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Adhoc schedule
    And logout from nimbly2
