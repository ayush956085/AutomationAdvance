package inventoryTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class AddProductToCartUsingBaseClass extends BaseClass {


	@Test(groups = "SmokeSuite")
	public void tc_001_addProductToCart() throws IOException
	
	 {
		
		
				//read the data from file
				String PRODUCTNAME = fUtil.readDataFromExcelFile("Product", 1, 2);
				
				
				//Click on product
				InventoryPage ip = new InventoryPage(driver);
				  String productAdded = ip.clickOnProduct(driver, PRODUCTNAME );
				 
				 //Add product to cart
				  InventoryItemPage iip = new InventoryItemPage(driver);
				  iip.clickOnAddToCartBtn();
				  
				  //Navigate to cart
				  ip.clickOnCartContainer();
				  
				  
				  //Validate in the cart page
				  CartPage cp = new CartPage(driver);
				  String productInCart = cp.getItemName();
				  
				  
				  if(productInCart.equals(productAdded))
				  {
					  System.out.println("PASS");
					  System.out.println(productInCart);
				  }
				  else
				  {
					  System.out.println("FAIL");
				  }
				  
				
	 }
}
