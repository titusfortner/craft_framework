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

        User lockedOutUser = new User();
        lockedOutUser.setUsername("locked_out_user");
        lockedOutUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(lockedOutUser.getUsername(), lockedOutUser.getPassword())
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        User validUser = new User();
        validUser.setUsername("standard_user");
        validUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword())
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = new User();
        validUser.setUsername("standard_user");
        validUser.setPassword("secret_sauce");

        homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword());

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
