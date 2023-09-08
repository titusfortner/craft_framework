package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(driver);
        homePage.login("standard_user", "secret_sauce");
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem("onesie");
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();

        InformationPage informationPage = new InformationPage(driver);
        informationPage.addInformation("Luke", "Perry", "90210");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assertions.assertTrue(checkoutPage.isOnPage(),"Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();

        InformationPage informationPage = new InformationPage(driver);
        informationPage.addInformation("Luke", "Perry", "90210");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.finish();

        FinishPage finish = new FinishPage(driver);
        Assertions.assertTrue(finish.isOnPage());
        Assertions.assertTrue(finish.isComplete());
    }
}
