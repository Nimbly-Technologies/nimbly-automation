Feature: Validate Camera Resolution,Delete Attachments,Choose Priority,Audit Progress,Annotation

  @P2
  Scenario: Validate Camera Resolution,Delete Attachments,Choose Priority,Audit Progress,Annotation
    Given I login to application with CameraResolutionUserEmail,CameraResolutionUserPassword
    And validate multiple login and version update popups
    When search for the CameraResolution_Schedule_Name
    And checkin a schedule
    And validate choosing different camera resolutions
    And validate user able to delete attachments
    And verify user can select any piority and schedule progress
    And validate image annotation
    And submit the report in online mode
