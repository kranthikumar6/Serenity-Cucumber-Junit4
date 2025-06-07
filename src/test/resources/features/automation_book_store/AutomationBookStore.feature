@automationBookStore
Feature: Automation Book Store

  As a user
  I want to open automation book store
  So that I can see the book page titles

  @regression
  Scenario: check if a book present
    Given I open automation book store
    Then I see if single book is present

  @regression
  Scenario: books page titles displayed
    Given I open automation book store
    Then I see the all the books page titles displayed
      | Test Automation in the Real World |
      | Experiences of Test Automation    |
      | Agile Testing                     |
      | How Google Tests Software         |
      | Java For Testers                  |
      | Advanced Selenium in Java         |
      | The Cucumber for Java Book        |
      | BDD in Action                     |
