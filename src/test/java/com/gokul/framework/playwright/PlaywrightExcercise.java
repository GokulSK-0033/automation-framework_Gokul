package com.gokul.framework.playwright;

import com.microsoft.playwright.*;

public class PlaywrightExcercise {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context1 = browser.newContext();
        BrowserContext context2 = browser.newContext();
        Page page = context1.newPage();
        page.navigate("https://www.google.com");
//        page.waitForTimeout(5000);
        page.locator("//textarea[@id=\"ti6dpd\"]").fill("hello");
        System.out.println(page.title());
        System.out.println(page.url());

        Page amazon = context2.newPage();
        amazon.navigate("https://www.amazon.in");
        System.out.println(amazon.title());
        System.out.println(amazon.url());

        Page flipkart= context2.newPage();
        flipkart.navigate("https://www.flipkart.com");
        System.out.println(flipkart.title());
        System.out.println(flipkart.url());

        amazon.close();
        playwright.close();
    }
}
