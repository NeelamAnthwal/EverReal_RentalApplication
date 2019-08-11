package rentalApp;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RentalApplication {

	WebDriver driver;
	boolean confirmationBar, ConfirmationText;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() throws Exception
	{
System.out.println("hello");
		System.setProperty("webdriver.chrome.driver", "Exe driver\\chromedriver.exe");
		driver=new ChromeDriver();
		wait=new WebDriverWait(driver, 5);
		//Launch URL
		driver.navigate().to("https://acme-qa.everreal.co/app/public/apply/d986458c-4423-4441-be64-77774109864b/applications/step2");
		driver.manage().window().maximize();
		
		//Close cookie banner if displayed
				boolean cookie;
				cookie=driver.findElement(By.xpath("//span[contains(text(),'We use cookies to deliver')]")).isDisplayed();
				if(cookie==true)
					driver.findElement(By.xpath("//span[contains(text(),'clear')]")).click();

	}

	@AfterMethod
	public void tearDown() throws Exception
	{

		driver.quit();
	}

	@Test(priority=0)
	public void rentalApplication() throws Exception
	{

		//Filling Household Information
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@aria-describedby='noOfAdultsMovingIn']"))));
		driver.findElement(By.xpath("//div[@aria-describedby='noOfAdultsMovingIn']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[data-value='1']"))));
		driver.findElement(By.cssSelector("li[data-value='1']")).click();

		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@aria-describedby='noOfChildrenMovingIn']"))));
		driver.findElement(By.xpath("//div[@aria-describedby='noOfChildrenMovingIn']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[data-value='0']"))));
		driver.findElement(By.cssSelector("li[data-value='0']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-describedby='noOfPeopleOnContract']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[data-value='1']"))));
		driver.findElement(By.cssSelector("li[data-value='1']")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("applications[0].applicationFormData.firstName")).sendKeys("neel1");
		driver.findElement(By.id("applications[0].applicationFormData.lastName")).sendKeys("neel1");
		driver.findElement(By.xpath("//span[text()='Next']")).click();

		//Filling Applicant1 Form
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("desiredStartDate"))));
		driver.findElement(By.id("desiredStartDate")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='OK']")).click();

		//Basic Information
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("123456789");
		driver.findElement(By.id("email")).sendKeys("neelrock5@test.com");


		//Current Address
		driver.findElement(By.id("street")).sendKeys("streetname1");
		driver.findElement(By.id("number")).sendKeys("streetnumber1");
		driver.findElement(By.id("zipCode")).sendKeys("411051");
		driver.findElement(By.id("city")).sendKeys("copenhagen");

		//Current Employer 
		driver.findElement(By.id("employerName")).sendKeys("TestEmployer");
		driver.findElement(By.id("employerAddress")).sendKeys("TestAddress");
		driver.findElement(By.id("occupation")).sendKeys("Service");
		driver.findElement(By.id("netMonthlyIncome")).sendKeys("2000");

		//Household information & General Information
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-describedby='applications[0].applicationFormData.isAnyoneSmoking']/div/div[1]/span")).click();
		driver.findElement(By.xpath("//div[@aria-describedby='applications[0].applicationFormData.hasPets.checked']/div/div[1]/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-describedby='applications[0].applicationFormData.hadRentIssues.checked']/div/div[1]/span")).click();
		driver.findElement(By.xpath("//div[@aria-describedby='applications[0].applicationFormData.wasEvicted.checked']/div/div[1]/span")).click();

		//Signature
		driver.findElement(By.tagName("canvas")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();

		//Save tab to set password 
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("repeatPassword")).sendKeys("123456");
		driver.findElement(By.name("areTermsAccepted")).click();
		driver.findElement(By.xpath("//span[text()='Register & Submit rental application']")).click();

		//Click on Submit 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='jss1690 jss1799 jss1801 jss1802 jss1804 jss1805 jss1899']/span[@class='jss1800']/span[text()='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='jss1690 jss1799 jss1801 jss1802 jss1804 jss1805']/span[@class='jss1800']/span[text()='Ok']")).click();
		Thread.sleep(1000);

		//Document added
		driver.findElement(By.xpath("//span[text()='View 1 document(s)']")).isDisplayed();
		System.out.println(driver.findElement(By.xpath("//span[text()='View 1 document(s)']")).getText());

		TakesScreenshot SS=(TakesScreenshot)driver;
		File srcFile=SS.getScreenshotAs(OutputType.FILE);
		File desFile=new File("Screenshots\\RentalApplicationImg.png");
		FileUtils.copyFile(srcFile, desFile);

	}
	@Test(priority=1)
	public void newInquiry() throws Exception {

		
		//Schedule an inquiry 
		driver.findElement(By.xpath("//span[contains(text(),'View exposé')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Schedule viewing')]")).click();
		Thread.sleep(2000);//for elements to load

		//Fill 'Submit your inquiry' Form
		driver.findElement(By.id("firstName")).sendKeys("user1");
		driver.findElement(By.id("lastName")).sendKeys("user");
		driver.findElement(By.id("email")).sendKeys("user1@test.com");

		driver.findElement(By.id("message")).sendKeys("Need this apartment.");
		Thread.sleep(2000);  //in case of	 user to view if details are entered
		driver.findElement(By.xpath("//span[text()='Submit']")).click();

		//Validating confirmation page
		Thread.sleep(3000);  //wait for page to load completely
		confirmationBar=driver.findElement(By.xpath("//span[text()='Great! Thank you for the information.']")).isDisplayed();
		ConfirmationText=driver.findElement(By.xpath("//span[text()='We will get back to you as soon as possible.']")).isDisplayed();
		if(confirmationBar==true)
			if(ConfirmationText==true)
				System.out.println("Your inquiry submitted successfully");

		TakesScreenshot SS=(TakesScreenshot)driver;
		File srcFile=SS.getScreenshotAs(OutputType.FILE);
		File desFile=new File("Screenshots\\InquirySubmittedI.png");
		FileUtils.copyFile(srcFile, desFile);

		driver.findElement(By.xpath("//span[text()='close']")).click();
	}

}

