package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractice {

	@Test
	public void sample()
	{
		
		System.out.println("hiii");
	}
	
	@Test(retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	public void sample1()
	{
		Assert.fail();
		
		System.out.println("hello");
	}
}
