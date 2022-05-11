package org.clipboardHealth;

import org.clipboardHealth.driverHandlers.DriverManager;
import org.clipboardHealth.pages.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    @Autowired
    private DriverManager manager;

    @BeforeAll
    void startService(){
        manager.startService();
    }

    @AfterAll
    void stopService(){
        manager.stopService();
    }

    protected void startBrowserAndInitializePage(Page page){
        manager.startBrowser();
        page.initialize();
    }

    protected void closeBrowser(){
        manager.stopBrowser();
    }
}
