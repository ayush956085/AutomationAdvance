package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.InventoryPage;
import objectRepository.LoginPage;

/**
 * 
 */
public class BaseClass {

	public FileUtility fUtil = new FileUtility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriver driver;
	
	//for listeners
	public static  WebDriver sdriver;
	
	@BeforeSuite(alwaysRun = true)
	public void bsConfig()
	{
		System.out.println("-------Database Connection Successful-------");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups = {"SmokeSuite", "RegressionSuite"})
	public void bcConfig(/*String BValue*/) throws IOException
	{
		String URL = fUtil.readDataFromPropertyFile("url");
		
		driver = new ChromeDriver();
		
		//for cross browser execution
		/*if(BValue.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BValue.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}*/
		
		
		
		
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
	
		driver.get(URL);
		
		System.out.println("-----Browser Launch Successful-----");
		
		//for listeners
		sdriver = driver;
	}
	

	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("-----Login to app Successful--------");
	}
	
	@AfterMethod(alwaysRun = true)
	public void anConfig()
	{
		InventoryPage ip = new InventoryPage(driver);
		ip.logoutOfApp();
		
		System.out.println("-----Logout of the Successfully-----");
	}
	
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		
		System.out.println("-----Browser closure Successful----");
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("----Database closure Successful-----");
	}
	
	
	
	
	
}
