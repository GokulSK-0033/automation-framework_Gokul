package com.gokul.framework.pages;

import com.gokul.framework.config.ConfigReader;
import com.gokul.framework.driver.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {
    private static final Logger log = LogManager.getLogger(HomePage.class);
    private Page page;
    private Locator searchBox;
    private Locator searchButton;
    String url= ConfigReader.getProperty("baseUrl");
    public HomePage() {
        this.page= BrowserManager.getPage();
        log.info("Page created");
        this.searchBox=page.locator("#twotabsearchtextbox");
        this.searchButton=page.locator("#nav-search-submit-text");
    }
    public void navigateAmazon(){
        page.navigate(url);
        log.info("Navigated to Amazon");
    }
    public void enterTextSearchbox(String searchText){
        searchBox.fill(searchText);
        log.info("Enter Text Searchbox");
        searchButton.click();
        log.info("Search button pressed");
        assertThat(page).hasTitle("Amazon.in");
    }

}
