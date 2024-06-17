#Author: your.email@your.domain.com
#Keywords Summary :


Feature: schedule page workflows
Background   

  @smoke @C2458 @C2459
  Scenario: schedulepage smoke
    Given navigate to schedule page
    Then search the adhoc single audit site in search tab and select the site
    And click on backarrow


@sanity @C4888 @C4887
  Scenario: Start a adhoc single audit schedule in schedulepage 
    Given navigates to app schedule page
    Then search the adhoc single audit site in search tab and select the site 
    Then search the questionnaire in search tab and select the questionnaire
    Then click on adhoc report and start adhoc report for single audit
    And accept the checkin popup
    And answer the questions
    And review and checkout the report
#
#
#@sanity @C4889 @C4890
  #Scenario: Start a adhoc team audit schedule in schedulepage 
    #Given navigate to schedule page
    #Then search the adhoc team audit site in search tab and select the site 
    #Then click on adhoc report and start adhoc report for team audit
    #And Accept the checkin popup
    #Then select sections from teamaudit and answer the questions
    #And upload and finalize the report
    #And checkout the report
