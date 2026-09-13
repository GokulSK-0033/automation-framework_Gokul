package com.gokul.framework.pages;

import com.gokul.framework.config.ConfigReader;
import com.gokul.framework.driver.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {
    private Page page;
    private Locator searchBox;
    private Locator searchButton;
    String url= ConfigReader.getProperty("baseUrl");
    public HomePage() {
        this.page= BrowserManager.getPage();
        this.searchBox=page.locator("#twotabsearchtextbox");
        this.searchButton=page.locator("#nav-search-submit-text");
    }
    public void navigateAmazon(){
        page.navigate(url);
    }
    public void enterTextSearchbox(String searchText){
        searchBox.fill(searchText);
        searchButton.click();
        assertThat(page).hasTitle("Amazon.in");
    }

}
