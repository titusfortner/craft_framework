package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    public InventoryPage login() {
        HomePage homePage = new HomePage(driver);
        return homePage.login("standard_user", "secret_sauce");
    }

    public InformationPage goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem("onesie");
        CartPage cartPage = inventoryPage.goToCart();
        return cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        InformationPage informationPage = goToCheckoutWithItem();

        CheckoutPage checkoutPage = informationPage.addInformation("Luke", "Perry", "90210");

        Assertions.assertTrue(checkoutPage.isOnPage(),"Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        login();
        InformationPage informationPage = goToCheckoutWithItem();
        CheckoutPage checkoutPage = informationPage.addInformation("Luke", "Perry", "90210");

        FinishPage finish = checkoutPage.finish();

        Assertions.assertTrue(finish.isOnPage());
        Assertions.assertTrue(finish.isComplete());
    }
}
