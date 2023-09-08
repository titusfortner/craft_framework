package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage{
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final Element item1Link = new Element(driver, By.id("item_1_title_link"));
    private final Element shoppingCartLink = new Element(driver, By.className("shopping_cart_link"));
    private final By addItemButton = By.cssSelector("button[data-test^='add-to-cart-']");
    private final By removeItemButton = By.cssSelector("button[data-test^='remove-']");

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void viewBoltTShirtProduct() {
        item1Link.click();
    }

    public void goToCart() {
        shoppingCartLink.click();
    }

    public void addItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before + 1;

        addItem();

        try {
            wait.until((d) -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Adding item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItem();

        try {
            wait.until((d) -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    private void addItem() {
        List<WebElement> items = driver.findElements(addItemButton);
        items.get(new Random().nextInt(items.size())).click();
    }

    private void removeItem() {
        List<WebElement> items = driver.findElements(removeItemButton);
        items.get(new Random().nextInt(items.size())).click();
    }
}
