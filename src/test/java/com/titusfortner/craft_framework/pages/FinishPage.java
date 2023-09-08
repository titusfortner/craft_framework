package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final By completeText = By.className("complete-text");

    public FinishPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public boolean isComplete() {
        return driver.findElement(completeText).isDisplayed();
    }
}
