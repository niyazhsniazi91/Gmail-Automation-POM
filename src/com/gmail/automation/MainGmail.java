package com.gmail.automation;

public class MainGmail extends BSSIIQTestBase
{
	public static void main(String[] args) throws Throwable 
	{
		if (configGenerator()) 
		{
			initialization("GmailConfig.properties");
			GoogleMailSender gmailScript = new GoogleMailSender();
			gmailScript.test();
			driver.quit();
		} 
	}
}
