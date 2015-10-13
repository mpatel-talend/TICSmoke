package com.talend.tic.api;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


/**
 * Factory class to make using Page Objects simpler and easier.
 *
 *  */
public class TPageFactory {

  
  public static <T> T initElements(ThreadLocal<WebDriver> thread, Class<T> pageClassToProxy) {
    T page = instantiatePage(thread, pageClassToProxy);
    initElements(thread, page);
    return page;
  }

  public static <T> T initElements(ThreadLocal<RemoteWebDriver> thread, Class<T> pageClassToProxy,boolean isRemote) {
	    T page = instantiatePage(thread, pageClassToProxy,isRemote);
	    initElements(thread, page,isRemote);
	    return page;
	  }

  
  public static void initElements(ThreadLocal<WebDriver> thread, Object page) {
    final ThreadLocal<WebDriver> threadlocal  = thread;
    initElements(new TDefaultElementLocatorFactory(threadlocal), page);
  }

  public static void initElements(ThreadLocal<RemoteWebDriver> thread, Object page,boolean isRemote) {
	    final ThreadLocal<RemoteWebDriver> threadlocal  = thread;
	    initElements(new TDefaultElementLocatorFactory(threadlocal,isRemote), page);
	  }
  
  public static void initElements(ElementLocatorFactory factory, Object page) {
    final ElementLocatorFactory factoryRef = factory;
    initElements(new DefaultFieldDecorator(factoryRef), page);
  }

  
  public static void initElements(FieldDecorator decorator, Object page) {
    Class<?> proxyIn = page.getClass();
    while (proxyIn != Object.class) {
      proxyFields(decorator, page, proxyIn);
      proxyIn = proxyIn.getSuperclass();
    }
  }

  private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
    Field[] fields = proxyIn.getDeclaredFields();
    for (Field field : fields) {
      Object value = decorator.decorate(page.getClass().getClassLoader(), field);
      if (value != null) {
        try {
          field.setAccessible(true);
          field.set(page, value);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private static <T> T instantiatePage(ThreadLocal <WebDriver> thread, Class<T> pageClassToProxy) {
    try {
      try {
        Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
        return constructor.newInstance(thread.get());
      } catch (NoSuchMethodException e) {
        return pageClassToProxy.newInstance();
      }
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
  
  private static <T> T instantiatePage(ThreadLocal <RemoteWebDriver> thread, Class<T> pageClassToProxy,boolean isRemote) {
	    try {
	      try {
	        Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
	        return constructor.newInstance(thread.get());
	      } catch (NoSuchMethodException e) {
	        return pageClassToProxy.newInstance();
	      }
	    } catch (InstantiationException e) {
	      throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	      throw new RuntimeException(e);
	    } catch (InvocationTargetException e) {
	      throw new RuntimeException(e);
	    }
	  }
}
