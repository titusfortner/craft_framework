package com.titusfortner.craft_framework.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartTest {
    ChromeDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addFromProductPage() {
    }

    @Test
    public void removeFromProductPage() {
    }

    @Test
    public void addFromInventoryPage() {
    }

    @Test
    public void removeFromInventoryPage() {
    }

    @Test
    public void removeFromCartPage() {
    }
}
