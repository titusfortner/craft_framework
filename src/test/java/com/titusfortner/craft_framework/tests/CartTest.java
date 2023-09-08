package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(driver);

        homePage.loginSuccessfully(User.valid());
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);

        Assertions.assertDoesNotThrow(productPage::addItemToCartSuccessfully);
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCartSuccessfully();

        Assertions.assertDoesNotThrow(productPage::removeItemFromCartSuccessfully);
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assertions.assertDoesNotThrow(() -> inventoryPage.addItemSuccessfully("bike-light"));
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemSuccessfully("bike-light");

        Assertions.assertDoesNotThrow(() -> inventoryPage.removeItemSuccessfully("bike-light"));
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemSuccessfully("backpack");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assertions.assertDoesNotThrow(() -> cartPage.removeItemSuccessfully("backpack"));
    }
}
