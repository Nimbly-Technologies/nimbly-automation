Feature: Validate User Navigation to Gallery

  @smoke
  Scenario: Validate User Navigation to Gallery
    Given I login to application with GalleryUserEmail,GalleryUserPassword
    And validate multiple login and version update popups
    When navigates to the gallery
