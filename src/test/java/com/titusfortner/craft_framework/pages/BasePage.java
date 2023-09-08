package com.titusfortner.craft_framework.pages;

import com.titusfortner.craft_framework.Browser;

public abstract class BasePage {
    protected Browser browser;

    public BasePage(Browser browser) {
        this.browser = browser;
    }
}
