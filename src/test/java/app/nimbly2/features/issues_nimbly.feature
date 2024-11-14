Feature: Validate Issue Card Details, Edit Issue Functionality and Create Adhoc Issue

  @sanity
  Scenario: Validate Issue Card Details, Edit Issue Functionality
    Given I login to application with IssueUserEmail,IssueUserPassword
    And validate multiple login and version update popups
    When search for the Issue_Schedule_Without_Attachments
    And start audit proccess and submit the report
    Then navigate back to issues page and search for Issue_Question_Name
    And validate issue card details
    And validate edit issue details

  @sanity
  Scenario: Create Adhoc Issue
    Given I login to application with AdhocIssueUserEmail,AdhocIssueUserPassword
    And validate multiple login and version update popups
    When add adhoc issue
    And logout from nimbly2
