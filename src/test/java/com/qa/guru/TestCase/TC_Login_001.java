package com.qa.guru.TestCase;

import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;

import com.qa.guru.BaseClass.TestBase;
import com.qa.guru.PageObjectModel.PageObject_Login;

public class TC_Login_001 extends TestBase{
public static PageObject_Login po;
	
	@Test
	public void login() throws Exception {
	po = new PageObject_Login();
	/*
	 * po.setUserName(p.getProperty("username"));
	 * po.setPswd(p.getProperty("password"));
	 */	po.clk_login();
	po.title();
	
			
	}
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert().accept();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	
	}

}