package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.HeaderSection;
import com.titusfortner.craft_framework.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);

        homePage.login("locked_out_user", "secret_sauce");

        Assertions.assertTrue(homePage.isLockedOut(), "Error Not Found");
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        homePage.login("standard_user", "secret_sauce");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertTrue(headerSection.isLoggedIn(), "Login Not Successful");
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        homePage.login("standard_user", "secret_sauce");

        HeaderSection headerSection = new HeaderSection(driver);
        headerSection.logOut();

        Assertions.assertFalse(headerSection.isLoggedIn(), "Logout Not Successful");
    }
}
