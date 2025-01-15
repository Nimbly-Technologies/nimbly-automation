Feature: Verify the functionality of downloading attachments at different frequencies

  @P1
  Scenario: Verify the functionality of downloading attachments at different frequencies
    Given I login to application with DownloadAttachmentUserName,DownloadAttachmentPassword
    And validate multiple login and version update popups
    When navigates to the gallery
    And validate by default library and all tabs are selected
    And validate user can download image by clicking on the attachment
    And validate select all popup
    And download attachments under all, days and months
    And verify user can navigate to issues by clicking go to issue on attachment
