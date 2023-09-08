package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.elements.Element;
import com.titusfortner.craft_framework.elements.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends BasePage {
    private final Element checkoutButton = new Element(driver, By.cssSelector("button[data-test='checkout']"));
    private final ElementList removeItemButtons = new ElementList(driver, By.cssSelector("button[data-test^='remove-']"));

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void checkout() {
        checkoutButton.click();
    }

    public void removeItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItemButtons.getRandom().click();

        try {
            wait.until((d) -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }
}
