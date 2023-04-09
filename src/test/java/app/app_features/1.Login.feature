

Feature: Login_to_Nimbly_App

@sanity @smoke @C2433 @failcase
  Scenario: Login to nimbly app 
    Then validate and enter the useremail
    And validate and click on login button
    Then validate and Enter the userpassword 
    And validate and click on login button
    And click on Allow Nimbly to access this device location

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
