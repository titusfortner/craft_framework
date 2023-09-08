package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final RemoteWebDriver driver;
    private final By finishButton = By.cssSelector("button[data-test='finish']");

    public CheckoutPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFinishButton() {
        return driver.findElement(finishButton);
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public FinishPage finish() {
        driver.findElement(finishButton).click();
        return new FinishPage(driver);
    }
}
