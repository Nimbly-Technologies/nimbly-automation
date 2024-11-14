Feature: Validate Filter Functionality,Issue Details,Add Comment with Attachments

  @P1
  Scenario: Validate Filter Functionality,Issue Details,Add Comment with Attachments and Default Saved Filter
    Given I login to application with IssueFilterUserEmail,IssueFilterUserPassword
    And validate multiple login and version update popups
    When search for the Issue_Filter_Schedule_Without_Attachments
    And start audit proccess and submit the report
    Then navigate back to issues page and search for Issue_Filter_Question_Name
    And validate issue details
    And navigate back to issues list
    And validate filter functionality
    And verify whether default filters are applied
    And validate add comments along with attachments under issue activity
