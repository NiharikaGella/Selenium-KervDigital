package steps;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.AfterStep;


import pages.Page;
import utils.Driver;




public class StepDefs extends Page implements En {
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("Reports\\Kervdigital.html");
	ExtentTest e = extent.createTest("Kervdigital test");	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);

	public StepDefs() {

		
		
		Given("^Navigate to kerv Digital site using (.*)$", (String url) -> {

			getUrl(url);
			if(driver.findElement(By.xpath("(//*[local-name()='svg' and contains(@transform,'')])[1]")).isDisplayed())
			{
				e.log(Status.PASS, "Kerv Digital website launched successfully");
				}
			else {
				e.log(Status.FAIL, "failed to launch Kerv Digital website");
				}

		});
		Given("Scroll down to get rid of cookie warning", () -> {
			Driver.waitTillElementVisible("(//a[@class='_brlbs-btn _brlbs-btn-accept-all _brlbs-cursor'])[1]");
			clickAButton("//a[@class='_brlbs-btn _brlbs-btn-accept-all _brlbs-cursor'])[1]",
					"(//div[@class='navbar__link navbar__link--root navbar__link--has-children'])[6]");
			e.log(Status.PASS, "Got rid of cookie warning");
		});
		When("User opens right-side menu and click on Careers page", () -> {
			Driver.waitTillElementClickable("(//div[@class='navbar__link navbar__link--root navbar__link--has-children'])[6]");
			driver.findElement(By.xpath("(//div[@class='navbar__link navbar__link--root navbar__link--has-children'])[6]")).click();
			
			driver.findElement(By.xpath("//div[@class='navbar__link navbar__link--root navbar__link--has-children']//child::a[text()='Careers']")).click();

		});
		When("Clicks on ‘Look for Job opportunities’", () -> {

			js.executeScript("window.scrollBy(0,300)", "");
			if(driver.findElement(By.xpath("(//a[@href='https://kerv.com/careers-at-kerv/job-opportunities/'])[4]")).isDisplayed())
			{
				e.log(Status.PASS, "Navigate to careers  page");
			}
			else
			{
				e.log(Status.FAIL, "Did not navigate to careers  page");
			}

			Driver.waitTillElementClickable("(//a[@href='https://kerv.com/careers-at-kerv/job-opportunities/'])[4]");

			driver.findElement(By.xpath("(//a[@href='https://kerv.com/careers-at-kerv/job-opportunities/'])[4]")).click();
			
			WebElement e= driver.findElement(By.xpath("//button[@title='Search vacancies']"));
			Point point = e.getLocation();
			int xcord = point.getX();
			int ycord = point.getY();
		action.moveToElement(e, xcord, ycord);
		
			
			if(driver.findElement(By.xpath("//div[text()='Filter your search:']")).isDisplayed())
			{
				System.out.println("Scrolled");
			}

			

		});
		When("Searches for UX designer", () -> {

			driver.findElement(By.xpath("//input[@class='search-field__input']")).sendKeys("UX Designer");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			js.executeScript("window.scrollBy(0,300)", "");
			if(driver.findElement(By.xpath("//button[@title='Search vacancies']")).isDisplayed())
			{
				e.log(Status.PASS, "Searched for UX designer succesfully");
			}
			else
			{
				e.log(Status.FAIL, "Failed to search for UX designer");
			}
			
			
		Driver.waitTillElementClickable("//button[@title='Search vacancies']");
		driver.findElement(By.xpath("//button[@title='Search vacancies']")).click();
		
		});
		When("Open the first opening, Fill the details and submit", () -> {

			String parentwindowhandle = driver.getWindowHandle();
			System.out.println("parent"+parentwindowhandle);
			js.executeScript("window.scrollBy(0,200)", "");

			Driver.waitTillElementClickable("//*[@id='search-results']/div/div[1]/a");
			driver.findElement(By.xpath("//*[@id='search-results']/div/div[1]/a")).click();
		
			Set<String> newWindowHandles = driver.getWindowHandles();

			for (String handle : newWindowHandles) {



				if(!(handle.equals(parentwindowhandle)))
				{
					driver.switchTo().window(handle);
					System.out.println("nav"+handle);
					System.out.println(driver.getWindowHandle());
					js.executeScript("window.scrollBy(0,12000)", "");


					Driver.waitTillElementClickable("//div[text()='Type your first name here']//preceding::input[@type='text']");
					driver.findElement(By.xpath("//div[text()='Type your first name here']//preceding::input[@type='text']")).sendKeys("Niha");
					Driver.waitTillElementClickable("(//div[text()='Type your last name here']//preceding::input[@type='text'])[2]");
					driver.findElement(By.xpath("(//div[text()='Type your last name here']//preceding::input[@type='text'])[2]")).sendKeys("gella");
					driver.findElement(By.xpath("(//div[text()='Type your email address here']//preceding::input[@type='email'])")).sendKeys("niha.gella@gmail.com");


					new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
							(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src,'https://www.google.com/recaptcha')]"))); 

					new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable
							(By.xpath("//div [@class='recaptcha-checkbox-border']"))).click(); 
					driver.switchTo().defaultContent();

					Driver.waitTillElementClickable("//button[@class='button btn btn--white gform_button']");
					driver.findElement(By.xpath("//button[@class='button btn btn--white gform_button']")).click();

					
					driver.findElement(By.xpath("//h1[text()='We’ll be in touch!']")).isDisplayed();

				}

			}

		});

		Then("User message should be submitted successfully", () -> {

			if(driver.findElement(By.xpath("//h1[text()='We’ll be in touch!']")).isDisplayed())
			{
				e.log(Status.PASS, "Candidate profile submitted successfully");

			}
			else {
				e.log(Status.FAIL, "Failed to submited candidate details");
			}


		});
		





	}


//	@AfterStep
//public void report() {
//		extent.attachReporter(spark);
//		extent.flush();
//		extent2.attachReporter(spark2);
//	extent2.flush();

	//}

}
