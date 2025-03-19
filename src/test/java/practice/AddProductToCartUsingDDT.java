package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductToCartUsingDDT {

	public static void main(String[] args) throws IOException {
		
		//read required data
		//property file-common data
		//open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//create object of property class - java.util
		Properties p = new Properties();
		
		//load the file input stream to properties
		p.load(fis);
		
		
		  //read the data using keys
		  String URL = p.getProperty("url");
		  String USERNAME = p.getProperty("username");
		  String PASSWORD = p.getProperty("password");
		
		
		//read test data from excel file
		//open the document in java readable format
		 FileInputStream file = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		  
		  
		 //create a workbook
		 Workbook wb = WorkbookFactory.create(file);
		           
		 //navigate to sheet
		 Sheet sh = wb.getSheet("Product");
		  
		 //navigate to row
		 Row rw = sh.getRow(1);
		  
		 //navigate to cell
		 Cell cl = rw.getCell(2);
		 
		 //capture the data inside the cell
		String PRODUCTNAME = cl.getStringCellValue();
		 System.out.println(PRODUCTNAME);
		
		
		
		//launch the browser
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				
				//load the application
				driver.get(URL);
				
				//login to application
				driver.findElement(By.id("user-name")).sendKeys(USERNAME);
				driver.findElement(By.id("password")).sendKeys(PASSWORD);
				driver.findElement(By.id("login-button")).click();
				
				//click a product- dynamic xpath
				String productTobeAdded = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();
				driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
				
				//add product to cart-jackets
				driver.findElement(By.id("add-to-cart")).click();
				
				
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
