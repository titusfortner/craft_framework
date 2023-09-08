package com.titusfortner.craft_framework.tests;

import com.titusfortner.craft_framework.Browser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;

public class BaseTest {
    Browser browser;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        RemoteWebDriver driver = new ChromeDriver(options);
        browser = new Browser(driver);

    }

    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                System.out.println("Test Failed!");
                browser.quit();
            } catch (Exception ignored) {
                // No Driver
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            try {
                System.out.println("Test Passed!");
                browser.quit();
            } catch (Exception ignored) {
                // No Driver
            }
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
        }
    }
}
