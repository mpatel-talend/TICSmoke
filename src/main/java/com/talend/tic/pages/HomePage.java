package com.talend.tic.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;




public class HomePage {

	
	
	@FindBy(id = "wl-title-button-view-activity")
	private WebElement viewActivityDashboard;

	@FindBy(className = "introjs-skipbutton")
	private WebElement homeSkipButton;
	
	@FindBy(id = "wlc-main-button-create-flow")
	private WebElement homeCreateNewFlow;

	

	
}