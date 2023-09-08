package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage extends BasePage {
    private final Element completeText = new Element(driver, By.className("complete-text"));

    public FinishPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isComplete() {
        return completeText.isDisplayed();
    }
}
