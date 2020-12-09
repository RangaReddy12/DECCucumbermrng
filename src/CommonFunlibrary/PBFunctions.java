package CommonFunlibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constant.PBConstant;

public class PBFunctions extends PBConstant {
//method for login
public static boolean verifyLogin(String username,String password) throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys(username);
	driver.findElement(By.xpath(p.getProperty("Objpassword"))).sendKeys(password);
	driver.findElement(By.xpath(p.getProperty("ObjLogin"))).click();
	Thread.sleep(5000);
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login success::"+expected+"   "+actual);
		return true;
	}
	else
	{
		
	Reporter.log("Login Fail::"+expected+"   "+actual);
	return false;
}
}
//method for clicking branches
public static void clickBranches()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objclickbranches"))).click();
	Thread.sleep(5000);
}
//method for admin logout
public static boolean verifyLogout()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objlogout"))).click();
	Thread.sleep(5000);
	if(driver.findElement(By.xpath(p.getProperty("ObjLogin"))).isDisplayed())
	{
		Reporter.log("Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
}
//method for new branch creation
public static boolean verifyBranchCreation(String bname,String address1,String address2,
		String address3,String area,String zcode,String country,String state,String city)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("ObjnewBranch"))).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath(p.getProperty("Objbname"))).sendKeys(bname);
	driver.findElement(By.xpath(p.getProperty("Objaddress1"))).sendKeys(address1);
	driver.findElement(By.xpath(p.getProperty("Objaddress2"))).sendKeys(address2);
	driver.findElement(By.xpath(p.getProperty("Objaddress3"))).sendKeys(address3);
	driver.findElement(By.xpath(p.getProperty("Objarea"))).sendKeys(area);
	driver.findElement(By.xpath(p.getProperty("Objzcode"))).sendKeys(zcode);
	new Select(driver.findElement(By.xpath(p.getProperty("Objcountry")))).selectByVisibleText(country);
	Thread.sleep(3000);
	new Select(driver.findElement(By.xpath(p.getProperty("Objstate")))).selectByVisibleText(state);
	Thread.sleep(3000);
	new Select(driver.findElement(By.xpath(p.getProperty("Objcity")))).selectByVisibleText(city);
	Thread.sleep(3000);
	driver.findElement(By.xpath(p.getProperty("Objsubmit"))).click();
	Thread.sleep(5000);
	//capture alert message
	String expectedmessage=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String actualmessage="New Branch";
	if(expectedmessage.toLowerCase().contains(actualmessage.toLowerCase()))
	{
		Reporter.log("Branch Created Success:::"+actualmessage+"    "+expectedmessage,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Created Fail:::"+actualmessage+"    "+expectedmessage,true);
		return false;	
	}
}
//method for branch updation
public static  boolean verifyUpdatebranch(String branchn,String addrees1,String zcode)throws Throwable
{
driver.findElement(By.xpath(p.getProperty("ObjEdit"))).click();
Thread.sleep(5000);
WebElement branchname=driver.findElement(By.xpath(p.getProperty("Objupdatebranch")));
branchname.clear();
branchname.sendKeys(branchn);
Thread.sleep(3000);
WebElement address=driver.findElement(By.xpath(p.getProperty("Objupdateadd")));
address.clear();
address.sendKeys(addrees1);
WebElement zipcode=driver.findElement(By.xpath(p.getProperty("Objupdatezcode")));
zipcode.clear();
zipcode.sendKeys(zcode);
driver.findElement(By.xpath(p.getProperty("Objupdate"))).click();
Thread.sleep(5000);
String expectedupdateb=driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().accept();
String actualudateb="Branch updated";
if(expectedupdateb.toLowerCase().contains(actualudateb.toLowerCase()))
{
	Reporter.log("Branch Update Success::"+expectedupdateb+"    "+actualudateb,true);
	return true;
}
else
{
	Reporter.log("Branch Update Fail::"+expectedupdateb+"    "+actualudateb,true);
	return false;
}
}

}











