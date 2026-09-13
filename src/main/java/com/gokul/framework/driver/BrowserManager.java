package com.gokul.framework.driver;

import com.gokul.framework.config.ConfigReader;
import com.microsoft.playwright.*;

public class BrowserManager {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void startBrowser() {

        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless")
        );

        String browserName = ConfigReader.getProperty("browser");

        if (browserName.equalsIgnoreCase("chromium")) {

            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(headless)
            );

        } else if (browserName.equalsIgnoreCase("firefox")) {

            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless(headless)
            );

        } else if (browserName.equalsIgnoreCase("webkit")) {

            browser = playwright.webkit().launch(
                    new BrowserType.LaunchOptions().setHeadless(headless)
            );

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browserName
            );
        }

        context = browser.newContext();
        page = context.newPage();
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}