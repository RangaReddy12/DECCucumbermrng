package DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DataDrivenframeWork {
String inputpath="D:\\Selenium9oclockBatch\\Selenium_Frameworks\\TestInput\\LoginData.xlsx";
String outputpath="D:\\Selenium9oclockBatch\\Selenium_Frameworks\\TestOutput\\Results.xlsx";
WebDriver driver;
Workbook wb;
Sheet ws;
Row row;
ExtentReports report;
ExtentTest test;
File screen;
FileInputStream fi;
FileOutputStream fo;
@BeforeTest
public void setUp()
{
	report=new ExtentReports("./Reports/Login.html");
	System.setProperty("webdriver.chrome.driver", "D:\\Selenium9oclockBatch\\Selenium_Frameworks\\CommonDriver\\chromedriver.exe");
	driver=new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
fi=new FileInputStream(inputpath);
wb=WorkbookFactory.create(fi);
ws=wb.getSheet("Login");
row=ws.getRow(0);
int rc=ws.getLastRowNum();
int cc=row.getLastCellNum();
Reporter.log("no of rows are::"+rc+"   "+"no of columns in first row::"+cc,true);
for(int i=1;i<=rc;i++)
{
test=report.startTest("Verify Login");
test.assignAuthor("Ranga Qa Tester");
test.assignCategory("data Drivern testing");
driver.get("http://orangehrm.qedgetech.com/");
driver.manage().window().maximize();
String username=ws.getRow(i).getCell(0).getStringCellValue();
String password=ws.getRow(i).getCell(1).getStringCellValue();
driver.findElement(By.cssSelector("#txtUsername")).sendKeys(username);
driver.findElement(By.cssSelector("#txtPassword")).sendKeys(password);
driver.findElement(By.cssSelector("#btnLogin")).click();
Thread.sleep(3000);
if(driver.getCurrentUrl().contains("dashboard"))
{
	test.log(LogStatus.PASS, "Login success");
screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("./Screens//iteration//"+i+"Loginpage.png"));
	Reporter.log("Login success",true);
	//write as login success in results 
	ws.getRow(i).createCell(2).setCellValue("Login success");
	//write as pass into status column
	ws.getRow(i).createCell(3).setCellValue("Pass");
	}
else
{
screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("./Screens//iteration//"+i+"Loginpage.png"));
String message=driver.findElement(By.cssSelector("#spanMessage")).getText();
test.log(LogStatus.FAIL, message);
//write as error message in results 
ws.getRow(i).createCell(2).setCellValue(message);
ws.getRow(i).createCell(3).setCellValue("Fail");
Reporter.log(message,true);
}
report.endTest(test);
report.flush();
}
fi.close();
fo=new FileOutputStream(outputpath);
wb.write(fo);
fo.close();
wb.close();
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}



















