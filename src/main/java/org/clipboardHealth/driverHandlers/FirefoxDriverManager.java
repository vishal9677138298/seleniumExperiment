package org.clipboardHealth.driverHandlers;

import org.clipboardHealth.utils.DriverUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import java.io.File;
import java.io.IOException;

public class FirefoxDriverManager extends DriverManager{
    private GeckoDriverService firefoxService;

    @Override
    public void startService() {
        if(firefoxService == null) {
            try {
                firefoxService = new GeckoDriverService.Builder().usingDriverExecutable(new File(DriverUtils.resolveDriverPath()))
                        .usingAnyFreePort().build();
                firefoxService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        firefoxService.stop();
    }

    @Override
    public void startBrowser() {
        driver = new FirefoxDriver(firefoxService);

    }
}
