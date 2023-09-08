package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final Element finishButton = new Element(driver, By.cssSelector("button[data-test='finish']"));

    public CheckoutPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void finishSuccessfully() {
        finishButton.click();
        try {
            wait.until((d) -> !URL.equals(d.getCurrentUrl()));
        } catch (TimeoutException ex) {
            FinishPage finishPage = new FinishPage(driver);
            if (!finishPage.isComplete()) {
                throw new PageValidationException("Checkout unsuccessful;");
            }
        }
    }
}
