package com.gmail.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMailSender extends BSSIIQTestBase
{

	@FindBy(xpath = "//input[@type='email']")
	WebElement emailTextBox;

	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextButton;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordTextBox;

	@FindBy(xpath = "//div[text()='COMPOSE']")
	WebElement composeButton;

	@FindBy(name = "to")
	WebElement toTextBox;

	@FindBy(xpath = "//span[contains(@data-tooltip,'Add Cc')]")
	WebElement ccLink;

	@FindBy(name = "cc")
	WebElement ccTextBox;

	@FindBy(xpath = "//div[contains(@data-tooltip,'Attach files')]/div/div/div")
	WebElement attachmentLink;

	@FindBy(name = "subjectbox")
	WebElement subjectboxtextBox;

	@FindBy(xpath = "//div[@aria-label='Message Body']")
	WebElement messageBodyTextBox;

	@FindBy(xpath = "//div[text()='Send']")
	WebElement sendButton;


	public GoogleMailSender()
	{
		PageFactory.initElements(driver, this);
	}

	public static String gmailUrl = prop.getProperty("gmailUrl");
	public static String loginEmailID = prop.getProperty("loginEmailID");
	public static String loginPassword = prop.getProperty("loginPassword");
	public static String emailRecipients = prop.getProperty("emailRecipients");
	public static String ccRecipients = prop.getProperty("ccRecipients");
	public static String emailSubject = prop.getProperty("emailSubject");
	public static String emailMessageBody = prop.getProperty("emailMessageBody");
	public static String senderName = prop.getProperty("senderName");
	public static boolean attachment = Boolean.parseBoolean(prop.getProperty("attachment"));
	public static String fileUploadPath = prop.getProperty("fileUploadPath");

	public void test() throws Throwable 
	{
		try 
		{
			driver.get(gmailUrl);

			uiElement(emailTextBox).sendKeys(loginEmailID);

			uiElement(nextButton).click();

			uiElement(passwordTextBox).sendKeys(loginPassword);

			uiElement(nextButton).click();

			uiElement(composeButton).click();

			uiElement(toTextBox).sendKeys(emailRecipients);

			uiElement(ccLink).click();

			uiElement(ccTextBox).sendKeys(ccRecipients);

			uiElement(subjectboxtextBox).sendKeys(emailSubject);

			uiElement(messageBodyTextBox).sendKeys("Hi Sir, \n\n" + emailMessageBody + "\n\nThanks and Regards... \n" + senderName);

			if (attachment) 
			{
				uiElement(attachmentLink).click();

				uploadFile(fileUploadPath);
				//long uploadTimeInMilliSeconds = Integer.parseInt(prop.getProperty("uploadTimeInMilliSeconds"));
				//Thread.sleep(uploadTimeInMilliSeconds);
			}
			uiElement(sendButton).click();
		} 
		catch (Exception e) 
		{
			System.out.println("Execution Failed due to issues with the System Performance or Internet Connection!!!");
			System.out.println("Please increase the commonTimeout to execute this script successfully");
			driver.quit();
		}
		driver.quit();
	}
}
