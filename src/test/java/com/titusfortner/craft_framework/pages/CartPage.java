package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
