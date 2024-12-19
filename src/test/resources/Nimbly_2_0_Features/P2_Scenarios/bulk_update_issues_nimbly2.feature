Feature: Validate Bulk Update Issues Functionality

  @P2
  Scenario: Validate Bulk Update Issues Functionality
    Given I login to application with BulkUpdateIssuesUserEmail,BulkUpdateIssuesUserPassword
    And validate multiple login and version update popups
    When search for the BulkUpdateIssues_Schedule_Without_Attachments
    And start audit proccess and submit the report
    And navigates back to issues tab
    And select issues and perform bulk update
    Then validate changes after bulk updates
