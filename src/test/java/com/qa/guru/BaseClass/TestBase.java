package com.qa.guru.BaseClass;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.guru.Utilities.TestUtility;

public class TestBase {

public	static WebDriver driver;
public	static Properties p;
public	static FileInputStream fis;
static ExtentReports extent;
static ExtentSparkReporter spark;
static ExtentTest extenttest;
static String timestamp;
static String repname;


	@BeforeTest
	public void initialiseBrowser(ITestContext context) throws Exception {
		p = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		p.load(fis);
		String browser = p.getProperty("browser");
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("invalid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtility.implicit));
		driver.get(p.getProperty("baseurl"));
		Capabilities	cap = ((RemoteWebDriver)driver).getCapabilities();
		extenttest =  extent.createTest(context.getName());
	String device =	cap.getBrowserName()+" "+cap.getBrowserVersion().substring(0,cap.getBrowserVersion().indexOf("."));
	String author = context.getCurrentXmlTest().getName();
	extenttest.assignAuthor(author);
	extenttest.assignAuthor(device);

	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@BeforeSuite
	public void extentReports() {
		timestamp = new SimpleDateFormat("dd.MMM.yyyy.HH.mm.ss").format(new Date());
		 repname = "Test_Report"+timestamp+".html";
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("ExtentReports//"+repname);
		extent.attachReporter(spark);
		extent.setSystemInfo("os name", System.getProperty("os.name"));
		extent.setSystemInfo("os version", System.getProperty("os.version"));
		extent.setSystemInfo("user name", System.getProperty("user.name"));
		extent.setSystemInfo("user country", System.getProperty("user.country"));
		extent.setSystemInfo("java version", System.getProperty("java.version"));
		spark.config().setDocumentTitle("Guru99 Test Login");
		spark.config().setReportName("its ayaansh project");
		spark.config().setTheme(Theme.DARK);
		spark.config().setTimeStampFormat("dd-MMM-yyyy HH:mm:ss");
										
	
	}
	@AfterSuite
	public void generateReports() throws IOException {
		extent.flush();
		Desktop.getDesktop().browse(new File("ExtentReports//"+repname).toURI());
	}
	@BeforeMethod
	public void groupsstatus(Method m) {
		extenttest.assignCategory(m.getAnnotation(Test.class).groups());
	}
	
	
	
	
	
	
	
	

}
