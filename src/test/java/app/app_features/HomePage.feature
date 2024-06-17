#Author: your.email@your.domain.com
#Keywords Summary :

Feature: HomePage_Workflows


@smoke  @C2442 
  Scenario: validation of home page 
    Then navigate to homepage screen
    Then click on notification bell icon 
    And click on close notifications x button
    Then click on sales icon 
    Then click on download toggle button 
    And click on cancel in download toggle button popup
    Then click on site card in homepage
    And click on backarrow