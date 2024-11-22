Feature: Validate Schedule Progress After Answering few Questions on Schedule Card

  @P2
  Scenario: Validate Schedule Progress After Answering few Questions on Schedule Card
    Given I login to application with ScheduleProgressUserEmail,ScheduleProgressUserPassword
    And validate multiple login and version update popups
    When search for the ScheduleProgress_Schedule_Without_Attachments
    And start audit process and answer only mandatory questions
    And verify schedule progress after answering few questions