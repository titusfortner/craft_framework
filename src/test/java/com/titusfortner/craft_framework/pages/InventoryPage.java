package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InventoryPage extends BasePage{
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final By item1Link = By.id("item_1_title_link");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void viewBoltTShirtProduct() {
        driver.findElement(item1Link).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }

    public void addItemSuccessfully(String product) {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before + 1;

        addItem(product);

        try {
            wait.until((d) -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Adding item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemSuccessfully(String product) {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItem(product);

        try {
            wait.until((d) -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    private void addItem(String product) {
        String cssSelector = "button[data-test='add-to-cart-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    private void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }
}
