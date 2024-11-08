Feature: Validate Report Submission with Use Deductions Enabled

  @P1
  Scenario: Validate Report Submission with Use Deductions Enabled
    Given I login to application with DeductionsUserEmail,DeductionsUserPassword
    And validate multiple login and version update popups
    When search for the Deductions_Schedule_Without_Attachments
    And start audit process and answer only mandatory questions
    And validate review repoprt page for use deductions
    And submit the report in online mode
