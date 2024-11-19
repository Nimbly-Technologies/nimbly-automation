Feature: Validate Audit Process for Conditional Questions with Attachments

  @P2
  Scenario: Validate Audit Process for Conditional Questions with Attachments
    Given I login to application with ConditionalQuestionUserEmail,ConditionalQuestionUserPassword
    And validate multiple login and version update popups
    When search for the ConditionalQuestion_Schedule_With_Attachments
    And start audit process for conditional questions
    And validate review page for conditional questions
    And submit the report in online mode
    And validate report submitted page for conditional questions
