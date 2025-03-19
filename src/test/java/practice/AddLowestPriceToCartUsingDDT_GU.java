package practice;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.FileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;


public class AddLowestPriceToCartUsingDDT_GU {
	
	

	public static void main(String[] args) throws IOException, InterruptedException {
		


	//create object of all utilities
	FileUtility fUtil = new FileUtility();
	SeleniumUtility sUtil = new SeleniumUtility();
	JavaUtility jUtil = new JavaUtility();
	
	

	  //read the data using keys
	String URL = fUtil.readDataFromPropertyFile("url");
	String USERNAME = fUtil.readDataFromPropertyFile("username");
	String PASSWORD = fUtil.readDataFromPropertyFile("password");
	
	//read data from excel file
	String SORTOPTION = fUtil.readDataFromExcelFile("Product",7,2);
	 String PRODUCTNAME = fUtil.readDataFromExcelFile("Product",7,3);
	 System.out.println(PRODUCTNAME);
	
	//launch the browser
	WebDriver driver = new EdgeDriver();
	sUtil.maximizeWindow(driver);
	sUtil.addImplicitlyWait(driver);
	
	//load the application
   driver.get(URL);
	

	//login to application
	//driver.findElement(By.id("user-name")).sendKeys(USERNAME);
	//driver.findElement(By.id("password")).sendKeys(PASSWORD);
	//driver.findElement(By.namegin-button")).click();
   
   LoginPage lp = new LoginPage(driver);
   
  // lp.getUsernameEdt().sendKeys(USERNAME);
  // lp.getPasswordEdt().sendKeys(PASSWORD);
  // lp.getLoginBtn().click();
	
   lp.loginToApp(USERNAME, PASSWORD);
   
   
	//sort the page for lowest price
	WebElement prodSort = driver.findElement(By.className("product_sort_container"));
	sUtil.handleDropDown(SORTOPTION, prodSort);
	
	Thread.sleep(1000);
	
	//click a product- dynamic xpath
	 WebElement Product = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']"));
	String productToBeAdded = Product.getText();
	Product.click();
	
	//add product to cart-jackets
	driver.findElement(By.id("add-to-cart")).click();
	
	
	//navigate to cart
	driver.findElement(By.id("shopping_cart_container")).click();
	
	
	//validate the product
	String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
	if (productToBeAdded.equals(productInCart))
	{
		System.out.println("pass");
		System.out.println(productInCart);
	}
	else
	{
		System.out.println("fail");
	}
	
	//logout of application
	driver.findElement(By.xpath("//button[.='Open Menu']")).click();
	driver.findElement(By.linkText("Logout")).click();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
