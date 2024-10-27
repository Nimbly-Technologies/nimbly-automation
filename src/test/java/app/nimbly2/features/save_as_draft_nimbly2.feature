Feature: Validate Save as Draft and Add Attachment Through Camera and Gallery

  @sanity
  Scenario: Validate Save as Draft and Upload Photos From Camera and Gallery
    Given I login to application with SaveUserEmail,SaveUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Save_Schedule
    And start the audit process to validate save as draft
    Then validate attachments after save as a draft
    And logout from nimbly2
