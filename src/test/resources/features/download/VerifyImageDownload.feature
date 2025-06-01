@ImageDownload
Feature: Image Download

  As a user
  I want to download url
  Where I click the image link
  So that I can verify the image is downloaded in the target folder

  @regression
  Scenario: Validate user able to download the image in target folder
    Given user launches the file download url
    When click on the download
    Then verify the "random_data.txt" is downloaded in target folder
