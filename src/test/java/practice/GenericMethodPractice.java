package practice;

import genericUtilities.JavaUtility;

public class GenericMethodPractice {

	public static void main(String[] args) {
		
	 int sum = add(10,20);
	 System.out.println(sum);
	 
	 int v = add(sum,50);
	 System.out.println(v);
	 
	 System.out.println(add(140,584));
	 
	 
	 JavaUtility j = new JavaUtility();
	 System.out.println(j.getSystemDateInformat());
		
	}
	
	public static int add(int a, int b)
	{
		int c = a+b;
		return c;
	}
}
