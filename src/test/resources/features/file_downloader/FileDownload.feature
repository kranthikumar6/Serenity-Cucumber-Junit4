@fileDownload
Feature: File Download

  As a user
  I want to open file downloader
  Where I click the file link
  So that I can see the file is downloaded in the target folder

  @regression
  Scenario: download the file in target folder
    Given I open file download url
    When I click on the file
    Then I should see the "random_data.txt" is downloaded in target
