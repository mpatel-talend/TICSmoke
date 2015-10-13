package com.talend.tic.pages;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.talend.tic.api.Assertion;
import com.talend.tic.utilities.ConfigurationReader;


/**
 * page object for Flows page of Talend Integration Cloud
 * @author mpatel
 *
 */
public class FlowsPage extends PageBase{

	Assertion asserting = new Assertion();
	Logger log = Logger.getLogger(FlowsPage.class);


	@FindBy(id = "fl-title-button-create-new-flow")
	private WebElement createNewFlow;

	@FindBy(xpath = "//div[@id='workspaces_container']/div/div/h2")
	private WebElement personalWorkspaceTab;

	@FindBy(id = "//div[@id='workspaces_container']/div[2]/div/h2")
	private WebElement sharedWorkspaceTab;

	@FindBy(id = "menu-header-link-activity")
	private WebElement menuActivityLink;

	@FindBy(id = "menu-header-link-management")
	private WebElement menuManageLink;

	@FindBy(id = "menu-header-link-admin")
	private WebElement menuAdminLink;

	@FindBy(id = "fd-title-button-delete")
	private WebElement deleteButton;

	@FindBy(id = "-dialog-delete-flow-button-delete")
	private WebElement confirmDeleteButton;

	@FindBy(xpath = "//ul[@class='chbox_items filter_options']/li[1]/div/label/span/span")
	private WebElement allRunsCount;

	@FindBy(xpath = "//ul[@class='chbox_items filter_options']/li[2]/div/label/span/span")
	private WebElement runningCount;

	@FindBy(xpath = "//ul[@class='chbox_items filter_options']/li[3]/div/label/span/span")
	private WebElement okCount;

	@FindBy(xpath = "//ul[@class='chbox_items filter_options']/li[4]/div/label/span/span")
	private WebElement rejectionCount;

	@FindBy(xpath = "//ul[@class='chbox_items filter_options']/li[5]/div/label/span/span")
	private WebElement failureCount;

	@FindBy(xpath = "//div[@id='page_content_inner_right']/div/div/button")
	private WebElement runRefreshButton;

	@FindBy(xpath = "//div[@id='flow_run_details']/ul/li[4]/div/table/tbody/tr/td/div/div/p")
	private WebElement runSchedule;

	@FindBy(css = "button[data-ng-click='refreshHistory()']")
	private WebElement jobRefreshButton;

	@FindBy(id = "fl-main-right-select-limit")
	private WebElement recordsFilter;

	@FindBy(xpath = "//div[@data-list-model='flows']/div[1]/div/div/div[2]/div")
	private WebElement flowsTitles;

	@FindBy(css = "div[data-list-model='flows']")
	private WebElement flowsResultList;

	public WebDriver getDriver(){


		if(null!=thread.get()){

			return super.thread.get();
		}

		return super.threadRemote.get();

	}

	/**
	 * clicks on create new flow button
	 * @return FlowBuilderPage
	 */
	public FlowBuilderPage clickCreateNewFlowButton() {
		super.waitForVisibilityOfElement(createNewFlow);
		super.highlightElement(createNewFlow);
		createNewFlow.click();
		return PageBase.getPageFactory(getDriver(), FlowBuilderPage.class);

	}

	/**
	 * clicks on the personal workspace tab
	 */
	public void clickPersonalTab() {
		super.waitForVisibilityOfElement(personalWorkspaceTab);
		super.highlightElement(personalWorkspaceTab);
		personalWorkspaceTab.click();

	}

	/**
	 * clicks on the shared workspace tab
	 */
	public void clickSharedTab() {
		super.waitForVisibilityOfElement(sharedWorkspaceTab);
		super.highlightElement(sharedWorkspaceTab);
		sharedWorkspaceTab.click();

	}

	/**
	 * clicks on the refresh button 
	 */
	public void clickRefreshJobButton() {
		super.waitForVisibilityOfElement(jobRefreshButton);
		super.highlightElement(jobRefreshButton);
		jobRefreshButton.click();

	}

	/**
	 * clicks on the delete button 
	 */
	public void clickDeleteJobButton() {
		super.waitForVisibilityOfElement(deleteButton);
		super.highlightElement(deleteButton);
		deleteButton.click();

	}

	/**
	 * clicks on the confirm delete button 
	 * @return MainPage object
	 */
	public void clickConfirmDeleteButton() {
		super.waitForVisibilityOfElement(confirmDeleteButton);
		super.highlightElement(confirmDeleteButton);
		confirmDeleteButton.click();


	}

