package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = new HomePage(driver);
        driver.get(HomePage.URL);
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();
        inventoryPage.getCartImageLink().click();

        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();

        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");
        informationPage.getContinueButton().click();

        Assertions.assertEquals(CheckoutPage.URL,
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");
        informationPage.getContinueButton().click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getFinishButton().click();

        Assertions.assertEquals(FinishPage.URL, driver.getCurrentUrl());

        FinishPage finishPage = new FinishPage(driver);
        Assertions.assertTrue(finishPage.getCompleteElement().isDisplayed());
    }
}
