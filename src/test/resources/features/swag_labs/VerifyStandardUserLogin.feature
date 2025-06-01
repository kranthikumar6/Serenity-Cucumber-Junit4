@StandardUserLogin
Feature: Swag Labs - Standard User Login

  As a user
  I want to launch swag url in browser
  Where I enter the standard user credential
  So that I can verify products page is displayed after login

  @regression
  Scenario: verify user able to login as standard user in swag labs website
    Given user has launched swag labs url
    Then user validates the page title
    When user enters the standard user credentials
    Then user validates the product page is displayed
