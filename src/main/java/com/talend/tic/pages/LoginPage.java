
package com.talend.tic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * page object for Login page of Talend Integration Cloud
 * @author mpatel
 *
 */
public class LoginPage extends PageBase{

	@FindBy(id = "lg-main-input-usrname")
	private WebElement username;

	@FindBy(id = "lg-main-input-password")
	private WebElement password;
	
	@FindBy(id = "lg-main-button-login")
	private WebElement loginButton;

	
	public WebDriver getDriver(){
		
		
		if(null!=thread.get()){
			
			return super.thread.get();
		}

		return super.threadRemote.get();

	}
	
	
	/**
	 * enters the username
	 * @param _userName
	 */
	public void enterUserName(String _userName) {
		super.waitForVisibilityOfElement(username);
		super.highlightElement(username);
		username.sendKeys(_userName);

	}
	
	/**
	 * enters the password
	 * @param _password
	 */
	public void enterPassword (String _password) {
		super.waitForVisibilityOfElement(password);
		super.highlightElement(password);
		password.sendKeys(_password);

	}
	
	/**
	 * clicks on the login button
	 */
	public MainPage clickLoginButton() {
		super.waitForVisibilityOfElement(loginButton);
		super.highlightElement(loginButton);
		loginButton.click();
		return PageBase.getPageFactory(getDriver(), MainPage.class);

	}
}