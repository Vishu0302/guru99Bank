package com.guru99Bank.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99Bank.pageObjects.LoginPage;

public class LoginTest_TC002DDT extends BaseClass{

	@Test(dataProvider= "LoginData")
	public void loginTestDDT(String user, String pass) throws IOException, InterruptedException 
	{
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(user);
		logger.info("Username Entered");
		
		lp.setPassword(pass);
		logger.info("Password Entered");
		
		lp.clickLogin();
		Thread.sleep(3000);
	
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
	}
	
	public boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/guru99Bank/testData/LoginData.xlsx";
		
		int rownum=com.guru99Bank.utilities.XLUtils.rowCount(path, "Sheet1");
		int colcount=com.guru99Bank.utilities.XLUtils.cellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=com.guru99Bank.utilities.XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}

}
