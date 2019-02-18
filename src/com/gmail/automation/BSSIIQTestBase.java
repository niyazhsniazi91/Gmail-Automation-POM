package com.gmail.automation;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BSSIIQTestBase 
{
	public static WebDriver driver;
	public static Properties prop;
	public static int numberOfAttemptsToFindWebElement;

	public static void initialization(String strConfigFile)
	{
		try 
		{
			prop = new Properties();
			FileInputStream fi = new FileInputStream("Config/" + strConfigFile);
			prop.load(fi);
		} 
		catch (Exception exception) 
		{
			exception.getMessage();
		}

		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		else if (browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("opera")) 
		{
			System.setProperty("webdriver.opera.driver", "driver/operadriver.exe");
			driver = new OperaDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			System.setProperty("webdriver.edge.driver", "driver/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		numberOfAttemptsToFindWebElement();
	}

	public static boolean configGenerator() throws IOException 
	{

		File file = new File("Config/GmailConfig.properties");
		if (!file.exists()) 
		{

			FileWriter writer = new FileWriter(file);

			writer.write("*********Author********");
			writer.append('\n');
			writer.write("*****Niyaz Hussain*****");
			writer.append('\n');
			writer.append('\n');
			writer.write("browser = chrome");  
			writer.append('\n');
			writer.write("gmailUrl = https://mail.google.com/");
			writer.append('\n');
			writer.write("loginEmailID = ");
			writer.append('\n');
			writer.write("loginPassword = ");
			writer.append('\n');
			writer.write("emailRecipients = kiran@bridgesoftsol.com,babu.k@bridgesoftsol.com");
			writer.append('\n');
			writer.write("ccRecipients = mahesh@bridgesoftsol.com,aswani.m@bridgesoftsol.com,gopal@bridgesoftsol.com,"
					+ "naresh@bridgesoftsol.com,pavani@bridgesoftsol.com,reeta.prajapati@bridgesoftsol.com,"
					+ "santosh.j@bridgesoftsol.com,karunakar@bridgesoftsol.com");
			writer.append('\n');
			writer.write("emailSubject = ");
			writer.append('\n');
			writer.write("emailMessageBody = ");
			writer.append('\n');
			writer.write("senderName = ");
			writer.append('\n');
			writer.write("attachment = ");
			writer.append('\n');
			writer.write("fileUploadPath = ");
			writer.append('\n');
			writer.write("numberOfAttemptsToFindWebElement = 20");

			writer.close();
			System.out.println("Configuration File created successfully");
			System.out.println("\nPlease fill the required credentials for sending the email!!!");
			return false;
		} 
		else 
		{
			System.out.println("Configuration File exists! \n\nIts good to go!!! \n");
			return true;
		}
	}
	
	public static void setClipboardData(String string) 
	{
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) 
	{
		try 
		{
			//Setting clipboard with file location
			setClipboardData(fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} 
		catch (Exception exp) 
		{
			exp.printStackTrace();
		}
	}

	public static void numberOfAttemptsToFindWebElement() 
	{
		try 
		{
			numberOfAttemptsToFindWebElement = Integer.parseInt(prop.getProperty("numberOfAttemptsToFindWebElement"));
		} 
		catch (Exception e) 
		{
			numberOfAttemptsToFindWebElement = 20;
		}	
	}

	public static WebElement uiElement(WebElement webElement)
	{
		boolean flag = true;
		int count = 1;
		while (flag) 
		{	
			if (count < numberOfAttemptsToFindWebElement) 
			{
				try 
				{
					
					if (webElement.isDisplayed()) 
					{
						flag = false;
						System.out.println("This Web Element was found after: " + count + " attempts");
					}
				} 
				catch (Exception e) 
				{
					try 
					{
						Thread.sleep(500);
					} 
					catch (InterruptedException e1) 
					{
					}
					count = count + 1;
					System.out.println("Number of Attempts to find this Web Element: " + count);
				}
			}
			else
			{
				System.out.println("Maximum number of attempts to find Web Element is " + count + " attempts");
				System.out.println(count + " attempts over");
				break;
			}
		}
		return webElement;
	}
	
	/*public static List<WebElement> uiElements(List<WebElement> webElements)
	{
		boolean flag = true;
		int count = 1;

		while (flag) 
		{	
			if (count < numberOfAttemptsToFindWebElement) 
			{
				try 
				{
					if (!webElements.isEmpty()) 
					{
						flag = false;
						System.out.println("This Web Element was found after: " + count + " attempts");
						
					}
				} 
				catch (Exception e) 
				{
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						
					}
					count = count + 1;
					System.out.println("Number of Attempts to find this Web Element: " + count);
				}
			}
			else
			{
				System.out.println("Maximum number of attempts to find Web Element is " + count + " attempts");
				System.out.println(count + " attempts over");
				break;
			}
		}
		return webElements;
	}*/
}
