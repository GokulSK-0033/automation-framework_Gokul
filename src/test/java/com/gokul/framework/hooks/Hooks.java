package com.gokul.framework.hooks;

import com.gokul.framework.driver.BrowserManager;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.ByteArrayInputStream;
import io.qameta.allure.Allure;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    @Before
    public void setUp()
    {
        BrowserManager.startBrowser();
    }
    @After
    public void tearDown(Scenario scenario)
    {
        if (scenario.isFailed()) {

            log.error("Scenario failed: {}", scenario.getName());

            Page page = BrowserManager.getPage();

            if (page != null) {

                byte[] screenshot = page.screenshot(
                        new Page.ScreenshotOptions()
                                .setFullPage(true)
                );

                Allure.addAttachment(
                        "Failure Screenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );

                log.info("Failure screenshot attached to Allure report");
            }
        }

        BrowserManager.closeBrowser();
    }
    }

