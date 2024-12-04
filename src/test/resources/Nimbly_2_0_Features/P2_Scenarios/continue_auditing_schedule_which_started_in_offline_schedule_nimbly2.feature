Feature: Validate the Continuation of Auditing that was started Offline

  @P2
  Scenario: Validate the Continuation of Auditing that was started Offline
    Given I login to application with OfflineSaveUserEmail,OfflineSaveUserPassword
    And validate multiple login and version update popups
    When switch to offline mode
    When search for the OfflineSave_Schedule
    And start the audit process to validate save as draft in Offline mode
    And navigates back to schedule page
    And switch to online mode
    When search for the OfflineSave_Schedule
    Then validate attachments after save as a draft
