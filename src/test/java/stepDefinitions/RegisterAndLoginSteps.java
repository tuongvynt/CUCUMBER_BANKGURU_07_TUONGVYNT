package stepDefinitions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterAndLoginSteps {

	WebDriver driver;
	String loginPageURL, email, userID, password;
	
	@Given("^I open application$")
	public void iOpenApplication()  {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/index.php");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Given("^I get Login page Url$")
	public void iGetLoginPageUrl()  {
		loginPageURL = driver.getCurrentUrl();
	}

	@Given("^I click to here link$")
	public void iClickToHereLink()  {
		driver.findElement(By.xpath("//a[text() ='here']")).click();
	}

	@Given("^I input to email textbox with data ​\"([^\"]*)\"$")
	public void iInputToEmailTextboxWithData​(String emailValue)  {
		email = emailValue + randomNumber() + "@mailinator.com";
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	}

	@Given("^I click to Submit button at Register page$")
	public void iClickToSubmitButtonAtRegisterPage()  {
		driver.findElement(By.xpath("//input[@name= 'btnLogin']")).click();
	}

	@Then("^I get UserID infor$")
	public void iGetUserIDInfor()  {
		userID = driver.findElement(By.xpath("//td[text() ='User ID :']/following-sibling::td")).getText();
	}

	@Then("^I get Password infor$")
	public void iGetPasswordInfor()  {
		password = driver.findElement(By.xpath("//td[text() ='Password :']/following-sibling::td")).getText();
	}

	@When("^I open Login page again$")
	public void iOpenLoginPageAgain()  {
		driver.get(loginPageURL);
	}

	@When("^I input to Username textbox$")
	public void iInputToUsernameTextbox()  {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	}

	@When("^I input to Password textbox$")
	public void iInputToPasswordTextbox()  {
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	}

	@When("^I click to Login button at Login page$")
	public void iClickToLoginButtonAtLoginPage()  {
		 driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	}

	@Then("^Verify Home page displayed with message ​\"([^\"]*)\"$")
	public void verifyHomePageDisplayedWithMessage​(String welcomeMessage)  {
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"" + welcomeMessage + "\"]")).isDisplayed());
	}
	
	public int randomNumber() {
		  Random random = new Random();
		  int number = random.nextInt(999999);
		  return number;
    }

}
