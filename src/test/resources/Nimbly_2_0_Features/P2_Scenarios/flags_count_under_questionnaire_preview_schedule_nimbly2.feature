Feature: Validate Flags Count for Questionnaire Preview

  @P2
  Scenario: Validate Flags Count for Questionnaire Preview
    Given I login to application with FlagsCountUserEmail,FlagsCountUserPassword
    And validate multiple login and version update popups
    When search for the FlagsCount_Schedule_With_Attachments
    And start audit process for conditional questions
    And verify green yellow and red flags count under preview of questionnaire
    And validate review page for conditional questions
    And submit the report in online mode
    And validate report submitted page for conditional questions
