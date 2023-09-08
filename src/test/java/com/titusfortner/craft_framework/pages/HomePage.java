package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    public static final String URL = "https://www.saucedemo.com/";
    private final RemoteWebDriver driver;
    private final By usernameTextfield = By.cssSelector("input[data-test='username']");
    private final By passwordTextfield = By.cssSelector("input[data-test='password']");
    private final By loginButton = By.cssSelector("input[data-test='login-button']");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public static HomePage visit(RemoteWebDriver driver) {
        HomePage homePage = new HomePage(driver);
        driver.get(URL);
        return homePage;
    }

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public InventoryPage login(String username, String password) {
        driver.findElement(usernameTextfield).sendKeys(username);
        driver.findElement(passwordTextfield).sendKeys(password);
        driver.findElement(loginButton).click();

        return new InventoryPage(driver);
    }

    public boolean isLockedOut() {
        return driver.findElement(errorElement).getText().contains("Sorry, this user has been locked out");
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }
}