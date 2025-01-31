Feature: Validate Attachment Selection, Viewing, Downloading, and Search Management Across Multiple Views

  @P2
  Scenario: Validate Attachment Selection, Viewing, Downloading, and Search Management Across Multiple Views
    Given I login to application with AttachmentSelectionUsername,AttachmentSelectionPassword
    And validate multiple login and version update popups
    When navigates to the gallery
    And validate user can select and deselect and collapse attachments day wise under library
    And validate user can select and deselect and collapse  attachments month wise under library
    And validate user can select and deselect attachments year wise under library
    And validate search and suggestion as per question
    And validate search and suggestion as per category
