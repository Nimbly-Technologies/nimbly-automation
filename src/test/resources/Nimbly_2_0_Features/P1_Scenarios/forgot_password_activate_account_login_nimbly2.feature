Feature: Validate Forgot Password and Activate Account Functionality

  @P1
  Scenario: Validate Forgot Password and Activate Account Functionality
    Given I login to application with InvalidUserName,InvalidUserPassword
    When validate invalid credentials error message
    Then validate forgot password functionality
    And validate activate account functionality
