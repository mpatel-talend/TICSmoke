package com.talend.tic.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;


public class CrossBrowser {

	private static WebDriver driver;
	private static WebDriver htmlDriver;
	static Logger log = Logger.getLogger(CrossBrowser.class);


	/**
	 * retrieves the browser session and loads it into webdriver
	 * @param browserName name of the browser to be used
	 * @param browserType type of the browser (local/remote)
	 * @return webdriver session loaded with selected browser
	 * @throws MalformedURLException
	 */
	public static  WebDriver getBrowser(String browserName, String environment) throws MalformedURLException {

		try{


			if(browserName.equalsIgnoreCase("headless") && environment.equalsIgnoreCase("local"))
			{
				log.info("---------------: Inside Headless profile :--------------");
				DesiredCapabilities hl = DesiredCapabilities.htmlUnitWithJs();
				hl.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);
				hl.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				hl.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				hl.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);
				hl.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
				htmlDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
				((HtmlUnitDriver) htmlDriver).setJavascriptEnabled(true);
				log.info("==========:::: got Headless driver ::::===========");
			}



			else if (browserName.equalsIgnoreCase("firefox") && environment.equalsIgnoreCase("local")) {

				log.info("---------------: Inside firefox profile :--------------");

				DesiredCapabilities ff = DesiredCapabilities.firefox();
				ff.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);
				ff.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				ff.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ff.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);
				ff.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);



				driver = new FirefoxDriver(ff);
				log.info("==========:::: got firefox driver ::::===========");

			} else if (browserName.equalsIgnoreCase("chrome") && environment.equalsIgnoreCase("local")) {

				log.info("---------------: Inside chrome profile :--------------");

				DesiredCapabilities chro = DesiredCapabilities.chrome();
				chro.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);
				chro.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
				chro.setCapability(CapabilityType.HAS_NATIVE_EVENTS,true);
				chro.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,true);
				chro.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
				chro.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
				chro.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);

				System.setProperty("webdriver.chrome.driver",PathReader.readPath("chromeDriver2.6"));


				driver = new ChromeDriver(chro);
				log.info("==========:::: got Chrome driver ::::===========");

			}  else if (browserName.equalsIgnoreCase("firefox") && environment.equalsIgnoreCase("remote")) {
				log.info("---------------: Inside remote firefox profile :--------------");

				DesiredCapabilities ff = DesiredCapabilities.firefox();
				ff.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);
				ff.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ff.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);
				ff.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
				ff.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ff);
				driver = new Augmenter().augment(driver);
				log.info("==========:::: got remote firefox driver ::::===========");
			}	else if (browserName.equalsIgnoreCase("chrome") && environment.equalsIgnoreCase("remote")) {
				log.info("---------------: Inside remote chrome profile :--------------");

				DesiredCapabilities chro = DesiredCapabilities.chrome();
				chro.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);
				chro.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
				chro.setCapability(CapabilityType.HAS_NATIVE_EVENTS,true);
				chro.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,true);
				chro.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
				chro.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
				chro.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, true);

				System.setProperty("webdriver.chrome.driver",PathReader.readPath("chromeDriver2.6"));

				chro.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chro);
				driver = new Augmenter().augment(driver);
				log.info("==========:::: got remote Chrome driver ::::===========");
			}

			else {

				log.info("default firefox driver called");
				driver = new FirefoxDriver();
			}

		} catch (IOException e) {
			log.error("An error occured while instantiating driver.");
			e.printStackTrace();
		}
		return driver;

	}
}
