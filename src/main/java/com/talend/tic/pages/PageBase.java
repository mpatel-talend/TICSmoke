package com.talend.tic.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.talend.tic.api.TPageFactory;
import com.talend.tic.utilities.ConfigurationReader;
import com.talend.tic.utilities.ExceptionHandler;
import com.talend.tic.utilities.Utility;
import com.talend.tic.utilities.Verification;



public class PageBase {

	
	
	protected WebElement element;
	private WebDriver  driver;
	

	private static Logger log = Logger.getLogger(PageBase.class);
	public static final int IMPLICIT_TIMEOUT_VALUE = 10; // seconds
	public static final int EXPLICIT_TIMEOUT_VALUE = 10; // seconds 
	public static final int PAGELOAD_TIMEOUT_VALUE = 10; // seconds 
	Properties path = new Properties();
	Verification verification=new Verification();
	Utility util = new Utility(getDriver());


	public WebDriver getDriver(){
		
	
		if(null!=thread.get()){
			
			return this.thread.get();
		}
		
		return this.threadRemote.get();
		
		
	
	}
	
	

	public void sleeper(int timeInMilliSeconds) {

		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void open(String url) {

		Assert.assertNotNull(this.getDriver());
		this.getDriver().get(url);

	}

	public void close() {

		this.getDriver().quit();
	}

	public String getTitle() {

		return this.getDriver().getTitle();
	}

	public void implicitTimeout() {

		this.getDriver().manage().timeouts()
				.implicitlyWait(IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);
	}

	public void logOut() {
		
		this.getDriver().findElement(By.id("forLogout")).click();
		
	}

	/**
	 * method highlights the element which is being selected.
	 * 
	 * @param element
	 */
	public void highlightElement(WebElement element) {

		for (int i = 0; i < 1; i++) {

			JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: yellow; border: 3px solid yellow;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");

		}

	}

	public void takeScreenShot(String testcaseNo) {

		try {
			this.sleeper(1000);
			File scrnshot;
			path.load(new FileInputStream("path.properties"));

			//if(this.getDriver() instanceof RemoteWebDriver){
				
			//	 scrnshot = ((TakesScreenshot) new Augmenter().augment( this.getDriver() )).getScreenshotAs(OutputType.FILE);
		        
		//}else{
				scrnshot = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.FILE);
		//}
			
			FileUtils.copyFile(scrnshot,
					new File(path.getProperty("pageScreenshot") + testcaseNo
							+ ".png"));
			log.debug("Screenshot taken and stored as : " + testcaseNo
					+ ".png in C:\\seleniumPOC\\screenshots folder");

		} catch (Exception e) {
			log.error("IOException occurred while taking the screenshot.");
			e.printStackTrace();
		}

	}

	public void clickOnOutsideScreen() {

		this.getDriver()
				.findElement(
						By.xpath("/html/body/section/div/div[3]/div/div/div/div/div[2]"))
				.click();

	}

	public boolean compareDropDownValues(List<String> values, String term) {

		WebElement dropdown = this.getDriver().findElement(By.id(term));
		Select select = new Select(dropdown);

		List<WebElement> options = select.getOptions();

		List<String> strings = new ArrayList<String>();
		for (WebElement we : options) {
			strings.add(we.getAttribute("value")); // maybe we.getText()?
			////System.out.println("value of the content 000---"+ we.getAttribute("value"));
		}

		return strings.equals(values);

	}

	

	public String getAttributeById(String elementId, String attribute) {

		String temp = "";
		temp = this.getDriver().findElement(By.id(elementId))
				.getAttribute(attribute);
		return temp;
	}

	public void checkDisableField(String elementId) {

		verification.verifyTrue(!this.getDriver().findElement(By.id(elementId))
				.isEnabled());
	}

	public void checkEnableField(String elementId) {

		verification.verifyTrue(this.getDriver().findElement(By.id(elementId)).isEnabled());
	}

