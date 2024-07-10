package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.driverFactory.DriverFactory;
import org.example.utils.GlobalVariables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductSteps extends DriverFactory {

    private String productPrice;
    private String cartPrice;
    String productName= System.getProperty("productName","gta");


    @When("I navigate to the G2A homepage")
    public void iNavigateToTheG2AHomepage() {
        searchPage.navigateTo(GlobalVariables.HOME_PAGE_URL);
    }

    @When("I search for product")
    public void iSearchForProduct() {
        searchPage.searchProduct(productName);
        if (!searchPage.productNotFoundValidation.isEmpty()) {
            fail(String.format("Product: %s, can't be found. Please search for other.", productName));
        }
    }

    @When("I note the price of the product")
    public void iNoteThePriceOfTheProduct() {
        productPrice = searchPage.getProductPrice();
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        searchPage.addToCart();
    }

    @Then("I verify the price in the cart")
    public void iVerifyThePriceInTheCart() {
        cartPrice = basketPage.getCartPrice();
        assertEquals("The price in the cart does not match the expected price.", productPrice, cartPrice);
    }
}