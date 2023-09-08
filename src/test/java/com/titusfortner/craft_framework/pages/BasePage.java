package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public abstract class BasePage {
    protected RemoteWebDriver driver;
    protected final By menuButton = By.id("react-burger-menu-btn");
    protected final By logoutLink = By.id("logout_sidebar_link");
    protected final By shoppingCartBadge = By.className("shopping_cart_badge");

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = driver.findElements(shoppingCartBadge);
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public void logOut() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }

}