	/**
	 * selects records filter to show records
	 * @param _filterValue
	 */
	public void selectShowRecordsFilter(String _filterValue) {
		super.waitForVisibilityOfElement(recordsFilter);
		super.highlightElement(recordsFilter);
		new Select(recordsFilter).selectByVisibleText(_filterValue);
		super.pressTab();
	}

	/**
	 * verifies run schedule of the flow
	 * @param _expectedSchedule
	 */
	public void verifyRunSchedule(String _expectedSchedule) {

		super.waitForVisibilityOfElement(runSchedule);
		super.highlightElement(runSchedule);
		Assertion.assertTrue(getDriver(), 
				runSchedule.getText().equals(_expectedSchedule),
				"Expected "+ _expectedSchedule +", but found "+
						runSchedule.getText(), "RunScheduleVerificationFailure");


	}

	/**
	 * checks if job is running
	 * @param _expectedSchedule
	 */
	private boolean isJobRunning(boolean _isScheduled, String _scheduledTime) {

		super.waitForVisibilityOfElement(runningCount);
		int runCount,allRunCount,secCount=23;

		if(_isScheduled){
			while(secCount>0){

				allRunCount = Integer.parseInt(allRunsCount.getText());

				if(allRunCount>0){

					this.checkJobExecutionTime(_scheduledTime);

					runCount = Integer.parseInt(runningCount.getText());

					if(runCount==1){

						return true;
					}	
					break;

				}
				else{
					try {
						Thread.sleep(8000);

						this.clickRefreshJobButton();
						secCount--;

					} catch (InterruptedException e) {

						log.error("Error occurred while putting thread to sleep"
								+ " and waiting for job to begin execution");
					}
				}
			}
		}
		else{

			runCount = Integer.parseInt(runningCount.getText());

			if(runCount==1){

				return true;
			}
		}
		return false;
	}

	/**
	 * waits for the job to finish running till timeout
	 * @return false if ran successfully or else true
	 */
	private boolean waitForJobToFinishRunning(boolean _isScheduled, String _scheduledTime){

		long waitingTime = Long.parseLong(ConfigurationReader.get("jobRunningTimeOut"));
		long startTime = System.currentTimeMillis();
		long endTime,totalTime = 0;
		boolean isRunning = this.isJobRunning(_isScheduled,_scheduledTime);

		while(isRunning){

			try {
				Thread.sleep(10000);

			} catch (InterruptedException e) {

				log.error("encountered an interrupted exception while sleeping the thread");
			}

			this.clickRefreshJobButton();
			isRunning = this.isJobRunning(_isScheduled,_scheduledTime);
			endTime = System.currentTimeMillis();
			totalTime = endTime -startTime;

			if(isRunning && (totalTime > waitingTime)){

				return isRunning;
			}

		};

		return isRunning;
	}


	/**
	 * verifies if scheduled job ran successfully by asserting various mechanisms
	 */
	public void verifyJobRunSuccessfully(boolean _isScheduled, String _scheduledTime){

		boolean isNotSuccessful = this.waitForJobToFinishRunning(_isScheduled,_scheduledTime);

		if(isNotSuccessful){

			Assertion.fail(getDriver(), "Job is still running after - "+
					Long.parseLong(ConfigurationReader.get("jobRunningTimeOut"))/1000 +
					" seconds", "jobStillRunningAfterTimeout");
		}
		else if(Integer.parseInt(rejectionCount.getText())>0){

			Assertion.fail(getDriver(), "Job is rejected", "jobRejected");
		}
		else if(Integer.parseInt(failureCount.getText())>0){

			Assertion.fail(getDriver(), "Job is Failed", "jobFailed");
		}
		else{

			int allRunCount = Integer.parseInt(allRunsCount.getText());
			int OKCount = Integer.parseInt(okCount.getText());

			if(allRunCount!=OKCount){

				Assertion.fail(getDriver(), "Failing test as #allRunCount("+ allRunCount +") "
						+ "is different from #OKCount("+ OKCount +")", "allRunDifferentFromOk");
			}
		}
	}


