Feature: Validate Search Functionality,Category Flags Count and Questions when Use Deductions is Enabled or Disabled

  @P2
  Scenario: Validate Search Functionality,Category Flags Count and Questions when Use Deductions is Enabled or Disabled
    Given I login to application with QuestionnairePreviewUserEmail,QuestionnairePreviewUserPassword
    And validate multiple login and version update popups
    When search for the QuestionnairePreview_Schedule_Without_Attachments
    And checkin a schedule
    And navigates to the questionnaire preview and validates the search and questions by toggling the required question option on and off
    And reset the schedule
    And start audit process and answer only mandatory questions
    And validate category wise flag counts at questionnaire preview
    And validate review repoprt page for use deductions
    And submit the report in online mode
