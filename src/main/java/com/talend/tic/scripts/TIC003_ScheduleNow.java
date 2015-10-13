package com.talend.tic.scripts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tic.pages.FlowBuilderPage;
import com.talend.tic.pages.FlowsPage;
import com.talend.tic.pages.MainPage;


public class TIC003_ScheduleNow {


	private MainPage pageMain;
	private FlowsPage pageFlow;
	private FlowBuilderPage pageFlowBuilder;
	private CommonScript commons = new CommonScript();
	Logger log = Logger.getLogger(TIC003_ScheduleNow.class);

	//	@Parameters(value = { "browser", "environment","scheduler"})
	@Test
	//public void testScheduleNow(String browser, String environment,String scheduler){
	public void testScheduleNow(){

		String browser = "firefox";
		String environment = "local";
		String scheduler = "Daily";
		pageMain=commons.setupTest(browser,environment);

		pageFlowBuilder=commons.createFlow(pageMain);

		log.info("click on Go Live button");
		pageFlowBuilder.clickOnGoLive();

		log.info("select #Run Once option from schedule dropdown");
		pageFlowBuilder.selectGoLiveSchedule(scheduler);

		log.info("enter europe/berlin time zone");
		pageFlowBuilder.selectTimeZone("Europe/Berlin");


		String scheduledTime = pageFlowBuilder.generatScheduledTime();
		
		log.info("schedule job to start running at "+ scheduledTime +"hours");
		pageFlowBuilder.enterScheduleRunTime(scheduledTime);

		log.info("click on Go button");
		pageFlow = pageFlowBuilder.clickOnApplyScheduleForGoLiveEnvironment();

		log.info("verify if job completed successfully");

		if(scheduler.contains("Once")){
			pageFlow.verifyScheduledRunTime(scheduledTime, "Once");
		}
		else{
			pageFlow.verifyScheduledRunTime(scheduledTime, "Daily");
		}
		log.info("verify if job completed successfully");
		pageFlow.verifyJobRunSuccessfully(true,scheduledTime);

		commons.cleaningTask(pageFlow);


	}

	

}
