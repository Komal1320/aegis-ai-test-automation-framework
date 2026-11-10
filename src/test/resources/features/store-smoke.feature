@ui @smoke
Feature: nopCommerce store smoke check

  Scenario: Store home page opens
    Given I open the nopCommerce store
    Then the store page should have a title
