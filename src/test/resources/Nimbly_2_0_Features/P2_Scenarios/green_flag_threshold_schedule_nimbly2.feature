Feature: Validate Green Flag Threshold

  @P2
  Scenario: Negative Score: Validate Green Flag Threshold
    Given I login to application with Green_Flag_Threshold_UserEmail,Green_Flag_Threshold_UserPassword
    And validate multiple login and version update popups
    When search for the Green_Flag_Threshold_Schedule_With_Attachments
    And start auditing and validate maximum green flag threshold and negative scoring
    And submit the report in online mode
