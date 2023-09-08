package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
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

    private void removeItem(String product) {
        String cssSelector = "button[data-test='remove-sauce-labs-" + product + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }
}
