package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductToCart {

	public static void main(String[] args) throws InterruptedException {
		
		//launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//load the application
		driver.get("https://www.saucedemo.com/");
		
		//login to application
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//click a product
		String productTobeAdded = driver.findElement(By.xpath("//div[.='Sauce Labs Fleece Jacket']")).getText();
		driver.findElement(By.xpath("//div[.='Sauce Labs Fleece Jacket']")).click();
		
		//add product to cart-jackets
		driver.findElement(By.id("add-to-cart")).click();
		
		Thread.sleep(1000);
		
		//navigate to cart
		driver.findElement(By.id("shopping_cart_container")).click();
		
		
		//validate the product
		String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if(productInCart.equals(productTobeAdded))
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
