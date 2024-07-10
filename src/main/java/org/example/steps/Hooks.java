package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.driverFactory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.Timestamp;

public class Hooks extends DriverFactory {

    @Before
    public void setup() {
        DriverFactory.initialize();
        searchPage = DriverFactory.getSearchPage();
        basketPage = DriverFactory.getBasketPage();
    }

    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeMilliseconds = Long.toString(timestamp.getTime());
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", timeMilliseconds);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}