	public boolean checkAlertText(String textValue) {

		this.sleeper(3000);
		boolean result = false;

		Alert alert = this.getDriver().switchTo().alert();
		
		////System.out.println("Alert from GUI :-" + alert.getText() + "--");
		/*if (alert.getText().trim().equalsIgnoreCase(textValue.trim())) {
			util.captureScreen("Alert window");
			result = true;
		}*/
		
		if(true)
		{
			
			alert.accept();
		}

		////System.out.println("alert accepted successfully");
		return true;

	}

	

	public void maximizeBrowser() {

		this.getDriver().manage().window().maximize();
	}

	
	public void mouseOver(String locator, String value) {

		WebElement toElement = null;

		if (locator.equalsIgnoreCase("xpath")) {

			toElement = this.getDriver().findElement(By.xpath(value));
		} else if (locator.equalsIgnoreCase("css")) {
			toElement = this.getDriver().findElement(By.cssSelector(value));
		} else if (locator.equalsIgnoreCase("id")) {
			toElement = this.getDriver().findElement(By.id(value));
		} else if (locator.equalsIgnoreCase("linkText")) {
			toElement = this.getDriver().findElement(By.linkText(value));
		} else if (locator.equalsIgnoreCase("name")) {
			toElement = this.getDriver().findElement(By.name(value));
		} else if (locator.equalsIgnoreCase("className")) {
			toElement = this.getDriver().findElement(By.className(value));
		}

		new Actions(this.getDriver()).moveToElement(toElement).build().perform();

	}

	public void pressTab() {

		new Actions(this.getDriver()).sendKeys(Keys.TAB).build().perform();

	}

