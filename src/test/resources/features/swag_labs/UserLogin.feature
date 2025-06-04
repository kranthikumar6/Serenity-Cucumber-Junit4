@userLogin
Feature: User Login

  As a user
  I want to open swag labs
  Where I enter the standard user credentials
  So that I can see the products page displayed

  @regression @standardUser
  Scenario: login as standard user in swag labs
    Given I open swag labs url
    When I enter the standard user credentials
    Then I should see the product page displayed
