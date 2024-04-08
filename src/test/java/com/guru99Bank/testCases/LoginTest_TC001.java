package com.guru99Bank.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99Bank.pageObjects.LoginPage;

public class LoginTest_TC001 extends BaseClass{

	@Test
	public void loginTest() throws IOException 
	{
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Username Entered");
		
		lp.setPassword(password);
		logger.info("Password Entered");
		
		lp.clickLogin();
	
		String expectedString="Welcome To Manager's Page of Guru99 Bank";
		String loginActual = driver.findElement(By.tagName("marquee")).getText();

		if(loginActual.equals(expectedString))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			capturePage(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}

}
