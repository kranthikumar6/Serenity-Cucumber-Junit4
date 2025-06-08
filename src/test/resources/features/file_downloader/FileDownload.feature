@fileDownload
Feature: File Download Functionality

  As a user
  I want to access the file downloader
  So that I can download a file by clicking its link and verify it is saved in the target folder

  @regression
  Scenario: download the file in target folder
    Given I have opened the file downloader URL
    When I click on the file
    Then I should see the "random_data.txt" is downloaded in target
