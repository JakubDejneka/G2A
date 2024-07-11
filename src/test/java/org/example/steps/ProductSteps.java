package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.driverFactory.DriverFactory;
import org.example.utils.GlobalVariables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductSteps extends DriverFactory {

    private String productPrice;
    String productName= System.getProperty("productName","diablo 2 lord of destruction");


    @When("I navigate to the G2A homepage")
    public void iNavigateToTheG2AHomepage() {
        searchPage.navigateTo(GlobalVariables.HOME_PAGE_URL);
    }

    @When("I search for product")
    public void iSearchForProduct() {
        searchPage.searchProduct(productName);
        if (!searchPage.getProductNotFoundValidation().isEmpty()) {
            fail(String.format("Product: %s, can't be found. Please search for other.", productName));
        }
    }

    @When("I note the price of the product")
    public void iNoteThePriceOfTheProduct() {
        productPrice = searchPage.getProductPrice();
    }

    @When("I add the product to the cart with priceVariant {string}")
    public void iAddTheProductToTheCart(String priceVariant) {
        searchPage.selectGame();
        switch (priceVariant) {
            case "yes":
                searchPage.selectPromotionVariantPrice();
                searchPage.subscribeAndBuy();
                break;
            case "no":
                searchPage.selectDefaultVariantPrice();
                searchPage.addToBasket();
                break;
            default:
                fail("Invalid priceVariant parameter. Expected 'yes' or 'no'.");
        }
    }

    @Then("I verify the price in the cart")
    public void iVerifyThePriceInTheCart() {
        assertEquals("The price in the cart does not match the expected price.", productPrice, basketPage.getCartPrice());
    }
}
