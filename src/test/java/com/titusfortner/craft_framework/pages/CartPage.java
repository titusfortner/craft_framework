package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class CartPage {
    private final RemoteWebDriver driver;
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRemoveBackPackButton() {
        return driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-backpack']"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(checkoutButton);
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(shoppingCartBadge);
    }

    public void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public InformationPage checkout() {
        driver.findElement(checkoutButton).click();
        return new InformationPage(driver);
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = getCartNumberElements();
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }
}
