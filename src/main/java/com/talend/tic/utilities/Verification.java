package com.talend.tic.utilities;

import org.testng.Assert;
import org.testng.Reporter;



public class Verification {

	private StringBuffer verificationErrors;
	private StringBuffer messages;
	private StringBuffer exceptionMessages;
	private static int counter = 1;
	Utility utility=new Utility(null);


	public Verification() {

		verificationErrors = new StringBuffer();
		messages = new StringBuffer();
		exceptionMessages=new StringBuffer();
	}

	public void verifyTrue(Boolean b ,String msg1) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {

			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}



	public void verifyFalse(String msg1, Boolean b) {
		try {
			Assert.assertFalse(b.booleanValue());
		} catch (Error e) {
			String msg=extractMsg(msg1);
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyTrue(String elementDefinition, String elementLocator,
			Boolean b, String pageURL) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("Verification failed for element: "
					+ elementDefinition + ",Element locator: " + elementLocator
					+ "On page: " + pageURL);
			Reporter.log("Verification failed for element: "
					+ elementDefinition + ",Element locator: " + elementLocator
					+ "On page: " + pageURL);
		}
	}

	public void verifyTrue(String elementDefinition, String elementLocator,
			boolean isElementPresent, int actualElementCount,
			int expectedElementCount, String pageURL) {

		try {
			Assert.assertTrue(isElementPresent);
		} catch (Error e) {
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("Verification failed for element: "
					+ elementDefinition + ", Element locator: "
					+ elementLocator + "On page: " + pageURL);
			Reporter.log("Verification failed for element: "
					+ elementDefinition + ", Element locator: "
					+ elementLocator + "On page: " + pageURL);
		}

		try {
			Assert.assertTrue(actualElementCount == expectedElementCount);
		} catch (Error e) {
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("Verification count failed for element: "
					+ elementDefinition + ",Element locator: " + elementLocator
					+ ", Expected Element Count: " + expectedElementCount
					+ ", while Actual Element Count: " + actualElementCount
					+ " On page: " + pageURL);
			Reporter.log("Verification count failed for element: "
					+ elementDefinition + ",Element locator: " + elementLocator
					+ ", Expected Element Count: " + expectedElementCount
					+ ", while Actual Element Count: " + actualElementCount
					+ " On page: " + pageURL);
		}
	}

	public void verifyEquals(String msg1, String s1, String s2) {
		try {
			Assert.assertEquals(s1, s2);
		} catch (Error e) {
			String msg=extractMsg(msg1);
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals(String msg1, Object obj1, Object obj2) {
		try {
			Assert.assertEquals(obj1, obj2);
		} catch (Error e) {
			String msg=extractMsg(msg1);
			verificationErrors.append("(" + counter + ")" + e);
			//System.out.println("eroor :-"+ verificationErrors.toString());
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyNotEquals(String msg, Object obj1, Object obj2) {
		try {
			Assert.assertNotEquals(obj1, obj2);
		} catch (Error e) {
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals(String msg1, String str1[], String str2[]) {
		try {
			Assert.assertEquals(str1, str2);
		} catch (Error e) {
			String msg=extractMsg(msg1);
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals(Object str1[], Object str2[], String msg) {
		try {
			Assert.assertEquals(((Object) (str1)), ((Object) (str2)));
		} catch (Error e) {
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	//************************************************************************************************************

	public void verifyTrue(Boolean b) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {

			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}



	public void verifyFalse( Boolean b) {
		try {
			Assert.assertFalse(b.booleanValue());
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}




	public void verifyEquals(String s1, String s2) {
		try {
			Assert.assertEquals(s1, s2);
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			messages.append("(" + counter + ")" + msg);
			Reporter.log("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals( Object obj1, Object obj2) {
		try {
			Assert.assertEquals(obj1, obj2);
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyNotEquals(Object obj1, Object obj2) {
		try {
			Assert.assertNotEquals(obj1, obj2);
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals( String str1[], String str2[]) {
		try {
			Assert.assertEquals(str1, str2);
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}

	public void verifyEquals(Object str1[], Object str2[]) {
		try {
			Assert.assertEquals(((Object) (str1)), ((Object) (str2)));
		} catch (Error e) {
			String msg=extractMsg("");
			verificationErrors.append("(" + counter + ")" + e);
			Reporter.log("(" + counter + ")" + msg);
			messages.append("(" + counter + ")" + msg);
			counter++;
		}
	}



	public void clearVerificationErrors() {
		verificationErrors = new StringBuffer();


	}


	public void clearMessages() {
		messages = new StringBuffer();


	}

	public static void fail(String message) {
		throw new AssertionError(message);
	}

	/**
	 * provides final assertion to the test script
	 */
	public void checkForVerificationErrors() {


		String verificationErrorString = verificationErrors.toString();

		// Clear Verification Errors so that it is ready to test new
		// verifications
		clearVerificationErrors();
		clearMessages();
		if (!"".equals(verificationErrorString))
			fail(verificationErrorString);
	}


	/**
	 * filters the error message to get proper cause
	 * @param msg
	 * @return
	 */
	private String extractMsg(String msg) {

		msg=Thread.currentThread().getStackTrace()[3].getClassName();
		String temp2=msg;
		String temp[]=msg.split("_");
		String temp1[]=temp[0].split("\\.");
		msg="";
		if(temp1.length>2)
		{
			if(!temp1[3].equalsIgnoreCase("testcases"))
			{
				msg= temp1[3]+" " ;
				if(temp1[4].equalsIgnoreCase("pretrade") ||temp1[4].equalsIgnoreCase("posttrade"))
					msg=msg+temp1[4]+" ";
			}
			msg= msg + temp1[temp1.length-3]+" "+temp1[temp1.length-2]+" "+temp1[temp1.length-1];
		}
		if(temp.length>3)
		{
			msg=msg+" "+temp[1]+" "+temp[2]+" "+temp[3];
			String childMethod = msg + "::"
					+ Thread.currentThread().getStackTrace()[3].getMethodName()
					+ ":"
					+ Thread.currentThread().getStackTrace()[3].getLineNumber();

			String parentMethod = "::"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber();
			msg= childMethod+" "+parentMethod;
			System.err.println(":::::::::::::::::::::::::::::::::");		
			System.err.println("MSG ::"+msg);
			System.err.println("+++++++++++++++++++++ERROR AT+++++++++++++++++++++");
			System.err.println("(" + temp2 + ".java:" + Thread.currentThread().getStackTrace()[3].getLineNumber() + ")");
			System.err.println(":::::::::::::::::::::::::::::::::");

		}
		else
		{

			String childMethod = msg + "::"
					+ Thread.currentThread().getStackTrace()[4].getMethodName()
					+ ":"
					+ Thread.currentThread().getStackTrace()[4].getLineNumber();

			String parentMethod = "::"
					+ Thread.currentThread().getStackTrace()[3].getMethodName()
					+ ":"
					+ Thread.currentThread().getStackTrace()[3].getLineNumber();
			msg= childMethod+" "+parentMethod;
			temp2=Thread.currentThread().getStackTrace()[4].getClassName();

			System.err.println(":::::::::::::::::::::::::::::::::");		
			System.err.println("MSG ::"+msg);
			System.err.println("+++++++++++++++++++++ERROR AT+++++++++++++++++++++");
			System.err.println("(" + temp2 + ".java:" + Thread.currentThread().getStackTrace()[4].getLineNumber() + ")");
			System.err.println(":::::::::::::::::::::::::::::::::");
		}

		return msg;
	}


}