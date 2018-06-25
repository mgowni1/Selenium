package org.naic.mfl.se.challenge;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.naic.mfl.se.base.TestBaseClass;
import org.naic.mfl.se.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebTest extends TestBaseClass {
	WebDriver driver;
	WebDriverWait wait;
	

	String existingUserEmail = "mflsqe@naic.org";
	String existingUserPassword = "mflsqe1234";

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", prop.getProperty("driverLocation"));
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10, 50);
		driver.get(prop.getProperty("URL"));
		System.out.println("Opening a new Session for Test");
	}

	@Test
	public void signInTest() {
		String timestamp = String.valueOf(new Date().getTime());
		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
		String name = "Firstname";
		String surname = "Lastname";

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		Utility utility = new Utility();
		utility.setValue("email_create", email);

		driver.findElement(By.id("SubmitCreate")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();

		utility.fillForm(name, surname);
		driver.findElement(By.id("submitAccount")).click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals(heading.getText(), "MY ACCOUNT");
		assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}

	@Test
	public void logInTest() {
		String fullName = "Joe Black";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		Utility utility = new Utility();
		utility.setValue("email", existingUserEmail);
		utility.setValue("passwd", existingUserPassword);

		driver.findElement(By.id("SubmitLogin")).click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals("MY ACCOUNT", heading.getText());
		assertEquals(fullName, driver.findElement(By.className("account")).getText());
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}

	public void setValue(String locator, String value) {
		driver.findElement(By.id(locator)).sendKeys(value);
	}

	@Test
	public void checkoutTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		Utility utility = new Utility();
		utility.setValue("email", existingUserEmail);
		utility.setValue("passwd", existingUserPassword);
		driver.findElement(By.id("SubmitLogin")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();

		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();

		driver.findElement(By.name("processCarrier")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button")))
				.click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		assertEquals("ORDER CONFIRMATION", heading.getText());
		assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText()
				.contains("Your order on My Store is complete."));
		assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
	}
}


//Havne't test the Configured Utils, Base, test files for Page Object Model
//Screenshot can be added for the error by writing a customize screenshot method (TakeScreeShot)driver.getScreenshotAsType.FILE and save in a object file and copy it to specific location 
//as required 