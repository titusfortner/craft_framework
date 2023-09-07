package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.HomePage;
import com.titusfortner.craft_framework.pages.InventoryPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = new HomePage(driver);

        homePage.login("locked_out_user", "secret_sauce");

        Assertions.assertTrue(homePage.isLockedOut(), "Error Not Found");
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);

        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        Assertions.assertTrue(inventoryPage.isOnPage(), "Login Not Successful");
    }

    @Test
    public void logout() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        inventoryPage.logOut();

        Assertions.assertTrue(homePage.isOnPage(), "Logout Not Successful");
    }
}
