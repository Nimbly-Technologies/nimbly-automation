Feature: Validate Audit Process For Both Scheduled and Adhoc Schedule

  @sanity
  Scenario: Validate Audit Process For Both Scheduled and Adhoc Schedule
    Given I login to application with BothScheduledAndAdhocUserEmail,BothScheduledAndAdhocUserPassword
    And validate multiple login and version update popups
    When search for the BothScheduledAndAdhoc_Schedule_With_Attachments
    And validate schedule card details for BothScheduledAndAdhoc schedule With_Attachments
    And verify check in pop up details for BothScheduledAndAdhoc schedule With_Attachments
    Then start the auditing process for BothScheduledAndAdhoc schedule with attachments
    And validate report review page for BothScheduledAndAdhoc schedule with attachments
    And submit the report in online mode
    And validate report submitted page for BothScheduledAndAdhoc schedule
    And logout from nimbly2

  @sanity
  Scenario: Validate Audit Process For Both Scheduled and Adhoc Schedule Without Attachments
    Given I login to application with BothScheduledAndAdhocWithoutAttachmentsUserEmail,BothScheduledAndAdhocWithoutAttachmentUserPassword
    And validate multiple login and version update popups
    When search for the BothScheduledAndAdhoc_Schedule_Without_Attachments
    And validate schedule card details for BothScheduledAndAdhoc schedule Without_Attachments
    And verify check in pop up details for BothScheduledAndAdhoc schedule Without_Attachments
    Then start the auditing process for BothScheduledAndAdhoc schedule without attachments
    And validate report review page for BothScheduledAndAdhoc schedule without attachments
    And submit the report in online mode
    And validate report submitted page for BothScheduledAndAdhoc schedule
    And logout from nimbly2
