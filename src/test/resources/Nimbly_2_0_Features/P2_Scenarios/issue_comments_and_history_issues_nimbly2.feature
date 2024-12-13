Feature: Validate issue comments and history, add new issue member, download attachment

  @P2
  Scenario: Validate issue comments and history, add new issue member, download attachment
    Given I login to application with AddIssueMemberUserEmail,AddIssueMemberUserPassword
    And validate multiple login and version update popups
    When search for the AddIssueMember_Schedule_Without_Attachments
    And start audit proccess and submit the report
    Then navigate back to issues page and search for AddIssueMember_Question_Name
    And validate user able to add new issue member in issue details
    And validate issue history and comments under All tab

  @P2
  Scenario: Verify if the user can upload maximum ten files through issue comments
    Given I login to application with UploadAttachmentsUserEmail,UploadAttachmentsUserPassword
    And validate multiple login and version update popups
    Then navigate back to issues page and search for AddIssueMember_Question_Name
    And validate user can upload ten attachments
