package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(browser);

        homePage.loginSuccessfully(User.valid());
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(browser);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(browser);

        Assertions.assertDoesNotThrow(() -> informationPage.addInformationSuccessfully());
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(browser);
        informationPage.addInformationSuccessfully();

        CheckoutPage checkoutPage = new CheckoutPage(browser);
        Assertions.assertDoesNotThrow(checkoutPage::finishSuccessfully);
    }
}
