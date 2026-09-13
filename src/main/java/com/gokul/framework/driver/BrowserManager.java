package com.gokul.framework.driver;

import com.gokul.framework.config.ConfigReader;
import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserManager {

    private static final Logger log = LogManager.getLogger(BrowserManager.class);
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void startBrowser() {
        log.info("Starting Playwright");
        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless")
        );

        String browserName = ConfigReader.getProperty("browser");
        log.info("Starting Playwright Browser " + browserName);
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
        log.info("Context and page created Successfully");
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {
        log.info("Closing browser");
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
        log.info("Playwright execution completed");
    }
}