package com.titusfortner.craft_framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ElementList {
    private final By locator;
    private final RemoteWebDriver driver;
    private List<WebElement> cachedElements;
    private final WebDriverWait wait;

    public ElementList(RemoteWebDriver driver, By locator) {
        this.locator = locator;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isEmpty() {
        return getElements().isEmpty();
    }

    public WebElement get(int i) {
        waitUntilPresent();
        return getElements().get(i);
    }

    public WebElement getFirst() {
        return get(0);
    }

    public WebElement getRandom() {
        waitUntilPresent();
        return getElements().get(new Random().nextInt(cachedElements.size()));
    }

    public void waitUntilPresent() {
        wait.until((d) -> {
            reset();
            return !getElements().isEmpty();
        });
    }

    private List<WebElement> getElements() {
        if (cachedElements == null) {
            cachedElements = driver.findElements(locator);
        }
        return cachedElements;
    }

    public void reset() {
        cachedElements = null;
    }
}
