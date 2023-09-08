package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.Browser;
import com.titusfortner.craft_framework.elements.Element;
import com.titusfortner.craft_framework.elements.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class HeaderSection extends BasePage {
    private final Element menuButton = browser.getElement(By.id("react-burger-menu-btn"));
    private final Element logoutLink = browser.getElement(By.id("logout_sidebar_link"));
    private final ElementList shoppingCartItems = browser.getElements(By.className("shopping_cart_badge"));

    public HeaderSection(Browser browser) {
        super(browser);
    }

    public Integer getNumberItemsInCart() {
        shoppingCartItems.reset();
        if (shoppingCartItems.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(shoppingCartItems.getFirst().getText());
        }
    }

    public void logOutSuccessfully() {
        logOut();

        try {
            browser.waitUntil(() -> !InventoryPage.URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            throw new PageValidationException("User is still logged in;");
        }
    }

    private void logOut() {
        menuButton.click();
        logoutLink.click();
    }
}
