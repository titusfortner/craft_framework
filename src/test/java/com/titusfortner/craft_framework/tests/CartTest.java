package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(browser);

        homePage.loginSuccessfully(User.valid());
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(browser);

        Assertions.assertDoesNotThrow(productPage::addItemToCartSuccessfully);
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(browser);
        productPage.addItemToCartSuccessfully();

        Assertions.assertDoesNotThrow(productPage::removeItemFromCartSuccessfully);
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);

        Assertions.assertDoesNotThrow(inventoryPage::addItemSuccessfully);
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();

        Assertions.assertDoesNotThrow(inventoryPage::removeItemSuccessfully);
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(browser);
        Assertions.assertDoesNotThrow(cartPage::removeItemSuccessfully);
    }
}
