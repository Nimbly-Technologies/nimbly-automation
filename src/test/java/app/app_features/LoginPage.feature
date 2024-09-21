Feature: Login_to_Nimbly_App


  @smoke @C2433 @failcase
  Scenario: Validate Login & Logout functionality
    Given handle allow notification pop up
    Then validate and enter the useremail
    And validate and click on login button
    Then validate and Enter the userpassword
    And validate and click on login button
    When handle notifications on home page
    And navigate to settings page
    Then click on logout
#@smoke
  #Scenario: validation of login screen 
    #Then validate useremail box
    #Then enter the useremail
    #Then validate login button
    #And click on login button
    #Then validate userpassword box
    #Then Enter the userpassword 
    #And click on login button
    #And click on Allow Nimbly to access this device location
