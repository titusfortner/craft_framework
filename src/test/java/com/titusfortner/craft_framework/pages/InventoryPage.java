package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class InventoryPage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final RemoteWebDriver driver;
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By item1Link = By.id("item_1_title_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
        if (!isOnPage()) {
            HomePage homePage = new HomePage(driver);
            homePage.login("standard_user", "secret_sauce");
        }
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(shoppingCartBadge);
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public void logOut() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }

    public ProductPage viewBoltTShirtProduct() {
        driver.findElement(item1Link).click();
        return new ProductPage(driver);
    }

    public void addItem(String product) {
        String cssSelector = "button[data-test='add-to-cart-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = getCartNumberElements();
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public CartPage goToCart() {
        driver.findElement(shoppingCartLink).click();
        return new CartPage(driver);
    }
}
