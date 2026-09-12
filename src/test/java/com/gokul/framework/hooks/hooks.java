package com.gokul.framework.hooks;

import com.gokul.framework.driver.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {
    @Before
    public void setUp()
    {
        BrowserManager.startBrowser();
    }
    @After
    public void tearDown()
    {
        BrowserManager.closeBrowser();
    }
}
