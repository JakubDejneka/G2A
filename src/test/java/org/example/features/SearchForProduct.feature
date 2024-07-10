@checkProductPrice
Feature: Product search and cart verification

  Scenario: Search for a product and verify the price in the cart
    Given I navigate to the G2A homepage
    When I search for product
    And I note the price of the product
    And I add the product to the cart
    Then I verify the price in the cart