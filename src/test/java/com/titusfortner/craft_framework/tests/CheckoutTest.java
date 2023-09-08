package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(driver);

        homePage.loginSuccessfully(User.valid());
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemSuccessfully("onesie");
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);

        Assertions.assertDoesNotThrow(() -> informationPage.addInformationSuccessfully("Luke", "Perry", "90210"));
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.addInformationSuccessfully("Luke", "Perry", "90210");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assertions.assertDoesNotThrow(checkoutPage::finishSuccessfully);
    }
}
