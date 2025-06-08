@automationBookStore
Feature: Automation Book Store Navigation

  As a user
  I want to access the Automation Book Store
  So that I can view available book titles

  Background:
    Given I have opened the Automation Book Store

  @regression
  Scenario: filter book displayed
    When I search for the book "Test Automation in the Real World"
    Then the book "Test Automation in the Real World" should be displayed

  @regression
  Scenario: all book titles displayed
    Then the following book titles should be displayed
      | Test Automation in the Real World |
      | Experiences of Test Automation    |
      | Agile Testing                     |
      | How Google Tests Software         |
      | Java For Testers                  |
      | Advanced Selenium in Java         |
      | The Cucumber for Java Book        |
      | BDD in Action                     |
