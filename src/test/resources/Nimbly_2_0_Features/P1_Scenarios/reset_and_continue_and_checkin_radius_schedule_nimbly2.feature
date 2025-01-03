Feature: Reset and Continue All Type of Schedules

  @P1
  Scenario Outline: Validated Reset and Continue Functionality for <scheduleType> Schedule Type
    Given I login to application with <username>,<password>
    And validate multiple login and version update popups
    When search for the <scheduleType>
    And start the audit process to validate save as draft in Online mode
    And navigates back to schedule page
    Then validate attachments after save as a draft
    And logout from nimbly2

    Examples: 
      | username    | password            | scheduleType |
      | DailyUser   | DailyUserPassword   | Daily        |
      | WeeklyUser  | WeeklyUserPassword  | Weekly       |
      | MonthlyUser | MonthlyUserPassword | Monthly      |

  @P1
  Scenario: Validate Checkin Radius
    Given I login to application with CheckinUser,CheckinPassword
    And validate multiple login and version update popups
    When search for the Schedule_Name
    And validate checkin radius