	public boolean isDraggable(String sourceLocator, String sourceValue,
			String destLocator, String destValue, int x, int y) {

		boolean dragged = false;
		WebElement fromElement = null, toElement = null;

		if (sourceLocator.equalsIgnoreCase("xpath")) {

			fromElement = this.getDriver().findElement(By.xpath(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("css")) {
			fromElement = this.getDriver().findElement(By.cssSelector(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("id")) {
			fromElement = this.getDriver().findElement(By.id(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("linkText")) {
			fromElement = this.getDriver().findElement(By.linkText(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("name")) {
			fromElement = this.getDriver().findElement(By.name(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("className")) {
			fromElement = this.getDriver().findElement(By.className(sourceValue));
		}

		if (destLocator.equalsIgnoreCase("xpath")) {

			toElement = this.getDriver().findElement(By.xpath(destValue));
		} else if (destLocator.equalsIgnoreCase("css")) {
			toElement = this.getDriver().findElement(By.cssSelector(destValue));
		} else if (destLocator.equalsIgnoreCase("id")) {
			toElement = this.getDriver().findElement(By.id(destValue));
		} else if (destLocator.equalsIgnoreCase("linkText")) {
			toElement = this.getDriver().findElement(By.linkText(destValue));
		} else if (destLocator.equalsIgnoreCase("name")) {
			toElement = this.getDriver().findElement(By.name(destValue));
		} else if (destLocator.equalsIgnoreCase("className")) {
			toElement = this.getDriver().findElement(By.className(destValue));
		}

		//this.highlightElement(fromElement);

		Point before = fromElement.getLocation();

		new Actions(this.getDriver()).clickAndHold(fromElement)
				.moveToElement(toElement, x, y).build().perform();

		Point after = fromElement.getLocation();

		if (after != before) {

			dragged = true;
		}

		return dragged;
	}

	public void login(String username, String password) {

		//this.highlightElement(this.getDriver().findElement(By.name("j_username")));
		this.getDriver().findElement(By.name("j_username")).sendKeys(username);
		this.pressTab();
		verification.verifyEquals(this.getClass().getName(),this.getDriver().findElement(By.name("j_username"))
				.getAttribute("value"), username);
		
		//this.highlightElement(this.getDriver().findElement(By.name("j_password")));
		this.getDriver().findElement(By.name("j_password")).sendKeys(password);
		this.pressTab();
		verification.verifyEquals(this.getClass().getName(),this.getDriver().findElement(By.name("j_password"))
				.getAttribute("value"), password);
		
		this.sleeper(1000);
		
		//this.highlightElement(this.getDriver().findElement(By.className("login-button")));
		this.getDriver().findElement(By.className("login-button")).click();
		
		this.getDriver().manage().timeouts()
				.pageLoadTimeout(PAGELOAD_TIMEOUT_VALUE, TimeUnit.SECONDS);
		
		this.sleeper(2000);
	}

	public void rightClickOn(WebElement element) {

		this.sleeper(2000);
		new Actions(this.getDriver()).contextClick(element).build().perform();
		////System.out.println("done right click");
	}

	public void rightClick() {

		new Actions(this.getDriver()).contextClick().build().perform();
	}

	public void isMessagePresent(String message) {

	
		this.waitForVisibilityOfElement(this.getDriver().findElement(By.id("message")));
		//this.highlightElement(this.getDriver().findElement(By.id("message")));
		////System.out.println("Success message from GUI :- "+this.getDriver().findElement(By.id("message")).getText());
		verification.verifyTrue(this.getDriver().findElement(By.id("message")).getText().contains(message.trim()));
	}

	public boolean isSortable(ArrayList<String> listBeforeSort, String locator,
			String value) {

		ArrayList<String> OldList = listBeforeSort;

		WebElement table = null;

		if (locator.equalsIgnoreCase("xpath")) {

			table = this.getDriver().findElement(By.xpath(value));
		} else if (locator.equalsIgnoreCase("css")) {
			table = this.getDriver().findElement(By.cssSelector(value));
		} else if (locator.equalsIgnoreCase("id")) {
			table = this.getDriver().findElement(By.id(value));
		} else if (locator.equalsIgnoreCase("linkText")) {
			table = this.getDriver().findElement(By.linkText(value));
		} else if (locator.equalsIgnoreCase("name")) {
			table = this.getDriver().findElement(By.name(value));
		} else if (locator.equalsIgnoreCase("className")) {
			table = this.getDriver().findElement(By.className(value));
		}

		List<WebElement> row = table.findElements(By.tagName("tr"));
		ArrayList<String> Newlist = new ArrayList<String>();
		WebElement el;

		for (int i = 0; i < row.size(); i++) {
			if (i != 0) {
				el = row.get(i);
				if (el.getText().toString() != null
						|| el.getText().toString().trim().length() > 0) {

					Newlist.add(el.getText().toString());
				}
			}
		}

		Collections.sort(OldList, String.CASE_INSENSITIVE_ORDER);

		

		if (OldList.equals(Newlist)) {
			return true;
		}
		return false;

	}

	public ArrayList<String> getListFromTable(String locatorOfTable,
			String valueOfTable) {

		WebElement table = null;

		if (locatorOfTable.equalsIgnoreCase("xpath")) {

			table = this.getDriver().findElement(By.xpath(valueOfTable));
		} else if (locatorOfTable.equalsIgnoreCase("css")) {
			table = this.getDriver().findElement(By.cssSelector(valueOfTable));
		} else if (locatorOfTable.equalsIgnoreCase("id")) {
			table = this.getDriver().findElement(By.id(valueOfTable));
		} else if (locatorOfTable.equalsIgnoreCase("linkText")) {
			table = this.getDriver().findElement(By.linkText(valueOfTable));
		} else if (locatorOfTable.equalsIgnoreCase("name")) {
			table = this.getDriver().findElement(By.name(valueOfTable));
		} else if (locatorOfTable.equalsIgnoreCase("className")) {
			table = this.getDriver().findElement(By.className(valueOfTable));
		}

		List<WebElement> row = table.findElements(By.tagName("tr"));
		ArrayList<String> newList = new ArrayList<String>();
		WebElement el;

		for (int i = 0; i < row.size(); i++) {
			if (i != 0) {
				el = row.get(i);
				if (el.getText().toString() != null
						|| el.getText().toString().trim().length() > 0) {

					newList.add(el.getText().toString());
				}
			}
		}

		return newList;
	}

	public boolean isDragAndDrop(String sourceLocator, String sourceValue,
			String destLocator, String destValue) {

		boolean dragged = false;
		WebElement fromElement = null, toElement = null;

		if (sourceLocator.equalsIgnoreCase("xpath")) {

			fromElement = this.getDriver().findElement(By.xpath(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("css")) {
			fromElement = this.getDriver().findElement(By.cssSelector(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("id")) {
			fromElement = this.getDriver().findElement(By.id(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("linkText")) {
			fromElement = this.getDriver().findElement(By.linkText(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("name")) {
			fromElement = this.getDriver().findElement(By.name(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("className")) {
			fromElement = this.getDriver().findElement(By.className(sourceValue));
		}

		if (destLocator.equalsIgnoreCase("xpath")) {

			toElement = this.getDriver().findElement(By.xpath(destValue));
		} else if (destLocator.equalsIgnoreCase("css")) {
			toElement = this.getDriver().findElement(By.cssSelector(destValue));
		} else if (destLocator.equalsIgnoreCase("id")) {
			toElement = this.getDriver().findElement(By.id(destValue));
		} else if (destLocator.equalsIgnoreCase("linkText")) {
			toElement = this.getDriver().findElement(By.linkText(destValue));
		} else if (destLocator.equalsIgnoreCase("name")) {
			toElement = this.getDriver().findElement(By.name(destValue));
		} else if (destLocator.equalsIgnoreCase("className")) {
			toElement = this.getDriver().findElement(By.className(destValue));
		}

		//this.highlightElement(fromElement);

		Point before = fromElement.getLocation();

		new Actions(this.getDriver()).dragAndDrop(fromElement, toElement).build().perform();

		Point after = fromElement.getLocation();

		if (after != before) {

			dragged = true;
		}

		return dragged;
	}


	/**
	 * waits for the presence of element in the page till timeout
	 * @param locator
	 * @return
	 */
	public WebElement waitForPresenceOfElement(final By locator) {

		final long startTime = System.currentTimeMillis();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.getDriver())
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class,
						ElementNotVisibleException.class);
		int tries = 1;
		boolean found = false;
		WebElement elem = null;
		int timeout = Integer.parseInt(ConfigurationReader.get("waitTimeOut"));
		while ((System.currentTimeMillis() - startTime) < timeout) {
			log.debug("Searching for element. Try number " + (tries++));
			long intTime = System.currentTimeMillis();
			try {
				elem = wait.until(ExpectedConditions.visibilityOf(element));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				log.info("Stale element: \n" + e.getMessage() + "\n");
			} catch (TimeoutException ex){
				log.info("Timed out after "+ (System.currentTimeMillis()-intTime)/1000 +" seconds. Retrying again!!" );
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			log.debug("Found element after waiting for " + totalTime/1000
					+ " seconds.");
		} else {
			log.info("Failed to find element after " + totalTime/1000
					+ " seconds.");
		}
		return elem;
	}

	/**
	 * waits for visibility of element in the page till timeout
	 * @param element
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(final WebElement element) {

		final long startTime = System.currentTimeMillis();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.getDriver())
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class,
						ElementNotVisibleException.class);
		int tries = 1;
		boolean found = false;
		WebElement elem = null;
		int timeout = Integer.parseInt(ConfigurationReader.get("waitTimeOut"));
		while ((System.currentTimeMillis() - startTime) < timeout) {
			log.debug("Searching for element. Try number " + (tries++));
			long intTime = System.currentTimeMillis();
			try {
				elem = wait.until(ExpectedConditions.visibilityOf(element));
				found = true;
				break;
			} catch (StaleElementReferenceException e) {
				log.info("Stale element: \n" + e.getMessage() + "\n");
			} catch (TimeoutException ex){
				log.info("Timed out after "+ (System.currentTimeMillis()-intTime)/1000 +" seconds. Retrying again!!" );
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			log.debug("Found element after waiting for " + totalTime/1000
					+ " seconds.");
		} else {
			log.info("Failed to find element after " + totalTime/1000
					+ " seconds.");
		}
		return elem;
	}

	public boolean isResizable(String sourceLocator, String sourceValue, int x,
			int y) {

		boolean resized = false;
		WebElement fromElement = null;

		if (sourceLocator.equalsIgnoreCase("xpath")) {

			fromElement = this.getDriver().findElement(By.xpath(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("css")) {
			fromElement = this.getDriver().findElement(By.cssSelector(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("id")) {
			fromElement = this.getDriver().findElement(By.id(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("linkText")) {
			fromElement = this.getDriver().findElement(By.linkText(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("name")) {
			fromElement = this.getDriver().findElement(By.name(sourceValue));
		} else if (sourceLocator.equalsIgnoreCase("className")) {
			fromElement = this.getDriver().findElement(By.className(sourceValue));
		}

		//this.highlightElement(fromElement);

		Dimension before = fromElement.getSize();

		new Actions(this.getDriver()).moveToElement(fromElement).clickAndHold()
				.moveByOffset(x, y).release().perform();

		Dimension after = fromElement.getSize();

		if (after != before) {

			resized = true;
		}

		return resized;
	}

	/**
	 * click on the element not visible on the screen
	 * 
	 * @param locator
	 *            - for locating the element
	 * @param value
	 *            - value of the locator
	 * @param occurence
	 *            - the index of the element in the page
	 */
	public void clickHiddenElement(String locator, String value,
			String occurence) {
		this.sleeper(5000);
		JavascriptExecutor hiddenElement = (JavascriptExecutor) this.getDriver();

		if (locator.equalsIgnoreCase("xpath")) {
			
			

			hiddenElement
					.executeScript("var getElementByXpath = function (path) { return document.evaluate(path, document, null, 9, null).singleNodeValue;}; getElementByXpath.click();");

		} else if (locator.equalsIgnoreCase("css")) {

			
			hiddenElement.executeScript("document.getElementById('" + value
					+ "').click();");
		} else if (locator.equalsIgnoreCase("id")) {

			hiddenElement.executeScript("document.getElementById('" + value
					+ "').click();");
		}

		else if (locator.equalsIgnoreCase("name")) {

			
			hiddenElement.executeScript("document.getElementsByName('" + value
					+ "')[" + occurence + "].click();");
		} else if (locator.equalsIgnoreCase("className")) {

		
			hiddenElement.executeScript("document.getElementsByClassName('"
					+ value + "')[" + occurence + "].click();");
		} else if (locator.equalsIgnoreCase("tagName")) {
		
			hiddenElement.executeScript("document.getElementsByTagName('"
					+ value + "')[" + occurence + "].click();");
		}

		else {

			////System.out.println("Pls provide correct locator");
		}

		this.sleeper(5000);
	}
	
	public static ThreadLocal<WebDriver> thread = new ThreadLocal<WebDriver>();
	public static ThreadLocal<RemoteWebDriver> threadRemote = new ThreadLocal<RemoteWebDriver>();

	/**
	 * initializes web driver session with the predefined locators
	 * @param webDriver
	 * @param clazz
	 * @return
	 */
	public static <T> T  getPageFactory(WebDriver webDriver, Class<T> clazz){
		
		if(webDriver instanceof WebDriver){
		
			thread.set( webDriver);
			threadRemote=null;
			return  TPageFactory.initElements( thread, clazz );
		}

			threadRemote.set( (RemoteWebDriver) webDriver);
			thread=null;
			return TPageFactory.initElements(threadRemote, clazz,true);
		}
	

	/**
	 * scrolls till the given element is visible
	 * @param driver
	 * @param element
	 */
	public void scroll(WebDriver driver, WebElement element){

		try{

			((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);

		}catch(Exception e){

			ExceptionHandler.handle(e, driver, 
					this.getClass().getSimpleName(),Utility.getMethodName());	
		}
	}


	/**
	 * returns MainPage instance for easy navigation
	 * @return
	 */
	public MainPage getMainPage(){
		
		return PageBase.getPageFactory(getDriver(), MainPage.class);
	}

}
