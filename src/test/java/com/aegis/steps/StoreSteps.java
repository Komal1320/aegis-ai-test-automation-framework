package com.aegis.steps;

import com.aegis.config.ConfigReader;
import com.aegis.core.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class StoreSteps {
    @Given("I open the nopCommerce store")
    public void openStore() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Then("the store page should have a title")
    public void verifyTitle() {
        String title = DriverFactory.getDriver().getTitle();
        Assertions.assertFalse(title == null || title.isBlank(),
            "Expected nopCommerce page title to be populated; ensure Docker site is running and installed.");
    }
}
