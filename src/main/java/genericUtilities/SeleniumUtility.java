package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of generic methods related to selenium tool
 * @author AYUSH KUMAR SINHA
 */
public class SeleniumUtility {

	

	/**
	 * This method will maximize window
	 * @param driver
	 */
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will add implicitly wait of 10 seconds
	 * @param driver
	 */
	
	public void addImplicitlyWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait for 10 seconds for element to be Visible
	 * @param driver
	 * @param element
	 */
	
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for 10 seconds for element to be Clickable
	 * @param driver
	 * @param element
	 */

	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle dropdown by index
	 * @param element
	 * @param index
	 */
	
	public void handleDropDown(WebElement element, int index )
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will handle dropdown by value
	 * @param element
	 * @param value
	 */
	
	public void handleDropDown(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	
	/**
	 * This method will handle dropdown by visible text
	 * @param element
	 * @param value
	 */
	
	public void handleDropDown(String visibleText ,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method will perform mouse hovering action on webelement
	 * @param driver
	 * @param element
	 */
	
	public void mouseOverAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform drag and drop action on webElement
	 * @param driver
	 * @param srcelement
	 * @param tarElement
	 */
	
	public void dragAndDropAction(WebDriver driver, WebElement srcelement, WebElement tarElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcelement, tarElement).perform();
	}
	
	/**
	 * This method will perform right click action on webElement
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform dubleClick  action on webElement
	 * @param driver
	 * @param element
	 */
	
	public void doubleClickAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	
	/**
	 * This method will scroll to particular webElement
	 * @param driver
	 * @param element
	 */
	
	public void scrollToElementAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.scrollToElement(element);
	}
	
	
	/**
	 * This method will switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver , int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	
   /**
    * This method will switch to frame based on parent frame
    * @param driver
    * @param element
    */
	public void switchToParentFrame(WebDriver driver , WebElement element)
	{
		driver.switchTo().parentFrame();
    }
	
	
	/**
	 * This method will switch to frame based on name or id
	 * @param driver
	 * @param windowId
	 */
	public void switchToWindow(WebDriver driver , String windowId)
	{
		driver.switchTo().frame(windowId);
	}
	
	
	    /**
	    * This method will switch to frame based on webElemnt
	    * @param driver
	    * @param element
	    */
		public void handleFrame(WebDriver driver , WebElement element)
		{
			driver.switchTo().frame(element);
	    }
		
	

		/**
		 * This method will switch to window
		 * @param driver
		 * @param windowId
		 */
		public void switchToWindow( String windowId , WebDriver driver)
		{
			driver.switchTo().window(windowId);
		}
		
		
		
		/**
		 * This method will accept the alert pop up
		 * @param driver
		 */
		public void acceptAlert(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		
		
		
		/**
		 * This method will dismiss the alert pop up
		 * @param driver
		 */
		public void dismissAlert(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		
		
		/**
		 * This method will get the alert text
		 * @param driver
		 * @return
		 */
		public String getAlertText(WebDriver driver)
		{
			return driver.switchTo().alert().getText();
		}
		
		/**
		 * This method will enter the data to alert pop up
		 * @param driver
		 * @param data 
		 */
		public void enterDataToAlert(WebDriver driver , String data)
		{
			driver.switchTo().alert().sendKeys(data);
		}
		
		/**
		 * 
		 * @param driver
		 * @param screenShotName 
		 * @throws IOException
		 */
		public String captureScreenshot(WebDriver driver, String screenShotName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dst = new File(".\\Screeshots\\"+screenShotName+".png");
			FileHandler.copy(src, dst);
			
			return dst.getAbsolutePath();//for extent reports
		
		}
		
	
	
}
