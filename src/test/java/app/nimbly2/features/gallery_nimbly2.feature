Feature: Validate User Navigation to Gallery

  @smoke
  Scenario: Validate User Navigation to Gallery
    Given I login to application with GalleryUserEmail,GalleryUserPassword
    And validate in app update popup
    And validate multiple login popup
    When navigates to the gallery