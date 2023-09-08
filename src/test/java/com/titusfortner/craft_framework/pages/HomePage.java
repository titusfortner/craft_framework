package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.data.User;
import com.titusfortner.craft_framework.elements.Element;
import com.titusfortner.craft_framework.elements.ElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/";
    private final Element usernameTextfield = new Element(driver, By.cssSelector("input[data-test='username']"));
    private final Element passwordTextfield = new Element(driver, By.cssSelector("input[data-test='password']"));
    private final Element loginButton = new Element(driver, By.cssSelector("input[data-test='login-button']"));
    private final ElementList errorElements = new ElementList(driver, By.cssSelector("[data-test=error]"));

    public static HomePage visit(RemoteWebDriver driver) {
        HomePage homePage = new HomePage(driver);
        driver.get(URL);
        return homePage;
    }

    public HomePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void loginUnsuccessfully(User user) {
        login(user);

        try {
            errorElements.waitUntilPresent();
        } catch (TimeoutException ex) {
            String url = driver.getCurrentUrl();
            throw new PageValidationException("Expected login errors, but none were found; current URL: " + url);
        }
    }

    public void loginSuccessfully(User user) {
        login(user);

        try {
            wait.until((d) -> !URL.equals(d.getCurrentUrl()));
        } catch (TimeoutException ex) {
            String additional = errorElements.isEmpty() ? "" : " found error: " + errorElements.getFirst().getText();
            throw new PageValidationException("User is not logged in;" + additional);
        }
    }

    private void login(User user) {
        usernameTextfield.sendKeys(user.getUsername());
        passwordTextfield.sendKeys(user.getPassword());
        loginButton.click();
    }
}
