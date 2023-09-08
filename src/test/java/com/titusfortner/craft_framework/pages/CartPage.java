package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.Browser;
import com.titusfortner.craft_framework.elements.Element;
import com.titusfortner.craft_framework.elements.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CartPage extends BasePage {
    private final Element checkoutButton = browser.getElement(By.cssSelector("button[data-test='checkout']"));
    private final ElementList removeItemButtons = browser.getElements(By.cssSelector("button[data-test^='remove-']"));

    public CartPage(Browser browser) {
        super(browser);
    }

    public void checkout() {
        checkoutButton.click();
    }

    public void removeItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(browser);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItemButtons.getRandom().click();

        try {
            browser.waitUntil(() -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }
}
