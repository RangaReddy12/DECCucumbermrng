package Constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBConstant {
public static WebDriver driver;
public static Properties p;
public static FileInputStream fi;
@BeforeMethod
public void setUp()throws Throwable
{
p=new Properties();
fi=new FileInputStream("F:\\Selenium_Frameworks\\PropertyFile\\Environment.properties");
p.load(fi);
if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	System.setProperty("webdriver.chrome.driver", "F:\\Selenium_Frameworks\\CommonDriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(p.getProperty("Url"));
	driver.manage().window().maximize();
	}
else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	System.setProperty("webdriver.gecko.driver", "F:\\Selenium_Frameworks\\CommonDriver\\geckodriver.exe");
	driver=new FirefoxDriver();
	driver.get(p.getProperty("Url"));	
}
}
@AfterMethod
public void tearDown()
{
	driver.close();
}
}













