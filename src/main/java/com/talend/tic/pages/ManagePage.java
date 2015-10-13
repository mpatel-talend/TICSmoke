package com.talend.tic.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.talend.tic.api.Assertion;




public class ManagePage extends PageBase{

	
	Assertion asserting = new Assertion();
	
	@FindBy(id = "mn-main-left-input-search")
	private WebElement searchBox;
	
	@FindBy(xpath = "//div[@class='item_list']/table[2]/tbody/tr/td/div[2]/span/span")
	private WebElement searchResult;
	
	private final String actionSearch = "//div[@class='item_list']/table[2]/tbody/tr/td/div[2]/span/span";
	
	@FindBy(id = "mn-main-right-button-create-new-action")
	private WebElement importFromExchange;
	
	@FindBy(id = "//div[@class='label_container'/input]")
	private WebElement exchangeSearchBox;

	@FindBy(css = "button[data-ng-click='addActionToIpaas(action)']")
	private WebElement addActivityFromExchangeButton;
	
	@FindBy(css = "input[placeholder='Talend Community username']")
	private WebElement exchangeUsername;

	@FindBy(css = "input[type='password']")
	private WebElement exchangePassword;
	
	@FindBy(css = "button[data-ng-click='marketplaceLogin()']")
	private WebElement exchangeLoginButton;
	
	@FindBy(id = "mn-dialog-new-action-button-cancel")
	private WebElement closeButton;
	
	@FindBy(id = "menu-header-link-flows")
	private WebElement menuFlowLink;
	
	
	
	
	public WebDriver getDriver(){
		
		
		if(null!=thread.get()){
			
			return super.thread.get();
		}

		return super.threadRemote.get();

	}
	
	/**
	 * clicks on import from exchange button
	 */
	public void clickImportFromExchangeButton() {
		super.waitForVisibilityOfElement(importFromExchange);
		super.highlightElement(importFromExchange);
		importFromExchange.click();

	}
	

	
	/**
	 * checks if action item is present in user's workspace
	 * @param _actionToBeFound
	 * @return
	 */
	public boolean checkAvailabilityOfAction(){
		
		List<WebElement> result = getDriver().findElements(By.xpath(actionSearch));
		boolean isActionPresent = false;
		
		if (result.size() > 0){
			
			isActionPresent = true;
		}
		
		return isActionPresent;
	}
	
	/**
	 * enters activity name in search box
	 */
	public void enterActivityInSearchBox(String _activity) {
		super.waitForVisibilityOfElement(searchBox);
		super.highlightElement(searchBox);
		searchBox.clear();
		searchBox.sendKeys(_activity);;

	}
	
	/**
	 * enters activity name in exchange search box
	 */
	public void enterActivityInExchangeSearchBox(String _activity) {
		super.waitForVisibilityOfElement(exchangeSearchBox);
		super.highlightElement(exchangeSearchBox);
		exchangeSearchBox.sendKeys(_activity);;

	}
	
	/**
	 * clicks on add activity from exchange button
	 */
	public void clickAddActivityFromExchangeButton() {
		super.waitForVisibilityOfElement(addActivityFromExchangeButton);
		super.highlightElement(addActivityFromExchangeButton);
		addActivityFromExchangeButton.click();

	}
	

	/**
	 * enters username in exchange login
	 */
	public void enterUsernameInExchange(String _username) {
		super.waitForVisibilityOfElement(exchangeUsername);
		super.highlightElement(exchangeUsername);
		exchangeUsername.sendKeys(_username);;

	}
	

	/**
	 * enters password in exchange login
	 */
	public void enterPasswordInExchange(String _password) {
		super.waitForVisibilityOfElement(exchangePassword);
		super.highlightElement(exchangePassword);
		exchangePassword.sendKeys(_password);;

	}
	
	/**
	 * clicks on login button for exchange
	 */
	public void clickLoginIntoExchangeButton() {
		super.waitForVisibilityOfElement(exchangeLoginButton);
		super.highlightElement(exchangeLoginButton);
		exchangeLoginButton.click();

	}
	
	/**
	 * clicks on close button
	 */
	public void clickCloseButton() {
		super.waitForVisibilityOfElement(closeButton);
		super.highlightElement(closeButton);
		closeButton.click();

	}
	
	/**
	 * clicks on the flow tab on menubar.
	 */
	public FlowsPage clickFlowTab() {
		super.waitForVisibilityOfElement(menuFlowLink);
		super.highlightElement(menuFlowLink);
		menuFlowLink.click();
		return PageBase.getPageFactory(getDriver(), FlowsPage.class);

	}
}