	/**
	 * finds the expected flow by flow name in the flows page
	 * @param _expectedFlowName
	 */
	public void findFlowByName(String _expectedFlowName){

		String flowTitlePath,flowName ;
		WebElement flowTitle = null;

		for(int i=1;;i++){

			try{
				flowTitlePath = "//div[@data-list-model='flows']/div["+i+"]/div/div/div[2]/div";

				flowTitle = getDriver().findElement(By.xpath(flowTitlePath));

				flowName = flowTitle.getText();

				if(flowName.equalsIgnoreCase(_expectedFlowName)){

					break;
				}
			}
			catch(ElementNotFoundException e){

				Assertion.fail(getDriver(), "Flow with name ("+ _expectedFlowName +") could not"
						+ " be found. Please check if this flow exists.", "FlowNotFound");
			}
		}

		flowTitle.click();

	}

	/**
	 * verify that flow is not present in flows page
	 * @param _expectedFlowName
	 */
	public void verifyFlowIsNotPresntInFlows(String _expectedFlowName){

		String flowTitlePath,flowName ;
		WebElement flowTitle = null;
		List<WebElement> flows = flowsResultList.findElements(By.xpath(".//div[@data-ng-repeat='node in listModel']"));


		for(int i=1;i<=flows.size();i++){

			try{

				flowTitlePath = "//div[@data-list-model='flows']/div["+i+"]/div/div/div[2]/div";

				flowTitle = getDriver().findElement(By.xpath(flowTitlePath));

				flowName = flowTitle.getText();

				if(flowName.equalsIgnoreCase(_expectedFlowName)){

					Assertion.fail(getDriver(), "Flow ("+ flowName +") still present in Flows"
							+ " page after deletion", "FlowPresentAfterDeletion");
				}
			}
			catch(NoSuchElementException e){

				log.info("Flow with name ("+ _expectedFlowName +") deleted successfully!!");
				break;
			}
		}

	}

	/**
	 * verifies schedule time 
	 * @param _scheduleTime
	 * @param _schedulePeriod
	 */
	public void verifyScheduledRunTime(String _scheduleTime,String _schedulePeriod ){


		WebElement runTime = getDriver().findElement(By.xpath("//div[@id='flow_run_details']/ul/li[4]/div/table/tbody/tr/td/div/div/p"));

		String onlyTime = runTime.getText();

		String time[] = onlyTime.split(",");

		String times[]=time[1].trim().split("\\s+");

		if(times[1].length()==1){

			times[1] = "0"+times[1];
		}

		String actualTime,expectedTime;

		if(_schedulePeriod.equalsIgnoreCase("Daily")){

			actualTime = time[0] +", "+ times[0]+" "+times[1];

			expectedTime = _schedulePeriod +", Everyday "+ _scheduleTime;
		}
		else{

			times[1] = times[1].replaceAll("\\D+", "");
			actualTime = time[0] +","+ times[0]+" "+times[1]+" "+times[2]+" "+times[3];

			expectedTime = _schedulePeriod +","+ new SimpleDateFormat("MMM dd YYYY",Locale.ENGLISH).format(new Date()) + " " + _scheduleTime;

		}

		Assertion.assertEquals(getDriver(), actualTime, expectedTime, "Actual Scheduled time("+actualTime+") "
				+ "does not match with expected time("+ expectedTime+")", "ScheduleTimeDisplayError");
	}

	/**
	 * check if job began execution on time with a buffer of 20 seconds
	 * @param _scheduledTime
	 */
	private void checkJobExecutionTime(String _scheduledTime){


		String originalTime =_scheduledTime.trim()+":00";
		String time[] = originalTime.split(":");

		int hours,minutes, seconds;
		hours = Integer.parseInt(time[0]);
		minutes = Integer.parseInt(time[1]);
		seconds = Integer.parseInt(time[2]);

		Date date = new Date();
		int actual_hours, actual_min, actual_sec;
		actual_hours = date.getHours();
		actual_min= date.getMinutes();
		actual_sec = date.getSeconds();


		if(hours==actual_hours){

			if(minutes==actual_min){

				if(actual_sec > 20){

					Assertion.fail(getDriver(), "Job started executing with a delay of"
							+ " more than 20 seconds from the scheduled time of "+ 
							originalTime, "JobDelayedByMoreThan20Sec");
				}
			}
			else if(minutes > actual_min){

				if(actual_sec < 40){


					Assertion.fail(getDriver(), "Job started executing with early by"
							+ " more than 20 seconds from the scheduled time of "+ 
							originalTime, "JobEarlyExecutionByMoreThan20Sec");
				}
			}

		}
		else{

			Assertion.fail(getDriver(), "Job's scheduled execution hours ( "+ hours +") "
					+ "and actual execution hours ("+actual_hours +") do not match - " +
					originalTime ,"JobNotExecutedOnSchedule");
		}


	}


}