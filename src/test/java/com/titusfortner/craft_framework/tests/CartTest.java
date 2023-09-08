package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(driver);
        homePage.login("standard_user", "secret_sauce");
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart();

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(1,
                headerSection.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart();

        productPage.removeItemFromCart();

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem("onesie");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(1,
                headerSection.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem("bike-light");

        inventoryPage.removeItem("bike-light");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem("backpack");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("backpack");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }
}
