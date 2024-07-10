package org.example.driverFactory;

import org.example.pages.BasketPage;
import org.example.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class DriverFactory{

    public static SearchPage searchPage;
    public static BasketPage basketPage;
    private static WebDriver driver;

    public static void initialize() {

        System.setProperty("webdriver.chrome.driver", "src/test/java/org/example/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-site-isolation-trials");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-features=SameSiteByDefaultCookies,CookiesWithoutSameSiteMustBeSecure");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");

        try {
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            System.err.println("Failed to initialize the Chrome driver: " + e.getMessage());
            throw e;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        searchPage = new SearchPage(driver);
        basketPage = new BasketPage(driver);

        PageFactory.initElements(driver, searchPage);
        PageFactory.initElements(driver, basketPage);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static SearchPage getSearchPage() {
        return searchPage;
    }

    public static BasketPage getBasketPage() {
        return basketPage;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
