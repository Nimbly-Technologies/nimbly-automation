Feature: Validate Duplicate Schedule Audit Process

  @P2
  Scenario Outline: Validate Duplicate Schedule Audit Process <iterations>
    Given I login to application with DuplicateUserEmail,DuplicateUserPassword
    And validate multiple login and version update popups
    When search for the <scheduleName>
    And validate schedule card details for Duplicate schedule Without_Attachments
    And verify check in pop up details for Duplicate schedule Without_Attachments
    Then start the auditing process for Duplicate schedule without attachments
    And validate report review page for Duplicate schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Duplicate schedule
    And logout from nimbly2

    Examples: 
      | scheduleName                           | iterations |
      | Duplicate_Schedule_Without_Attachments |          1 |
      | Duplicate_Schedule_Without_Attachments |          2 |
