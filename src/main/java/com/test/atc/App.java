package com.test.atc;

/**
 * Hello world!
 *
 */
public class App extends Tasks
{
	static String dress1 = "//*[@id='center_column']/ul/li[1]/div/div/div[2]/h5/a";
	static String dress2 = "//*[@id='center_column']/ul/li[2]/div/div/div[2]/h5/a";
	static String dress3 = "//*[@id='center_column']/ul/li[3]/div/div/div[2]/h5/a";
	static Tasks Make = new Tasks();
	
    public static void main( String[] args )
    {
        Tasks.Login();
        Tasks.AddAddress();
        Tasks.AddToCart(dress1);
        Tasks.ContinueShop();
        Tasks.AddToCart(dress2);
        Tasks.ContinueShop();
        Tasks.AddToCart(dress3);
        Tasks.Checkout();
        Tasks.Screenshot(); 
        Tasks.Signout();
        
                
    }
    
    
}
