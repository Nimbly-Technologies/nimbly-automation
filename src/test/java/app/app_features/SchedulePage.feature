Feature: schedule page workflows

  Scenario: Single Audit Adhoc Schedule Without Attachments
    Given handle allow notification pop up
    Given Enter useremail and password
    And click on login button
    When handle notifications on home page
    When navigates to app schedule page
    Then search the adhoc single audit site in search tab and select the site
    Then search the questionnaire in search tab and select the questionnaire
    Then click on adhoc report and start adhoc report for single audit
    And accept the checkin popup
    And answer the questions
    And review and checkout the report
    And manage feedback popup
    And navigate to settings page
    Then click on logout

  Scenario: Single Audit Adhoc Schedule With Attachments
    Given handle allow notification pop up
    Given Enter useremail and password
    And click on login button
    When handle notifications on home page
    When navigates to app schedule page
    Then search the site and choose
    And search the adhoc schedule and tab on it
    And accept the checkin popup
    And start auditing the schedule
    And tab on review button
    And add signature and selfie signature
    And checkout the report
    And manage feedback popup
    And navigate to settings page
    Then click on logout
#@sanity @C4889 @C4890
  #Scenario: Start a adhoc team audit schedule in schedulepage 
    #Given navigate to schedule page
    #Then search the adhoc team audit site in search tab and select the site 
    #Then click on adhoc report and start adhoc report for team audit
    #And Accept the checkin popup
    #Then select sections from teamaudit and answer the questions
    #And upload and finalize the report
    #And checkout the report
