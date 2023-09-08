package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.Browser;
import com.titusfortner.craft_framework.elements.Element;
import org.openqa.selenium.By;

public class FinishPage extends BasePage {
    private final Element completeText = browser.getElement(By.className("complete-text"));

    public FinishPage(Browser browser) {
        super(browser);
    }

    public boolean isComplete() {
        return completeText.isDisplayed();
    }
}
