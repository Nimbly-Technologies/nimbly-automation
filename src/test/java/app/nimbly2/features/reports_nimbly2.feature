Feature: Validate Serach, Report Card and Download Report Functionality

@sanity
  Scenario: Validate Serach, Report Card and Download Report Functionality
    Given I login to application with ReportUserEmail,ReportUserPassword
    And validate in app update popup
    And validate multiple login popup
    When search for the Report_Schedule_Without_Attachments
    And validate schedule card details for Report schedule Without_Attachments
    And verify check in pop up details for Report schedule Without_Attachments
    Then start the auditing process for Report schedule without attachments
    And validate report review page for Report schedule without attachments
    And submit the report in online mode
    And validate report submitted page for Report schedule
    When access the Reports tab and checks the search functionality
    And verify report card details
    Then validate download report button and report generation popup
    And logout from nimbly2