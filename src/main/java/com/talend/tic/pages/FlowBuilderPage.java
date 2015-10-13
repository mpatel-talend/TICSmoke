package com.talend.tic.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.talend.tic.api.Assertion;
import com.talend.tic.utilities.ConfigurationReader;


/**
 * page object for FlowBuilder page of Talend Integration Cloud
 * @author mpatel
 *
 */
public class FlowBuilderPage extends PageBase{

	Logger log = Logger.getLogger(FlowBuilderPage.class);

	@FindBy(id = "fb-title-input-flow-name")
	private WebElement enterFlowName;

	@FindBy(className = "flow_description_prompt")
	private WebElement enterDescription;

	@FindBy(id = "fb-title-button-save-update")
	private WebElement saveOrUpdate;

	@FindBy(id = "fb-title-button-close")
	private WebElement close;

	@FindBy(id = "fb-main-top-button-click-source")
	private WebElement source;

	@FindBy(id = "fb-main-top-button-click-target")
	private WebElement target;

	@FindBy(id = "fb-main-top-button-add-step")
	private WebElement addStep;

	@FindBy(id = "fb-main-top-button-node-test")
	private WebElement test;

	@FindBy(id = "fb-main-top-button-node-go-live")
	private WebElement goLive;

	@FindBy(id = "fb-dialog-choose-action-input-filter-search")
	private WebElement searchFilter;

	@FindBy(css = "span.highlight")
	private WebElement firstResult;

	@FindBy(id = "parameter_name0")
	private WebElement sayHelloSourceInput;

	@FindBy(id = "fb-main-top-button-preview-source")
	private WebElement previewSource;

	@FindBy(xpath = "//a[@id='fb-main-top-button-preview-source']/span")
	private WebElement previewSourceInlineData;

	@FindBy(id = "fb-main-top-button-preview-target")
	private WebElement previewTarget;

	@FindBy(xpath = "//a[@id='fb-main-top-button-preview-target']/span")
	private WebElement previewTargetInlineData;

	@FindBy(id = "fb-main-top-button-preview-step0")
	private WebElement previewStep;

	@FindBy(xpath = "//a[@id='fb-main-top-button-preview-step0']/span")
	private WebElement previewStepInlineData;

	@FindBy(id = "fb-main-top-button-map-step0")
	private WebElement mapStep0;

	@FindBy(id = "fb-main-right-button-auto-map")
	private WebElement autoMap;

	@FindBy(id = "fb-main-top-button-map-target")
	private WebElement mapTarget;

	@FindBy(id = "fb-main-right-button-test-schedule-apply")
	private WebElement testSchedule;

	@FindBy(xpath = "//tr[@class='ng-scope']/td")
	private WebElement previewData;

	@FindBy(id = "fb-main-right-select-live-schedule-type")
	private WebElement scheduleType;

	@FindBy(id = "fb-main-right-button-live-schedule-apply")
	private WebElement scheduleGo;

	@FindBy(name = "timeZone_input")
	private WebElement timeZone;
	
	@FindBy(id = "fb-main-right-text-live-schedule-intervalStartTime")
	private WebElement runStartTime;

	@FindBy(xpath = "//ul[@id='fb-main-right-select-live-schedule-time-zone_listbox']/li")
	private WebElement berlinTimeZone;
	
	public WebDriver getDriver(){


		if(null!=thread.get()){

			return super.thread.get();
		}

		return super.threadRemote.get();

	}

	/**
	 * enters the flow name
	 */
	public void enterFlowName(String _flowName) {
		super.waitForVisibilityOfElement(enterFlowName);
		super.highlightElement(enterFlowName);
		enterFlowName.sendKeys(_flowName);;

	}

	/**
	 * enters the schedule run start time
	 */
	public void enterScheduleRunTime(String _runTime) {
		super.waitForVisibilityOfElement(runStartTime);
		super.highlightElement(runStartTime);
		runStartTime.clear();
		runStartTime.sendKeys(_runTime);

	}
	/**
	 * enters flow description
	 */
	public void enterFlowDescription(String _flowDescription) {
		super.waitForVisibilityOfElement(enterDescription);
		super.highlightElement(enterDescription);
		enterDescription.sendKeys(_flowDescription);

	}

