package stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterAndLoginSteps {

	WebDriver driver;
	String loginPageURL, email, userID, password;

	@Given("^I open application$")
	public void iOpenApplication() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/index.php");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Given("^I get Login page Url$")
	public void iGetLoginPageUrl() {
		loginPageURL = driver.getCurrentUrl();
	}

	@Given("^I click to here link$")
	public void iClickToHereLink() {
		driver.findElement(By.xpath("//a[text() ='here']")).click();
	}

	@Given("^I input to email textbox with data ​\"([^\"]*)\"$")
	public void iInputToEmailTextboxWithData​(String emailValue) {
		email = emailValue + randomNumber() + "@gmail.com";
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	}

	@Given("^I click to Submit button at Register page$")
	public void iClickToSubmitButtonAtRegisterPage() {
		driver.findElement(By.xpath("//input[@name= 'btnLogin']")).click();
	}

	@Then("^I get UserID infor$")
	public void iGetUserIDInfor() {
		userID = driver.findElement(By.xpath("//td[text() ='User ID :']/following-sibling::td")).getText();
	}

	@Then("^I get Password infor$")
	public void iGetPasswordInfor() {
		password = driver.findElement(By.xpath("//td[text() ='Password :']/following-sibling::td")).getText();
	}

	@When("^I open Login page again$")
	public void iOpenLoginPageAgain() {
		driver.get(loginPageURL);
	}

	@When("^I input to Username textbox$")
	public void iInputToUsernameTextbox() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	}

	@When("^I input to Password textbox$")
	public void iInputToPasswordTextbox() {
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	}

	@When("^I click to Login button at Login page$")
	public void iClickToLoginButtonAtLoginPage() {
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	}

	@Then("^Verify Home page displayed with message ​\"([^\"]*)\"$")
	public void verifyHomePageDisplayedWithMessage​(String welcomeMessage) {
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"" + welcomeMessage + "\"]")).isDisplayed());
	}

	@Given("^I open New Customer page$")
	public void iOpenNewCustomerPage() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	}

	@When("^Input to New Customer form with data$")
	public void inputToNewCustomerFormWithData(DataTable customerTable) {

		List<Map<String, String>> customer = customerTable.asMaps(String.class, String.class);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customer.get(0).get("Name"));
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(customer.get(0).get("DateOfBirth"));
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(customer.get(0).get("Address"));
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(customer.get(0).get("City"));
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(customer.get(0).get("State"));
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(customer.get(0).get("Pin"));
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(customer.get(0).get("Phone"));
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(customer.get(0).get("Email") + randomNumber() + "@yopmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(customer.get(0).get("Password"));
	}

	@When("^I click to Submit button at New Customer page$")
	public void iClickToSubmitButtonAtNewCustomerPage() {
		driver.findElement(By.xpath("//input[@name='sub']")).click();
	}

	@Then("^Verify message displayed with data \"([^\"]*)\"$")
	public void verifyMessageDisplayedWithData(String arg1) {
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text() ='Customer Registered Successfully!!!']")).isDisplayed());
	}

	@Then("^I verify all above infomation created success$")
	public void iVerifyAllAboveInfomationCreatedSuccess(DataTable getcustomerTable) {
		List<Map<String, String>> customer = getcustomerTable.asMaps(String.class, String.class);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customer.get(0).get("Name"));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),customer.get(0).get("Address"));
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

}
