package com.talend.tic.api;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public final class TDefaultElementLocatorFactory implements ElementLocatorFactory {
  private  ThreadLocal<WebDriver> thread;
  private  ThreadLocal<RemoteWebDriver> threadremote;
  private  boolean isRemote;
  

  public TDefaultElementLocatorFactory(ThreadLocal<WebDriver> thread) {
    this.thread = thread;
    this.isRemote=false;
  }

  public TDefaultElementLocatorFactory(ThreadLocal<RemoteWebDriver> thread,boolean isRemote) {
	    this.threadremote = thread;
	    this.isRemote=isRemote;
	  }
  
  public ElementLocator createLocator(Field field) {
	  
	  if(!isRemote){
		  
		  return new TDefaultElementLocator(thread, field);
	  }
  
	  return new TDefaultElementLocator(threadremote, field,isRemote);
  }
}