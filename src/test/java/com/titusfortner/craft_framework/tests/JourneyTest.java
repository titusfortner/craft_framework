package com.titusfortner.craft_framework.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JourneyTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userJourney() throws InterruptedException {
        // Go to Sauce Demo Site
        // Attempt Sign in with locked user
        // Validate error
        // Sign in with Standard User
        // Validate authenticated
        // Log Out
        // Validate unauthenticated
        // Log In Again
        // Open Bolt T-Shirt About Page
        // Verify correct page
        // Add a Bolt T-Shirt to the Cart
        // Verify in cart
        // Remove Item
        // Verify not in cart
        // Go back to Products page
        // Verify correct page
        // Add a Fleece Jacket, Bike Light & Backpack to the Cart
        // Verify 3 items in cart
        // Remove Bike Light
        // Verify 2 items in art
        // Go to Cart Page
        // Verify Jacket & backpack are in cart
        // Remove Backpack
        // Verify backpack not in cart
        // Back to Shopping
        // Verify correct page
        // Back to Cart
        // Go to Checkout Page
        // Verify correct page
        // Cancel
        // Verify correct page
        // Go back to Checkout Page
        // Select Continue
        // Verify errors
        // Enter info and continue
        // Verify correct page
        // Cancel
        // Verify correct page
        // Go to cart
        // Go to checkout
        // re-enter info
        // Verify correct item
        // Finish
        // Verify success message
    }
}
