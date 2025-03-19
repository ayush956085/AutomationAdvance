package inventoryTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class AddLowestPriceProductToCartTest {

	@Test
	public void tc_002_addLowestProductToCart() throws IOException
	
	 {
		
		//Create object of all utilities
		SeleniumUtility sUtil = new SeleniumUtility();
		FileUtility fUtil = new FileUtility();
		
		//Read the data from files
		String URL =  fUtil.readDataFromPropertyFile("url");
		 String USERNAME = fUtil.readDataFromPropertyFile("username");
		 String PASSWORD = fUtil.readDataFromPropertyFile("password");
		 
		 String SORTOPTION = fUtil.readDataFromExcelFile("Product",7,2);
		 String PRODUCTNAME = fUtil.readDataFromExcelFile("Product",7,3);
		
		//Launch the browser
		WebDriver driver = new ChromeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		
		//Load the application
		driver.get(URL);
		
		//Login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		//Click on product
		InventoryPage ip = new InventoryPage(driver);
		  String productAdded = ip.clickOnSortedProduct(driver, PRODUCTNAME, SORTOPTION);
		 
		 //Add product to cart
		  InventoryItemPage iip = new InventoryItemPage(driver);
		  iip.clickOnAddToCartBtn();
		  
		  //Navigate to cart
		  ip.clickOnCartContainer();
		  
		  
		  //Validate in the cart page
		  CartPage cp = new CartPage(driver);
		  String productInCart = cp.getItemName();
		  
		  
		  if(productInCart.equals(productInCart))
		  {
			  System.out.println("PASS");
			  System.out.println(productInCart);
		  }
		  else
		  {
			  System.out.println("FAIL");
		  }
		  
		  
		  //Logout
		  ip.logoutOfApp();
		  
		  
		  
		  
		
		
		
		
		
		
		
		
		
		
	}
}
