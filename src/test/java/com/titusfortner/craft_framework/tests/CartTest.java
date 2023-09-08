package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.CartPage;
import com.titusfortner.craft_framework.pages.HomePage;
import com.titusfortner.craft_framework.pages.InventoryPage;
import com.titusfortner.craft_framework.pages.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    public InventoryPage login() {
        return new InventoryPage(driver);
    }

    @Test
    public void addFromProductPage() {
        InventoryPage inventoryPage = login();
        ProductPage productPage = inventoryPage.viewBoltTShirtProduct();

        productPage.addItemToCart();

        Assertions.assertEquals(1,
                productPage.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        InventoryPage inventoryPage = login();
        ProductPage productPage = inventoryPage.viewBoltTShirtProduct();

        productPage.addItemToCart();
        productPage.removeItemFromCart();

        Assertions.assertEquals(0,
                productPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem("onesie");

        Assertions.assertEquals(1,
                inventoryPage.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromInventoryPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem("bike-light");

        inventoryPage.removeItem("bike-light");

        Assertions.assertEquals(0,
                inventoryPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void removeFromCartPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem("backpack");
        CartPage cartPage = inventoryPage.goToCart();

        cartPage.removeItem("backpack");

        Assertions.assertEquals(0,
                cartPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }
}
