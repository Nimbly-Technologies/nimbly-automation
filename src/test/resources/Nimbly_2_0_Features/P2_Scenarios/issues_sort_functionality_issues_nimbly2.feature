Feature: Validate Issues Sort Functionality

  @P2
  Scenario: Validate Issues Sort Functionality
    Given I login to application with IssuesSortUserName,IssuesSortPassword
    And validate multiple login and version update popups
    When navigates to issues
    Then verify sorting in ascending and descending order
    And validate issue sort functionality
    And verify all, overdue, and my issues, along with the saved filters
