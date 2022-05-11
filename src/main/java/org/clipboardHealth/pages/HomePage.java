package org.clipboardHealth.pages;

import org.clipboardHealth.domain.SortOption;
import org.clipboardHealth.seleniumUtils.WindowHandler;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomePage extends Page {
    @FindBy(xpath = "//*[@id='nav-main']//*[text()='All']")
    private WebElement all;
    @FindBy(xpath = "//*[@id='hmenu-content']//*[text()='TV, Appliances, Electronics']")
    private WebElement TVMenu;
    @FindBy(xpath = "//a[text()='Televisions']")
    private WebElement televisions;

    @FindBy(xpath = "//*[text()=' About this item ']")
    private WebElement aboutItem;
    private String brandXpath = "//span[text()='Brands']//parent::div//following-sibling::ul//descendant::*[text()='%s']";
    private String sortOptionXpath = "//a[text()='%s']";
    @FindBy(className = "a-dropdown-container")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//*[text()=' About this item ']//following-sibling::ul//child::li")
    List<WebElement> infoAboutItem;
    private String result = "//*[@data-cel-widget='search_result_%d']//descendant::a[2]";

    @Autowired
    private WindowHandler windowHandler;

    private String originalWindow;

    @Override
    public void initialize() {
        PageFactory.initElements(manager.getDriver(), this);
    }

    public HomePage viewAllTelevisions(){
        clickAfterElementIsClickable(all);
        clickAfterElementIsClickable(TVMenu);
        clickAfterElementIsClickable(televisions);
        return this;
    }

    public HomePage selectBrand(String brand){
        clickAfterElementIsClickable(String.format(brandXpath, brand));
        return this;
    }

    public HomePage sortBy(SortOption option){
        clickAfterElementIsClickable(sortingDropdown);
        clickAfterElementIsClickable(String.format(sortOptionXpath, option.code));
        return this;
    }

    public HomePage navigateToResult(int resultIndex){
        originalWindow = windowHandler.getOriginalWindow();
        var resultPath = String.format(result, resultIndex);
        waitTillVisibilityOf(resultPath);
        clickAfterElementIsClickable(resultPath);
        return this;
    }

    public boolean isAboutItemPresent(){
        windowHandler.switchToNewTab(originalWindow);
        try{
            waitTillVisibilityOf(aboutItem);
        }catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public List<String> getInfoAboutItem(){
        return infoAboutItem.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
