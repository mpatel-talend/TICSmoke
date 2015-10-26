package com.talend.tic.scripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tic.api.Assertion;
import com.talend.tic.pages.FlowBuilderPage;
import com.talend.tic.pages.FlowsPage;
import com.talend.tic.pages.MainPage;
import com.talend.tic.utilities.ConfigurationReader;


public class TIC002_TestJob {

	private MainPage pageMain;
	private FlowsPage pageFlow;
	private FlowBuilderPage pageFlowBuilder;
	private CommonScript commons = new CommonScript();
	Logger log = Logger.getLogger(TIC002_TestJob.class);
	long start;

	@Parameters(value = { "browser", "environment" })
	@Test
	public void testJob(String browser, String environment){

		try{

			pageMain=commons.setupTest(browser,environment);

			pageFlowBuilder=commons.createFlow(pageMain);

			log.info("click on Test button");
			pageFlowBuilder.clickOnTest();

			log.info("click on confirm Test button");
			pageFlowBuilder.clickOnTestSchedule();

			log.info("verify if test completed successfully before timeout");
			pageFlowBuilder.verifyIfTestCompletedSuccessfully();

			log.info("clicking the step preview data icon");
			pageFlowBuilder.clickOnPreviewStepDataIcon();

			log.info("verify step output data");
			pageFlowBuilder.verifyPreviewData(ConfigurationReader.get("stepOutput"));

			log.info("clicking the target preview data icon");
			pageFlowBuilder.clickOnPreviewTargetDataIcon();

			log.info("verify target output data");
			pageFlowBuilder.verifyPreviewData(ConfigurationReader.get("targetOutput"));

			log.info("get main page");
			pageMain=pageFlowBuilder.getMainPage();

			log.info("click Flows tab");
			pageFlow=pageMain.clickFlowTab();

			log.info("select show ALL from search filter");
			pageFlow.selectShowRecordsFilter("All");

			log.info("search flow by name and click on that flow");
			pageFlow.findFlowByName(ConfigurationReader.get("flowName"));

			commons.cleaningTask(pageFlow);
			
		}catch(Exception ex){

			log.error("An exception occured : " + ex.getMessage(),ex);
			Assertion.fail(pageFlow.getDriver(), "An exception occured "
					+ ": " + ex.getMessage(), "TestJob");

		}
	}

}
