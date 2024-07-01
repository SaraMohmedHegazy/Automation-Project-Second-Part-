Feature: E-Shop Vodafone Shopping

  Scenario: Add items to Cart
    Given user navigate to E-Shop Vodafone website
    And user log with mobile and password
    When user select 2 items and add it to the cart
    And search for an item and add it to the cart
    Then user verify the items in the cart