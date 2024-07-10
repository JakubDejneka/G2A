@checkProductPrice
Feature: Product search and cart verification

  Scenario Outline: Search for a product and verify the price in the cart without promotion
    Given I navigate to the G2A homepage
    When I search for product
    And I note the price of the product
    And I add the product to the cart with priceVariant '<usageOfG2A>'
    Then I verify the price in the cart

    Examples:
      | usageOfG2A |
      | yes        |
      | no         |