package inventoryTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
  
@Listeners(genericUtilities.ListenersImplementation.class)
public class AddProductToCartListeners extends BaseClass {  


	@Test(groups = "RegressionSuite")
	public void tc_005_addProductToCart() throws IOException   
	
	 {
		
		
				//read the data from file
				String PRODUCTNAME = fUtil.readDataFromExcelFile("Product", 1, 2);
				
				
				//Click on product
				InventoryPage ip = new InventoryPage(driver);
				  String productAdded = ip.clickOnProduct(driver, PRODUCTNAME );
				  
				  Assert.fail();
				 
				 //Add product to cart
				  InventoryItemPage iip = new InventoryItemPage(driver);
				  iip.clickOnAddToCartBtn();
				  
				  //Navigate to cart
				  ip.clickOnCartContainer();
				  
				  
				  //Validate in the cart page
				  CartPage cp = new CartPage(driver);
				  String productInCart = cp.getItemName();
				  
				 Assert.assertEquals(productInCart, productAdded ); 
				 
				 Assert.assertTrue(productInCart.equals(productAdded));
				 
}
}
