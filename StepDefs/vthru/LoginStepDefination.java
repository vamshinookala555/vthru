package vthru;

import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefination extends Baseclass{
	
	@Given("User is on Loginpage")
	public void user_login()
	{
		setupBrowser();
	}

	@Then("all fields should be displayed including CopyRight")
	public void all_fields_should_be_displayed_including_CopyRight() {
	    verifyElement(Loginprop.getProperty("userNametextbox"),driver);
	    verifyElement(Loginprop.getProperty("passwordtextbox"),driver);
	    verifyElement(Loginprop.getProperty("checkbox"),driver);
	    verifyElement(Loginprop.getProperty("forgotpasswordlink"),driver);
	    verifyElement(Loginprop.getProperty("LoginButton"),driver);
	    verifyElement(Loginprop.getProperty("companyLogo"),driver);
	    verifyElement(Loginprop.getProperty("companyCopyright"),driver);
	}
	
	@Then("Login to Dashboard button should be displayed in dark bule color")
	public void login_to_Dashboard_button_should_be_displayed_in_dark_bule_color() {
	    String BgColor=findElement(Loginprop.getProperty("LoginButton"),driver).getCssValue("background-color");
	    Assert.assertEquals(BgColor, "rgba(63, 106, 216, 1)");
	}

	@When("user enters invalid email")
	public void user_enters_invalid_email() {
	    entertext(Loginprop.getProperty("userNametextbox"),prop.getProperty("username"),driver);
	    entertext(Loginprop.getProperty("passwordtextbox"),prop.getProperty("password"),driver);
	    findElement(Loginprop.getProperty("LoginButton"), driver).click();
	}
	@Then("proper error message is displayed")
	public void proper_error_message_is_displayed() {
		String errorMessage=findElement(Loginprop.getProperty("ErrorMessage"), driver).getText();
		Assert.assertEquals("Invalid credentials.", errorMessage.trim());
	}
	@When("user clicks on Forgot Password")
	public void user_clicks_on_Forgot_Password() {
	 
		findElement(Loginprop.getProperty("forgotpasswordlink"), driver).click();
	
	}

	@Then("email address textbox is diplayed")
	public void email_address_textbox_is_diplayed() {
	    
	}

	@When("user enters email id and click Recover password")
	public void user_enters_email_id_and_click_Recover_password() {
		explicitWait(findElement(Loginprop.getProperty("forgotEmail"),driver), driver);
		findElement(Loginprop.getProperty("forgotEmail"),driver).sendKeys(prop.getProperty("username"));
		findElement(Loginprop.getProperty("RecoverPassword"),driver).click();
	}
	
	@After()
	public void teardown()
	{
		if(driver!=null)
		driver.close();
	}
	
}
