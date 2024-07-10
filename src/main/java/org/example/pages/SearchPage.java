package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SearchPage extends BasePage {


    public @FindBy(xpath = "//input[@type='search']")
    WebElement searchInputField;

    public @FindBy(xpath = "//input[@placeholder='Czego szukasz?']//ancestor::div/following-sibling::div[contains(@class,'IconContainer')]")
    WebElement searchButton;

    public @FindBy(xpath = "//li[contains(@class,'productCard')]//span[@data-locator='zth-price']")
    WebElement productPrice;

    public @FindBy(xpath = "//button[contains(text(), 'Dodaj do koszyka')]")
    WebElement addToBasketButton;

    public @FindBy(xpath = "//p[contains(text(), 'Nie znaleźliśmy wyników dla')]")
    List<WebElement> productNotFoundValidation;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        searchInputField.sendKeys(productName);
        searchButton.click();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void addToCart() {
        productPrice.click();
        addToBasketButton.click();
    }
}

