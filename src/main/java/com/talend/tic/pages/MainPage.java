package com.talend.tic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



/**
 * page object for home(common) page of Talend Integration CLoud
 * @author mpatel
 *
 */
public class MainPage extends PageBase{


	
	@FindBy(id = "menu-header-link-flows")
	private WebElement menuFlowLink;
	
	@FindBy(id = "menu-header-link-welcome")
	private WebElement menuHomeLink;
	
	@FindBy(id = "menu-header-link-flowbuilder")
	private WebElement menuFlowBuilderLink;
	
	@FindBy(id = "menu-header-link-activity")
	private WebElement menuActivityLink;

	@FindBy(id = "menu-header-link-management")
	private WebElement menuManageLink;
	
	@FindBy(id = "menu-header-link-admin")
	private WebElement menuAdminLink;

	@FindBy(id = "menu-header-link-marketplace")
	private WebElement menuExchangeLink;
	
	@FindBy(id = "menu-header-link-community")
	private WebElement menuCommunityLink;
	
	@FindBy(id = "menu-header-link-help")
	private WebElement menuHelpLink;
	
	@FindBy(id = "menu-header-link-settings")
	private WebElement menuUserSettingsLink;
	
	@FindBy(xpath = "//div[@id='user_settings_container']/ul/li[2]/a")
	private WebElement menuLogOutLink;
	
	@FindBy(css = "a[data-ng-click='logout();']")
	private WebElement logOutButton;
	
	public WebDriver getDriver(){
		
		
		if(null!=thread.get()){
			
			return super.thread.get();
		}

		return super.threadRemote.get();

	}
	
	/**
	 * clicks on the home tab on menubar.
	 */
	public void clickHomeTab() {
		super.waitForVisibilityOfElement(menuHomeLink);
		super.highlightElement(menuHomeLink);
		menuHomeLink.click();

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
	
	/**
	 * clicks on the flowbuilder tab on menubar.
	 */
	public FlowBuilderPage clickFlowBuilderTab() {
		super.waitForVisibilityOfElement(menuFlowBuilderLink);
		super.highlightElement(menuFlowBuilderLink);
		menuFlowBuilderLink.click();
		return PageBase.getPageFactory(getDriver(), FlowBuilderPage.class);

	}
	
	/**
	 * clicks on the activity tab on menubar.
	 */
	public void clickActivityTab() {
		super.waitForVisibilityOfElement(menuActivityLink);
		super.highlightElement(menuActivityLink);
		menuActivityLink.click();

	}
	
	/**
	 * clicks on the manage tab on menubar.
	 */
	public ManagePage clickManageTab() {
		super.waitForVisibilityOfElement(menuManageLink);
		super.highlightElement(menuManageLink);
		menuManageLink.click();
		return PageBase.getPageFactory(getDriver(),ManagePage.class);

	}
	
	/**
	 * clicks on the admin tab on menubar.
	 */
	public void clickAdminTab() {
		super.waitForVisibilityOfElement(menuAdminLink);
		super.highlightElement(menuAdminLink);
		menuAdminLink.click();

	}
	
	/**
	 * clicks on the user setting icon on menubar.
	 */
	public void clickUserSettings() {
		super.waitForVisibilityOfElement(menuUserSettingsLink);
		super.highlightElement(menuUserSettingsLink);
		menuUserSettingsLink.click();

	}
	
	/**
	 * clicks on the logout button in user settings on menubar.
	 */
	public void clickLogOutButton() {
		super.waitForVisibilityOfElement(logOutButton);
		super.highlightElement(logOutButton);
		logOutButton.click();

	}
}