Feature: Issue_Tracker

  #@smoke @C2445 @C2446
  #Scenario: Issue Tracker Page Smoke
    #Given navigate to issue tracker main tab
    #And click on add issue
    #And click on backarrow
    #Then click on filter
    #And click on backarrow
    #Then click on sort
    #And click on backarrow

  @sanity @C2456 @C2457
  Scenario: Add and Edit Issue
    Given handle allow notification pop up
    Given Enter useremail and password
    And click on login button
    When handle notifications on home page
    And navigate to issue tracker main tab
    And click on add issue
    And Add all the required fields
    And successfully add the issue
    And manage feedback popup
    Then perform edit operations
    And click on backarrow
    And navigate to settings page
    Then click on logout

  #@failcase
  #Scenario: Add and Edit Issue
    #Given navigate to issue tracker main tab
    #And click on add issue
    #And Add all the required fields
    #And successfully add the issue
    #Then perform edit operations fail case
    #And click on backarrow
