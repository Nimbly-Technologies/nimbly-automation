Feature: Validate Reset and Continue Auditing in Offline Mode

  @P2
  Scenario: Validate Reset and Continue Auditing in Offline Mode
    Given I login to application with OfflineSaveUserEmail,OfflineSaveUserPassword
    And validate multiple login and version update popups
    When switch to offline mode
    When search for the OfflineSave_Schedule
    And start the audit process to validate save as draft in Offline mode
    And navigates back to schedule page
    Then validate attachments after save as a draft
