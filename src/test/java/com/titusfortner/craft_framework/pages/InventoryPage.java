package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InventoryPage extends BasePage{
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final By item1Link = By.id("item_1_title_link");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public void viewBoltTShirtProduct() {
        driver.findElement(item1Link).click();
    }

    public void addItem(String product) {
        String cssSelector = "button[data-test='add-to-cart-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
