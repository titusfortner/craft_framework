package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.Browser;
import com.titusfortner.craft_framework.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CheckoutPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final Element finishButton = browser.getElement(By.cssSelector("button[data-test='finish']"));

    public CheckoutPage(Browser browser) {
        super(browser);
    }

    public void finishSuccessfully() {
        finishButton.click();
        try {
            browser.waitUntil(() -> !URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            FinishPage finishPage = new FinishPage(browser);
            if (!finishPage.isComplete()) {
                throw new PageValidationException("Checkout unsuccessful;");
            }
        }
    }
}
