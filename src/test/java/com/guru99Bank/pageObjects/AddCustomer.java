package com.guru99Bank.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomer {

	WebDriver ldriver;
	
	public AddCustomer(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name = "name")
	WebElement customerName;
	
	public void setCustomerName(String cname)
	{
		customerName.clear();
		customerName.sendKeys(cname);
	}
	
	
}
