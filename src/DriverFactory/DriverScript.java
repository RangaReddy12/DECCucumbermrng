package DriverFactory;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunlibrary.PBFunctions;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;


public class DriverScript extends PBConstant{
	String inputpath="F:\\Selenium_Frameworks\\TestInput\\Controller.xlsx";
	String outputpath="F:\\Selenium_Frameworks\\TestOutput\\keyword.xlsx";
	String TCSheet="TestCases";
	String TSSheet="TestSteps";
	//ExtentReports report;
	//ExtentTest test;
	@Test
	public void PB()throws Throwable
	{
		//generate reports folder
		//report=new ExtentReports("./Extent-Report/Keyword.html");
		boolean res=false;
		String tcres=null;
		
		//access excel methods
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows in TCSheet
		int TCCount=xl.rowCount(TCSheet);
		//count no of rows in TSSheet
		int TSCount=xl.rowCount(TSSheet);
		for(int i=1;i<=TCCount;i++)
		{
			//start test case 
		//	test=report.startTest("Keyword framework");
			//test.assignAuthor("Ranga Senior manager");
			//test.assignCategory("Keyword Driven Framework");
			//read execute column from TCSheeet
			String Execute=xl.getCellData(TCSheet, i, 2);
			if(Execute.equalsIgnoreCase("Y"))
			{
				//read tcid from TCSheet
				String tcid=xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++)
				{
					//read description column from TSSheet
					String Description=xl.getCellData(TSSheet, j, 2);
					//read tsid from TSSheet	
					String tsid=xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword column
						String keyword=xl.getCellData(TSSheet, j, 3);
						//call methods
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
						res=PBFunctions.verifyLogin("Admin", "Admin");
						//test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("NewBranchCreation"))
						{
							PBFunctions.clickBranches();
							res=PBFunctions.verifyBranchCreation("Kadiri", "anantapur", "madanapalli", "tanakal", "kadiri", "12345", "INDIA", "Andhra Pradesh", "Nellore");
							//test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							PBFunctions.clickBranches();
							res=PBFunctions.verifyUpdatebranch("kukatpalli", "Hyderabad", "45678");
							//test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res=PBFunctions.verifyLogout();
							//test.log(LogStatus.INFO, Description);
						}
						
						String tsres=null;
						if(res)
						{
							tsres="Pass";
							//write as pass into results column in TSSheet
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							//test.log(LogStatus.PASS, Description);
						}
						else
						{
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							//test.log(LogStatus.FAIL, Description);
						}
						if(!tsres.equalsIgnoreCase("Fail"))
						{
							tcres=tsres;
						}
					}
					//report.endTest(test);
					//report.flush();
				}
				//wirte as pass or fail into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into Results coulmn in TCsheet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);	
			}
		}
	}

}














