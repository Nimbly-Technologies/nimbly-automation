Feature: Validate Review Page

  @P2
  Scenario: Validate Report Review Page
    Given I login to application with ReviewReportUserEmail,ReviewReportUserPassword
    And validate multiple login and version update popups
    When search for the ReviewReport_Schedule_With_Attachments
    And start audit process and answer only mandatory questions
    And validate site and questionnaire names along with signature and selfie error messagaes
    And submit the report in online mode