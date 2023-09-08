package com.titusfortner.craft_framework.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Element {
    private final By locator;
    private final WebDriverWait wait;
    private final WebDriver driver;
    private WebElement cachedElement;

    public Element(RemoteWebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoreAll(List.of(ElementNotInteractableException.class, NoSuchElementException.class));
    }

    // Does not wait
    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }

    public void sendKeys(String value) {
        run(() -> getElement().sendKeys(value), "send keys " + value + " to element at: " + locator);
    }

    public void click() {
        run(() -> getElement().click(), "click element at: " + locator);
    }

    private WebElement getElement() {
        if (cachedElement == null) {
            cachedElement = driver.findElement(locator);
        }
        return cachedElement;
    }

    private void reset() {
        cachedElement = null;
    }

    private void run(Runnable block, String message) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                block.run();
                break;
            } catch (StaleElementReferenceException e) {
                reset();
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                long currentTime = System.currentTimeMillis();
                Duration duration = Duration.ofMillis(currentTime - startTime);

                if (duration.compareTo(Duration.ofSeconds(20)) > 0) {
                    String msg = "Unable to " + message + " after " + duration + " seconds";
                    throw new ElementValidationException(msg, e);
                }
            }
        }
    }
}
