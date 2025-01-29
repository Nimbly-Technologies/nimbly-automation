Feature: Verify filtering and sorting of attachments by source, type, site, department, date range, and questionnaire

  @P2
  Scenario: Verify filtering and sorting of attachments by source, type, site, department, date range, and questionnaire
    Given I login to application with GalleryFilterUserName,GalleryFilterPassword
    And validate multiple login and version update popups
    When navigates to the gallery
    And validate attachments visibility and grouped by sites under album
    And verify multi select attachments under album
    And verify filter and sort functionality for gallery