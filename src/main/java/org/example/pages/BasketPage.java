package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {


    public @FindBy(xpath = "//*[contains(text(), 'Cena całkowita')]//ancestor::span/following-sibling::span")
    WebElement cartPrice;


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public String getCartPrice() {
        return cartPrice.getText();
    }

}