package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.pages.HeaderSection;
import com.titusfortner.craft_framework.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(User.lockedOut())
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(User.valid())
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);

        homePage.loginSuccessfully(User.valid());

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
