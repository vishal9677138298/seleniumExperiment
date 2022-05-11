package org.clipboardHealth.driverHandlers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;
    public abstract void startService();
    public abstract void stopService();
    public abstract void startBrowser();

    public WebDriver getDriver() {
        return driver;
    }

    public void stopBrowser() {
        driver.quit();
        driver = null;
    }

}
