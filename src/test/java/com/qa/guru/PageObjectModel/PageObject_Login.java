package com.qa.guru.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.guru.BaseClass.TestBase;

public class PageObject_Login extends TestBase {

	@FindBy(name = "uid")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "btnLogin")
	WebElement clk_login_bn;
	

	public PageObject_Login() {
		PageFactory.initElements(driver, this);
	}

	public void setUserName(String un) {
		username.sendKeys(un);
	}

	public void setPswd(String p) {
		password.sendKeys(p);
	}

	public void clk_login() {
		clk_login_bn.click();
	}

	public String title() {
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}

}

