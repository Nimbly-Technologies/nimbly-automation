Feature: Validate report download functionality for different time periods

  @P2
  Scenario: Validate report download functionality for different time periods
    Given I login to application with PeriodicReportDownloadUserEmail,PeriodicReportDownloadPassword
    And validate multiple login and version update popups
    When search for the PeriodicReportDownload_Schedule_Without_Attachments
    And validate schedule card details for PeriodicReportDownload schedule Without_Attachments
    And verify check in pop up details for PeriodicReportDownload schedule Without_Attachments
    Then start the auditing process for PeriodicReportDownload schedule without attachments
    And validate report review page for PeriodicReportDownload schedule without attachments
    And submit the report in online mode
    And validate report submitted page for PeriodicReportDownload schedule
    When navigates to reports tab
    Then validate download report functionalty for different time periods
    And verify that the user can successfully download reports from the group by location section
    And validate user can successfully download reports from last seven days and last thirty days
    And logout from nimbly2
