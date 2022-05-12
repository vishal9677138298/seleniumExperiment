package org.clipboardHealth;

import org.clipboardHealth.driverhandler.DriverManager;
import org.clipboardHealth.pages.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    @Autowired
    private DriverManager manager;

    @BeforeAll
    void setup(){
        if (AppConfig.executionEnvironment.equals("local")){
            manager.setUpDriver();
        }
    }

    protected void startBrowserAndInitialize(Page page){
        manager.startBrowser();
        page.initialize();
    }

    protected void closeBrowser(){
        manager.stopBrowser();
    }
}
