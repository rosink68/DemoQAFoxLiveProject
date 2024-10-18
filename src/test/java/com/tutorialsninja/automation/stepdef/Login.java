package com.tutorialsninja.automation.stepdef;

import static org.junit.Assert.assertTrue;

import com.tutorialsninja.automation.framework.Elements;
import com.tutorialsninja.automation.pages.ForgotPasswordPage;
import com.tutorialsninja.automation.pages.HeadersSection;
import com.tutorialsninja.automation.pages.LoginPage;
import com.tutorialsninja.automation.pages.MyAccountPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

	HeadersSection headersSection = new HeadersSection();
	LoginPage loginPage = new LoginPage();
	MyAccountPage myAccountPage = new MyAccountPage();
	ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
	
	
	@Given("^I naviage to Account Login page$")
	public void i_naviage_to_account_login_page() {
		
		Elements.click(HeadersSection.myAccountLink);
		Elements.click(HeadersSection.login);
	}
	
	@When("^I login to the application using Username \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void i_login_to_the_application_using_Username_and_Password(String username, String password)  {
		
		LoginPage.doLogin(username, password);
	}
	
	@Then("^I should see that the User is able to successfully login$")
	public void i_should_see_that_the_user_is_able_to_successfully_login() {
		
		assertTrue(Elements.isDisplayed(MyAccountPage.registerAffiliateAccount));
	}
	
	@Then("^I should see an error message informing the User about invalid credentials$")
	public void i_should_see_an_error_message_informing_the_User_about_invalid_credentials()  {
		
		assertTrue(Elements.VerifyTextEquals(LoginPage.mainWarning,"Warning: No match for E-Mail Address and/or Password."));   
	}
	
	@When("^I reset the forgotten password for \"([^\"]*)\"$")
	public void i_reset_the_forgotten_password_for(String email) {
	    
		Elements.click(LoginPage.forgottenPassword);
		Elements.TypeText(ForgotPasswordPage.emailField, email);
		Elements.click(ForgotPasswordPage.continueButton);
	}
	
	@Then("^I should see a message informing that the password reset details have been sent to the email address$")
	public void i_should_see_a_message_informing_that_the_password_reset_details_have_been_sent_to_the_email_address()  {
	    
		assertTrue(Elements.VerifyTextEquals(LoginPage.mainWarning,"An email with a confirmation link has been sent your email address."));
	}
}
