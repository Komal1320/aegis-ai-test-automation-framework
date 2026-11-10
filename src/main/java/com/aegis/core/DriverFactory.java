package com.aegis.core;

import com.aegis.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() {}
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            String browser = ConfigReader.get("browser");
            if (!"chrome".equalsIgnoreCase(browser)) {
                throw new IllegalArgumentException("Starter currently supports chrome only; requested: " + browser);
            }
            DRIVER.set(new ChromeDriver());
        }
        return DRIVER.get();
    }
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
