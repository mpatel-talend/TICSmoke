package com.talend.tic.scripts;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.talend.tic.pages.FlowBuilderPage;
import com.talend.tic.pages.FlowsPage;
import com.talend.tic.pages.LoginPage;
import com.talend.tic.pages.MainPage;
import com.talend.tic.pages.ManagePage;
import com.talend.tic.pages.PageBase;
import com.talend.tic.utilities.ConfigurationReader;
import com.talend.tic.utilities.CrossBrowser;
import com.talend.tic.utilities.LogConfig;

public class CommonScript {

	private WebDriver driver;
	private LoginPage pageLogin;
	private MainPage pageMain;
	private FlowsPage pageFlow;
	private FlowBuilderPage pageFlowBuilder;
	private ManagePage pageManage;
	private PageBase base;
	Logger log = Logger.getLogger(CommonScript.class);
	long start;

	
	//@Parameters(value = { "browser", "environment" })
	
		public MainPage setupTest(String browser, String environment) {
			try {

				/**setting http/https proxies**/
				System.getProperties().put("java.net.preferIPv4Stack", "true");
				System.getProperties().put("https.proxyHost", "195.233.25.20");
				System.getProperties().put("https.proxyPort", "8080");
				System.getProperties().put("https.nonProxyHosts", "localhost|127.0.0.1");
				System.getProperties().put("http.proxyHost", "195.233.25.20");
				System.getProperties().put("http.proxyPort", "8080");
				System.getProperties().put("http.nonProxyHosts", "localhost|127.0.0.1");

				
				start = System.currentTimeMillis();
				LogConfig.setLogger();

//				String browser = "firefox";
//				String environment = "local";

				log.info("retrieving the browser #" + browser + " on #" + 
						environment +" environment.");
				driver=CrossBrowser.getBrowser(browser, environment);

				driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

				driver.manage().window().maximize();

				log.info("loading the locators in the driver");
				pageLogin = PageBase.getPageFactory(driver,LoginPage.class);

				String baseURL = ConfigurationReader.get("baseURL");
				log.info("navigating to the URL - "+ baseURL);
				pageLogin.open(baseURL);

				String username = ConfigurationReader.get("username");
				log.info("Entering username - " + username);
				pageLogin.enterUserName(username);

				String password = ConfigurationReader.get("password");
				log.info("Entering password");
				pageLogin.enterPassword(password);

				log.info("clicking login button");
				pageMain=pageLogin.clickLoginButton();
				log.info("Logging in!!");

				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			return pageMain;
		}	
		
		/**
		 * creates the flow in the flow builder
		 * @return current instance of {@link FlowBuilderPage}
		 */
		public FlowBuilderPage createFlow(MainPage pageMain){

			
			log.info("go to manage page");
			pageManage=pageMain.clickManageTab();


			List<String> activities = new LinkedList<String>();

			activities.add(ConfigurationReader.get("sourceActivity"));
			activities.add(ConfigurationReader.get("stepActivity"));
			activities.add(ConfigurationReader.get("targetActivity"));

			int counter = 0;
			boolean isActivityPresent;

			while(counter < activities.size()){

				log.info("enter source activity name in search box");
				pageManage.enterActivityInSearchBox(activities.get(counter));

				log.info("check if Activity exist in Personal workspace");
				isActivityPresent = pageManage.checkAvailabilityOfAction();

				if(!isActivityPresent){

					log.info("Activity item #"+ activities.get(counter) + " is not present in workspace");
					log.info("Getting it from Exchange");
					log.info("clicking on Import From Exchange Button"); 
					pageManage.clickImportFromExchangeButton();

					if(counter == 0){

						log.info("Probably first time import from exchange, so waiting for "
								+ "3 seconds to get exchange loaded properly");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {

							log.error("Encountered an Interrupted exception while waiting.");
						}

					}

					log.info("Searching activity in Exchange search box");
					pageManage.enterActivityInExchangeSearchBox(activities.get(counter));

					log.info("clicking on add action button");
					pageManage.clickAddActivityFromExchangeButton();

					String exchangeUsername = ConfigurationReader.get("exchangeUsername");
					String exchangePassword = ConfigurationReader.get("exchangePassword");

					log.info("logging into Exchange");
					log.info("enter exchange username - "+ exchangeUsername);
					pageManage.enterUsernameInExchange(exchangeUsername);

					log.info("entering exchange password - "+ exchangePassword);
					pageManage.enterPasswordInExchange(exchangePassword);

					log.info("clicking on exchange login button");
					pageManage.clickLoginIntoExchangeButton();

					log.info("clicking on close button");
					pageManage.clickCloseButton();

				}

				counter++;
			}
			log.info("clicking on Flows tab on menu bar");
			pageFlow = pageManage.clickFlowTab();

			log.info("clicking on create new flow button");
			pageFlowBuilder = pageFlow.clickCreateNewFlowButton();

			log.info("entering Flow Name");
			pageFlowBuilder.enterFlowName(ConfigurationReader.get("flowName"));	  

			log.info("clicking add source activity icon");
			pageFlowBuilder.clickSourceButton();

			log.info("searching and adding source action #"+activities.get(0)+" in search box");
			pageFlowBuilder.searchActionAddAction(activities.get(0));


			String input = ConfigurationReader.get("sourceInput");
			log.info("entering source input - "+input);
			pageFlowBuilder.enterSourceInput(input);

			log.info("clicking step process icon");
			pageFlowBuilder.clickAddStep();

			log.info("searching and adding step process");
			pageFlowBuilder.searchActionAddAction(activities.get(1));

			log.info("clicking on add targget action icon");
			pageFlowBuilder.clickTargetButton();

			log.info("searching and adding target action");
			pageFlowBuilder.searchActionAddAction(activities.get(2));

			log.info("clicking on step mapper icon");
			pageFlowBuilder.clickOnStepMapper();

			log.info("clicking on auto map button");
			pageFlowBuilder.clickOnAutoMap();

			log.info("clicking on target mapper icon");
			pageFlowBuilder.clickOnTargetMapper();

			log.info("clicking on auto map button");
			pageFlowBuilder.clickOnAutoMap();

			log.info("click on Save button");
			pageFlowBuilder.clickSaveOrUpdateButton();

			return pageFlowBuilder;
		}


		public void cleaningTask(FlowsPage _pageFlow){
			
			log.info("clicking on delete button to delete job");
			_pageFlow.clickDeleteJobButton();
			
			log.info("clicking on confirm job delete button");
			_pageFlow.clickConfirmDeleteButton();
			
			log.info("verify that deleted flow is not present in flows result");
			_pageFlow.verifyFlowIsNotPresntInFlows(ConfigurationReader.get("flowName"));
			
			log.info("go to main page");
			pageMain = _pageFlow.getMainPage();
			
			
			log.info("click on user settings on menu bar");
			pageMain.clickUserSettings();
			
			log.info("click log out button");
			pageMain.clickLogOutButton();
			
			if(null != pageManage){
			pageManage = null;
			}
			
			if(null != pageFlow){
				pageFlow = null;
			}
			
			if(null != pageFlowBuilder){
				pageFlowBuilder = null;
			}
			
			if(null != pageLogin){
				pageLogin = null;
			}
			
			log.info("closing the browser");
			pageMain.close();
		}
		
}
