package com.aegis.steps;

import com.aegis.core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before("@ui")
    public void startBrowser() {
        DriverFactory.getDriver();
    }
    @After("@ui")
    public void stopBrowser() {
        DriverFactory.quitDriver();
    }
}
