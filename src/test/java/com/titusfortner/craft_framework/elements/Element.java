package com.titusfortner.craft_framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Element {
    private final By locator;
    private final WebDriverWait wait;
    private final WebDriver driver;

    public Element(RemoteWebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoreAll(List.of(ElementNotInteractableException.class, NoSuchElementException.class));
    }

    public boolean isDisplayed() {
        return driver.findElement(locator).isDisplayed();
    }

    public void sendKeys(String value) {
        wait.until((d) -> {
            d.findElement(locator).sendKeys(value);
            return true;
        });
    }

    public void click() {
        wait.until((d) -> {
            d.findElement(locator).click();
            return true;
        });
    }
}
