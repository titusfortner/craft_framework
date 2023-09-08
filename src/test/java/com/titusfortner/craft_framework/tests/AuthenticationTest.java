package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.pages.HeaderSection;
import com.titusfortner.craft_framework.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully("locked_out_user", "secret_sauce")
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully("standard_user", "secret_sauce")
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        homePage.loginSuccessfully("standard_user", "secret_sauce");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
