package com.titusfortner.craft_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InformationPage {
    private final RemoteWebDriver driver;
    private final By firstNameElement = By.cssSelector("input[data-test='firstName']");
    private final By lastNameElement = By.cssSelector("input[data-test='lastName']");
    private final By postalCodeElement = By.cssSelector("input[data-test='postalCode']");
    private final By continueButton = By.cssSelector("input[data-test='continue']");

    public InformationPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstNameElement() {
        return driver.findElement(firstNameElement);
    }

    public WebElement getLastNameElement() {
        return driver.findElement(lastNameElement);
    }

    public WebElement getPostalCodeElement() {
        return driver.findElement(postalCodeElement);
    }

    public WebElement getContinueButton() {
        return driver.findElement(continueButton);
    }

    public CheckoutPage addInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameElement).sendKeys(firstName);
        driver.findElement(lastNameElement).sendKeys(lastName);
        driver.findElement(postalCodeElement).sendKeys(postalCode);
        driver.findElement(continueButton).click();

        return new CheckoutPage(driver);
    }

}
