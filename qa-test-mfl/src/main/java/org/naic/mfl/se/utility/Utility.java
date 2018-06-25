/**
 * 
 */
package org.naic.mfl.se.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Monu
 *
 */
public class Utility {
	WebDriver driver;
	
	public void setValue(String locator, String value) {
		driver.findElement(By.id(locator)).sendKeys(value);
	}

	public void fillForm(String firstName, String lastName) {
		setValue("customer_firstname", firstName);
		setValue("customer_lastname", lastName);
		setValue("passwd", "Qwerty");

		Select select = new Select(driver.findElement(By.id("days")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("months")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("years")));
		select.selectByValue("2000");

		setValue("company", "Company");
		setValue("address1", "Qwerty, 123");
		setValue("address2", "zxcvb");
		setValue("city", "Qwerty");

		select = new Select(driver.findElement(By.id("id_state")));
		select.selectByVisibleText("Colorado");

		setValue("postcode", "12345");
		setValue("other", "Qwerty");
		setValue("phone", "12345123123");
		setValue("phone_mobile", "12345123123");
		setValue("alias", "hf");

	}


}
