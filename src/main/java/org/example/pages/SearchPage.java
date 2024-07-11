package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {


     @FindBy(xpath = "//input[@type='search']")
     private WebElement searchInputField;

     @FindBy(xpath = "//input[@placeholder='Czego szukasz?']//ancestor::div/following-sibling::div[contains(@class,'IconContainer')]")
     private WebElement searchButton;

     @FindBy(xpath = "//li[contains(@class,'productCard')]//span[@data-locator='zth-price']")
     private WebElement productPrice;

     @FindBy(xpath = "//button[contains(text(), 'Dodaj do koszyka')]")
     private WebElement addToBasketButton;

     @FindBy(xpath = "//button[contains(text(), 'Subskrybuj i kup')]")
     private WebElement subscribeAndBuyButton;

     @FindBy(xpath = "//p[contains(text(), 'Nie znaleźliśmy wyników dla')]")
     private List<WebElement> productNotFoundValidation;

     @FindBy(xpath = "//label[@data-locator='ppa-payment-plus']/input")
     private WebElement promotionVariantPrice;

     @FindBy(xpath = "//label[@data-locator='ppa-payment']/input")
    private WebElement defaultVariantPrice;

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

    public WebElement getSearchInputField() {
        return searchInputField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getAddToBasketButton() {
        return addToBasketButton;
    }

    public WebElement getSubscribeAndBuyButton() {
        return subscribeAndBuyButton;
    }

    public List<WebElement> getProductNotFoundValidation() {
        return productNotFoundValidation;
    }

    public WebElement getPromotionVariantPrice() {
        return promotionVariantPrice;
    }

    public WebElement getDefaultVariantPrice() {
        return defaultVariantPrice;
    }

    public void selectGame() {
        productPrice.click();
    }

    public void addToBasket(){
        addToBasketButton.click();
    }

    public void subscribeAndBuy(){
        subscribeAndBuyButton.click();
    }

    public void selectPromotionVariantPrice(){
        promotionVariantPrice.click();
    }

    public void selectDefaultVariantPrice(){
        defaultVariantPrice.click();
    }
}

