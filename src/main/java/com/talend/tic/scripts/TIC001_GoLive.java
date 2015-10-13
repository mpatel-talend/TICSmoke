package com.talend.tic.scripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tic.pages.FlowBuilderPage;
import com.talend.tic.pages.FlowsPage;
import com.talend.tic.pages.MainPage;


public class TIC001_GoLive {


	private MainPage pageMain;
	private FlowsPage pageFlow;
	private FlowBuilderPage pageFlowBuilder;
	private CommonScript commons = new CommonScript();
	Logger log = Logger.getLogger(TIC001_GoLive.class);
	
	@Parameters(value = { "browser", "environment" })
	@Test
	public void testGoLive(String browser, String environment){

		pageMain=commons.setupTest(browser,environment);
		
		pageFlowBuilder=commons.createFlow(pageMain);
		
		log.info("click on Go Live button");
		pageFlowBuilder.clickOnGoLive();

		log.info("click on Go button");
		pageFlow = pageFlowBuilder.clickOnApplyScheduleForGoLiveEnvironment();

		log.info("verify if job completed successfully");
		pageFlow.verifyJobRunSuccessfully(false,null);

		commons.cleaningTask(pageFlow);
	}


	

}
