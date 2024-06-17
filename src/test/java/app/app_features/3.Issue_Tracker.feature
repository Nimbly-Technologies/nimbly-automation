#Author: your.email@your.domain.com

Feature: Issue_Tracker

@smoke @C2445 @C2446
  Scenario: Issue Tracker Page Smoke
    Given navigate to issue tracker main tab
    #And click on add issue
    #And click on backarrow
    #Then click on filter
    #And click on backarrow
    #Then click on sort
    #And click on backarrow

@sanity @C2456 @C2457
  Scenario: Add and Edit Issue
    Given navigate to issue tracker main tab
    And click on add issue
    And Add all the required fields
    And successfully add the issue
   Then perform edit operations
   And click on backarrow

@failcase
  Scenario: Add and Edit Issue
    Given navigate to issue tracker main tab
    And click on add issue
    And Add all the required fields
    And successfully add the issue
   Then perform edit operations fail case
   And click on backarrow
