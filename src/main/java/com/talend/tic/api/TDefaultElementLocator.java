package com.talend.tic.api;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * The default element locator, which will lazily locate an element on a page.
 * This class is designed for use with the {@link org.openqa.selenium.support.PageFactory} and understands
 * the annotations {@link org.openqa.selenium.support.FindBy} and {@link org.openqa.selenium.support.CacheLookup}.
 */
public class TDefaultElementLocator implements ElementLocator {
	private  ThreadLocal<WebDriver> thread;
	private  ThreadLocal<RemoteWebDriver> threadremote;
	private  boolean cacheElement;
	private final By by;
	private WebElement cachedElement;
	private  boolean isRemote;

	/**
	 * Creates a new element locator.
	 * 
	 * @param driver The driver to use when finding the element
	 * @param field The field on the Page Object that will hold the located value
	 */
	public TDefaultElementLocator(ThreadLocal<WebDriver> thread, Field field) {
		this.thread = thread;
		Annotations annotations = new Annotations(field);
		cacheElement = annotations.isLookupCached();
		by = annotations.buildBy();
		this.isRemote=false;
	}

	public TDefaultElementLocator(ThreadLocal<RemoteWebDriver> thread, Field field, boolean isRemote) {
		this.threadremote = thread;
		Annotations annotations = new Annotations(field);
		cacheElement = annotations.isLookupCached();
		by = annotations.buildBy();
		this.isRemote=isRemote;
	}

	/**
	 * Find the element.
	 */
	public WebElement findElement() {
		if (cachedElement != null && cacheElement) {
			return cachedElement;
		}

		WebElement element;
		if(!isRemote){
			element = thread.get().findElement(by);
		}

		else{
			element = threadremote.get().findElement(by); 
		}


		if (cacheElement) {
			cachedElement = element;
		}

		return element;
	}

	public List<WebElement> findElements() {
		// TODO Auto-generated method stub
		return null;
	}
}

