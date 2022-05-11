package org.clipboardHealth;

import org.clipboardHealth.domain.SortOption;
import org.clipboardHealth.driverHandlers.DriverManager;
import org.clipboardHealth.pages.HomePage;
import org.clipboardHealth.utils.PropertiesHolder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, PropertiesHolder.class})
public class AboutItemTest extends BaseTest{
    @Autowired
    private PropertiesHolder properties;
    @Autowired
    private HomePage homePage;

    @BeforeEach
    void openBrowser(){
        startBrowserAndInitializePage(homePage);
    }

    @Test
    void aboutItemTest() {
        homePage.goTo(properties.getAppUrl());
        var isAboutItemPresent = homePage.viewAllTelevisions()
                .selectBrand("Samsung")
                .sortBy(SortOption.PRICE_HIGH_TO_LOW)
                .navigateToResult(2)
                .isAboutItemPresent();
        assertThat("Information about item is not present",isAboutItemPresent, is(equalTo(true)));
        homePage.getInfoAboutItem().forEach(System.out::println);
    }

    @AfterEach
    void close_browser(){
        closeBrowser();
    }

}
