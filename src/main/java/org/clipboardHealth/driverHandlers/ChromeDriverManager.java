package org.clipboardHealth.driverHandlers;

import org.clipboardHealth.utils.DriverUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;

public class ChromeDriverManager extends DriverManager{
    private ChromeDriverService chromeDriverService;

    @Override
    public void startService() {
        if(chromeDriverService==null) {
            try {
                chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(DriverUtils.resolveDriverPath()))
                        .usingAnyFreePort().build();
                chromeDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        chromeDriverService.stop();
    }

    @Override
    public void startBrowser() {
        if(driver==null) {
            driver = new ChromeDriver(chromeDriverService, getChromeOptions());
        }
    }

    private ChromeOptions getChromeOptions() {
        var options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--disable-notifications");
        return options;
    }
}
