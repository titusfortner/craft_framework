package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.elements.Element;
import com.titusfortner.craft_framework.elements.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HeaderSection extends BasePage {
    private final Element menuButton = new Element(driver, By.id("react-burger-menu-btn"));
    private final Element logoutLink = new Element(driver, By.id("logout_sidebar_link"));
    private final ElementList shoppingCartItems = new ElementList(driver, By.className("shopping_cart_badge"));

    public HeaderSection(RemoteWebDriver driver) {
        super(driver);
    }

    public Integer getNumberItemsInCart() {
        shoppingCartItems.reset();
        if (shoppingCartItems.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(shoppingCartItems.getFirst().getText());
        }
    }

    public boolean isLoggedIn() {
        return InventoryPage.URL.equals(driver.getCurrentUrl());
    }

    public void logOutSuccessfully() {
        logOut();

        try {
            wait.until((d) -> !isLoggedIn());
        } catch (TimeoutException ex) {
            throw new PageValidationException("User is still logged in;");
        }
    }

    private void logOut() {
        menuButton.click();
        logoutLink.click();
    }
}
