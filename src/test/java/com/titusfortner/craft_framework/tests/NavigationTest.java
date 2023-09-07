package com.titusfortner.craft_framework.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTest {
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
    public void cancelFromCart() {
    }

    @Test
    public void cancelFromInfoPage() {
    }

    @Test
    public void cancelFromCheckoutPage() {
    }

    @Test
    public void startCheckout() {
    }
}
