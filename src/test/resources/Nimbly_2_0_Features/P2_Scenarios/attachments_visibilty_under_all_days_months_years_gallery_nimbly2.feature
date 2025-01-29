Feature: Validate Attachments Visibility under All, Days, Months, Years tabs and Tiles per Row

  @P2
  Scenario: Validate Attachments Visibility under All, Days, Months, Years tabs and Tiles per Row
    Given I login to application with AttachmentsVisibiltyUserName,AttachmentsVisibiltyPassword
    And validate multiple login and version update popups
    When navigates to the gallery
    And validate attachments visibility under all tab
    And validate attachments visibility under days tab
    And validate attachments visibility under months tab
    And validate attachments visibility under years tab
    And verify tile view per row