	/**
	 * clicks on save/update button
	 */
	public void clickSaveOrUpdateButton() {
		super.waitForVisibilityOfElement(saveOrUpdate);
		super.highlightElement(saveOrUpdate);
		saveOrUpdate.click();

	}

	/**
	 * clicks on close button
	 */
	public void clickCloseButton() {
		super.waitForVisibilityOfElement(close);
		super.highlightElement(close);
		close.click();

	}

	/**
	 * clicks on source button
	 */
	public void clickSourceButton() {
		super.waitForVisibilityOfElement(source);
		super.highlightElement(source);
		source.click();	
	}

	/**
	 * @param _action action item
	 * search action and add it 
	 */
	public void searchActionAddAction(String _action) {
		super.waitForVisibilityOfElement(searchFilter);
		super.highlightElement(searchFilter);
		searchFilter.clear();
		searchFilter.sendKeys(_action);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Encountered interrupted exception");
		}

		//add action in flow builder
		super.waitForVisibilityOfElement(firstResult);
		super.highlightElement(firstResult);
		firstResult.click();

	}


	/**
	 * clicks on target button
	 */
	public void clickTargetButton() {
		super.waitForVisibilityOfElement(target);
		super.highlightElement(target);
		target.click();	
	}

	/**
	 * clicks on addStep icon
	 */
	public void clickAddStep() {
		super.waitForVisibilityOfElement(addStep);
		super.highlightElement(addStep);
		addStep.click();	
	}

	/**
	 * @param _sourceInput
	 * enters the source input parameters
	 */
	public void enterSourceInput(String _sourceInput) {
		super.waitForVisibilityOfElement(sayHelloSourceInput);
		super.highlightElement(sayHelloSourceInput);
		sayHelloSourceInput.clear();
		sayHelloSourceInput.sendKeys(_sourceInput);

	}

	/**
	 * clicks on step mapper icon
	 */
	public void clickOnStepMapper() {
		super.waitForVisibilityOfElement(mapStep0);
		super.highlightElement(mapStep0);
		mapStep0.click();	
	}

	/**
	 * clicks on target mapper icon
	 */
	public void clickOnTargetMapper() {
		super.waitForVisibilityOfElement(mapTarget);
		super.highlightElement(mapTarget);
		mapTarget.click();	
	}

	/**
	 * clicks on automap button
	 */
	public void clickOnAutoMap() {
		super.waitForVisibilityOfElement(autoMap);
		super.highlightElement(autoMap);
		autoMap.click();	
	}


	/**
	 * clicks on test button
	 */
	public void clickOnTest() {
		super.waitForVisibilityOfElement(test);
		super.highlightElement(test);
		test.click();	
	}

	/**
	 * clicks on test schedule button
	 */
	public void clickOnTestSchedule() {
		super.waitForVisibilityOfElement(testSchedule);
		super.highlightElement(testSchedule);
		testSchedule.click();	
	}

	/**
	 * clicks on go live button
	 */
	public void clickOnGoLive() {
		super.waitForVisibilityOfElement(goLive);
		super.highlightElement(goLive);
		goLive.click();	
	}

	/**
	 * clicks on preview source data icon
	 */
	public void clickOnPreviewSourceDataIcon() {
		super.waitForVisibilityOfElement(previewSource);
		super.highlightElement(previewSource);
		previewSource.click();	
	}

	/**
	 * clicks on preview step data icon
	 */
	public void clickOnPreviewStepDataIcon() {
		super.waitForVisibilityOfElement(previewStep);
		super.highlightElement(previewStep);
		previewStep.click();	
	}

	/**
	 * clicks on preview target data icon
	 */
	public void clickOnPreviewTargetDataIcon() {
		super.waitForVisibilityOfElement(previewTarget);
		super.highlightElement(previewTarget);
		previewTarget.click();	
	}

	/**
	 * selects go live schedule
	 * @param _schedule
	 */
	public void selectGoLiveSchedule(String _schedule) {
		super.waitForVisibilityOfElement(scheduleType);
		super.highlightElement(scheduleType);
		new Select(scheduleType).selectByVisibleText(_schedule);
		super.pressTab();
	}


	/**
	 * enters go live time zone
	 * @param _timeZone
	 */
	public void selectTimeZone(String _timeZone) {
		super.waitForVisibilityOfElement(timeZone);
		super.highlightElement(timeZone);
		timeZone.clear();
		timeZone.sendKeys(_timeZone);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("encountered interrupted error while putting thread to sleep");
		}
		
		berlinTimeZone.click();
		super.pressTab();

	}
	
	/**
	 * selects go live environment
	 * @param _environment
	 */
	public void selectGoLiveEnvironment(String _environment) {
		super.waitForVisibilityOfElement(scheduleType);
		super.highlightElement(scheduleType);
		new Select(scheduleType).selectByVisibleText(_environment);
		super.pressTab();
	}

	/**
	 * clicks on apply schedule and go live button
	 */
	public FlowsPage clickOnApplyScheduleForGoLiveEnvironment() {
		super.waitForVisibilityOfElement(scheduleGo);
		super.highlightElement(scheduleGo);
		scheduleGo.click();	
		return PageBase.getPageFactory(getDriver(), FlowsPage.class);
	}

	/**
	 * checks if test is running
	 */
	private boolean isTestRunning() {

		super.waitForVisibilityOfElement(previewSourceInlineData);
		String inlineData=previewSourceInlineData.getText();

		if(inlineData.equalsIgnoreCase("NaN")){

			return true;
		}

		return false;
	}

	/**
	 * waits for the job to finish running till timeout
	 * @return false if ran successfully or else true
	 */
	private boolean waitForTestToComplete(){

		long waitingTime = Long.parseLong(ConfigurationReader.get("jobRunningTimeOut"));
		long startTime = System.currentTimeMillis();
		long endTime,totalTime = 0;
		boolean isRunning = this.isTestRunning();

		while(isRunning){

			try {
				Thread.sleep(10000);

			} catch (InterruptedException e) {

				log.error("encountered an interrupted exception while sleeping the thread");
			}

			isRunning = this.isTestRunning();
			endTime = System.currentTimeMillis();
			totalTime = endTime -startTime;

			if(isRunning && (totalTime > waitingTime)){

				return isRunning;
			}

		};

		return isRunning;
	}

	/**
	 * verifies if test was completed successfully by asserting various mechanisms
	 */
	public void verifyIfTestCompletedSuccessfully(){

		boolean isNotSuccessful = this.waitForTestToComplete();

		if(isNotSuccessful){

			Assertion.fail(getDriver(), "Test is still running after - "+
					Long.parseLong(ConfigurationReader.get("jobRunningTimeOut"))/1000 +
					" seconds", "jobStillRunningAfterTimeout");
		}

	}

	/**
	 * compares and verifies preview data
	 * @param _expectedOutput
	 */
	public void verifyPreviewData(String _expectedOutput){

		super.waitForVisibilityOfElement(previewData);
		super.highlightElement(previewData);

		Assertion.assertEquals(getDriver(), previewData.getText(),
				_expectedOutput, "Actual Preview data("+ previewData.getText()
				+") do not match expected preview data("+ _expectedOutput +")",
				"IncorrectPreviewData");
		
	}
	
	/**
	 * generates scheduled time (3 minutes later) based on current time 
	 * @return scheduled time
	 */
	public String generatScheduledTime(){

		int currentTime = Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date()));
		int scheduleTime;

		if((currentTime%100) == 57){

			scheduleTime = ((currentTime/100) + 1 ) * 100 ;
		}
		else if((currentTime%100) == 58){

			scheduleTime = (((currentTime/100) + 1 ) * 100 ) + 1 ;
		}
		else if((currentTime%100) == 59){

			scheduleTime = (((currentTime/100) + 1 ) * 100 ) + 2 ;
		}
		else{

			scheduleTime = currentTime + 0003;
		}


		int min = scheduleTime%100 ;
		String minutes = null;

		if(min<10){

			minutes = "0"+ Integer.toString(min);
		}
		else{

			minutes = Integer.toString(min);
		}

		String time = Integer.toString(scheduleTime/100) + ":" + minutes;

		return time;
	}

}